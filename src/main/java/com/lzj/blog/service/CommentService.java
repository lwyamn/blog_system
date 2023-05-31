package com.lzj.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.blog.entity.Comment;
import com.lzj.blog.model.dto.AddCommentDTO;
import com.lzj.blog.model.dto.DeleteCommentDTO;
import com.lzj.blog.model.dto.UpdateCommentDTO;
import com.lzj.blog.model.dto.ViewCommentDTO;
import com.lzj.blog.model.vo.CommentVO;
import com.lzj.blog.model.vo.PageVO;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.service
 * @Date : 2023/2/24 10:12
 * @Author : linzj
 * @Description :
 */
@Deprecated
public interface CommentService extends IService<Comment> {
    Boolean add(AddCommentDTO commentDTO);

    Boolean delete(DeleteCommentDTO commentDTO);

    Boolean update(UpdateCommentDTO commentDTO);

    PageVO<CommentVO> View(ViewCommentDTO commentDTO);
}
