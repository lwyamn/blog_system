package com.lzj.blog.service.impl;

import com.google.common.collect.Lists;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.converter.UserConverter;
import com.lzj.blog.entity.User;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.model.dto.request.FollowReq;
import com.lzj.blog.model.vo.FollowVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void add(FollowReq followReq) {
        redisTemplate.opsForSet().add(followReq.getUserId()+ RedisType.FOLLOW, followReq.getFollowUserId());
        redisTemplate.opsForSet().add(followReq.getFollowUserId()+ RedisType.FANS, followReq.getUserId());
    }

    @Override
    public void cancel(FollowReq followReq) {
        redisTemplate.opsForSet().remove(followReq.getUserId()+ RedisType.FOLLOW, followReq.getFollowUserId());
        redisTemplate.opsForSet().remove(followReq.getFollowUserId()+ RedisType.FANS, followReq.getUserId());
    }

    @Override
    public List<UserVO> list(String userId) {
        Set<String> followUsers = redisTemplate.opsForSet().members(userId+RedisType.FOLLOW);
        if(CollectionUtils.isEmpty(followUsers)){
            return Lists.newArrayList();
        }
        List<User> users = userMapper.selectBatchIds(followUsers);
        return UserConverter.INSTANCE.userListTOUserVOList(users);
    }

    @Override
    public FollowVO fansList(String userId) {
        SetOperations<String, String> follow = redisTemplate.opsForSet();
        Set<String> fansId = follow.members(userId+RedisType.FANS);
        if(CollectionUtils.isEmpty(fansId)){
            return new FollowVO();
        }
        Set<String> followId = follow.members(userId+RedisType.FOLLOW);
        FollowVO followVO = new FollowVO();
        List<User> users = userMapper.selectBatchIds(fansId);
        followVO.setUserList(UserConverter.INSTANCE.userListTOUserVOList(users));
        followVO.setFollowIdList(followId);
        return followVO;
    }

    @Override
    public boolean isFollow(FollowReq followReq) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(followReq.getUserId() + RedisType.FOLLOW, followReq.getFollowUserId()));
    }
}
