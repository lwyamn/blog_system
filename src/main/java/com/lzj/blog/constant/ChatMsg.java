package com.lzj.blog.constant;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 9:12
 */
public interface ChatMsg {
    interface ChatType{
        /**
         * 单聊
         */
        String SINGLE_CHAT = "SINGLE";
        /**
         * 群聊
         */
        String GROUP_CHAT = "GROUP";
        /**
         * 通知
         */
        String MESSAGE_CHAT = "MESSAGE";
    }
}
