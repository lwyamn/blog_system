package com.lzj.blog.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/23 16:55
 */
@Getter
@Setter
@ToString
@Document(collection = "ChatMessage")
public class ChatMessage extends BaseEntity{
    @Field("消息内容")
    private String content;
    @Field("用户Id")
    private String userId;
    @Field("聊天对象Id")
    private String chatUserId;
    @Field("聊天类型")
    private String type;
}
