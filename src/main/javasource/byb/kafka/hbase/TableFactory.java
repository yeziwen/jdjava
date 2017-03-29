package byb.kafka.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by ye on 2017/2/24.
 */
public class TableFactory {
    /**
     * 一个线程，一个consumer,一个hbase连接
     */
    private static Logger logger = Logger.getLogger(TableFactory.class);
    private  Connection connection = null;
    private static Configuration conf = null;
    static  {
        conf = HBaseConfiguration.create();
        //设置zooKeeper集群地址，也可以通过将hbase-site.xml导入classpath，但是建议在程序里这样设置
        conf.set("hbase.zookeeper.quorum",
                "192.168.10.81,192.168.10.82,192.168.10.83,192.168.10.84,192.168.10.85,192.168.10.86,192.168.10.87");
        //设置zookeeper连接端口，默认2181
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        TableName shopDetailTableNameObj = null;
        TableName productDetailTableNameObj = null;
    }

    public TableFactory() {
    try {
        this.connection = ConnectionFactory.createConnection(conf);
            /*Admin admin = connection.getAdmin();
            admin.close();// todo 连接如何处理*/
    } catch (IOException e) {
        logger.error("*****************HBase连接失败"+e.getMessage());
    }

}


    private  Table JDProductDetailTable =null;
    private  Table JDShopDetailTable = null;
    private  Table JDCommentDetailTable = null;
    private  Table JDProductTimeHistoryTable =null;
    private  Table JDHotShellTable =null;

    public static  String  JDProductDetailTableName ="JDProductDetail";
    public  static String JDShopDetailTableName = "JDShopDetail";
    public  static String JDCommentDetailTableName = "JDCommentDetail";
    public  static String JDHotShellTableName ="JDHotShell";
    public  static String JDProductTimeHistoryName ="JDProductTimeHistory";

    public Table getJDProductHistoryTable(){

        if (JDProductTimeHistoryTable == null) {
            try {
                this.JDProductTimeHistoryTable =  connection.getTable(TableName.valueOf(JDProductTimeHistoryName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("JDProductDetailService"+"拿表失败"+e.getMessage());
            }
        }
        return  JDProductTimeHistoryTable;
    }


    public Table getJDProductDetailTable(){

        if (JDProductDetailTable == null) {
            try {
                this.JDProductDetailTable =  connection.getTable(TableName.valueOf(JDProductDetailTableName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("JDProductDetailService"+"拿表失败"+e.getMessage());
            }
        }
        return  JDProductDetailTable;
    }


    public Table getJDShopDetailTable() {
        if (JDShopDetailTable == null) {
            try {
                this.JDShopDetailTable =  connection.getTable(TableName.valueOf(JDShopDetailTableName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("JDShopDetailTable"+"拿表失败"+e.getMessage());
            }
        }
        return  JDShopDetailTable;
    }



    public Table getJDCommentDetailTable()  {
        if (JDCommentDetailTable == null) {
            try {
                this.JDCommentDetailTable =  connection.getTable(TableName.valueOf(JDCommentDetailTableName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("JDCommentDetailTable"+"拿表失败"+e.getMessage());
            }
        }
        return  JDCommentDetailTable;
    }



    public Table getJDHotShellTable() {
        if (JDHotShellTable == null) {
            try {
                this.JDHotShellTable =  connection.getTable(TableName.valueOf(JDHotShellTableName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("JDHotShellTable"+"拿表失败"+e.getMessage());
            }
        }
        return  JDHotShellTable;
    }

















}
