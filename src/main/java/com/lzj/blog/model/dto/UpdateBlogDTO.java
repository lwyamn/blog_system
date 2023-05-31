package com.lzj.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateBlogDTO {
    @NotNull(message = "博客id不能为空")
    private String blogId;
    private String title;
    private String content;
    private String description;
    private String cover;
    private String author;
    private Integer likeNumber;
}
