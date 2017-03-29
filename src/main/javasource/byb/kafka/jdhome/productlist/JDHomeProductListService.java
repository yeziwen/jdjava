package byb.kafka.jdhome.productlist;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ye on 2017/3/1.
 */
public class JDHomeProductListService {

   // private static Logger logger = Logger.getLogger("JDHomeProductListService");

    /**
     *
     * @param tableFactory:
     * @param json
     * @param pageKey
     */
    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDHomeProductListKey.equals(pageKey)) return;
       // logger.info(json);

        Gson gson = new Gson();
        ArrayList<HBaseProductDetailValue> hBaseProductDetailValueList = new ArrayList<HBaseProductDetailValue>();
        JDHomeProductListModel model = gson.fromJson(json, JDHomeProductListModel.class);

        for (int i = 0, len = model.Data.goods.length; i < len; i++) {
            JDHomeProductListModel.Good good = model.Data.goods[i];

            HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
            hBaseProductDetailValue.setSku(good.skuId);//京东到家产品ID=rowkey

          //排序字段jdhomeIndex
            hBaseProductDetailValue.setProductName(good.skuName);//商品名称

            hBaseProductDetailValue.setImageURL(good.imgUrl);//图片URL

            hBaseProductDetailValue.setProductPriceFromList(good.realTimePrice);//来自于到家的列表接口

            hBaseProductDetailValue.setBasicPrice(good.basicPrice);//原价

            hBaseProductDetailValue.setProductStockCount(good.stockCount);//商品库存

            hBaseProductDetailValue.setIsSoldout(good.是否有货);//是否有货

            hBaseProductDetailValue.setShopID(good.storeId);//店家ID

            //hBaseProductDetailValue.setDownloadTime(model.DownloadTime);//下载时间

            hBaseProductDetailValue.jdHomeDownloadTime = model.DownloadTime;

            hBaseProductDetailValue.setPageModelKey(pageKey);

            hBaseProductDetailValueList.add(hBaseProductDetailValue);
        }

        ProductDetailDao.writeList(tableFactory.getJDProductDetailTable(),hBaseProductDetailValueList);
    }
}
