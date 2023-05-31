package com.lzj.blog.auth;

import io.swagger.v3.oas.annotations.Parameter;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.auth
 * @Date : 2023/2/16 17:00
 * @Author : linzj
 * @Description :
 */
public interface CurrentUser {

    @Parameter(hidden = true)
    String getId();

    @Parameter(hidden = true)
    String getName();
}
