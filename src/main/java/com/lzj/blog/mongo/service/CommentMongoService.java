package com.lzj.blog.mongo.service;

import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.model.vo.CommentVO;

import java.util.List;

public interface CommentMongoService {
    Boolean add(AddCommentDTO commentDTO);

    Boolean deleteById(DeleteCommentDTO commentDTO);

    Boolean update(UpdateCommentDTO commentDTO);

    List<CommentVO> viewByBlogId(ViewCommentDTO commentDTO);
}
