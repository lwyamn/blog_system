package com.lzj.blog.mongo.controller;

import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.mongo.repository.CommentRepository;
import com.lzj.blog.mongo.service.CommentMongoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "评论Mongo接口")
public class CommentMongoController {

    @Autowired
    private CommentMongoService commentMongoService;

    @PostMapping(BlogUrl.MongoComment.ADD_MONGO_COMMENT)
    @Operation(summary = "添加评论")
    public Result<String> add(@RequestBody AddCommentDTO commentDTO) {
        commentMongoService.add(commentDTO);
        return Result.success("评论成功");
    }

    @PostMapping(BlogUrl.MongoComment.DELETE_MONGO_COMMENT)
    @Operation(summary = "删除评论")
    public Result<String> delete(@RequestBody DeleteCommentDTO commentDTO) {
        commentMongoService.deleteById(commentDTO);
        return Result.success("删除成功");
    }

    @PostMapping(BlogUrl.MongoComment.UPDATE_MONGO_COMMENT)
    @Operation(summary = "更新评论")
    public Result<String> delete(@RequestBody UpdateCommentDTO commentDTO) {
        commentMongoService.update(commentDTO);
        return Result.success("修改成功");
    }

    @PostMapping(BlogUrl.MongoComment.VIEW_MONGO_COMMENT)
    @Operation(summary = "查看评论")
    public Result<List<CommentVO>> delete(@RequestBody ViewCommentDTO commentDTO) {
        return Result.success(commentMongoService.viewByBlogId(commentDTO));
    }
}
