package com.lzj.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddBlogDTO {

    @NotNull(message = "标题不能为空")
    private String title;
    @NotNull(message = "内容不能为空")
    private String content;
    @NotNull(message = "描述不能为空")
    private String description;
    @NotNull(message = "封面不能为空")
    private String cover;
    private String author;
    private String userId;
}
