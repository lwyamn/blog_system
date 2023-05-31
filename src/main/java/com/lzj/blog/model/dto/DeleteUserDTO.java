package com.lzj.blog.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.dto.request
 * @Date : 2023/2/17 14:44
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class DeleteUserDTO {
    List<String> userLists;
}
