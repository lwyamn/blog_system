package com.lzj.blog.service.impl;

import com.lzj.blog.constant.RedisType;
import com.lzj.blog.model.dto.LikeDTO;
import com.lzj.blog.model.vo.LikeVO;
import com.lzj.blog.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/21 16:05
 * @Description:
 */
@Slf4j
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean addLike(LikeDTO likeDTO) {
        redisTemplate.opsForSet().add(likeDTO.getBlogId()+ RedisType.LIKE,likeDTO.getUserId());
        redisTemplate.opsForSet().add(likeDTO.getUserId()+RedisType.LIKE,likeDTO.getBlogId());
        redisTemplate.opsForValue().increment(likeDTO.getAuthor()+RedisType.ALL_LIKE,1);
        return true;
    }

    @Override
    public boolean cancelLike(LikeDTO likeDTO) {
        redisTemplate.opsForSet().remove(likeDTO.getBlogId()+RedisType.LIKE,likeDTO.getUserId());
        redisTemplate.opsForSet().remove(likeDTO.getUserId()+RedisType.LIKE,likeDTO.getBlogId());
        redisTemplate.opsForValue().decrement(likeDTO.getAuthor()+RedisType.ALL_LIKE,1);
        return true;
    }

    @Override
    public List<LikeVO> LikeMessage(LikeDTO likeDTO) {
        if(CollectionUtils.isEmpty(likeDTO.getBlogIds())){
            List<LikeVO> likeVOS = new ArrayList<>();
            likeVOS.add(getMessage(likeDTO.getUserId(), likeDTO.getBlogId()));
            return likeVOS;
        }
        return likeDTO.getBlogIds().stream().map(s -> getMessage(likeDTO.getUserId(), s)).toList();
    }

    private LikeVO getMessage(String userId,String blogId){
        LikeVO likeVO = new LikeVO();
        likeVO.setBlogId(blogId);
        SetOperations<String, Object> blogSet = redisTemplate.opsForSet();
        likeVO.setLikeNumber(blogSet.size(blogId+RedisType.LIKE));
        likeVO.setIsLike(blogSet.isMember(blogId+RedisType.LIKE,userId));
        return likeVO;
    }

}
