package com.lzj.blog.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.exception
 * @Date : 2023/2/24 15:53
 * @Author : linzj
 * @Description :
 */
@Getter
public class BlogException extends RuntimeException implements ErrorCode{
    private Integer code;
    private String msg;
    public BlogException(ErrorCode errorCode){
        super("系统错误"+errorCode.getCode()+":"+errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
    public BlogException(ErrorCode errorCode, Throwable cause) {
        super("系统错误" + errorCode.getCode() + ":" + errorCode.getMsg(), cause);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
}
