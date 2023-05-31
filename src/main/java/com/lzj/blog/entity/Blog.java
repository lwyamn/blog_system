package com.lzj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.entity
 * @Date : 2023/2/17 9:28
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
@Schema(description = "博客表")
@TableName("blog")
@NoArgsConstructor
public class Blog extends BaseEntity{
    @Schema(description = "博客标题")
    @TableField(value = "title")
    private String title;
    @Schema(description = "博客封面")
    @TableField(value = "cover")
    private String cover;
    @Schema(description = "博客封面")
    @TableField(value = "description")
    private String description;
    @Schema(description = "博客内容")
    @TableField(value = "content")
    private String content;
    @Schema(description = "博客作者")
    @TableField(value = "author")
    private String author;
    @Schema(description = "用户id")
    @TableField(value = "userId")
    private String userId;
    @Schema(description = "博客点赞数")
    @TableField(value = "likeNumber")
    private Integer likeNumber;
    @Schema(description = "博客收藏数")
    @TableField(value = "collectNumber")
    private Integer collectNumber;

}
