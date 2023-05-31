package com.lzj.blog.controller;

import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.request.LoginReq;
import com.lzj.blog.model.vo.response.LoginResp;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "登录接口")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(BlogUrl.Open.LOGIN)
    @Operation(summary = "登录")
    public Result<LoginResp> login(@RequestBody LoginReq loginReq){
        return Result.success(loginService.Login(loginReq));
    }

}
