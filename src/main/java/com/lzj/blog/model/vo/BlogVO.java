package com.lzj.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class BlogVO {
    private String id;
    private String title;
    private String content;
    private String description;
    private String cover;
    private String author;
    private String userId;
    private Integer likeNumber;
    private Integer collectNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
