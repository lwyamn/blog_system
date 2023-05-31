package com.lzj.blog.exception;

import com.lzj.blog.model.vo.response.Result;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.exception
 * @Date : 2023/2/24 15:57
 * @Author : linzj
 * @Description :
 */
public interface ErrorCode {
    Integer getCode();
    String getMsg();

    default Result<?> toResult(){return Result.error(getCode(),getMsg());}
}
