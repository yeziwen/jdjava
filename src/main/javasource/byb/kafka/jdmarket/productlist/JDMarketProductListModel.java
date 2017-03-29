package byb.kafka.jdmarket.productlist;

/**
 * Created by ye on 2017/2/27.
 *
 * 京东*小类商品列表页--68c3b283-5969-4e1c-afd9-6c0347a4315d
 */

public class JDMarketProductListModel {


    public Data Data; //商品数组
    public String[]  PageModelNames;//接口key
    public String  PageModelKey;
    public String  DownloadTime;//下载时间
    public String  title;//店铺title

    public Propertys Propertys;
    public static class Propertys {
        public String category;
    }

    public static class Data {
        public String shopId;//店铺表：店铺ID
        public int 页码;//商品所属店铺的展示页码
        public  Good[] goods;
        public String 排序;
        public String[] 类目;
        public String 商品总量;
        public String 总页数;


    }
    public static class Good {
        public String sku;//与skuID 互斥
        public String name;//商品名称
        public String promo;//促销
        public String price;
        public String 评论数;
        public String 是否自营;
        public String   是否全球购;
        public int index;//0，1，2，

        public String   venderid;



        public String skuID;
        public String basicPrice;//
        public String mkPrice;//
        public String realTimePrice;//
        public String imgUrl;//图片


        public boolean 是否有货;

        public String stockCount;//库存数
        public String storeId;//京东店铺ID
        public String venderId;//供应商ID

        public Object 折扣;


    }




}
