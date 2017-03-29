package byb.kafka.hbase.productDetail;

import byb.common.MyTextUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ye on 2017/3/6.
 */
public class JDProductTimeHistoryDao {


    private static String familyName = "content";
    private static Logger logger = Logger.getLogger(JDProductTimeHistoryDao.class);
    private static String errorMsg = "writeProductDetailTable 写表失败:";


    private static Put getPut(HBaseProductDetailValue hBaseProductDetailValue) {
//sku 就是ProductID
        String rowKey = hBaseProductDetailValue.sku + hBaseProductDetailValue.downloadTime;
        Put put = new Put(Bytes.toBytes(rowKey));
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.sku))
        {put.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(HBaseProductDetailKey.sku),Bytes.toBytes(hBaseProductDetailValue.sku));}
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.downloadTime))
        {put.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(HBaseProductDetailKey.downloadTime),Bytes.toBytes(hBaseProductDetailValue.downloadTime));}
        return put;
    }

    public static void  writeALL(Table table,ArrayList<HBaseProductDetailValue> hBaseProductDetailValues) {
        List<Put> puts = new ArrayList<Put>();

        for (HBaseProductDetailValue hBaseProductDetailValue : hBaseProductDetailValues) {
            Put    put = getPut(hBaseProductDetailValue);

            puts.add(put);
        }
        try {
            table.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(errorMsg+e.getMessage());
            //todo 记录写入错误日志
        }


    }

}
