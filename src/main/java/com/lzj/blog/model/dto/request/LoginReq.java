package com.lzj.blog.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginReq {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "类型不能为空")
    private String type;
    @NotNull(message = "密码不能为空")
    private String password;
}
