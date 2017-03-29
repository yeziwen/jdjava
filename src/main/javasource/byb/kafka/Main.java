package byb.kafka;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) throws IOException {

       /* log4j.appender.INFOLOG.File= ${log.dir}/${log.info.file}*/

         //System.setProperty("log.dir", logDir);
        // System.setProperty("log.info.file", infoLogFileName);
        //System.setProperty("log.debug.file", debugLogFileName);

       /* ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) {
            Consumer consumerThread_ = new Consumer("jd-com");
            cachedThreadPool.execute(consumerThread_);
        }*/




    }
}
