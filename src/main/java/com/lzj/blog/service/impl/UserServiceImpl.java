package com.lzj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.converter.UserConverter;
import com.lzj.blog.entity.User;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.model.dto.AddChatUserDTO;
import com.lzj.blog.model.dto.AddUserDTO;
import com.lzj.blog.model.dto.DeleteUserDTO;
import com.lzj.blog.model.dto.QueryUsersDTO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.service.impl
 * @Date : 2023/2/17 11:12
 * @Author : linzj
 * @Description :
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public void add(AddUserDTO addUserDTO) {
        if (!checkExists(addUserDTO.getEmail(),addUserDTO.getUsername(),addUserDTO.getPhone())){
            throw new BlogException(BlogErrorCode.USER_IS_EXISTS);
        }
        User user = new User();
        user.setUserType(addUserDTO.getUserType());
        user.setPhone(addUserDTO.getPhone());
        user.setEmail(addUserDTO.getEmail());
        user.setPassword(addUserDTO.getPassword());
        user.setUsername(addUserDTO.getUsername());
        this.baseMapper.insert(user);
    }

    @Override
    public void delete(DeleteUserDTO deleteUserDTO) {
        this.baseMapper.deleteBatchIds(deleteUserDTO.getUserLists());
    }

    @Override
    public void update(User user) {
        this.baseMapper.updateById(user);
    }

    @Override
    public PageVO<UserVO> query(QueryUsersDTO queryUsersDTO) {
        //分页数据
        Page<User> page = Page.of(queryUsersDTO.getCurrentPage(), queryUsersDTO.getPageSize());
        //查询用户多参数(用户名,邮箱,用户类型,用户id),创建时间降序
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryUsersDTO.getUsername()), User::getUsername, queryUsersDTO.getUsername())
                .likeRight(StringUtils.isNotBlank(queryUsersDTO.getPhone()),User::getPhone,queryUsersDTO.getPhone())
                .likeRight(StringUtils.isNotBlank(queryUsersDTO.getEmail()),User::getEmail, queryUsersDTO.getEmail())
                .in(!CollectionUtils.isEmpty(queryUsersDTO.getUserTypes()),User::getUserType, queryUsersDTO.getUserTypes())
                .likeRight(StringUtils.isNotBlank(queryUsersDTO.getId()),User::getId, queryUsersDTO.getId())
                .orderByDesc(User::getCreateTime);
        Page<User> data = this.baseMapper.selectPage(page,queryWrapper);
        PageVO<UserVO> pageVO = new PageVO<>();
        pageVO.setDataVO(UserConverter.INSTANCE.userListTOUserVOList(data.getRecords()));
        pageVO.setTotal(data.getTotal());
        return pageVO;
    }

    @Override
    public UserVO getUser(String userId) {
        return UserConverter.INSTANCE.userTOUserVO(this.baseMapper.selectById(userId));
    }

    @Override
    public UserVO getUserById(String userId) {
        User user = this.baseMapper.selectById(userId);
        return UserConverter.INSTANCE.userTOUserVO(user);
    }

    @Override
    public List<UserVO> getUserChatList(String userId) {
        Set<String> chatList = redisTemplate.opsForSet().members(userId+RedisType.CHAT_LIST);
        if (CollectionUtils.isEmpty(chatList)){
            return Lists.newArrayList();
        }
        return UserConverter.INSTANCE.userListTOUserVOList(this.baseMapper.selectBatchIds(chatList));
    }

    @Override
    public void addUserChatList(AddChatUserDTO chatUserDTO) {
        redisTemplate.opsForSet().add(chatUserDTO.getUserId()+ RedisType.CHAT_LIST, chatUserDTO.getChatUserId());
    }


    private boolean checkExists(String email,String username,String phone){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail,email).or()
                .eq(User::getPhone,phone).or()
                .eq(User::getUsername,username);
        Long number = this.baseMapper.selectCount(queryWrapper);
        return number==0L;
    }
}
