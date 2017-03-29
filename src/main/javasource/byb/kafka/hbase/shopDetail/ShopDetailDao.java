package byb.kafka.hbase.shopDetail;

import byb.common.MyTextUtils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.common.BaseValue;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.ByteStringer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ye on 2017/3/2.
 */
public class ShopDetailDao {


    private Table jdShopDetailTable;

    public ShopDetailDao(TableFactory tableFactory) {
        jdShopDetailTable = tableFactory.getJDShopDetailTable();
    }


    private static String familyName = "content";
    private static Logger logger = Logger.getLogger(ShopDetailDao.class);
    private static String errorMsg = " ShopDetailDao写表失败:";

    private static Put getPut(ShopDetailValue shopDetailValue, String rowKey) {


        Put  put = new Put(Bytes.toBytes(rowKey));

        if(!MyTextUtils.isBlank(shopDetailValue.shopID)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.shopID), Bytes.toBytes(shopDetailValue.shopID));}
        if(!MyTextUtils.isBlank(shopDetailValue.shopName)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.shopName), Bytes.toBytes(shopDetailValue.shopName));}
        if(!MyTextUtils.isBlank(shopDetailValue.imgUrl)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.imgUrl), Bytes.toBytes(shopDetailValue.imgUrl));}
        if(!MyTextUtils.isBlank(shopDetailValue.rankNo)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.rankNo), Bytes.toBytes(shopDetailValue.rankNo));}
        if(!MyTextUtils.isBlank(shopDetailValue.deliveryFirst)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.deliveryFirst), Bytes.toBytes(shopDetailValue.deliveryFirst));}
        if(!MyTextUtils.isBlank(shopDetailValue.deliverySecond)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.deliverySecond), Bytes.toBytes(shopDetailValue.deliverySecond));}
        if(!MyTextUtils.isBlank(shopDetailValue.deliveryCounts)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.deliveryCounts), Bytes.toBytes(shopDetailValue.deliveryCounts));}
        if(!MyTextUtils.isBlank(shopDetailValue.deliveryCompany)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.deliveryCompany), Bytes.toBytes(shopDetailValue.deliveryCompany));}
        if(!MyTextUtils.isBlank(shopDetailValue.storeType)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.storeType), Bytes.toBytes(shopDetailValue.storeType));}
        if(!MyTextUtils.isBlank(shopDetailValue.onSales)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.onSales), Bytes.toBytes(shopDetailValue.onSales));}
        if(!MyTextUtils.isBlank(shopDetailValue.monthlySales)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.monthlySales), Bytes.toBytes(shopDetailValue.monthlySales));}
        if(!MyTextUtils.isBlank(shopDetailValue.scoreAvg)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.scoreAvg), Bytes.toBytes(shopDetailValue.scoreAvg));}
        if(!MyTextUtils.isBlank(shopDetailValue.storeStar)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.storeStar), Bytes.toBytes(shopDetailValue.storeStar));}
        if(!MyTextUtils.isBlank(shopDetailValue.isOpen)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.isOpen), Bytes.toBytes(shopDetailValue.isOpen));}
        if(!MyTextUtils.isBlank(shopDetailValue.shopAddress)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.shopAddress), Bytes.toBytes(shopDetailValue.shopAddress));}
        if(!MyTextUtils.isBlank(shopDetailValue.shopAddressCity)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.shopAddressCity), Bytes.toBytes(shopDetailValue.shopAddressCity));}
        if(!MyTextUtils.isBlank(shopDetailValue.shopAddressPoint)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.shopAddressPoint), Bytes.toBytes(shopDetailValue.shopAddressPoint));}
        if(!MyTextUtils.isBlank(shopDetailValue.logoUrl)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.logoUrl), Bytes.toBytes(shopDetailValue.logoUrl));}
        if(!MyTextUtils.isBlank(shopDetailValue.fansCounts)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.fansCounts), Bytes.toBytes(shopDetailValue.fansCounts));}
        if(!MyTextUtils.isBlank(shopDetailValue.tags)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.tags), Bytes.toBytes(shopDetailValue.tags));}
        if(!MyTextUtils.isBlank(shopDetailValue.coupons)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.coupons), Bytes.toBytes(shopDetailValue.coupons));}
        if(!MyTextUtils.isBlank(shopDetailValue.cateList)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.cateList), Bytes.toBytes(shopDetailValue.cateList));}
        if(!MyTextUtils.isBlank(shopDetailValue.discount)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.discount), Bytes.toBytes(shopDetailValue.discount));}
        if(!MyTextUtils.isBlank(shopDetailValue.pageModelKey)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.pageModelKey), Bytes.toBytes(shopDetailValue.pageModelKey));}
        if(!MyTextUtils.isBlank(shopDetailValue.downloadTime)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(ShopDetailKey.downloadTime), Bytes.toBytes(shopDetailValue.downloadTime));}



        return put;
    }

    public   void write(ShopDetailValue shopDetailValue,String rowKey) {

        Put put = getPut(shopDetailValue, rowKey);

        try {
            jdShopDetailTable.put(put);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(errorMsg+ MyTextUtils.getStackTraceString(e));
        }
    }

    /**
     * 店铺rowKey = shopID
     * @param
     * @param shopDetailValues
     * 店铺列表接口---》店铺表
     */

    public   void writeAll(ArrayList<ShopDetailValue> shopDetailValues) {

        List<Put> puts = new ArrayList<Put>();

        for (ShopDetailValue shopDetailValue : shopDetailValues) {

            String rowKey = shopDetailValue.shopID;

            Put put =getPut(shopDetailValue,rowKey);

            puts.add(put);
        }

        try {
            jdShopDetailTable.put(puts);
        } catch (IOException e) {
            logger.error(errorMsg+ MyTextUtils.getStackTraceString(e));
        }

    }


}
