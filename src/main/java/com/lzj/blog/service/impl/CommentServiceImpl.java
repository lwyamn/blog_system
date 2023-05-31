package com.lzj.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.blog.converter.CommentConverter;
import com.lzj.blog.entity.Comment;
import com.lzj.blog.mapper.CommentMapper;
import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.model.vo.PageVO;
import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.service.CommentService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.service.impl
 * @Date : 2023/2/24 10:12
 * @Author : linzj
 * @Description :
 */
@Slf4j
@Service
@Deprecated
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Boolean add(AddCommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setBlogId(commentDTO.getBlogId());
        comment.setType(commentDTO.getType());
        comment.setContent(commentDTO.getContent());
        comment.setUserId(commentDTO.getUserId());
        comment.setAvatar(commentDTO.getAvatar());
        comment.setUsername(commentDTO.getUsername());
        this.baseMapper.insert(comment);
        return true;
    }

    @Override
    public Boolean delete(DeleteCommentDTO commentDTO) {
        LambdaQueryWrapper<Comment> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.in(!CollectionUtils.isEmpty(commentDTO.getIdList()), Comment::getId, commentDTO.getIdList())
                .or(CollectionUtils.isEmpty(commentDTO.getIdList()), dw -> dw.eq(StringUtils.isNotBlank(commentDTO.getBlogId()), Comment::getBlogId, commentDTO.getBlogId())
                        .eq(StringUtils.isNotBlank(commentDTO.getUserId()), Comment::getUserId, commentDTO.getUserId()));
        this.baseMapper.delete(deleteWrapper);
        return true;
    }

    @Override
    public Boolean update(UpdateCommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setId(commentDTO.getId());
        comment.setType(commentDTO.getType());
        this.baseMapper.updateById(comment);
        return true;
    }

    @Override
    public PageVO<CommentVO> View(ViewCommentDTO commentDTO) {
        //分页数据
        Page<Comment> page = Page.of(commentDTO.getCurrentPage(), commentDTO.getPageSize());
        //查询
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(commentDTO.getId()), Comment::getId, commentDTO.getId())
                .eq(StringUtils.isNotBlank(commentDTO.getUserId()), Comment::getUserId, commentDTO.getUserId())
                .eq(StringUtils.isNotBlank(commentDTO.getType()),Comment::getType,commentDTO.getType())
                .eq(StringUtils.isNotBlank(commentDTO.getBlogId()), Comment::getBlogId, commentDTO.getBlogId())
                .orderByDesc(Comment::getCreateTime);
        Page<Comment> data = this.baseMapper.selectPage(page, queryWrapper);
        //转化出数据
        PageVO<CommentVO> commentVOPageVO = new PageVO<>();
        commentVOPageVO.setDataVO(CommentConverter.INSTANCE.commentListTOCommentVOList(data.getRecords()));
        commentVOPageVO.setTotal(data.getTotal());
        return commentVOPageVO;
    }
}
