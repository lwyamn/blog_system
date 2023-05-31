package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.dto.request
 * @Date : 2023/2/17 11:15
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class AddUserDTO {
    private String username;
    private String password;
    private Integer userType;
    private String email;
    private String phone;
}
