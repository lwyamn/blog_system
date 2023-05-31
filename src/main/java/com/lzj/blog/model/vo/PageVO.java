package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.VO
 * @Date : 2023/2/17 17:31
 * @Author : linzj
 * @Description :
 */
@Setter
@Getter
@ToString
public class PageVO<T> {
    private List<T> dataVO;
    private Long total;

}
