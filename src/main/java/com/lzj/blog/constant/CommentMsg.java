package com.lzj.blog.constant;

/**
 * @Author: linzj
 * @Date: 2023/3/20 14:29
 * @Description:
 */
public interface CommentMsg {
    interface CommentType{
        /**
         * 普通评论
         */
        String GENERAL_COMMENT = "GENERAL";
        /**
         * 管理员评论
         */
        String ADMINISTRATOR_COMMENT = "ADMINISTRATOR";
        /**
         * 回复
         */
        String REPLY_COMMENT = "REPLY";
    }
}
