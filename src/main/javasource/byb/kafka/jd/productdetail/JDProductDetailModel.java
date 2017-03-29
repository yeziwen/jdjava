package byb.kafka.jd.productdetail;

/**
 * Created by ye on 2017/2/27.
 * 京东商品详情*  3b8e05ce-ca87-491c-8b89-9d2f25be511e
 */
public class JDProductDetailModel {


    public Data Data; //商品数组
    public String[]  PageModelNames;//接口key
    public String  PageModelKey;
    public String  DownloadTime;//下载时间
    public String  title;//店铺title
    public String  RequestUrl;//店铺title



    public Propertys Propertys;
    public static class Propertys {
        public String category;
    }

    public static class Data {
        public String shopId;//店铺表：店铺ID
        public String shopName;//

        public String Price;
        public String commentCount;
        public String Title;//名称
        public String[] Category;//条目
        public String categoryPath;//条目

        public String  hasFittingsRecommend;

        //todo
       // public String parameter_brand;//条目 parameter-brand
        public Promotion[] promotion;
        public parameter2[] parameter2;
        public parameterBrand[] parameter_brand;

        public String 排序;
        public String[] 类目;
        public String 商品总量;
        public String 总页数;
    }

    public static class parameterBrand{
        public int index;
        public String Name;
        public String Value;
    }

    public static class parameter2{
        public int index;
        public String Name;
        public String Value;
    }

    public static class Promotion{
        public String title;
        public String desc;
    }


}
