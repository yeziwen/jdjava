package byb.kafka.jd.commentlist;

import byb.common.Utils;
import byb.kafka.hbase.TableFactory;
import byb.kafka.hbase.commentDetail.CommentDetailDao;
import byb.kafka.hbase.commentDetail.HBaseCommentDetailValue;
import byb.kafka.hbase.productDetail.HBaseProductDetailValue;
import byb.kafka.hbase.productDetail.ProductDetailDao;
import byb.kafka.jdmarket.searchproductlist.JDMarketSearchProductListService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by ye on 2017/3/1.
 */
public class JDCommentListService {

    private static Logger commentLog = Logger.getLogger("comment");


    public static void service(TableFactory tableFactory, String json, String pageKey) {
        if(!Utils.JDCommentKey.equals(pageKey)) return;
        commentLog.info(json);

        Gson gson = new Gson();
        JDCommentListModel model = gson.fromJson(json, JDCommentListModel.class);
        int len = model.Data.comments.length;
        ArrayList<HBaseCommentDetailValue> hBaseCommentDetailValues = new ArrayList<HBaseCommentDetailValue>();
        for(int i=0;i<len;i++) {
             //todo 解析評論
            HBaseCommentDetailValue hBaseCommentDetailValue = new HBaseCommentDetailValue();
            hBaseCommentDetailValue.id = model.Data.comments[i].id;
            hBaseCommentDetailValue.content = model.Data.comments[i].content;
            hBaseCommentDetailValue.creationTime = model.Data.comments[i].creationTime;
            hBaseCommentDetailValue.isTop = model.Data.comments[i].isTop;
            hBaseCommentDetailValue.score = model.Data.comments[i].score;
            hBaseCommentDetailValue.replyCount = model.Data.comments[i].replyCount;
            hBaseCommentDetailValue.usefulVoteCount = model.Data.comments[i].usefulVoteCount;
            hBaseCommentDetailValue.uselessVoteCount = model.Data.comments[i].uselessVoteCount;
            hBaseCommentDetailValue.userImageUrl = model.Data.comments[i].userImageUrl;
            hBaseCommentDetailValue.userLevelId = model.Data.comments[i].userLevelId;
            hBaseCommentDetailValue.userProvince = model.Data.comments[i].userProvince;//产品特征
            hBaseCommentDetailValue.isReplyGrade = model.Data.comments[i].isReplyGrade;
            hBaseCommentDetailValue.nickname = model.Data.comments[i].nickname;
            hBaseCommentDetailValue.userClient = model.Data.comments[i].userClient;
            hBaseCommentDetailValue.commentTags = gson.toJson(model.Data.comments[i].commentTags);
            hBaseCommentDetailValue.userLevelName = model.Data.comments[i].userLevelName;
            hBaseCommentDetailValue.recommend = model.Data.comments[i].recommend;
            hBaseCommentDetailValue.isMobile = model.Data.comments[i].isMobile;

            hBaseCommentDetailValue.   sku = model.Data.   sku;

            hBaseCommentDetailValues.add(hBaseCommentDetailValue);
        }

        // 评论列表 进评论表
        String sku = model.Data.sku;
        CommentDetailDao.write2Table(tableFactory.getJDCommentDetailTable(),
                hBaseCommentDetailValues, sku);



        //评论列表关联的商品 进商品表
        HBaseProductDetailValue hBaseProductDetailValue = new HBaseProductDetailValue();
        hBaseProductDetailValue.commentGoodRate=model.Data.commentGoodRate;
        hBaseProductDetailValue.commentGeneralRate=model.Data.commentGeneralRate;
        hBaseProductDetailValue.commentPoorRate=model.Data.commentPoorRate;
        hBaseProductDetailValue.commentGoodCount=model.Data.commentGoodCount;
        hBaseProductDetailValue.commentGeneralCount=model.Data.commentGeneralCount;
        hBaseProductDetailValue.commentPoorCount=model.Data.commentPoorCount;

        hBaseProductDetailValue.averageScore=model.Data.averageScore;
        hBaseProductDetailValue.imageListCount=model.Data.imageListCount;
        hBaseProductDetailValue.hotCommentTagStatistics =gson.toJson(model.Data.    hotCommentTagStatistics);
        //评论接口有效数据
        hBaseProductDetailValue.sku =  model.Data.sku;
        hBaseProductDetailValue.commentCount = model.Data.commentCount;
        hBaseProductDetailValue.jdCommentDownloadTime = model.DownloadTime;

        ProductDetailDao.write(tableFactory.getJDProductDetailTable(),
                hBaseProductDetailValue);
    }
}
