package byb.kafka.jdmarket.productlist;

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
public class JDMarketProductListService {

   private static Logger jdMarketProductListService = Logger.getLogger("JDMarketproductlist");

    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if (!Utils.JDMarketProductListKey.equals(pageKey)){
            return;
        }
        jdMarketProductListService.info(json);

        Gson gson = new Gson();

        JDMarketProductListModel model = gson.fromJson(json, JDMarketProductListModel.class);

        ArrayList<HBaseProductDetailValue> hBaseProductDetailValues = new ArrayList<HBaseProductDetailValue>();
         int len = model.Data.goods.length;
        for(int i=0;i<len;i++) {
            JDMarketProductListModel.Good good = model.Data.goods[i];
            HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();

            hBaseProductDetailValue.setStandardCategory(model.Propertys.category);//精准类别
            hBaseProductDetailValue.setCategory(Utils.string(model.Data.类目));//爬虫类别

            hBaseProductDetailValue.setSku(good.sku);//商品编号

            hBaseProductDetailValue.setIndex(Utils.pageNumConst*model.Data.页码 + good.index);//默认排序字段

            hBaseProductDetailValue.setProductName(good.name);//商品名称
            hBaseProductDetailValue.setProductSpec(Utils.getSpec(good.name));//商品规格
            hBaseProductDetailValue.setProductURL(Utils.getProductURL(good.sku));//

         //   hBaseProductDetailValue.setSalesEvents(good.promo);//促销(唯一来源)
           // hBaseProductDetailValue.setProductPrice(good.price);//价格
            hBaseProductDetailValue.setProductPriceFromList(good.price);//价格
            //hBaseProductDetailValue.setCommentCount(good.评论数);//
           // hBaseProductDetailValue.setDownloadTime(model.DownloadTime);//下载时间

            hBaseProductDetailValue.setPageModelKey(pageKey);
            hBaseProductDetailValue.setIsSelfBusiness(good.是否自营);
            hBaseProductDetailValue.setIsWorldWide(good.是否全球购);
            hBaseProductDetailValue.setVenderid(good.venderid);

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
