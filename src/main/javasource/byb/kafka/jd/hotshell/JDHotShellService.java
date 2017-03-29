package byb.kafka.jd.hotshell;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.hotShell.HotShellDao;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.UUID;



public class JDHotShellService {


    private static Logger jdHotShellService = Logger.getLogger("hotsell");


    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if (!Utils.JDHotShellKey.equals(pageKey)) return;
        jdHotShellService.info(json);
        Gson gson = new Gson();
        JDHotShellModel model = gson.fromJson(json, JDHotShellModel.class);

        ArrayList<HBaseProductDetailValue> hBaseProductDetailValueList = new ArrayList<HBaseProductDetailValue>();

        String productHotSellID = model.Data.sku + "-" + UUID.randomUUID().toString();

        int listLen = model.Data.list.length;
        for (int i = 0; i < listLen; i++) {
            if (model.Data.list[i].accessoryShows != null && model.Data.list[i].accessoryShows.length > 0) {
                int accessoryShowsLen = model.Data.list[i].accessoryShows.length;
                for (int j = 0; j < accessoryShowsLen; j++) {
                    String hotString = model.Data.list[i].accessoryShows[j];
                    String[] hots = hotString.split("  ");
                    String  wName = "";
                    String wid ="";
                    String imageUrl = "";
                    String oldPrice = "";
                    String productPrice = "";
                    for(int k=0;k<hots.length;k++) {
                       if(k==0) wName = hots[k].split(":")[1];
                       if(k==1) wid = hots[k].split(":")[1];
                       if(k==2) imageUrl = hots[k].split(":")[1];
                       if(k==3) oldPrice = hots[k].split(":")[1];
                       if(k==4) productPrice = hots[k].split(":")[1];
                    }
                        //寫入爆品表，方便
                    HBaseProductDetailValue hBaseProductDetailValue1 = new HBaseProductDetailValue();
                    hBaseProductDetailValue1.setSku(wid);//這是主鍵
                    hBaseProductDetailValue1.setProductName(wName);
                    hBaseProductDetailValue1.setProductURL(Utils.getProductURL(wid));
                    hBaseProductDetailValue1.setProductSpec(Utils.getSpec(wName));
                    hBaseProductDetailValue1.setProductPrice(productPrice);
                    hBaseProductDetailValue1.setImageURL(imageUrl);
                    hBaseProductDetailValue1.setProductURL(Utils.getProductURL(wid));
                    hBaseProductDetailValue1.setDownloadTime(model.DownloadTime);
                    //这么设计的目的是使爆品表记录尽可能少
                    hBaseProductDetailValue1.hotShellRowKey =model.Data.sku+hBaseProductDetailValue1.sku;
                    hBaseProductDetailValue1.setProductHotSellID(productHotSellID);
                    hBaseProductDetailValueList.add(hBaseProductDetailValue1);
                }
            }
        }
        ////寫入爆品表,
        HotShellDao.writeList(tableFactory.getJDHotShellTable(),
                hBaseProductDetailValueList
        );
       //写入商品爆品信息写入商品
        HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
       // hBaseProductDetailValue.setProductName(model.Data.name);
      //  hBaseProductDetailValue.setProductSpec(Utils.getSpec(model.Data.name));
       // hBaseProductDetailValue.setProductURL(Utils.getProductURL(model.Data.sku));
     //   hBaseProductDetailValue.setProductBrand(model.Data.brank);
       // hBaseProductDetailValue.setDownloadTime(model.DownloadTime);

        hBaseProductDetailValue.setProductHotSellID(productHotSellID);
        hBaseProductDetailValue.setSku(model.Data.sku);
        hBaseProductDetailValue.jdHotSellDownloadTime = model.DownloadTime;
        ProductDetailDao.write(tableFactory.getJDProductDetailTable(),//补充爆品信息进入主表
                hBaseProductDetailValue);


        //hBaseProductDetailValueList.add(hBaseProductDetailValue);


    }




}
