package com.lzj.blog.config;

import com.lzj.blog.exception.BlogException;
import com.lzj.blog.model.vo.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.config
 * @Date : 2023/2/24 17:26
 * @Author : linzj
 * @Description :
 */

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> handle(Exception e) {
        if (e instanceof BlogException) {   //判断异常是否是我们定义的异常
            BlogException blogException = (BlogException) e;
            return Result.error(blogException.getCode(), blogException.getMsg());
        }else {
            logger.error("系统异常{}", e);
            return Result.error(500, "系统异常");
        }
    }
}
