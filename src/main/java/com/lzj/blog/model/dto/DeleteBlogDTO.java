package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DeleteBlogDTO {
    private String blogId;
    private String userId;
    private List<String> blogIdLists;
}
