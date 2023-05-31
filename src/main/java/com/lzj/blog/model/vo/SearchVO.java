package com.lzj.blog.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: linzj
 * @see:
 * @since: 2023/5/16 11:22
 */
@ToString
@Getter
@Setter
public class SearchVO {
    @Schema(description = "类型 0 用户 1 博客")
    private Integer type;
    private String id;
    private String name;
}
