package com.lzj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.entity
 * @Date : 2023/2/17 10:16
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
@Schema(description = "评论表")
@TableName("comment")
@NoArgsConstructor
@Deprecated
public class Comment extends BaseEntity{
    @Schema(description = "用户id")
    @TableField(value = "userId")
    private String userId;
    @Schema(description = "博客id")
    @TableField(value = "blogId")
    private String blogId;
    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;
    @Schema(description = "头像")
    @TableField(value = "avatar")
    private String avatar;
    @Schema(description = "评论类型")
    @TableField(value = "type")
    private String type;
    @Schema(description = "评论内容")
    @TableField(value = "content")
    private String content;
}
