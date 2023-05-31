package com.lzj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lzj.blog.auth.login.BlogUser;
import com.lzj.blog.constant.LoginType;
import com.lzj.blog.entity.User;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.model.dto.request.LoginReq;
import com.lzj.blog.model.vo.response.LoginResp;
import com.lzj.blog.properties.TokenProperties;
import com.lzj.blog.service.LoginService;
import com.lzj.blog.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenProperties tokenProperties;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public LoginResp Login(LoginReq loginReq) {
        LoginResp loginResp = new LoginResp();
        switch (loginReq.getType()){
            case LoginType.USERNAME -> loginResp.setToken(loginByUsername(loginReq));
            case LoginType.EMAIL -> loginResp.setToken(loginByEmail(loginReq));
            case LoginType.PHONE -> loginResp.setToken(loginByPhone(loginReq));
            default -> throw new BlogException(BlogErrorCode.GET_NULL_LOGIN_MSG);
        }
        return loginResp;
    }

    private String loginByUsername(LoginReq loginReq){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getPassword,User::getId,User::getUsername).eq(User::getUsername,loginReq.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        checkPassword(user,loginReq);
        return getToken(user);
    }
    private String loginByEmail(LoginReq loginReq){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getPassword,User::getId).eq(User::getEmail,loginReq.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        checkPassword(user,loginReq);
        return getToken(user);
    }
    private String loginByPhone(LoginReq loginReq){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getPassword,User::getId).eq(User::getPhone,loginReq.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        checkPassword(user,loginReq);
        return getToken(user);
    }
    private void checkPassword(User user,LoginReq loginReq){
        if(Objects.isNull(user)){
            throw new BlogException(BlogErrorCode.USER_NOT_EXIST);
        }
        if (!Objects.equals(user.getPassword(),loginReq.getPassword())){
            throw new BlogException(BlogErrorCode.PASSWORD_ERROR);
        }
    }
    private String getToken(User user){
        String token = getTokenFromRedis(user.getId());
        if(!Objects.isNull(token)){
            return token;
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setUserId(user.getId());
        blogUser.setUsername(user.getUsername());
        token = TokenUtils.createToken(blogUser, tokenProperties);
        addToRedis(user.getId(),token);
        return token;
    }
    private void addToRedis(String userId,String token){
        redisTemplate.opsForValue().set(userId,token,8,TimeUnit.HOURS);
    }

    private String getTokenFromRedis(String userId){
        return redisTemplate.opsForValue().get(userId);
    }
}
