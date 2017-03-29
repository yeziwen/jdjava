package byb.kafka;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.common.PageModelKey;
import byb.kafka.jd.commentlist.JDCommentListService;
import byb.kafka.jd.hotshell.JDHotShellService;
import byb.kafka.jdhome.productlist.JDHomeProductListService;
import byb.kafka.jdhome.shopdetail.JDHomeShopDetailService;
import byb.kafka.jdhome.shoplist.JDHomeShopListService;
import byb.kafka.jdmarket.productlist.JDMarketProductListService;
import byb.kafka.jd.productdetail.JDProductDetailService;
import byb.kafka.jdmarket.searchproductlist.JDMarketSearchProductListService;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by ye on 2017/2/24.
 */


public class Consumer extends  Thread {
    private  static Logger allDataLog = Logger.getLogger(Utils.allServiceLog);

    //三个连接对象
    private TableFactory tableFactory = null;
    private Gson gson = new Gson();
    //三个消费者
    private KafkaConsumer<String, String> consumer;

    public Consumer(String topic) throws IOException {
        consumer = new KafkaConsumer(KafkaProperties.getProperty());
        consumer.subscribe(Arrays.asList(topic));
        tableFactory = new TableFactory();
    }
    /**
     *
     */
    public void switchTask(String json,String pageModelKey) {

            JDProductDetailService.service(tableFactory, json, pageModelKey);
            JDHotShellService.service(tableFactory, json, pageModelKey);
            JDCommentListService.service(tableFactory, json, pageModelKey);
            JDMarketSearchProductListService.service(tableFactory, json, pageModelKey);
            JDMarketProductListService.service(tableFactory, json, pageModelKey);
            JDHomeShopListService.service(tableFactory, json,pageModelKey);
            JDHomeShopDetailService.service(tableFactory, json,pageModelKey);
            JDHomeProductListService.service(tableFactory, json, pageModelKey);

    }

    public void run(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        int i = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                String json = record.value();
                PageModelKey PageModelKey = gson.fromJson(json, PageModelKey.class);
                try {
                    switchTask(json, PageModelKey.PageModelKey);
                    allDataLog.info(json);
                    System.out.println(simpleDateFormat.format(new Date().getTime())+":===="+(i++));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(json);
                    break;
                }
            }
        }
    }




    /**
     * 处理消息
     * @param json
     */



}