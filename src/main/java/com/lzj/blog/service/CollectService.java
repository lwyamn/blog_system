package com.lzj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.blog.entity.Collect;
import com.lzj.blog.model.dto.CollectDTO;
import com.lzj.blog.model.vo.CollectVO;

import java.util.List;

/**
 * @Author: linzj
 * @Date: 2023/3/23 9:28
 * @Description:
 */
@Deprecated
public interface CollectService extends IService<Collect> {
    /**
     * 添加收藏
     * @param collectDTO
     * @return
     */
    Boolean addCollect(CollectDTO collectDTO);

    /**
     * 取消收藏
     * @param collectDTO
     * @return
     */
    Boolean cancelCollect(CollectDTO collectDTO);
    /**
     * 收藏信息
     * @param collectDTO
     * @return
     */
    List<CollectVO> collectMessage(CollectDTO collectDTO);
    /**
     * 添加收藏
     * @param collectDTO
     * @return
     */
    Boolean addCollectRedis(CollectDTO collectDTO);

    /**
     * 取消收藏
     * @param collectDTO
     * @return
     */
    Boolean cancelCollectRedis(CollectDTO collectDTO);
    /**
     * 收藏信息
     * @param collectDTO
     * @return
     */
    List<CollectVO> collectMessageRedis(CollectDTO collectDTO);
}
