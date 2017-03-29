package byb.kafka.jd.productdetail;

import byb.common.MyTextUtils;
import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import byb.kafka.jd.commentlist.JDCommentListService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;


/**
 * Created by ye on 2017/3/1.
 */
public class JDProductDetailService {


    private static Logger productDetailLog = Logger.getLogger("productdetail");

    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDProductDetailKey.equals(pageKey)) return;

        productDetailLog.info(json);

        Gson gson = new Gson();
        JDProductDetailModel model = gson.fromJson(json, JDProductDetailModel.class);
        HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
        int len = model.Data.parameter2.length;
        String productName = "";
        String sku = "";
        String productArea = "";
        String spec_productWeight = "";

        String productShortName_jd = "";
        String[] productMultiFeatureValues = new String[6];
        String v1="";
        String v2="";
        String v3="";
        String v4="";
        String v5="";
        String v6="";
        for (int i = 0; i < len; i++) {
            String name = model.Data.parameter2[i].Name;
            String value = model.Data.parameter2[i].Value;
            if ("商品名称".equals(name)) {
                productName = value;
                productShortName_jd = value;
            } else if ("商品编号".equals(name)) {
                sku = value;
            } else if ("商品编码".equals(name)) {
                sku = value;
            } else if ("商品毛重".equals(name)) {
                spec_productWeight = value;
            } else if ("商品产地".equals(name)) {
                productArea = value;
            } else if ("产品产地".equals(name)) {
                productArea = value;
            }
            else if ("适用人群".equals(name)) {
                v1 = value == null ? "" : value;
            }
            else if ("适合对象".equals(name)) {
                v2 = value == null ? "" : value;
            }
            else if ("性别".equals(name)) {
                v3 = value == null ? "" : value;
            }
            else if ("适合发质".equals(name)) {
                v4 = value == null ? "" : value;
            }
            else if ("适合肤质".equals(name)) {
                v5 = value == null ? "" : value;
            }
            else if ("适用年龄".equals(name)) {
                v6 = value == null ? "" : value;
            } else if ("功效".equals(name)) {
            } else if ("店铺".equals(name)) {
            } else if ("类别".equals(name)) {
            } else if ("分类".equals(name)) {
            } else if ("PA值".equals(name)) {
            } else if ("净含量".equals(name)) {
            } else if ("货号".equals(name)) {
            }
        }
        //应需求变化：适应人群字段==多个字段的组合
        productMultiFeatureValues[0] = v1;
        productMultiFeatureValues[1] = v2;
        productMultiFeatureValues[2] = v3;
        productMultiFeatureValues[3] = v4;
        productMultiFeatureValues[4] = v5;
        productMultiFeatureValues[5] = v6;
        hBaseProductDetailValue.productMultiFeatureValue = Utils.join(productMultiFeatureValues, ",").replace(",,",",");
        hBaseProductDetailValue.fitPeople =  hBaseProductDetailValue.productMultiFeatureValue;




        //取SKu的
        String sku_fromURL =Utils.getSku(model.RequestUrl);
        if (!MyTextUtils.isBlank(sku_fromURL)) {
            sku = sku_fromURL;
        }

        hBaseProductDetailValue.setSku(sku);//商品编号
        hBaseProductDetailValue.setProductURL(model.RequestUrl);
        //取規格的策略
        String spec_productName = Utils.getSpec(productName);
        String spec_productTitle = Utils.getSpec(model.Data.Title);
        if(!MyTextUtils.isBlank(spec_productName)) hBaseProductDetailValue.setProductSpec(spec_productName);
        else{
            if(!MyTextUtils.isBlank(spec_productTitle)) hBaseProductDetailValue.setProductSpec(spec_productTitle);
            else hBaseProductDetailValue.setProductSpec(spec_productWeight);
        }
        //取名字的策略：直接拿title
        hBaseProductDetailValue.setProductName(productName);
        if (MyTextUtils.isBlank(productName)) {
            hBaseProductDetailValue.setProductName(model.Data.Title);
        }
        hBaseProductDetailValue.setProductName(model.Data.Title);
        hBaseProductDetailValue.hasFittingsRecommend=model.Data.hasFittingsRecommend;
        String brand = null;
        if (model.Data.parameter_brand != null && model.Data.parameter_brand.length > 0) {
            brand = model.Data.parameter_brand[0].Value;
        }
        if (brand == null) {
            Utils.getBrand(productName);//抽取出來
        }
        hBaseProductDetailValue.setProductBrand(brand);//商品品牌
        hBaseProductDetailValue.setProductPrice(model.Data.Price);//价格
        hBaseProductDetailValue.setProductArea(productArea);//产品产地
        hBaseProductDetailValue.setCategory(Utils.string(model.Data.Category));
        hBaseProductDetailValue.setCategoryPath(model.Data.categoryPath);
        hBaseProductDetailValue.setPageModelKey(pageKey);
        hBaseProductDetailValue.setShopID(model.Data.shopId);
        hBaseProductDetailValue.setShopName(model.Data.shopName);
        //填充shortName
        if (MyTextUtils.isBlank(productShortName_jd)) {
            productShortName_jd = productName;
        }
        hBaseProductDetailValue.productShortName_jd = productShortName_jd;
        String promotionString = Utils.getPromotionString(model.Data.promotion);
        hBaseProductDetailValue.salesEvents = promotionString;

        hBaseProductDetailValue.setDownloadTime(model.DownloadTime);//下载时间
        hBaseProductDetailValue.jdProductDetailDownloadTime = model.DownloadTime;
        ProductDetailDao.write(
                tableFactory.getJDProductDetailTable(),
                hBaseProductDetailValue
                );
    }
}
