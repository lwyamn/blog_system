package com.lzj.blog.auth.resolver;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.auth.login.BlogUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.properties.TokenProperties;
import com.lzj.blog.utils.TokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
public class CommonResolver implements HandlerMethodArgumentResolver {
    private static final String AUTHORIZATION = "Authorization";
    @Resource
    private TokenProperties tokenProperties;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CurrentUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String authorization = webRequest.getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(authorization)){
            BlogUser blogUser = new BlogUser();
            blogUser.setUserId("1629495477589856258");
            blogUser.setUsername("模拟用户");
            log.info("启动模拟用户{}",blogUser);
            return blogUser;
        }
        CurrentUser currentUser = TokenUtils.fromToken(authorization, tokenProperties);
        return currentUser;
    }
}
