package byb.kafka.jdmarket.searchproductlist;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.JDProductTimeHistoryDao;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by ye on 2017/3/1.
 */
public class JDMarketSearchProductListService {

    private static Logger jdSearch = Logger.getLogger("jdSearch");

    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDSearchKey.equals(pageKey)) return;
        jdSearch.info(json);

        Gson gson = new Gson();
        JDMarketSearchProductListModel model  = gson.fromJson(json, JDMarketSearchProductListModel.class);

        ArrayList<HBaseProductDetailValue> hBaseProductDetailValues = new ArrayList<HBaseProductDetailValue>();
        for (int i = 0, len = model.Data.goods.length; i < len; i++) {
            JDMarketSearchProductListModel.Good good = model.Data.goods[i];
            HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
            hBaseProductDetailValue.setSku(good.sku);//sku ID
            hBaseProductDetailValue.setProductURL(Utils.getProductURL(good.sku));
            hBaseProductDetailValue.setIndex(Utils.pageNumConst * model.Data.页码 + good.index);//默认排序字段
            hBaseProductDetailValue.setProductName(good.name);//商品名称
            hBaseProductDetailValue.setProductSpec(Utils.getSpec(good.name));
            // hBaseProductDetailValue.setSalesEvents(good.promo);//促销
            //hBaseProductDetailValue.setProductPrice(good.price);//
            hBaseProductDetailValue.setProductPriceFromList(good.price);//
            //hBaseProductDetailValue.setCommentCount(good.comment);//
            hBaseProductDetailValue.setImageURL(good.image);
            hBaseProductDetailValue.setIsSelfBusiness(good.是否自营);//是否自营
            hBaseProductDetailValue.setIsWorldWide(good.是否全球购);//是否全球购
           // hBaseProductDetailValue.setDownloadTime(model.DownloadTime);//下载时间
            hBaseProductDetailValue.setStandardCategory(model.Propertys.category);

            hBaseProductDetailValue.setPageModelKey(pageKey);
            hBaseProductDetailValues.add(hBaseProductDetailValue);

            hBaseProductDetailValue.jdProductListDownloadTime = model.DownloadTime;

        }

        ProductDetailDao.writeList(
                tableFactory.getJDProductDetailTable(),
                hBaseProductDetailValues
                );

        JDProductTimeHistoryDao.writeALL(tableFactory.getJDProductHistoryTable(),hBaseProductDetailValues);
    }
}
