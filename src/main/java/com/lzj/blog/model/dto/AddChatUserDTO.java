package com.lzj.blog.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddChatUserDTO {
    private String userId;
    @NotNull(message = "聊天对象为空")
    private String chatUserId;
}
