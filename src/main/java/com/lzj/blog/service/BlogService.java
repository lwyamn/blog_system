package com.lzj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.blog.entity.Blog;
import com.lzj.blog.model.dto.AddBlogDTO;
import com.lzj.blog.model.dto.DeleteBlogDTO;
import com.lzj.blog.model.dto.QueryBlogDTO;
import com.lzj.blog.model.dto.UpdateBlogDTO;
import com.lzj.blog.model.vo.BlogHomeVO;
import com.lzj.blog.model.vo.BlogVO;
import com.lzj.blog.model.vo.PageVO;

import java.util.List;


public interface BlogService extends IService<Blog> {
    /**
     * 添加博客
     * @param blogDTO
     * @return
     */
    boolean add(AddBlogDTO blogDTO);
    boolean delete(DeleteBlogDTO blogDTO);
    boolean update(UpdateBlogDTO blogDTO);
    PageVO<BlogVO> query(QueryBlogDTO blogDTO);
    PageVO<BlogHomeVO> home(QueryBlogDTO blogDTO);
    BlogVO blogDetail(String blogId);

    /**
     * 获取收藏
     * @param userId
     * @return
     */
    List<BlogVO> collect(String userId);
    PageVO<BlogHomeVO> queryAttention(QueryBlogDTO blogDTO);
}
