package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.DTO
 * @Date : 2023/2/24 10:15
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class AddCommentDTO {
    private String userId;
    private String blogId;
    private String replyId;
    private String replyName;
    private String commentId;
    private String username;
    private String avatar;
    private String content;
    private String type;
}
