package byb.kafka.jd.commentlist;

/**
 * Created by ye on 2017/3/1.
 */
public class JDCommentListModel {

    public Data Data; //商品数组
    public String  PageModelKey;//接口key
    public String  DownloadTime;//下载时间



    public static class Data {
        public String  sku;
        public String  commentGoodRate;
        public String  commentGeneralRate;
        public String  commentPoorRate;
        public String  commentGoodCount;
        public String  commentGeneralCount;
        public String  commentPoorCount;
        public String  commentCount;
        public String  averageScore;
        public String  imageListCount;

        public Comment[] comments;

        public Object hotCommentTagStatistics;

    }
    public static class Comment{
        public String  index    ;
        public String  id;
        public String  content  ;
        public String  creationTime;//本次需求字段
        public String  isTop;
        public String  score;
        public String  replyCount;
        public String  usefulVoteCount;
        public String  uselessVoteCount;
        public String  userImageUrl;
        public String  userLevelId;
        public String  userProvince;
        public String  isReplyGrade;
        public String  nickname;
        public String  userClient;
        public String  userLevelName;
        public String  recommend;
        public String  isMobile;
        public Object  commentTags;

    }



}
