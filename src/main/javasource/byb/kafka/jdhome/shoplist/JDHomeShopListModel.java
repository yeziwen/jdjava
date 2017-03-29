package byb.kafka.jdhome.shoplist;

import byb.kafka.common.PageBaseModel;

/**
 * Created by ye on 2017/3/2.
 */
public class JDHomeShopListModel extends PageBaseModel {

    public Data Data;
    public Propertys  Propertys; //属性字段

    public static class Propertys{
        public String point;
        public String city;
    }
    public static class Shop{
        public String storeId;
        public String name;
        public String imgUrl;
        public String rankNo;
        public String deliveryFirst;
        public String deliverySecond;
        public String 配送;
        public String storeType;
        public String 销售中商品数;
        public String 月销量;
        public String scoreAvg;
        public String storeStar;
        public String 营业中;
        public Object 折扣;
    }
    public static class Data {
        public Shop[] shops;
    }




}
