package byb.kafka.jdhome.shoplist;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.shopDetail.ShopDetailDao;
import byb.kafka.hbase.shopDetail.ShopDetailValue;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;


/**
 * Created by ye on 2017/3/2.
 */
public class JDHomeShopListService {

    //京东到家*店铺列表
    private static Logger jdHomeShopListService = Logger.getLogger("JDHomeShopListService");

    public static void service(TableFactory tableFactory, String json, String pageKey) {

        if(!Utils.JDHomeShopListKey.equals(pageKey)) return;
     //  jdHomeShopListService.info(json);

        Gson gson = new Gson();
        JDHomeShopListModel model = gson.fromJson(json, JDHomeShopListModel.class);
        ArrayList<ShopDetailValue> shopDetailValueList = new ArrayList<ShopDetailValue>();
        for (int i = 0, len = model.Data.shops.length; i < len; i++) {
            JDHomeShopListModel.Shop shop = model.Data.shops[i];
            ShopDetailValue shopDetailValue = new ShopDetailValue();
            //匹配
            shopDetailValue.shopID = shop.storeId;
            shopDetailValue.shopName = shop.name;
            shopDetailValue.imgUrl = shop.imgUrl;
            shopDetailValue.rankNo = shop.rankNo;
            shopDetailValue.deliveryFirst = shop.deliveryFirst;
            shopDetailValue.deliverySecond = shop.deliverySecond;
            shopDetailValue.deliveryCounts = shop.配送;
            shopDetailValue.storeType = shop.storeType;
            shopDetailValue.onSales = shop.销售中商品数;
            shopDetailValue.monthlySales = shop.月销量;
            shopDetailValue.scoreAvg = shop.scoreAvg;
            shopDetailValue.storeStar = shop.storeStar;
            shopDetailValue.isOpen = shop.营业中;
            shopDetailValue.discount = gson.toJson(shop.折扣);//特殊字段

            shopDetailValue.downloadTime = model.DownloadTime;
            shopDetailValue.pageModelKey = model.PageModelKey;
        }
        //rowKey = shopID
        ShopDetailDao shopDetailDao = new ShopDetailDao(tableFactory);
        shopDetailDao.writeAll(shopDetailValueList);

    }
}
