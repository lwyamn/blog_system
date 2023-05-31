package com.lzj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.lzj.blog.constant.RedisType;
import com.lzj.blog.converter.BlogConverter;
import com.lzj.blog.entity.Blog;
import com.lzj.blog.entity.User;
import com.lzj.blog.mapper.BlogMapper;
import com.lzj.blog.mapper.UserMapper;
import com.lzj.blog.model.dto.AddBlogDTO;
import com.lzj.blog.model.dto.DeleteBlogDTO;
import com.lzj.blog.model.dto.QueryBlogDTO;
import com.lzj.blog.model.dto.UpdateBlogDTO;
import com.lzj.blog.model.vo.BlogHomeVO;
import com.lzj.blog.model.vo.BlogVO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public boolean add(AddBlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setDescription(blogDTO.getDescription());
        blog.setCover(blogDTO.getCover());
        blog.setAuthor(blogDTO.getAuthor());
        blog.setContent(blogDTO.getContent());
        blog.setTitle(blogDTO.getTitle());
        blog.setUserId(blogDTO.getUserId());
        blog.setLikeNumber(0);
        this.baseMapper.insert(blog);
        return true;
    }

    @Override
    public boolean delete(DeleteBlogDTO blogDTO) {
        LambdaQueryWrapper<Blog> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.in(!CollectionUtils.isEmpty(blogDTO.getBlogIdLists()),Blog::getId,blogDTO.getBlogIdLists())
                .or(CollectionUtils.isEmpty(blogDTO.getBlogIdLists()),dw->dw.eq(StringUtils.isNotBlank(blogDTO.getBlogId()),Blog::getId,blogDTO.getBlogId())
                        .eq(StringUtils.isNotBlank(blogDTO.getUserId()),Blog::getUserId,blogDTO.getUserId()));
        this.baseMapper.delete(deleteWrapper);
        return true;
    }

    @Override
    public boolean update(UpdateBlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setCover(blogDTO.getCover());
        blog.setDescription(blogDTO.getDescription());
        blog.setId(blogDTO.getBlogId());
        blog.setAuthor(blogDTO.getAuthor());
        blog.setContent(blogDTO.getContent());
        blog.setLikeNumber(blogDTO.getLikeNumber());
        blog.setTitle(blogDTO.getTitle());
        this.baseMapper.updateById(blog);
        return true;
    }

    @Override
    public PageVO<BlogVO> query(QueryBlogDTO blogDTO) {
        //分页数据
        Page<Blog> data = blogData(blogDTO);
        PageVO<BlogVO> blogVOPageVO = new PageVO<>();
        blogVOPageVO.setDataVO(BlogConverter.INSTANCE.blogListTOBlogVOList(data.getRecords()));
        blogVOPageVO.setTotal(data.getTotal());
        return blogVOPageVO;
    }

    @Override
    public PageVO<BlogHomeVO> home(QueryBlogDTO blogDTO) {
        Page<Blog> data = blogData(blogDTO);
        return blogHome(data);
    }

    @Override
    public BlogVO blogDetail(String blogId) {
        return BlogConverter.INSTANCE.blogTOBlogVO(this.baseMapper.selectById(blogId));
    }

    @Override
    public List<BlogVO> collect(String userId) {
        List<String> collectList = getCollectList(userId);
        if (collectList.isEmpty()){
            return new ArrayList<>();
        }
        List<Blog> blogs = this.baseMapper.selectBatchIds(collectList);
        return BlogConverter.INSTANCE.blogListTOBlogVOList(blogs);
    }

    @Override
    public PageVO<BlogHomeVO> queryAttention(QueryBlogDTO blogDTO) {
        PageVO<BlogHomeVO> blogVOPageVO = new PageVO<>();
        Set<String> follows = redisTemplate.opsForSet().members(blogDTO.getUserId() + RedisType.FOLLOW);
        if(CollectionUtils.isEmpty(follows)){
            blogVOPageVO.setDataVO(Lists.newArrayList());
            blogVOPageVO.setTotal(0L);
            return blogVOPageVO;
        }
        Page<Blog> page = Page.of(blogDTO.getCurrentPage(), blogDTO.getPageSize());
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Blog::getUserId,follows);
        Page<Blog> data = this.baseMapper.selectPage(page, queryWrapper);
        return blogHome(data);
    }

    private PageVO<BlogHomeVO> blogHome(Page<Blog> data){
        PageVO<BlogHomeVO> blogHomeVOPageVO = new PageVO<>();
        List<BlogHomeVO> blogVOS = BlogConverter.INSTANCE.blogListTOBlogHomeVOList(data.getRecords());
        if(CollectionUtils.isEmpty(blogVOS)){
            blogHomeVOPageVO.setDataVO(Lists.newArrayList());
            blogHomeVOPageVO.setTotal(0L);
            return blogHomeVOPageVO;
        }
        List<String> userId = data.getRecords().stream().map(Blog::getUserId).toList();
        List<User> users = userMapper.selectBatchIds(userId);
        blogVOS.forEach(blogHomeVO -> users.forEach(user -> {
            if (Objects.equals(user.getId(),blogHomeVO.getUserId())){
                blogHomeVO.setUserAvatar(user.getAvatar());
            }
        }));
        blogHomeVOPageVO.setDataVO(blogVOS);
        blogHomeVOPageVO.setTotal(data.getTotal());
        return blogHomeVOPageVO;
    }

    private Page<Blog> blogData(QueryBlogDTO blogDTO){
        Page<Blog> page = Page.of(blogDTO.getCurrentPage(), blogDTO.getPageSize());
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(blogDTO.getBlogId()), Blog::getId, blogDTO.getBlogId())
                .eq(StringUtils.isNotBlank(blogDTO.getUserId()), Blog::getUserId, blogDTO.getUserId())
                .likeRight(StringUtils.isNotEmpty(blogDTO.getTitle()), Blog::getTitle, blogDTO.getTitle())
                .likeRight(StringUtils.isNotEmpty(blogDTO.getAuthor()), Blog::getAuthor, blogDTO.getAuthor())
                .orderByDesc(Blog::getCreateTime);
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    private List<String> getCollectList(String userId){
        Set<String> collects = redisTemplate.opsForSet().members(userId + RedisType.COLLECT);
        if(CollectionUtils.isEmpty(collects)){
            return new ArrayList<>();
        }
        return new ArrayList<>(collects);
    }
}
