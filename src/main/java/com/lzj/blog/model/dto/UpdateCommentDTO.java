package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.DTO
 * @Date : 2023/2/24 10:19
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class UpdateCommentDTO {
    private String id;
    private String content;
    private String avatar;
    private String username;
    private String type;
}
