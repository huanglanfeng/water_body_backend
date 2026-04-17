//package com.blackwater.entity.Comment;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Date;
//
///**
// * 文章评论实体类
// */
//@Document(collection = "comment")
//@Data
////复合索引
// @CompoundIndex( def = "{'userid': 1, 'nickname': -1}")
//public class Comment implements Serializable {
//    @ApiModelProperty("主键")
//    private String id;
//    @Field("content")
//    @ApiModelProperty("吐槽内容")
//    private String content;
//    @ApiModelProperty("发布日期")
//    private Date publishtime;
//    //添加了一个单字段的索引
//    @Indexed

//    @ApiModelProperty("发布人ID")
//    private String userid;
//    @ApiModelProperty("昵称")
//    private String nickname;
//    @ApiModelProperty("评论的日期时间")
//    private LocalDateTime createdatetime;
//    @ApiModelProperty("点赞数")
//    private Integer likenum;
//    @ApiModelProperty("回复数")
//    private Integer replynum;
//    @ApiModelProperty("状态")
//    private String state;
//    @ApiModelProperty("上级ID")
//    private String parentid;
//    private String articleid;
//
//    //getter and setter.....
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Date getPublishtime() {
//        return publishtime;
//    }
//
//    public void setPublishtime(Date publishtime) {
//        this.publishtime = publishtime;
//    }
//
//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public LocalDateTime getCreatedatetime() {
//        return createdatetime;
//    }
//
//    public void setCreatedatetime(LocalDateTime createdatetime) {
//        this.createdatetime = createdatetime;
//    }
//
//    public Integer getLikenum() {
//        return likenum;
//    }
//
//    public void setLikenum(Integer likenum) {
//        this.likenum = likenum;
//    }
//
//    public Integer getReplynum() {
//        return replynum;
//    }
//
//    public void setReplynum(Integer replynum) {
//        this.replynum = replynum;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getParentid() {
//        return parentid;
//    }
//
//    public void setParentid(String parentid) {
//        this.parentid = parentid;
//    }
//
//    public String getArticleid() {
//        return articleid;
//    }
//
//    public void setArticleid(String articleid) {
//        this.articleid = articleid;
//    }
//
//    @Override
//    public String toString() {
//        return "Comment{" +
//                "id='" + id + '\'' +
//                ", content='" + content + '\'' +
//                ", publishtime=" + publishtime +
//                ", userid='" + userid + '\'' +
//                ", nickname='" + nickname + '\'' +
//                ", createdatetime=" + createdatetime +
//                ", likenum=" + likenum +
//                ", replynum=" + replynum +
//                ", state='" + state + '\'' +
//                ", parentid='" + parentid + '\'' +
//                ", articleid='" + articleid + '\'' +
//                '}';
//    }
//}
////把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
////@Document(collection="mongodb 对应 collection 名")
//// 若未加 @Document ，该 bean save 到 mongo 的 comment collection
//// 若添加 @Document ，则 save 到 comment collection