package byb.kafka.jd.productlist;

import byb.kafka.jdmarket.productlist.JDMarketProductListModel;

/**
 * Created by ye on 2017/2/27.
 * 京东*小类商品列表******  68c3b283-5969-4e1c-afd9-6c0347a4315d
 */
public class JDProductListModel {

    public Data Data; //商品数组
    public String[]  PageModelNames;//接口key
    public String  PageModelKey;
    public String  DownloadTime;//下载时间
    public String  title;//店铺title

    public class Good {
        public String sku;//与skuID 互斥
        public String name;//商品名称
        public String promo;
        public String price;
        public String 评论数;
        public String 是否自营;
        public String   是否全球购;
        public String   venderid;

        public String index;//0，1，2，


        public String skuName;//商品名称

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

    public  class Data {
        public String shopId;//店铺表：店铺ID
        public String 页码;//商品所属店铺的展示页码
        public Good[] goods;
        public String 排序;
        public String 类目;
        public String 商品总量;
        public String 总页数;
    }
}
