package com.lzj.blog;

import com.lzj.blog.properties.FileProperties;
import com.lzj.blog.properties.TokenProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableConfigurationProperties({TokenProperties.class, FileProperties.class})
@MapperScan(basePackages = {"com.lzj.blog.mapper"})
@SpringBootApplication
@EnableMongoAuditing
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
