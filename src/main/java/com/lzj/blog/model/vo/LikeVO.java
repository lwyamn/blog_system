package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: linzj
 * @Date: 2023/3/21 16:37
 * @Description:
 */
@Getter
@Setter
@ToString
public class LikeVO {
    private String blogId;
    private Long likeNumber;
    private Boolean isLike;
}
