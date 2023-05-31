package com.lzj.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.config
 * @Date : 2023/2/16 16:39
 * @Author : linzj
 * @Description :
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("博客系统接口文档")
                .description("博客系统项目接口文档")
                .version("1.0"));
    }
}
