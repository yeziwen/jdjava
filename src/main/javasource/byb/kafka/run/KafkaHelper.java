package byb.kafka.run;
import byb.kafka.KafkaProperties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.*;
/**
 * Created by ye on 2017/3/10.
 */
public class KafkaHelper {

    /**
     * 配置文件
     *
     * @return
     */
    //test2017022,test2017023,test2017024,test20170307,test2017021
    String groupID = "test2017024";

    public ConsumerConfig consumerConfig() {
        Properties props = new Properties();
        props.put("group.id", groupID);
        //props.put("zookeeper.connect", "192.168.10.91:2181,192.168.10.92:2181,192.168.10.93:2181,192.168.10.94:2181,192.168.10.95:2181");
        props.put("zookeeper.connect", "192.168.10.88:2181,192.168.10.89:2181,192.168.10.90:2181");
        props.put("auto.offset.reset", "smallest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("partition.assignment.strategy", "roundrobin");
        return new ConsumerConfig(props);
    }


    /**
     * 在这里配置topic 的消费者个数
     * @return
     */
    public Map<String, Integer> topicCountMap() {

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

        int localConsumerCount = 3;

        topicCountMap.put("jd-com", localConsumerCount);

        //topicCountMap.put("topic2", localConsumerCount);
        return topicCountMap;
    }

    public  Map<String, List<KafkaStream<byte[], byte[]>>> multiTopicStreams() {

       ConsumerConfig consumerConfig =consumerConfig() ;


       ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

       //多个topic的List<kafkaStream>,每个topic有消费者，一个消费者对应一个流
       Map<String, List<KafkaStream<byte[], byte[]>>> multiTopicStreams = consumerConnector.createMessageStreams(topicCountMap());

       return multiTopicStreams;
   }




}
