package byb.kafka.jdhome.shopdetail;

import byb.common.MyTextUtils;
import byb.common.Utils;
import byb.kafka.Consumer;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.shopDetail.ShopDetailDao;
import byb.kafka.hbase.shopDetail.ShopDetailValue;
import com.google.gson.Gson;
import org.apache.hadoop.hbase.client.Table;
import org.apache.log4j.Logger;


/**
 * Created by ye on 2017/3/2.
 */
public class JDHomeShopDetailService {

    private static Logger jdHomeShopDetailService = Logger.getLogger("JDHomeShopDetailService");
    private static Logger logger = Logger.getLogger(JDHomeShopDetailService.class);

    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDHomeShopDetailKey.equals(pageKey)) return;
      //  jdHomeShopDetailService.info(json);

        Gson gson = new Gson();
        JDHomeShopDetailModel model = gson.fromJson(json, JDHomeShopDetailModel.class);

        ShopDetailValue hbaseShopDetailValue = new ShopDetailValue();
        //排序字段jdhomeIndex
        hbaseShopDetailValue.shopID=model.Data.storeId;
        hbaseShopDetailValue.shopName=model.Data.storeName;
        hbaseShopDetailValue.deliveryFirst=model.Data.deliveryFirst;
        hbaseShopDetailValue.deliverySecond=model.Data.deliverySecond;
        hbaseShopDetailValue.deliveryCompany=model.Data.配送;
        hbaseShopDetailValue.onSales=model.Data.inSaleNum;
        hbaseShopDetailValue.monthlySales=model.Data.monthSaleNum;
        hbaseShopDetailValue.scoreAvg=model.Data.scoreAvg;
        hbaseShopDetailValue.storeStar=model.Data.storeStar;
        hbaseShopDetailValue.isOpen=model.Data.营业中;
        hbaseShopDetailValue.shopAddress=model.Data.storeAddress;
        hbaseShopDetailValue.fansCounts=model.Data.店铺关注人数;
        hbaseShopDetailValue.tags=gson.toJson(model.Data.tags);
        hbaseShopDetailValue.coupons=gson.toJson(model.Data.优惠券);
        hbaseShopDetailValue.cateList=gson.toJson(model.Data.cateList);

        String rowKey = model.Data.storeId;


        //rowKey檢查
        if (MyTextUtils.isBlank(rowKey)) {
            logger.error("rowKey==null:"+json);
        }
        ShopDetailDao shopDetailDao = new ShopDetailDao(tableFactory);
        shopDetailDao.write(hbaseShopDetailValue,rowKey);

    }
}
