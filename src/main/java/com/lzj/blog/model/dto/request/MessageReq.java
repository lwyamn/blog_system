package com.lzj.blog.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 11:25
 */
@Getter
@Setter
@ToString
public class MessageReq {
    private String userId;
    @NotNull(message = "聊天对象为空")
    private String chatUserId;
    private String content;
}
