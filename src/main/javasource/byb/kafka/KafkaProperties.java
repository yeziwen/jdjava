package byb.kafka;

import java.util.Properties;

/**
 * Created by ye on 2017/2/24.
 */
public class KafkaProperties {

    public static Properties  getProperty() {
        Properties props = new Properties();
        /* 制定consumer group */
        //test2017022,test2017023,test2017024,test20170307,test2017021
        props.put("group.id", "test2017024");
        /* 定义kakfa 服务的地址，不需要将所有broker指定上 */
        props.put("bootstrap.servers", "192.168.10.92:9092");
        props.put("zookeeper.connect", "192.168.10.91:2181,192.168.10.92:2181,192.168.10.93:2181,192.168.10.94:2181,192.168.10.95:2181");
        props.put("auto.offset.reset", "earliest");
        /* 是否自动确认offset */
        props.put("enable.auto.commit", "true");
        /* 自动确认offset的时间间隔 */
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        /* key的序列化类 */
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        /* value的序列化类 */
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }
}
