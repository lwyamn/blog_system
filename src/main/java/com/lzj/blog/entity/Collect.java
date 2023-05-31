package com.lzj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: linzj
 * @Date: 2023/3/23 9:13
 * @Description:
 */
@Getter
@Setter
@ToString
@Schema(description = "收藏")
@TableName("collect")
@NoArgsConstructor
@Deprecated
public class Collect extends BaseEntity{

    @Schema(description = "博客id")
    @TableField(value = "blogId")
    private String blogId;

    @Schema(description = "用户Id")
    @TableField(value = "userId")
    private String userId;

}
