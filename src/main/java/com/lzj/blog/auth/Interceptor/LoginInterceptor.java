package com.lzj.blog.auth.Interceptor;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.properties.TokenProperties;
import com.lzj.blog.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.el.parser.Token;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.auth.Interceptor
 * @Date : 2023/2/24 15:47
 * @Author : linzj
 * @Description :
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenProperties tokenProperties;
    @Autowired
    private RedissonClient redissonClient;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("进行token身份验证");
        String authorization = request.getHeader("authorization");
        if(StringUtils.isBlank(authorization)){
            log.info("未登录");
            throw new BlogException(BlogErrorCode.NOT_LOGIN);
        }
        CurrentUser currentUser = TokenUtils.fromToken(authorization, tokenProperties);
        String id = currentUser.getId();
        RSet<String> set = redissonClient.getSet(id);
        if (!Objects.equals(authorization,set)){
            log.info("token不存在");
            throw new BlogException(BlogErrorCode.TOKEN_NOT_EXIST);
        }
        return true;
    }

}
