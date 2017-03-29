package byb.kafka.run;

import kafka.consumer.KafkaStream;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static int count = 0;

    public static void  main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(3);

        Map<String, List<KafkaStream<byte[], byte[]>>> multiTopicStreams =  new KafkaHelper().multiTopicStreams();

        for(Map.Entry<String, List<KafkaStream<byte[], byte[]>>> entry : multiTopicStreams.entrySet()) {

            if (entry.getKey().equals("jd-com")) {
                List<KafkaStream<byte[], byte[]>> value = entry.getValue();
                for(int i=0;i<value.size();i++) {
                    service.submit(new JDMessageHandlerTask(value.get(i)));
                }
            }

        }
    }




}
