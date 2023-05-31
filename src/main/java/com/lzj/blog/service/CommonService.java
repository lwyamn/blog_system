package com.lzj.blog.service;

import com.lzj.blog.model.vo.*;
import com.lzj.blog.model.vo.response.RankResp;

import java.util.List;

public interface CommonService {
    AchievementVO getAchievement(String userId);
    PersonalVO getPersonal(String userId);
    List<SearchVO> search(String searchKey);
    RankResp rankList();

}
