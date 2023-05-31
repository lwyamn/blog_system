package com.lzj.blog.mapper;

import com.lzj.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.mapper
 * @Date : 2023/2/17 10:55
 * @Author : linzj
 * @Description :
 */
@Mapper
public interface UserMapper extends CommonMapper<User> {
}
