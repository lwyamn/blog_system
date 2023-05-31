package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.constant.UserMsg;
import com.lzj.blog.model.dto.AddUserDTO;
import com.lzj.blog.model.dto.DeleteUserDTO;
import com.lzj.blog.model.dto.request.FollowReq;
import com.lzj.blog.model.vo.FollowVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "关注接口")
public class FollowController {
    @Autowired
    private FollowService followService;
    @PostMapping(BlogUrl.Follow.ADD_FOLLOW)
    @Operation(summary = "关注")
    public Result<String> add(@RequestBody FollowReq followReq,CurrentUser user){
        followReq.setUserId(user.getId());
        followService.add(followReq);
        return Result.success("关注成功");
    }
    @PostMapping(BlogUrl.Follow.CANCEL_FOLLOW)
    @Operation(summary = "取关")
    public Result<String> cancel(@RequestBody FollowReq followReq,CurrentUser user){
        followReq.setUserId(user.getId());
        followService.cancel(followReq);
        return Result.success("取关成功");
    }

    @GetMapping(BlogUrl.Follow.GET_FOLLOW_LIST)
    @Operation(summary = "关注列表")
    public Result<List<UserVO>> list(CurrentUser user){
        return Result.success( followService.list(user.getId()));
    }

    @GetMapping(BlogUrl.Follow.GET_FANS_LIST)
    @Operation(summary = "粉丝列表")
    public Result<FollowVO> fansList(CurrentUser user){
        return Result.success( followService.fansList(user.getId()));
    }
    @PostMapping(BlogUrl.Follow.IS_FOLLOW)
    @Operation(summary = "是否关注")
    public Result<Boolean> isFollow(@RequestBody FollowReq followReq,CurrentUser user){
        followReq.setUserId(user.getId());
        return Result.success(followService.isFollow(followReq));
    }
}
