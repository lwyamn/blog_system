package com.lzj.blog.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.entity.Blog;
import com.lzj.blog.entity.User;
import com.lzj.blog.mapper.BlogMapper;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: linzj
 * @Date: 2023/3/23 14:39
 * @Description:
 */
@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class ScheduleTask {
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void asyncCollectAndLike() {
        log.info("开始执行点赞博客收藏定时任务");
        SetOperations<String, Object> operations = redisTemplate.opsForSet();
        List<Blog> blogs = blogMapper.selectList(null);
        blogs.forEach(blog -> {
            blog.setCollectNumber(operations.size(blog.getId() + "collect").intValue());
            blog.setLikeNumber(operations.size(blog.getId() + "like").intValue());
        });
        blogService.updateBatchById(blogs);
        log.info("执行点赞博客收藏定时任务结束");
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void asyncRankList() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getId);
        List<String> userIds = userMapper.selectList(userLambdaQueryWrapper).stream().map(user -> user.getId()).toList();
        fanRank(userIds);
        likeRank(userIds);
        collectRank(userIds);
    }

    private void fanRank(List<String> userIds) {
        log.info("开始执行粉丝排行定时任务");
        SetOperations<String, Object> fan = redisTemplate.opsForSet();
        ZSetOperations<String, Object> fanRank = redisTemplate.opsForZSet();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        String key = String.format(RedisType.FANS_RANK, currentDate);
        userIds.forEach(userId -> {
            Long size = fan.size(userId + RedisType.FANS);
            if (Objects.isNull(size)) {
                fanRank.incrementScore(key, userId, 0);
                return;
            }
            fanRank.incrementScore(key, userId, size);
        });
        log.info("结束执行粉丝排行定时任务");
    }

    private void likeRank(List<String> userIds) {
        log.info("开始执行点赞排行定时任务");
        ValueOperations<String, Object> like = redisTemplate.opsForValue();
        ZSetOperations<String, Object> likeRank = redisTemplate.opsForZSet();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        String key = String.format(RedisType.LIKES_RANK, currentDate);
        userIds.forEach(userId -> {
            Object size = like.get(userId + RedisType.ALL_LIKE);
            if (Objects.isNull(size)) {
                likeRank.incrementScore(key, userId, 0);
                return;
            }
            System.out.println(userId+" "+Double.parseDouble(size.toString()));
            likeRank.incrementScore(key, userId, Double.parseDouble(size.toString()));
        });
        log.info("结束执行点赞排行定时任务");
    }

    private void collectRank(List<String> userIds) {
        log.info("开始执行收藏排行定时任务");
        ValueOperations<String, Object> collect = redisTemplate.opsForValue();
        ZSetOperations<String, Object> collectRank = redisTemplate.opsForZSet();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());
        String key = String.format(RedisType.COLLECTS_RANK, currentDate);
        userIds.forEach(userId -> {
            Object size = collect.get(userId + RedisType.ALL_COLLECT);
            if (Objects.isNull(size)) {
                collectRank.incrementScore(key, userId, 0);
                return;
            }
            System.out.println(userId+" "+Double.parseDouble(size.toString()));
            collectRank.incrementScore(key, userId, Double.parseDouble(size.toString()));
        });
        log.info("结束执行收藏排行定时任务");
    }


}
