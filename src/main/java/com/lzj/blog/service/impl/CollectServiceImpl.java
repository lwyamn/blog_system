package com.lzj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.entity.Collect;
import com.lzj.blog.mapper.CollectMapper;
import com.lzj.blog.model.dto.CollectDTO;
import com.lzj.blog.model.vo.CollectVO;
import com.lzj.blog.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: linzj
 * @Date: 2023/3/23 9:28
 * @Description:
 */
@Slf4j
@Service
@Deprecated
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean addCollect(CollectDTO collectDTO) {
        Collect collect = new Collect();
        collect.setUserId(collectDTO.getUserId());
        collect.setBlogId(collectDTO.getBlogId());
        this.baseMapper.insert(collect);
        return true;
    }

    @Override
    public Boolean cancelCollect(CollectDTO collectDTO) {
        LambdaQueryWrapper<Collect> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(Collect::getBlogId,collectDTO.getBlogId())
                .eq(Collect::getUserId,collectDTO.getUserId());
        this.baseMapper.delete(deleteWrapper);
        return true;
    }

    @Override
    public List<CollectVO> collectMessage(CollectDTO collectDTO) {
        List<CollectVO> collectVOS = new ArrayList<>();
        if(CollectionUtils.isEmpty(collectDTO.getBlogIds())){
            collectVOS.add(getCollectMessage(collectDTO.getBlogId(), collectDTO.getUserId()));
        }else {
            collectVOS = collectDTO.getBlogIds().stream().map(id -> getCollectMessage(id, collectDTO.getUserId())).toList();
        }
        return collectVOS;
    }

    @Override
    public Boolean addCollectRedis(CollectDTO collectDTO) {
        redisTemplate.opsForSet().add(collectDTO.getUserId()+ RedisType.COLLECT, collectDTO.getBlogId());
        redisTemplate.opsForSet().add(collectDTO.getBlogId()+RedisType.COLLECT, collectDTO.getUserId());
        redisTemplate.opsForValue().increment(collectDTO.getAuthor()+RedisType.ALL_COLLECT,1);
        return true;
    }

    @Override
    public Boolean cancelCollectRedis(CollectDTO collectDTO) {
        redisTemplate.opsForSet().remove(collectDTO.getUserId()+RedisType.COLLECT, collectDTO.getBlogId());
        redisTemplate.opsForSet().remove(collectDTO.getBlogId()+RedisType.COLLECT, collectDTO.getUserId());
        redisTemplate.opsForValue().decrement(collectDTO.getAuthor()+RedisType.ALL_COLLECT,1);
        return true;
    }

    @Override
    public List<CollectVO> collectMessageRedis(CollectDTO collectDTO) {
        List<CollectVO> collectVOS = new ArrayList<>();
        if(CollectionUtils.isEmpty(collectDTO.getBlogIds())){
            collectVOS.add(getCollectMessageByRedis(collectDTO.getBlogId(), collectDTO.getUserId()));
        }else {
            collectVOS = collectDTO.getBlogIds().stream().map(id -> getCollectMessageByRedis(id, collectDTO.getUserId())).toList();
        }
        return collectVOS;
    }

    private CollectVO getCollectMessage(String blogId,String userId){
        LambdaQueryWrapper<Collect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collect::getBlogId,blogId);
        List<Collect> collects = this.baseMapper.selectList(queryWrapper);
        CollectVO collectVO = new CollectVO();
        collectVO.setBlogId(blogId);
        collectVO.setCollectNumber((long) collects.size());
        collectVO.setIsCollect(collects.stream().map(Collect::getUserId).anyMatch(id-> Objects.equals(userId,userId)));
        return collectVO;
    }

    private CollectVO getCollectMessageByRedis(String blogId,String userId){
        CollectVO collectVO = new CollectVO();
        collectVO.setCollectNumber(redisTemplate.opsForSet().size(blogId+RedisType.COLLECT));
        collectVO.setIsCollect(redisTemplate.opsForSet().isMember(blogId+RedisType.COLLECT,userId));
        collectVO.setBlogId(blogId);
        return collectVO;
    }
}
