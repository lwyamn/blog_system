package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/21 16:13
 * @Description:
 */
@Getter
@Setter
@ToString
public class LikeDTO {
    private String blogId;
    private String userId;
    private String author;
    private List<String> blogIds;
}
