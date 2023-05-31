package com.lzj.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/23 15:06
 * @Description:
 */
public interface CommonMapper<T> extends BaseMapper<T> {
    int updateBatch(@Param("list") List<T> list);
}
