package com.lzj.blog.converter;

import com.lzj.blog.entity.Blog;
import com.lzj.blog.model.vo.BlogHomeVO;
import com.lzj.blog.model.vo.BlogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogConverter {
    BlogConverter INSTANCE = Mappers.getMapper(BlogConverter.class);
    BlogVO blogTOBlogVO(Blog blog);
    List<BlogVO> blogListTOBlogVOList(List<Blog> blogs);
    BlogHomeVO blogTOBlogHomeVO(Blog blog);
    List<BlogHomeVO> blogListTOBlogHomeVOList(List<Blog> blogs);
}
