package com.lzj.blog.constant;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.constant
 * @Date : 2023/2/17 10:51
 * @Author : linzj
 * @Description :
 */
public interface UserMsg {
    interface UserType{
        /**
         * 管理员
         */
        Integer BLOG_ADMIN_USER = 1;
        /**
         * 普通用户
         */
        Integer BLOG_COMMON_USER = 2;
        /**
         * 游客
         */
        Integer BLOG_VISITOR = 0 ;
    }
}
