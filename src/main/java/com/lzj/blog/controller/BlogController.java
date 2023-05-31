package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.AddBlogDTO;
import com.lzj.blog.model.dto.DeleteBlogDTO;
import com.lzj.blog.model.dto.QueryBlogDTO;
import com.lzj.blog.model.dto.UpdateBlogDTO;
import com.lzj.blog.model.vo.BlogHomeVO;
import com.lzj.blog.model.vo.BlogVO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "博客接口")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping(BlogUrl.Blog.ADD_BLOG)
    @Operation(summary = "添加博客")
    public Result<String> add(@RequestBody AddBlogDTO addBlogDTO, CurrentUser user){
        addBlogDTO.setAuthor(user.getName());
        addBlogDTO.setUserId(user.getId());
        blogService.add(addBlogDTO);
        return Result.success("添加成功");
    }
    @PostMapping(BlogUrl.Blog.DELETE_BLOG)
    @Operation(summary = "删除博客")
    public Result<String> delete( @RequestBody DeleteBlogDTO deleteBlogDTO, CurrentUser currentUser){
        blogService.delete(deleteBlogDTO);
        return Result.success("删除成功");
    }
    @PostMapping(BlogUrl.Blog.UPDATE_BLOG)
    @Operation(summary = "更新博客")
    public Result<String> update(@RequestBody UpdateBlogDTO updateBlogDTO,CurrentUser currentUser){
        blogService.update(updateBlogDTO);
        return Result.success("更新成功");
    }
    @PostMapping(BlogUrl.Blog.QUERY_BLOG)
    @Operation(summary = "查找博客")
    public Result<PageVO<BlogVO>> query(@RequestBody QueryBlogDTO queryBlogDTO,CurrentUser currentUser){
        return Result.success(blogService.query(queryBlogDTO));
    }

    @GetMapping(BlogUrl.Blog.BLOG_DETAIL)
    @Operation(summary = "博客详情")
    public Result<BlogVO> detail(@PathVariable String id){
        return Result.success(blogService.blogDetail(id));
    }

    @PostMapping(BlogUrl.Blog.QUERY_HOME_BLOG)
    @Operation(summary = "首页博客")
    public Result<PageVO<BlogHomeVO>> home(@RequestBody QueryBlogDTO queryBlogDTO, CurrentUser currentUser){
        return Result.success(blogService.home(queryBlogDTO));
    }

    @PostMapping(BlogUrl.Blog.QUERY_BLOG_ATTENTION)
    @Operation(summary = "关注用户的博客")
    public Result<PageVO<BlogHomeVO>> attention(@RequestBody QueryBlogDTO queryBlogDTO,CurrentUser user){
        queryBlogDTO.setUserId(user.getId());
        return Result.success(blogService.queryAttention(queryBlogDTO));
    }

    @GetMapping(BlogUrl.Blog.BLOG_COLLECT)
    @Operation(summary = "收藏博客")
    public Result<List<BlogVO>> collect(@PathVariable String id){
        return Result.success(blogService.collect(id));
    }

}
