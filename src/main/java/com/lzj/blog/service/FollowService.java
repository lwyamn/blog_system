package com.lzj.blog.service;

import com.lzj.blog.model.dto.request.FollowReq;
import com.lzj.blog.model.vo.FollowVO;
import com.lzj.blog.model.vo.UserVO;

import java.util.List;

public interface FollowService {
    void add(FollowReq followDTO);
    void cancel(FollowReq followReq);
    List<UserVO> list(String userId);
    FollowVO fansList(String userId);
    boolean isFollow(FollowReq followReq);
}
