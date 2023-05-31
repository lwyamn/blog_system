package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.DTO
 * @Date : 2023/2/24 10:17
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class DeleteCommentDTO {
    private String id;
    private String userId;
    private String blogId;
    private String type;
    private List<String> idList;
}
