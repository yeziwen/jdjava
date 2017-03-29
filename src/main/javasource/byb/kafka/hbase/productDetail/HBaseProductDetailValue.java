package byb.kafka.hbase.productDetail;

/**
 * Created by ye on 2017/2/27.
 */
public class HBaseProductDetailValue {
    //value
    public String hotShellRowKey;

    public	String	platformID	;   //	平台ID
    public	String	shopID	;   //	店铺ID
    public	String	sku	;   //	商品ID
    public	String	productName	;   //	商品名称
    public	String	productCode	;   //	商品条码
    public	String	productBrand	;   //	商品品牌
    public	String	productSpec	;   //	商品规格
    public	String	productPrice	;   //	商品价格
    public	String	productPriceFromList	;   //	商品价格来自于列表接口
    public	String	commentCount	;   //	商品评论数
    public	String	enshrineCount	;   //	商品收藏数
    public	String	monthlySales	;   //	商品月销量
    public	String	salesEvents	;   //	促销活动
    public	String	productArea	;   //	商品产地
    public	String	productSalesTime	;   //	商品上架时间
    public	String	fitPeople	;   //	适合人群
    public	String	regionalism	;   //	区域特点
    public	String	isSelfBusiness	;   //	是否自营
    public	String	category	;   //	商品类别
    public	String	categoryPath	;   //	商品类别编号
    public	String	productHotSellID	;   //	爆品的关联ID
 /*   public	String	hotSellSku	;   //	爆品的关联ID数组*/
    public	String	productURL	;   //	链接
    public	String	imageURL	;   //	商品的图片的链接
    public	String	commentGoodRate	;   //	好评率
    public	String	commentGeneralRate	;   //	中评率
    public	String	commentPoorRate	;   //	差评率
    public	String	commentGoodCount	;   //	好评数
    public	String	commentGeneralCount	;   //	中评数
    public	String	commentPoorCount	;   //	差评数
    public	String	averageScore	;   //	平均得分
    public	String	imageListCount	;   //	晒图数
    public	String	hotCommentTagStatistics	;   //	热门关键字标签（字符串数组）
    //添加字段
    public  String  downloadTime;
    public  int  index=0 ;
    public  String  standardCategory; //类别
    public  String  pageModelKey;
    public  String  isWorldWide; //是否全球购
    public  String  venderid; //供应商ID
    public  String  shopName; //商店名称
    public String productStockCount;//商品库存数量
    public  String isSoldout;//是否有货
    public  String basicPrice ;//原价

    public  String productShortName_jd ;//京东产品名称shortName
    public  String productMultiFeatureValue ;//京东产品名称多特整组合名字


    //通過以下時間判斷数据来源
    public String jdProductDetailDownloadTime = null;
    public String jdProductListDownloadTime = null;
    public String jdCommentDownloadTime = null;
    public String jdHotSellDownloadTime = null;
    public String jdSearchDownloadTime = null;

    public String jdHomeDownloadTime = null;



    public  String hasFittingsRecommend ;//是否有人氣配件

    public String getProductShortName_jd() {
        return productShortName_jd;
    }

    public void setProductShortName_jd(String productShortName_jd) {
        this.productShortName_jd = productShortName_jd;
    }

    public String getHotShellRowKey() {
        return hotShellRowKey;
    }

    public void setHotShellRowKey(String hotShellRowKey) {
        this.hotShellRowKey = hotShellRowKey;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getProductPriceFromList() {
        return productPriceFromList;
    }

    public void setProductPriceFromList(String productPriceFromList) {
        this.productPriceFromList = productPriceFromList;
    }

    public String getPlatformID() {
        return platformID;
    }

    public String getProductHotSellID() {
        return productHotSellID;
    }

    public void setProductHotSellID(String productHotSellID) {
        this.productHotSellID = productHotSellID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getHasFittingsRecommend() {
        return hasFittingsRecommend;
    }

    public void setHasFittingsRecommend(String hasFittingsRecommend) {
        this.hasFittingsRecommend = hasFittingsRecommend;
    }

    public void setPlatformID(String platformID) {
        this.platformID = platformID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getEnshrineCount() {
        return enshrineCount;
    }

    public void setEnshrineCount(String enshrineCount) {
        this.enshrineCount = enshrineCount;
    }

    public String getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(String monthlySales) {
        this.monthlySales = monthlySales;
    }

    public String getSalesEvents() {
        return salesEvents;
    }

    public void setSalesEvents(String salesEvents) {
        this.salesEvents = salesEvents;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public String getProductSalesTime() {
        return productSalesTime;
    }

    public void setProductSalesTime(String productSalesTime) {
        this.productSalesTime = productSalesTime;
    }

    public String getFitPeople() {
        return fitPeople;
    }

    public void setFitPeople(String fitPeople) {
        this.fitPeople = fitPeople;
    }

    public String getRegionalism() {
        return regionalism;
    }

    public void setRegionalism(String regionalism) {
        this.regionalism = regionalism;
    }

    public String getIsSelfBusiness() {
        return isSelfBusiness;
    }

    public void setIsSelfBusiness(String isSelfBusiness) {
        this.isSelfBusiness = isSelfBusiness;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCommentGoodRate() {
        return commentGoodRate;
    }

    public void setCommentGoodRate(String commentGoodRate) {
        this.commentGoodRate = commentGoodRate;
    }

    public String getCommentGeneralRate() {
        return commentGeneralRate;
    }

    public void setCommentGeneralRate(String commentGeneralRate) {
        this.commentGeneralRate = commentGeneralRate;
    }

    public String getCommentPoorRate() {
        return commentPoorRate;
    }

    public void setCommentPoorRate(String commentPoorRate) {
        this.commentPoorRate = commentPoorRate;
    }

    public String getCommentGoodCount() {
        return commentGoodCount;
    }

    public void setCommentGoodCount(String commentGoodCount) {
        this.commentGoodCount = commentGoodCount;
    }

    public String getCommentGeneralCount() {
        return commentGeneralCount;
    }

    public void setCommentGeneralCount(String commentGeneralCount) {
        this.commentGeneralCount = commentGeneralCount;
    }

    public String getCommentPoorCount() {
        return commentPoorCount;
    }

    public void setCommentPoorCount(String commentPoorCount) {
        this.commentPoorCount = commentPoorCount;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public String getImageListCount() {
        return imageListCount;
    }

    public void setImageListCount(String imageListCount) {
        this.imageListCount = imageListCount;
    }

    public String getHotCommentTagStatistics() {
        return hotCommentTagStatistics;
    }

    public void setHotCommentTagStatistics(String hotCommentTagStatistics) {
        this.hotCommentTagStatistics = hotCommentTagStatistics;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }



    public String getStandardCategory() {
        return standardCategory;
    }

    public void setStandardCategory(String standardCategory) {
        this.standardCategory = standardCategory;
    }

    public String getPageModelKey() {
        return pageModelKey;
    }

    public void setPageModelKey(String pageModelKey) {
        this.pageModelKey = pageModelKey;
    }

    public String getIsWorldWide() {
        return isWorldWide;
    }

    public void setIsWorldWide(String isWorldWide) {
        this.isWorldWide = isWorldWide;
    }

    public String getVenderid() {
        return venderid;
    }

    public void setVenderid(String venderid) {
        this.venderid = venderid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProductStockCount() {
        return productStockCount;
    }

    public void setProductStockCount(String productStockCount) {
        this.productStockCount = productStockCount;
    }

    public String getIsSoldout() {
        return isSoldout;
    }

    public void setIsSoldout(String isSoldout) {
        this.isSoldout = isSoldout;
    }

    public String getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(String basicPrice) {
        this.basicPrice = basicPrice;
    }
}
