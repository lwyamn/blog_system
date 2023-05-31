package com.lzj.blog.mongo.service.impl;

import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.mongo.converter.CommentConverter;
import com.lzj.blog.mongo.entity.Comment;
import com.lzj.blog.mongo.repository.CommentRepository;
import com.lzj.blog.mongo.service.CommentMongoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentMongoServiceImpl implements CommentMongoService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Boolean add(AddCommentDTO commentDTO) {
        if(StringUtils.isEmpty(commentDTO.getUserId()) ||StringUtils.isEmpty(commentDTO.getBlogId())){
            throw new BlogException(BlogErrorCode.MISS_REQUIRED_PARAMETER);
        }
        Comment comment = new Comment();
        comment.setReplyId(commentDTO.getReplyId());
        comment.setReplyName(commentDTO.getReplyName());
        comment.setAvatar(commentDTO.getAvatar());
        comment.setUserId(commentDTO.getUserId());
        comment.setCommentId(commentDTO.getCommentId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setBlogId(commentDTO.getBlogId());
        comment.setUsername(commentDTO.getUsername());
        commentRepository.insert(comment);
        return true;
    }

    @Override
    public Boolean deleteById(DeleteCommentDTO commentDTO) {
        commentRepository.deleteById(commentDTO.getId());
        commentRepository.deleteCommentsByCommentId(commentDTO.getId());
        return true;
    }

    @Override
    public Boolean update(UpdateCommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setAvatar(commentDTO.getAvatar());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setUsername(commentDTO.getUsername());
        commentRepository.save(comment);
        return true;
    }

    @Override
    public List<CommentVO> viewByBlogId(ViewCommentDTO commentDTO) {
        List<Comment> comments = commentRepository.findCommentsByBlogIdOrderByCreateTimeDesc(commentDTO.getBlogId());
        return CommentConverter.INSTANCE.commentListTOCommentVOList(comments);
    }


}
