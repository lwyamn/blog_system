package com.lzj.blog.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowReq {
    private String userId;
    private String followUserId;
    private Integer type;
}
