package byb.kafka.jdhome.shopdetail;

import byb.kafka.common.PageBaseModel;

/**
 * Created by ye on 2017/3/2.
 */
public class JDHomeShopDetailModel  extends PageBaseModel{
    public Data Data;


    public static class Data {
        public String storeId;
        public String storeName;
        public String deliveryFirst;
        public String deliverySecond;
        public String 配送;
        public String inSaleNum;
        public String monthSaleNum;
        public String scoreAvg;
        public String storeStar;
        public String 营业中;
        public String storeAddress;
        public String 店铺关注人数;
        public Object tags;
        public Object 优惠券;
        public Object cateList;

    }
}
