package com.lzj.blog.constant;

public interface RedisType {
    String COLLECT = "collect";
    String CHAT_LIST = "chatList";
    String LIKE = "like";
    String FOLLOW = "follow";
    String FANS = "fans";
    String ALL_LIKE = "all_like";
    String ALL_FANS = "all_fans";
    String ALL_COLLECT = "all_collect";
    String ALL_COMMENT = "all_comment";
    String FANS_RANK = "fans_rank:%s";
    String LIKES_RANK = "likes_rank:%s";
    String COLLECTS_RANK = "collects_rank:%s";
}
