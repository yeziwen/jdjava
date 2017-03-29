package byb.kafka.hbase.productDetail;

import byb.common.MyTextUtils;
import byb.kafka.hbase.TableFactory;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ye on 2017/3/1.
 */
public class ProductDetailDao {

    private static String familyName = "content";
    private static Logger logger = Logger.getLogger(ProductDetailDao.class);
    private static String errorMsg = "writeProductDetailTable 写表失败:";

    private static Put getOnePut(HBaseProductDetailValue hBaseProductDetailValue) {

        Put put = new Put(Bytes.toBytes(hBaseProductDetailValue.getSku()));

        if (hBaseProductDetailValue.jdProductDetailDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdProductDetailDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdProductDetailDownloadTime)));
        }

        if (hBaseProductDetailValue.jdProductListDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdProductListDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdProductListDownloadTime)));
        }

        if (hBaseProductDetailValue.jdCommentDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdCommentDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdCommentDownloadTime)));
        }

        if (hBaseProductDetailValue.jdHotSellDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdHotSellDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdHotSellDownloadTime)));
        }

        if (hBaseProductDetailValue.jdSearchDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdSearchDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdSearchDownloadTime)));
        }

        if (hBaseProductDetailValue.jdHomeDownloadTime !=null) {
            put.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(HBaseProductDetailKey.jdHomeDownloadTime),
                    Bytes.toBytes(String.valueOf(hBaseProductDetailValue.jdHomeDownloadTime)));
        }




        if (hBaseProductDetailValue.index != 0) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.index), Bytes.toBytes(String.valueOf(hBaseProductDetailValue.index)));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productShortName_jd)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productShortName_jd), Bytes.toBytes(String.valueOf(hBaseProductDetailValue.productShortName_jd)));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.categoryPath)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.categoryPath), Bytes.toBytes(String.valueOf(hBaseProductDetailValue.categoryPath)));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productHotSellID)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productHotSellID), Bytes.toBytes(hBaseProductDetailValue.productHotSellID));
        }

        if (!MyTextUtils.isBlank(hBaseProductDetailValue.averageScore)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.averageScore), Bytes.toBytes(hBaseProductDetailValue.averageScore));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.basicPrice)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.basicPrice), Bytes.toBytes(hBaseProductDetailValue.basicPrice));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.category)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.category), Bytes.toBytes(hBaseProductDetailValue.category));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentCount), Bytes.toBytes(hBaseProductDetailValue.commentCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentGeneralCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentGeneralCount), Bytes.toBytes(hBaseProductDetailValue.commentGeneralCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentGeneralRate)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentGeneralRate), Bytes.toBytes(hBaseProductDetailValue.commentGeneralRate));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentGoodCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentGoodCount), Bytes.toBytes(hBaseProductDetailValue.commentGoodCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentGoodRate)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentGoodRate), Bytes.toBytes(hBaseProductDetailValue.commentGoodRate));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentPoorCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentPoorCount), Bytes.toBytes(hBaseProductDetailValue.commentPoorCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.commentPoorRate)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.commentPoorRate), Bytes.toBytes(hBaseProductDetailValue.commentPoorRate));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.downloadTime)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.downloadTime), Bytes.toBytes(hBaseProductDetailValue.downloadTime));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.enshrineCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.enshrineCount), Bytes.toBytes(hBaseProductDetailValue.enshrineCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.fitPeople)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.fitPeople), Bytes.toBytes(hBaseProductDetailValue.fitPeople));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.hotCommentTagStatistics)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.hotCommentTagStatistics), Bytes.toBytes(hBaseProductDetailValue.hotCommentTagStatistics));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.imageListCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.imageListCount), Bytes.toBytes(hBaseProductDetailValue.imageListCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.imageURL)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.imageURL), Bytes.toBytes(hBaseProductDetailValue.imageURL));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.isSelfBusiness)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.isSelfBusiness), Bytes.toBytes(hBaseProductDetailValue.isSelfBusiness));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.isSoldout)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.isSoldout), Bytes.toBytes(hBaseProductDetailValue.isSoldout));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.isWorldWide)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.isWorldWide), Bytes.toBytes(hBaseProductDetailValue.isWorldWide));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.monthlySales)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.monthlySales), Bytes.toBytes(hBaseProductDetailValue.monthlySales));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.pageModelKey)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.pageModelKey), Bytes.toBytes(hBaseProductDetailValue.pageModelKey));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.platformID)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.platformID), Bytes.toBytes(hBaseProductDetailValue.platformID));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productArea)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productArea), Bytes.toBytes(hBaseProductDetailValue.productArea));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productBrand)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productBrand), Bytes.toBytes(hBaseProductDetailValue.productBrand));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productCode)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productCode), Bytes.toBytes(hBaseProductDetailValue.productCode));
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
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productPriceFromList)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productPriceFromList), Bytes.toBytes(hBaseProductDetailValue.productPriceFromList));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productSalesTime)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productSalesTime), Bytes.toBytes(hBaseProductDetailValue.productSalesTime));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productSpec)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productSpec), Bytes.toBytes(hBaseProductDetailValue.productSpec));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productStockCount)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productStockCount), Bytes.toBytes(hBaseProductDetailValue.productStockCount));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.productURL)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.productURL), Bytes.toBytes(hBaseProductDetailValue.productURL));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.regionalism)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.regionalism), Bytes.toBytes(hBaseProductDetailValue.regionalism));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.salesEvents)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.salesEvents), Bytes.toBytes(hBaseProductDetailValue.salesEvents));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.shopID)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.shopID), Bytes.toBytes(hBaseProductDetailValue.shopID));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.standardCategory)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.standardCategory), Bytes.toBytes(hBaseProductDetailValue.standardCategory));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.venderid)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.venderid), Bytes.toBytes(hBaseProductDetailValue.venderid));
        }
        if (!MyTextUtils.isBlank(hBaseProductDetailValue.hasFittingsRecommend)) {
            put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseProductDetailKey.hasFittingsRecommend), Bytes.toBytes(hBaseProductDetailValue.hasFittingsRecommend));
        }


        return put;
    }

    private static void write2Table(Table table, Put put) {
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(errorMsg + e.getMessage());
            //todo 记录写入错误日志
        }
    }

    private static void write2Table(Table table, List<Put> puts) {
        try {
            table.put(puts);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(errorMsg + e.getMessage());
            //todo 记录写入错误日志
        }
    }

    public static void write(Table table,
                             HBaseProductDetailValue hBaseProductDetailValue
    ) {

        write2Table(table, getOnePut(hBaseProductDetailValue));
    }


    public static void writeList(Table table, ArrayList<HBaseProductDetailValue> hBaseProductDetailValues) {
        List<Put> puts = new ArrayList<Put>();

        for (HBaseProductDetailValue hBaseProductDetailValue : hBaseProductDetailValues) {
            Put put = getOnePut(hBaseProductDetailValue);
            puts.add(put);
        }
        write2Table(table, puts);

    }

    //刪除操作
    public static void delete(Table table) {

        List<Delete> deletes = new ArrayList<Delete>();
        Delete delete = new Delete(Bytes.toBytes("rowkey"));
        try {
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   //直接某一个行键比较
    public static Filter getRowFilter(String rowKey) {
        return null;
    }
    public static Filter  getSimpleColunmValueFilter(String familyName,String colunmame){
        return null;
    }
    public static void query(String productID) throws IOException {
        Scan scan = new Scan();
        FilterList filterList = new FilterList();
       // RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(rowKey))); // OK 筛
      RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator("111\\d+")); // OK 筛
      // RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, Bytes.toBytes(productID)); // OK 筛
        scan.setFilter(filter);
        Table table = new TableFactory().getJDProductDetailTable();
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();
            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("content"),Bytes.toBytes("productID"))));
            System.out.println(Bytes.toString(next.getValue(Bytes.toBytes("content"),Bytes.toBytes("proudctBrand"))));
            //打印所有列
        }
    }
    public static void main(String[] args) {
        try {
            ProductDetailDao.query("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
