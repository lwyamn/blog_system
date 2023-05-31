package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.dto.request
 * @Date : 2023/2/17 15:32
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class QueryUsersDTO {
    private String id;
    private String username;
    private List<Integer> userTypes;
    private String phone;
    private String email;
    private Integer currentPage;
    private Integer pageSize;
}
