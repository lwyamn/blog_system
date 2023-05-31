package com.lzj.blog.service;

import com.lzj.blog.model.dto.request.LoginReq;
import com.lzj.blog.model.vo.response.LoginResp;

public interface LoginService {
    LoginResp Login(LoginReq loginReq);
}
