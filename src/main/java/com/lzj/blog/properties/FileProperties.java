package com.lzj.blog.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Getter
@Setter
@ToString
@ConfigurationProperties( prefix = "blog.file")
public class FileProperties {

    private String accessKey;
    public  String secretKey;
    public  String bucket;
}