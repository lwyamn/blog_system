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
 * @Date : 2023/2/16 16:35
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
@Schema(description = "用户表")
@TableName("user")
@NoArgsConstructor
public class User extends BaseEntity{
    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;
    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;
    @Schema(description = "用户类型")
    @TableField(value = "userType")
    private Integer userType;
    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;
    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;
    @Schema(description = "个人简介")
    @TableField(value = "personalProfile")
    private String personalProfile;
    @Schema(description = "头像")
    @TableField(value = "avatar")
    private String avatar;
}
