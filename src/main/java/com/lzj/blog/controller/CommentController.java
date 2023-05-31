package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.controller
 * @Date : 2023/2/24 10:12
 * @Author : linzj
 * @Description :
 */
@Slf4j
@RestController
@Tag(name = "评论接口")
@Deprecated
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(BlogUrl.Comment.ADD_COMMENT)
    @Operation(summary = "增加评论")
    public Result<String> add(@RequestBody AddCommentDTO commentDTO, CurrentUser currentUser){
        commentService.add(commentDTO);
        return Result.success("添加评论成功");
    }

    @PostMapping(BlogUrl.Comment.DELETE_COMMENT)
    @Operation(summary = "删除评论")
    public Result<String> delete(@RequestBody DeleteCommentDTO commentDTO,CurrentUser currentUser){
        commentService.delete(commentDTO);
        return Result.success("删除成功");
    }

    @PostMapping(BlogUrl.Comment.UPDATE_COMMENT)
    @Operation(summary = "更新评论")
    public Result<String> update(@RequestBody UpdateCommentDTO commentDTO,CurrentUser currentUser){
        commentService.update(commentDTO);
        return Result.success("更新成功");
    }

    @PostMapping(BlogUrl.Comment.VIEW_COMMENT)
    @Operation(summary = "查看评论")
    public Result<PageVO<CommentVO>> view(@RequestBody ViewCommentDTO commentDTO,CurrentUser currentUser){
        return Result.success(commentService.View(commentDTO));
    }
}
