package com.lzj.blog.constant;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.constant
 * @Date : 2023/2/17 14:34
 * @Author : linzj
 * @Description :
 */
public interface BlogUrl {
    String BASE_PATH = "/BLOG";
    String BASE_PATH_AUTH = "/blog/**";
    interface Open{
        String COMMON_PATH = BASE_PATH+"/open";
        String SIGN_UP_USER = COMMON_PATH+"/signUp";
        String LOGIN = COMMON_PATH+"/login";
        String GET_ACHIEVEMENT = COMMON_PATH+"/achievement/{userId}";
        String GET_PERSONAL = COMMON_PATH+"/personal/{userId}";
        String SEARCH_ALL = COMMON_PATH+"/search/{searchKey}";
        String RANK = COMMON_PATH+"/rank";

    }

    interface File{
        String COMMON_PATH = BASE_PATH+"/file";
        String UPLOAD_FILE = COMMON_PATH+"/upload";
    }

    interface Like{
        String COMMON_PATH = BASE_PATH+"/like";
        String ADD_LIKE = COMMON_PATH+"/add";
        String CANCEL_LIKE = COMMON_PATH+"/cancel";
        String GET_LIKE_MESSAGE = COMMON_PATH+"/get";
    }

    interface Collect{
        String COMMON_PATH = BASE_PATH+"/collect";
        String ADD_COLLECT = COMMON_PATH+"/add";
        String CANCEL_COLLECT = COMMON_PATH+"/cancel";
        String GET_COLLECT_MESSAGE = COMMON_PATH+"/get";

        String ADD_COLLECT_REDIS = COMMON_PATH+"/addRedis";
        String CANCEL_COLLECT_REDIS = COMMON_PATH+"/cancelRedis";
        String GET_COLLECT_MESSAGE_REDIS = COMMON_PATH+"/getRedis";
    }

    interface Blog{
        String COMMON_PATH = BASE_PATH+"/blog";
        String ADD_BLOG = COMMON_PATH+"/add";
        String DELETE_BLOG = COMMON_PATH+"/delete";
        String UPDATE_BLOG = COMMON_PATH+"/update";
        String QUERY_BLOG = COMMON_PATH+"/query";
        String QUERY_HOME_BLOG = COMMON_PATH+"/home";
        String BLOG_DETAIL = COMMON_PATH+"/detail/{id}";
        String BLOG_COLLECT = COMMON_PATH+"/collect/{id}";
        String QUERY_BLOG_ATTENTION = COMMON_PATH+"/attention";
    }
    interface User{
        String COMMON_PATH = BASE_PATH+"/user";
        String ADD_USER = COMMON_PATH+"/add";
        String GET_LOGIN_USER = COMMON_PATH+"/getUser";
        String DELETE_USER = COMMON_PATH+"/delete";
        String UPDATE_USER = COMMON_PATH+"/update";
        String QUERY_USERS = COMMON_PATH+"/query";
        String GET_USER_BY_ID = COMMON_PATH+"/get/{id}";
    }
    interface Comment{
        String COMMON_PATH = BASE_PATH+"/comment";
        String ADD_COMMENT = COMMON_PATH+"/add";
        String DELETE_COMMENT = COMMON_PATH+"/delete";
        String UPDATE_COMMENT = COMMON_PATH+"/update";
        String VIEW_COMMENT = COMMON_PATH+"/view";
    }

    interface MongoComment{
        String COMMON_PATH = BASE_PATH+"/mongo/comment";
        String ADD_MONGO_COMMENT = COMMON_PATH+"/add";
        String DELETE_MONGO_COMMENT = COMMON_PATH+"/delete";
        String UPDATE_MONGO_COMMENT = COMMON_PATH+"/update";
        String VIEW_MONGO_COMMENT = COMMON_PATH+"/view";
    }

    interface ChatMongo{
        String COMMON_PATH = BASE_PATH+"/mongo/chat";
        String SEND_MONGO_CHAT = COMMON_PATH+"/send";
        String SEND_ALL_MONGO_CHAT = COMMON_PATH+"/sendAll";
        String GET_CHAT_MESSAGE = COMMON_PATH+"/get";
        String ADD_CHAT_LIST = COMMON_PATH+"/addChatList";
        String GET_CHAT_LIST = COMMON_PATH+"/getChatList";
    }

    interface Follow{
        String COMMON_PATH = BASE_PATH+"/follow";
        String ADD_FOLLOW = COMMON_PATH+"/add";
        String CANCEL_FOLLOW = COMMON_PATH+"/cancel";
        String GET_FOLLOW_LIST = COMMON_PATH+"/list";
        String GET_FANS_LIST = COMMON_PATH+"/fansList";
        String IS_FOLLOW = COMMON_PATH+"/isFollow";
    }

}
