package byb.kafka.jd.hotshell;

/**
 * Created by ye on 2017/3/1.
 */

public class JDHotShellModel {

    public String PageModelKey;
    public String DownloadTime;

    public Data Data;

    public static class Data {
        public String sku;
        public String name;
        public String brank;
        public String model;

        public List_[] list;

    }



    public static class List_ {
        public String[] accessoryShows;
    }


    public static class HotShellModel{
        public String wName;
        public String wid;
        public String imageUrl;
    }


}
