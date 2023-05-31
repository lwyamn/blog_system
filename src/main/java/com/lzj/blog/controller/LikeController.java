package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.LikeDTO;
import com.lzj.blog.model.vo.LikeVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/21 16:30
 * @Description:
 */
@Slf4j
@RestController
@Tag(name = "点赞接口")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(BlogUrl.Like.ADD_LIKE)
    @Operation(summary = "点赞")
    public Result<String> add(@RequestBody LikeDTO likeDTO, CurrentUser currentUser){
        likeDTO.setUserId(currentUser.getId());
        likeService.addLike(likeDTO);
        return Result.success("点赞成功");
    }

    @PostMapping(BlogUrl.Like.CANCEL_LIKE)
    @Operation(summary = "取消点赞")
    public Result<String> cancel(@RequestBody LikeDTO likeDTO, CurrentUser currentUser){
        likeDTO.setUserId(currentUser.getId());
        likeService.cancelLike(likeDTO);
        return Result.success("取消点赞");
    }
    @PostMapping(BlogUrl.Like.GET_LIKE_MESSAGE)
    @Operation(summary = "点赞信息")
    public Result<List<LikeVO>> likeNumber(@RequestBody LikeDTO likeDTO, CurrentUser currentUser){
        likeDTO.setUserId(currentUser.getId());
        return Result.success(likeService.LikeMessage(likeDTO));
    }

}
