package byb.kafka.run;

import byb.kafka.common.PageModelKey;
import byb.kafka.hbase.TableFactory;
import byb.kafka.jd.commentlist.JDCommentListService;
import byb.kafka.jd.hotshell.JDHotShellService;
import byb.kafka.jd.productdetail.JDProductDetailService;
import byb.kafka.jd.productlist.JDProductListService;
import byb.kafka.jdhome.productlist.JDHomeProductListService;
import byb.kafka.jdhome.shopdetail.JDHomeShopDetailService;
import byb.kafka.jdhome.shoplist.JDHomeShopListService;
import byb.kafka.jdmarket.productlist.JDMarketProductListService;
import byb.kafka.jdmarket.searchproductlist.JDMarketSearchProductListService;
import com.google.gson.Gson;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ye on 2017/3/10.
 */
public class JDMessageHandlerTask extends Thread{

    private static final Logger logger = Logger.getLogger(JDMessageHandlerTask.class);

    KafkaStream<byte[], byte[]> kafkaStreams;

    TableFactory tableFactory = new TableFactory();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");


    public JDMessageHandlerTask(KafkaStream<byte[], byte[]> kafkaStreams) {
        super();
        this.kafkaStreams = kafkaStreams;
    }

    public void run() {

        ConsumerIterator<byte[], byte[]> streamIterator = kafkaStreams.iterator();

        while (streamIterator.hasNext()) {

            MessageAndMetadata<byte[], byte[]> record = streamIterator.next();
            String message = new String(record.message());
            try {
                logger.info(simpleDateFormat.format(new Date().getTime())+":===="+(Main.count++));
                logger.info(message);
                handleMessage(message, tableFactory);
            } catch (Exception e) {
                logger.error(message+"解析异常");
            }
        }
    }

    public void handleMessage(String json,TableFactory tableFactory) {
        Gson gson = new Gson();
        PageModelKey pageModel = gson.fromJson(json, PageModelKey.class);
        String pageModelKey = pageModel.PageModelKey;


        JDProductDetailService.service(tableFactory, json, pageModelKey);
        JDHotShellService.service(tableFactory, json, pageModelKey);
        JDCommentListService.service(tableFactory, json, pageModelKey);
        JDProductListService.service(tableFactory,json,pageModelKey);


        JDMarketSearchProductListService.service(tableFactory, json, pageModelKey);
        JDMarketProductListService.service(tableFactory, json, pageModelKey);


        JDHomeShopListService.service(tableFactory, json,pageModelKey);
        JDHomeShopDetailService.service(tableFactory, json,pageModelKey);
        JDHomeProductListService.service(tableFactory, json, pageModelKey);
    }
}
