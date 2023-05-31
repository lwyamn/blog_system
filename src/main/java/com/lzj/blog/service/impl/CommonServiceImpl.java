package com.lzj.blog.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.lzj.blog.constant.CommonMsg;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.entity.Blog;
import com.lzj.blog.entity.User;
import com.lzj.blog.mapper.BlogMapper;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.model.vo.*;
import com.lzj.blog.model.vo.response.RankResp;
import com.lzj.blog.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public AchievementVO getAchievement(String userId) {
        AchievementVO achievementVO = new AchievementVO();
        Object collectNumber = redisTemplate.opsForValue().get(userId + RedisType.ALL_COLLECT);
        achievementVO.setCollectNumber(Objects.isNull(collectNumber) ? 0 : (Integer) collectNumber);
        Object likeNumber = redisTemplate.opsForValue().get(userId + RedisType.ALL_LIKE);
        achievementVO.setLikeNumber(Objects.isNull(likeNumber) ? 0 : (Integer) likeNumber);
        return achievementVO;
    }

    @Override
    public PersonalVO getPersonal(String userId) {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getUserId, userId);
        Long blogNumber = blogMapper.selectCount(queryWrapper);
        Long fansNumber = redisTemplate.opsForSet().size(userId + RedisType.FANS);
        PersonalVO personalVO = new PersonalVO();
        personalVO.setBlogNumber(blogNumber);
        personalVO.setFansNumber(fansNumber);
        return personalVO;
    }

    @Override
    public List<SearchVO> search(String searchKey) {
        List<SearchVO> searchResult = new ArrayList<>();
        List<SearchVO> users = searchUser(searchKey);
        List<SearchVO> blogs = searchBlog(searchKey);
        searchResult.addAll(users);
        searchResult.addAll(blogs);
        return searchResult;
    }

    @Override
    public RankResp rankList() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        RankResp rankResp = new RankResp();
        rankResp.setCollectRank(getCollectRankList(currentDate));
        rankResp.setFanRank(getFanRankList(currentDate));
        rankResp.setLikeRank(getLikeRankList(currentDate));
        return rankResp;
    }

    private List<SearchVO> searchUser(String searchKey) {
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.like(User::getPhone, searchKey)
                .or().like(User::getUsername, searchKey)
                .or().like(User::getEmail, searchKey);
        List<User> users = userMapper.selectList(userWrapper);
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }
        return users.stream().map(user -> {
            SearchVO searchVO = new SearchVO();
            searchVO.setId(user.getId());
            searchVO.setName(user.getUsername());
            searchVO.setType(CommonMsg.SearchType.USER);
            return searchVO;
        }).toList();
    }

    private List<SearchVO> searchBlog(String searchKey) {
        LambdaQueryWrapper<Blog> blogWrapper = new LambdaQueryWrapper<>();
        blogWrapper.like(Blog::getAuthor, searchKey)
                .or().like(Blog::getTitle, searchKey);
        List<Blog> blogs = blogMapper.selectList(blogWrapper);
        if (CollectionUtils.isEmpty(blogs)) {
            return Lists.newArrayList();
        }
        return blogs.stream().map(blog -> {
            SearchVO searchVO = new SearchVO();
            searchVO.setId(blog.getId());
            searchVO.setName(blog.getTitle());
            searchVO.setType(CommonMsg.SearchType.BLOG);
            return searchVO;
        }).toList();
    }

    private List<RankVO> getFanRankList(String currentDate) {
        String key = String.format(RedisType.FANS_RANK, currentDate);
        Set<ZSetOperations.TypedTuple<Object>> fanRank = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 9);
        JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(fanRank));
        return jsonArray.stream().map(rank -> {
            JSONObject o = JSONObject.parseObject(rank.toString());
            RankVO rankVO = new RankVO();
            rankVO.setUsername(o.getString("value"));
            rankVO.setRankNumber(o.getLongValue("score"));
            return rankVO;
        }).toList();
    }

    private List<RankVO> getCollectRankList(String currentDate) {
        String key = String.format(RedisType.COLLECTS_RANK, currentDate);
        Set<ZSetOperations.TypedTuple<Object>> collectRank = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 9);
        JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(collectRank));
        return jsonArray.stream().map(rank -> {
            JSONObject o = JSONObject.parseObject(rank.toString());
            RankVO rankVO = new RankVO();
            rankVO.setUsername(o.getString("value"));
            rankVO.setRankNumber(o.getLongValue("score"));
            return rankVO;
        }).toList();
    }

    private List<RankVO> getLikeRankList(String currentDate) {
        String key = String.format(RedisType.LIKES_RANK, currentDate);
        Set<ZSetOperations.TypedTuple<Object>> likeRank = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 9);
        JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(likeRank));
        return jsonArray.stream().map(rank -> {
            JSONObject o = JSONObject.parseObject(rank.toString());
            RankVO rankVO = new RankVO();
            rankVO.setUsername(o.getString("value"));
            rankVO.setRankNumber(o.getLongValue("score"));
            return rankVO;
        }).toList();
    }
}
