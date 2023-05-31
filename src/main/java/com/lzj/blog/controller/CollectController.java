package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.CollectDTO;
import com.lzj.blog.model.vo.CollectVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.CollectService;
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
 * @Date: 2023/3/23 9:55
 * @Description:
 */
@Slf4j
@RestController
@Tag(name = "收藏接口")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping(BlogUrl.Collect.ADD_COLLECT)
    @Operation(summary = "收藏")
    public Result<String> add(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        collectService.addCollect(collectDTO);
        return Result.success("收藏成功");
    }

    @PostMapping(BlogUrl.Collect.CANCEL_COLLECT)
    @Operation(summary = "取消收藏")
    public Result<String> cancel(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        collectService.cancelCollect(collectDTO);
        return Result.success("取消收藏");
    }
    @PostMapping(BlogUrl.Collect.GET_COLLECT_MESSAGE)
    @Operation(summary = "收藏信息")
    public Result<List<CollectVO>> likeNumber(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        return Result.success(collectService.collectMessage(collectDTO));
    }

    @PostMapping(BlogUrl.Collect.ADD_COLLECT_REDIS)
    @Operation(summary = "收藏Redis")
    public Result<String> addRedis(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        collectService.addCollectRedis(collectDTO);
        return Result.success("收藏成功");
    }

    @PostMapping(BlogUrl.Collect.CANCEL_COLLECT_REDIS)
    @Operation(summary = "取消收藏Redis")
    public Result<String> cancelRedis(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        collectService.cancelCollectRedis(collectDTO);
        return Result.success("取消收藏");
    }
    @PostMapping(BlogUrl.Collect.GET_COLLECT_MESSAGE_REDIS)
    @Operation(summary = "收藏信息Redis")
    public Result<List<CollectVO>> likeNumberRedis(@RequestBody CollectDTO collectDTO, CurrentUser currentUser){
        collectDTO.setUserId(currentUser.getId());
        return Result.success(collectService.collectMessageRedis(collectDTO));
    }
}
