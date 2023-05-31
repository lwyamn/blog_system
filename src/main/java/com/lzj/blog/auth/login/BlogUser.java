package com.lzj.blog.auth.login;

import com.lzj.blog.auth.CurrentUser;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.auth.login
 * @Date : 2023/2/16 18:24
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser implements CurrentUser {
    @Parameter(hidden = true)
    private String username;
    @Parameter(hidden = true)
    private String userId;

    @Override
    public String getId() {
        return this.userId;
    }

    @Override
    public String getName() {
        return this.username;
    }

}
