package com.lzj.blog.converter;

import com.lzj.blog.entity.Comment;
import com.lzj.blog.model.vo.CommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.converter
 * @Date : 2023/2/24 14:25
 * @Author : linzj
 * @Description :
 */
@Mapper
public interface CommentConverter {
    CommentConverter INSTANCE = Mappers.getMapper(CommentConverter.class);

    CommentVO commentTOCommentVO(Comment comment);

    List<CommentVO> commentListTOCommentVOList(List<Comment> comments);
}
