package com.lzj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.mapper
 * @Date : 2023/2/17 10:56
 * @Author : linzj
 * @Description :
 */
@Mapper
public interface CommentMapper extends CommonMapper<Comment> {
}
