package byb.kafka.hbase.hotShell;

/**
 * Created by ye on 2017/2/28.
 */
public class HBaseHotShellKey {


//rowKey==爆破rowKey，不同的推荐接口使用不同的列戳（人气推荐接口为"content")

    public static String	downloadTime	=	"downloadTime"	;	//	时间
    public static String	productIDS	=	"productIDS"	;	//	"商品的ID,商品ID,商品ID"

  /*
      public static String	productHotID	=	"productHotID"	;	//	爆品关联ID
  public static String	productName	=	"productName"	;	//	商品名称
    public static String	productSpec	=	"productSpec"	;	//	规格
    public static String	productPrice	=	"productPrice"	;	//	价格?
    public static String	productURL	=	"productURL"	;	//	链接 +拼接出来的http://item.jd.com/productID.html
    public static String	imageURL	=	"imageURL"	;	//	链接 +拼接出来的http://item.jd.com/productID.html
*/
    /*public static String	pageModelKey	=	"pageModelKey"	;	//	链接 +拼接出来的http://item.jd.com/productID.html*/







}
