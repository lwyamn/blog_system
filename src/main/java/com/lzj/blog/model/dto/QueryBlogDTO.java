package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryBlogDTO {
    private String BlogId;
    private String title;
    private String author;
    private String userId;
    private Integer currentPage;
    private Integer pageSize;
}
