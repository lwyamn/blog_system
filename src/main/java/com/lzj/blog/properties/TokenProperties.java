package com.lzj.blog.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.config
 * @Date : 2023/2/16 16:41
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ConfigurationProperties( prefix = "blog.token")
public class TokenProperties {
    private String secret ="PLUMOONLWYAMNBLOG";

    private Duration expires = Duration.ofHours(8);

    private String issuer = "BLOGSYSTEM";

    public Date getExpiresAt() {
        return Date.from(Instant.now().plus(getExpires()));
    }
}
