package com.lzj.blog.model.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.VO.response
 * @Date : 2023/2/17 11:04
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
@Schema(description = "返回结果集")
public class Result<T> {

    @Schema(description = "接口业务编号，默认成功为 0")
    private Integer code;

    @Schema(description = "接口业务消息，默认为 成功")
    private String msg;

    @Schema(description = "接口业务数据")
    private T data;

    private Map map = new HashMap();
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<T>();
        result.msg = msg;
        result.code = 1;
        return result;
    }
    public static <T> Result<T> success(String msg,T object) {
        Result<T> result = new Result<T>();
        result.data=object;
        result.msg = msg;
        result.code = 1;
        return result;
    }
    public static <T> Result<T> success(String msg,Integer code) {
        Result<T> result = new Result<T>();
        result.code = code;
        result.msg = msg;
        return result;
    }
    public static <T> Result<T> success(T object) {
        Result<T> Result = new Result<T>();
        Result.data = object;
        Result.code = 1;
        return Result;
    }

    public static <T> Result<T> error(Integer code,String msg) {
        Result Result = new Result();
        Result.msg = msg;
        Result.code = code;
        return Result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
