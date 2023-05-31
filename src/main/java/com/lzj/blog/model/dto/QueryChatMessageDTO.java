package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/27 18:21
 */
@Getter
@Setter
@ToString
public class QueryChatMessageDTO {
    private String userId;
    private String chatUserId;
    private String type;
}
