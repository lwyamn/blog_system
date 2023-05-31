package com.lzj.blog.model.vo.response;

import com.lzj.blog.model.vo.RankVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: linzj
 * @see:
 * @since: 2023/5/16 16:48
 */
@Getter
@Setter
@ToString
public class RankResp {
    private List<RankVO> likeRank;
    private List<RankVO> fanRank;
    private List<RankVO> collectRank;
}
