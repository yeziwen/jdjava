package byb.kafka.jdhome.productlist;

/**
 * Created by ye on 2017/2/24.
 * 京东到家·店铺商品列表接口--2825cd61-457f-4115-8a0f-cd7b48257014
 */

public class JDHomeProductListModel {
    /**
     *第一层
     */
    public Data Data; //商品数组
    public String[]  PageModelNames;
    public String  PageModelKey;
    public String  DownloadTime;//下载时间
    public String  UpdateTime;//更新时间
    public String  title;//店铺title
    public String  RequestUrl;
    public String  ResponseUrl;
    public String  Propertys; //不明字段

   public static class Data {

        public String shopId;//店铺表：店铺ID
        public int 页码;//商品所属店铺的展示页码
        public Good[] goods;
    }

    public static class Good {

        public String index;//0，1，2，
        public String skuId;//
        public String skuName;//商品名称

        public String basicPrice;//
        public String mkPrice;//
        public String realTimePrice;//
        public String imgUrl;//图片

        public String 是否有货;

        public String stockCount;//库存数
        public String storeId;//京东店铺ID
        public String venderId;//供应商ID

        public int promotion;//促销
        public Object 折扣;


    }

}
