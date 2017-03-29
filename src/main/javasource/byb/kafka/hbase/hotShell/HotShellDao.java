package byb.kafka.hbase.hotShell;

import byb.common.MyTextUtils;
import byb.kafka.hbase.productDetail.HBaseProductDetailKey;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ye on 2017/3/1.
 */
public class HotShellDao {
    //写入爆品表*来源于人气推荐接口
    private static String familyName = "content";
    private static Logger logger = Logger.getLogger(HotShellDao.class);
    private static String errorMsg = "writeProductDetailTable 写表失败:";

    private static Put getPut(HBaseProductDetailValue hBaseProductDetailValue,String rowKey) {

        Put put = new Put(Bytes.toBytes(rowKey));

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productHotSellID)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productHotSellID), Bytes.toBytes(hBaseProductDetailValue.productHotSellID));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.downloadTime)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.downloadTime), Bytes.toBytes(hBaseProductDetailValue.downloadTime));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.sku)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.sku), Bytes.toBytes(hBaseProductDetailValue.sku));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productName)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productName), Bytes.toBytes(hBaseProductDetailValue.productName));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productPrice)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productPrice), Bytes.toBytes(hBaseProductDetailValue.productPrice));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productBrand)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productBrand), Bytes.toBytes(hBaseProductDetailValue.productBrand));
        }


        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productSpec)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productSpec), Bytes.toBytes(hBaseProductDetailValue.productSpec));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productURL)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productURL), Bytes.toBytes(hBaseProductDetailValue.productURL));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productArea)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productArea), Bytes.toBytes(hBaseProductDetailValue.productArea));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.imageURL)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.imageURL), Bytes.toBytes(hBaseProductDetailValue.imageURL));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.pageModelKey)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.pageModelKey), Bytes.toBytes(hBaseProductDetailValue.pageModelKey));
        }




        return put;
    }


    public static void writeList(Table table, ArrayList<HBaseProductDetailValue> hBaseProductDetailValues) {

        List<Put> puts = new ArrayList<Put>();
        for (HBaseProductDetailValue hBaseProductDetailValue : hBaseProductDetailValues) {
            Put put = getPut(hBaseProductDetailValue,hBaseProductDetailValue.hotShellRowKey);
            puts.add(put);
        }
        try {
            table.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(errorMsg + e.getMessage());
            //todo 记录写入错误日志
        }

    }
}
