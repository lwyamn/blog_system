package com.lzj.blog.mongo.repository;

import com.lzj.blog.mongo.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;


public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findCommentsByBlogIdOrderByCreateTimeDesc(String blogId);

    int deleteCommentsByCommentId(String commentId);
}
