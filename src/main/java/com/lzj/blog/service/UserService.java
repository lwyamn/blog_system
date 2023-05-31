package com.lzj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.entity.User;
import com.lzj.blog.model.dto.AddChatUserDTO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.model.dto.DeleteUserDTO;
import com.lzj.blog.model.dto.QueryUsersDTO;
import com.lzj.blog.model.dto.AddUserDTO;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.service
 * @Date : 2023/2/17 11:02
 * @Author : linzj
 * @Description :
 */

public interface UserService extends IService<User> {


    void add(AddUserDTO user);

    void delete(DeleteUserDTO deleteUserDTO);

    void update(User user);

    PageVO<UserVO> query(QueryUsersDTO queryUsersDTO);

    UserVO getUser(String userId);

    UserVO getUserById(String userId);

    List<UserVO> getUserChatList(String userId);

    void addUserChatList(AddChatUserDTO chatUserDTO);
}
