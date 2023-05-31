package com.lzj.blog.controller;

import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.vo.AchievementVO;
import com.lzj.blog.model.vo.PersonalVO;
import com.lzj.blog.model.vo.SearchVO;
import com.lzj.blog.model.vo.response.RankResp;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "公共接口")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @GetMapping(BlogUrl.Open.GET_ACHIEVEMENT)
    @Operation(summary = "获取成就信息")
    public Result<AchievementVO> achievement(@PathVariable("userId") String userId){
        return Result.success(commonService.getAchievement(userId));
    }

    @GetMapping(BlogUrl.Open.GET_PERSONAL)
    @Operation(summary = "获取成就信息")
    public Result<PersonalVO> personal(@PathVariable("userId") String userId){
        return Result.success(commonService.getPersonal(userId));
    }

    @GetMapping(BlogUrl.Open.SEARCH_ALL)
    @Operation(summary = "全搜索")
    public Result<List<SearchVO>> search(@PathVariable("searchKey") String searchKey){
        return Result.success(commonService.search(searchKey));
    }

    @GetMapping(BlogUrl.Open.RANK)
    @Operation(summary = "排行榜")
    public Result<RankResp> rank(){
        return Result.success(commonService.rankList());
    }
}
