package byb.kafka.jdmarket.searchproductlist;

import byb.kafka.jdmarket.productlist.JDMarketProductListModel;

/**
 * Created by ye on 2017/2/27.
 * 京东超市* 搜索结果接口*c53b32b0-621a-4ea2-8118-b41164472156
 */
public class JDMarketSearchProductListModel {

    public Data Data; //商品数组
    public String[]  PageModelNames;//接口key
    public String  PageModelKey;
    public String  DownloadTime;//下载时间
    public String  title;//店铺title

    public JDMarketProductListModel.Propertys Propertys;
    public static class Propertys {
        public String category;
    }

    public static  class Data {
        public String shopId;//店铺表：店铺ID
        public int 页码;//商品所属店铺的展示页码
        public  Good[] goods;
        public String 排序;
        public String 类目;
        public String 商品总量;
        public String 总页数;
    }
  public static  class Good {

        public String sku;
        public String name;//商品名称
        public String promo;
        public String price;
        public String comment;
        public String 是否自营;
       public String   是否全球购;
        public String image;//图片




        public int index;//0，1，2，


        public String skuName;//商品名称

      public String   venderid;
        public String basicPrice;//
        public String mkPrice;//
        public String realTimePrice;//
        public String imgUrl;//图片

        public String skuId;//

        public boolean 是否有货;

        public String stockCount;//库存数
        public String storeId;//京东店铺ID
        public String venderId;//供应商ID

        public int promotion;//促销
        public Object 折扣;


    }


}
