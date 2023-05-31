package com.lzj.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.VO
 * @Date : 2023/2/24 10:28
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class CommentVO {

    private String id;
    private String userId;
    private String blogId;
    private String commentId;
    private String replyId;
    private String replyName;
    private String content;
    private String avatar;
    private String username;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
