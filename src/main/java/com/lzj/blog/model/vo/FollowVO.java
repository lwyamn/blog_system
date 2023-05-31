package com.lzj.blog.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class FollowVO {
    private List<UserVO> userList;
    private Set<String> followIdList;
}
