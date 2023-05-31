package com.lzj.blog.mongo.converter;

import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.mongo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentConverter {
    CommentConverter INSTANCE = Mappers.getMapper(CommentConverter.class);

    CommentVO commentTOCommentVO(Comment comment);

    List<CommentVO> commentListTOCommentVOList(List<Comment> comments);
}