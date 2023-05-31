package com.lzj.blog.service;

import com.lzj.blog.model.dto.LikeDTO;
import com.lzj.blog.model.vo.LikeVO;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/21 16:05
 * @Description:
 */
public interface LikeService {
    boolean addLike(LikeDTO likeDTO);
    boolean cancelLike(LikeDTO likeDTO);
    List<LikeVO> LikeMessage(LikeDTO likeDTO);

}
