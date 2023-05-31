package com.lzj.blog.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Document(collection = "Comment")
public class Comment extends BaseEntity{

    @Field("用户ID")
    private String userId;
    @Field("用户名")
    private String username;
    @Field("用户头像")
    private String avatar;
    @Field("被回复人")
    private String replyId;
    @Field("被回复人姓名")
    private String replyName;
    @Field("评论ID")
    private String commentId;
    @Field("博客Id")
    private String blogId;
    @Field("评论类型")
    private String type;
    @Field("内容")
    private String content;

}
