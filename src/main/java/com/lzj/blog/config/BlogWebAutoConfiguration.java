package com.lzj.blog.config;

import com.lzj.blog.auth.Interceptor.LoginInterceptor;
import com.lzj.blog.auth.resolver.CommonResolver;
import com.lzj.blog.constant.BlogUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.config
 * @Date : 2023/2/24 16:13
 * @Author : linzj
 * @Description :
 */
@Configuration
public class BlogWebAutoConfiguration {
    @Bean
    public LoginInterceptor loginInterceptor(){return new LoginInterceptor();}

    @Bean
    public CommonResolver commonResolver(){
        return new CommonResolver();
    }

    @Configuration
    protected class BlogWebConf implements WebMvcConfigurer{
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOriginPatterns("*")//允许哪些域访问
                    .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")//允许哪些方法访问
                    .allowCredentials(true)//是否允许携带cookie
                    .maxAge(3600)//设置浏览器询问的有效期
                    .allowedHeaders("*");//
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(loginInterceptor())
                    .addPathPatterns(BlogUrl.BASE_PATH_AUTH)
                    .excludePathPatterns(BlogUrl.Open.COMMON_PATH)
                    .order(15);
        }
        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(commonResolver());
        }
    }
}
