package com.lzj.blog.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.constant.UserMsg;
import com.lzj.blog.entity.User;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.model.dto.DeleteUserDTO;
import com.lzj.blog.model.dto.QueryUsersDTO;
import com.lzj.blog.model.dto.AddUserDTO;
import com.lzj.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.controller
 * @Date : 2023/2/17 11:34
 * @Author : linzj
 * @Description :
 */
@Slf4j
@RestController
@Tag(name = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(BlogUrl.Open.SIGN_UP_USER)
    @Operation(summary = "注册用户")
    public Result<String> signUp(@RequestBody AddUserDTO addUserDTO){
        addUserDTO.setUserType(UserMsg.UserType.BLOG_COMMON_USER);
        userService.add(addUserDTO);
        return Result.success("注册成功");
    }
    @PostMapping(BlogUrl.User.ADD_USER)
    @Operation(summary = "添加用户")
    public Result<String> add(@RequestBody AddUserDTO addUserDTO){
        userService.add(addUserDTO);
        return Result.success("添加成功");
    }

    @PostMapping(BlogUrl.User.DELETE_USER)
    @Operation(summary = "删除用户")
    public Result<String> delete(@RequestBody DeleteUserDTO deleteUserDTO,CurrentUser currentUser){
        userService.delete(deleteUserDTO);
        return Result.success("删除成功");
    }

    @PostMapping(BlogUrl.User.UPDATE_USER)
    @Operation(summary = "更新用户")
    public Result<String> update(@RequestBody User user,CurrentUser currentUser){
        userService.update(user);
        return Result.success("更新成功");
    }

    @PostMapping(BlogUrl.User.QUERY_USERS)
    @Operation(summary = "查找用户")
    public Result<PageVO<UserVO>> query(@RequestBody QueryUsersDTO queryUsersDTO,CurrentUser currentUser){
        return Result.success(userService.query(queryUsersDTO));
    }
    @GetMapping(BlogUrl.User.GET_LOGIN_USER)
    @Operation(summary = "获取当前用户")
    public Result<UserVO> getUser(CurrentUser currentUser){
        return Result.success(userService.getUser(currentUser.getId()));
    }

    @GetMapping(BlogUrl.User.GET_USER_BY_ID)
    @Operation(summary = "根据id获取用户")
    public Result<UserVO> getUserById(@PathVariable String id){
        return Result.success(userService.getUserById(id));
    }

}
