package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/27 18:22
 */
@Setter
@Getter
@ToString
public class ChatMessageVO {
    private String id;
    private String content;
    private String userId;
    private String chatUserId;
    private String type;
    private Date createTime;
    private Date updateTime;
}
