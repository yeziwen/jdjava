package byb.kafka.hbase.commentDetail;

import byb.common.MyTextUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by ye on 2017/3/2.
 */
public class CommentDetailDao {
    private static  String familyName = "content";
    private static Logger logger = Logger.getLogger(CommentDetailDao.class);

    /**
     * 评论表rowKey=skuID+commentID
     * @param table
     * @param hBaseCommentDetailValues
     */
    public static void write2Table(Table table,
                                               ArrayList<HBaseCommentDetailValue> hBaseCommentDetailValues,
                                               String sku) {

        //評論標生成策略
        ArrayList<Put> puts = new ArrayList<Put>();
        if (hBaseCommentDetailValues != null && hBaseCommentDetailValues.size() > 0) {
            int len = hBaseCommentDetailValues.size();
            for(int i=0;i<len;i++) {
                HBaseCommentDetailValue baseCommentDetailValue = hBaseCommentDetailValues.get(i);
                String rowKey = sku + "-"+baseCommentDetailValue.getId();
                Put put = new Put(Bytes.toBytes(rowKey));
                //="if(!MyTextUtils.isBlank(baseCommentDetailValue."&A1&")){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey."&A1&"), Bytes.toBytes(baseCommentDetailValue."&A1&"));}"
                if(!MyTextUtils.isBlank(baseCommentDetailValue.id)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.id), Bytes.toBytes(baseCommentDetailValue.id));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.sku)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.sku), Bytes.toBytes(baseCommentDetailValue.sku));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.content)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.content), Bytes.toBytes(baseCommentDetailValue.content));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.creationTime)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.creationTime), Bytes.toBytes(baseCommentDetailValue.creationTime));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.isTop)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.isTop), Bytes.toBytes(baseCommentDetailValue.isTop));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.score)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.score), Bytes.toBytes(baseCommentDetailValue.score));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.replyCount)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.replyCount), Bytes.toBytes(baseCommentDetailValue.replyCount));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.usefulVoteCount)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.usefulVoteCount), Bytes.toBytes(baseCommentDetailValue.usefulVoteCount));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.uselessVoteCount)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.uselessVoteCount), Bytes.toBytes(baseCommentDetailValue.uselessVoteCount));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.userImageUrl)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.userImageUrl), Bytes.toBytes(baseCommentDetailValue.userImageUrl));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.userLevelId)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.userLevelId), Bytes.toBytes(baseCommentDetailValue.userLevelId));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.userProvince)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.userProvince), Bytes.toBytes(baseCommentDetailValue.userProvince));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.isReplyGrade)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.isReplyGrade), Bytes.toBytes(baseCommentDetailValue.isReplyGrade));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.nickname)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.nickname), Bytes.toBytes(baseCommentDetailValue.nickname));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.userClient)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.userClient), Bytes.toBytes(baseCommentDetailValue.userClient));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.commentTags)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.commentTags), Bytes.toBytes(baseCommentDetailValue.commentTags));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.userLevelName)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.userLevelName), Bytes.toBytes(baseCommentDetailValue.userLevelName));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.recommend)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.recommend), Bytes.toBytes(baseCommentDetailValue.recommend));}
                if(!MyTextUtils.isBlank(baseCommentDetailValue.isMobile)){put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(HBaseCommentDetailKey.isMobile), Bytes.toBytes(baseCommentDetailValue.isMobile));}
                puts.add(put);
            }
        }
        try {
            if (table != null) {
                table.put(puts);
            }
        } catch (Exception e) {
            logger.error("writeCommentDetailTable ****************失败"+e.getMessage());
        }
    }




}
