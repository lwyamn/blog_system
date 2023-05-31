package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.DTO
 * @Date : 2023/2/24 10:22
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class ViewCommentDTO {
    private String id;
    private String userId;
    private String blogId;
    private String type;
    private Integer currentPage;
    private Integer pageSize;
}
