package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: linzj
 * @Date: 2023/3/23 9:41
 * @Description:
 */
@Setter
@Getter
@ToString
public class CollectVO {
    private String blogId;
    private Long collectNumber;
    private Boolean isCollect;
}
