package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/23 9:30
 * @Description:
 */
@Getter
@Setter
@ToString
public class CollectDTO {
    private String userId;
    private String blogId;
    private String author;
    private List<String> blogIds;
}
