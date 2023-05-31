package com.lzj.blog.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.exception
 * @Date : 2023/2/24 15:59
 * @Author : linzj
 * @Description :
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BlogErrorCode implements ErrorCode{

    //通用问题
    UNKNOWN_ERROR(1001,"未知错误"),
    //用户问题
    TOKEN_NOT_EXIST(2001,"token不存在"),
    TOKEN_NOT_LEGAL(2002,"token不合法"),
    TOKEN_EXPIRE(2003,"token过期"),
    PASSWORD_ERROR(2010,"密码错误"),
    USER_NOT_EXIST(2011,"用户不存在"),
    USER_IS_EXISTS(2012,"用户已注册"),
    NOT_LOGIN(2020,"用户未登录"),

    //文件问题
    UPLOAD_FILE_FAILURE(3001,"上传文件失败"),

    //传值问题
    GET_NULL_LOGIN_MSG(3001,"登录传值出错"),
    MISS_REQUIRED_PARAMETER(3002,"缺少必要参数"),
    //链接问题
    FAILURE_IN_LINK(4001,"链接失败"),
    FAILURE_IN_BROKEN_LINK(4002,"断开链接失败"),
    //消息问题
    SEND_CHAT_MESSAGE_ERROR(5001,"发送聊天消息失败"),
    //工具类问题
    TO_JSON_STRING_ERROR(6001,"json转换失败"),
    //聊天问题
    NOT_FIND_CHAT_USER(7001,"未找到聊天对象"),
    SEND_MESSAGE_ERROR(7002,"发送消息失败");
    private final Integer code;

    private final String msg;
}
