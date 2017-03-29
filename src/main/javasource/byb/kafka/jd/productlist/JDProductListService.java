package byb.kafka.jd.productlist;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import byb.kafka.jdhome.productlist.JDHomeProductListModel;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by ye on 2017/3/9.
 */
public class JDProductListService {
    private static Logger logger = Logger.getLogger("JDProductListService");
    private static Logger productlist = Logger.getLogger("productlist");

    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDProductListKey.equals(pageKey)) return;
        productlist.info(json);


        Gson gson = new Gson();
        ArrayList<HBaseProductDetailValue> hBaseProductDetailValueList = new ArrayList<HBaseProductDetailValue>();
        JDProductListModel model = gson.fromJson(json, JDProductListModel.class);

        for (int i = 0, len = model.Data.goods.length; i < len; i++) {
            JDProductListModel.Good good = model.Data.goods[i];

            HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
            hBaseProductDetailValue.setSku(good.skuId);


            hBaseProductDetailValue.setProductName(good.skuName);//商品名称
            hBaseProductDetailValue.setImageURL(good.imgUrl);//图片URL
            hBaseProductDetailValue.setProductPriceFromList(good.realTimePrice);//来自于到家的列表接口
            hBaseProductDetailValue.setBasicPrice(good.basicPrice);//原价
            hBaseProductDetailValue.setProductStockCount(good.stockCount);//商品库存
            hBaseProductDetailValue.setIsSoldout(String.valueOf(good.是否有货));//是否有货
            hBaseProductDetailValue.setShopID(good.storeId);//店家ID
            hBaseProductDetailValue.setDownloadTime(model.DownloadTime);//下载时间
            hBaseProductDetailValue.setPageModelKey(pageKey);

            hBaseProductDetailValueList.add(hBaseProductDetailValue);
        }

        ProductDetailDao.writeList(tableFactory.getJDProductDetailTable(),hBaseProductDetailValueList);
    }
}
