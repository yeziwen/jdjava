package byb.common;

import byb.kafka.jd.commentlist.JDCommentListService;
import byb.kafka.jd.hotshell.JDHotShellService;
import byb.kafka.jd.productdetail.JDProductDetailModel;
import byb.kafka.jd.productdetail.JDProductDetailService;
import byb.kafka.jdmarket.productlist.JDMarketProductListService;
import byb.kafka.jdmarket.searchproductlist.JDMarketSearchProductListService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ye on 2017/2/27.
 */
public class Utils {
    //京东--詳情，爆品,評論
    public static String JDProductDetailKey =  "3b8e05ce-ca87-491c-8b89-9d2f25be511e";
    public static String JDHotShellKey = "a54ee6b1-498c-40fc-b811-4d75a39e94ab";
    public static String JDCommentKey = "699ba5d4-beea-435f-9fec-0dabd04bdc57";

    //京東超市，搜索接口
    public static String JDSearchKey = "c53b32b0-621a-4ea2-8118-b41164472156";
    public static String JDMarketProductListKey = "0ddfffed-356d-45f2-abe4-4880d042fa16";


    //京东到家--店铺列表接口
    public static String JDHomeShopListKey = "42e1c45f-fe09-4c17-abc4-3d8d9746269e";
   //京东到家--店铺商品列表
    public static String JDHomeProductListKey = "2825cd61-457f-4115-8a0f-cd7b48257014";

    //京东到家--店铺详情
    public static String JDHomeShopDetailKey = "ae990979-47d8-4b3e-9506-34358e927867";

    //京东--小商品列表
    public static String JDProductListKey = "68c3b283-5969-4e1c-afd9-6c0347a4315d";

    public static String allServiceLog = "JDAllData";


    public static String string(String[] array) {
        String pageModelValues;
        if (array == null || array.length == 0) {
            pageModelValues = null;
        }
        else{
            pageModelValues = "";
            for (String e : array) {
                pageModelValues =pageModelValues+","+e;
            }
            pageModelValues = pageModelValues.replaceFirst(",", "");
        }
        return  pageModelValues;
    }

    public static String join(String[] array,String split) {
        String pageModelValues;
        if (array == null || array.length == 0) {
            pageModelValues = null;
        }
        else{
            pageModelValues = "";
            for (String e : array) {
                if(!MyTextUtils.isBlank(e))
                pageModelValues =pageModelValues+split+e;
            }
            pageModelValues = pageModelValues.replaceFirst(split, "");
        }
        return  pageModelValues;
    }

    public static String string(ArrayList<String> array) {
        String pageModelValues;
        if (array == null || array.size() == 0) {
            pageModelValues = null;
        }
        else{
            pageModelValues = "";
            for (String e : array) {
                pageModelValues =pageModelValues+","+e;
            }
            pageModelValues = pageModelValues.replaceFirst(",", "");
        }
        return  pageModelValues;
    }

    public static int pageNumConst = 100; //自定义一个常量：每页的商品个数

    public static String intRegex = "((\\d+\\.)?\\d+)";
    public static String constReigex = "(?i)(ml|L|KG|G|毫升|升|克|千克|片|抽|瓶|罐|粒|包)";
    public static String xRegex = "(?i)(\\*|x|×|\\+)";

    public static String case1 = intRegex + constReigex + xRegex+ intRegex;//200L*3
    public static String case2 = intRegex + xRegex+ intRegex+constReigex;//200*3L
    public static String case3 = intRegex + xRegex+ intRegex;//200*3
    public static String case4 = intRegex+constReigex;//200L
    public static String case5 = intRegex+constReigex;//200*3*200*3

    public static String regexString = String.format("(%s|%s|%s|%s)",case1,case2,case3,case4);
    public static Pattern pattern =Pattern.compile(regexString);
    public static Matcher matcher =null;
    public static String getSpec(String name) {
        String result = "";
        matcher = pattern.matcher(name);
        if(matcher.find()) {
            result = matcher.group();
        }
        return result;
    }


    //【韩国原版】爱茉莉(Amore) 牙膏 麦迪安系列牙膏 86%美白牙膏蓝色3支组合装
    public static String getBrand(String name) {
        name =name.replaceAll("【.*】","");
        String result =name.split(" ")[0];
        return result;
    }

    public static void main(String[] args)

    {

        System.out.println(getSpec(
                " 舒适达 （sensodyne） 多效护理 牙膏 180g+25g  180g+25"));
    }



    public static String getEffective(String name) {
        StringBuffer ss= new StringBuffer("");
        String Firstregex = "\\w|[\\u4e00-\\u9fa5]";
        Pattern pattern = Pattern.compile(Firstregex);
        Matcher matcher = pattern.matcher(name);
        while(matcher.find()){
            ss.append(matcher.group());
        }
        return ss.toString();
    }
    //取productID
    public static String getSku(String name) {
        String result = "";
        String regex = "[0-9]{3,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        //优先取第一种规格
        while(matcher.find()){
            result=matcher.group();
        }

        return result;
    }
    public static String getWid(String json) {
        String result = "";
        String regex = "wid:\\s?[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        //优先取第一种规格
        ArrayList<String> results = new ArrayList<String>();
        while(matcher.find()){
            result=matcher.group().split(":")[1];
            results.add(result);
        }
        return  Utils.string(results);
    }
    public static String getProductURL(String sku) {
        return  "http:item.jd.com/" + sku + ".html";
    }

    public static String getPromotionString(JDProductDetailModel.Promotion[] promotions) {
        if(promotions==null || promotions.length == 0) return null;
        String promotionString = null;
        String[] temp = new String[promotions.length];
        for(int i=0;i<promotions.length;i++) {
            if("领券".equals(promotions[i].title)) continue;
            temp[i]=("["+promotions[i].title + "]" + promotions[i].desc);
        }
        promotionString =Utils.join(temp, "");
        return promotionString;
    }


}
