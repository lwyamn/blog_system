package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: linzj
 * @Date: 2023/3/21 9:57
 * @Description:
 */
@Setter
@Getter
@ToString
public class BlogHomeVO {
    private String id;
    private String title;
    private String content;
    private String description;
    private String cover;
    private String author;
    private String userId;
    private String userAvatar;
}
