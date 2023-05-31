package com.lzj.blog.utils;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.auth.login.BlogUser;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.utils
 * @Date : 2023/2/16 16:51
 * @Author : linzj
 * @Description :
 */
@Slf4j
public class TokenUtils {

    public static String createToken(CurrentUser user,TokenProperties properties){
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .claim("userId", user.getId())
                .claim("username",user.getName())
                .claim("issuer",properties.getIssuer())
                .setExpiration(properties.getExpiresAt())
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public static CurrentUser fromToken(String token,TokenProperties tokenProperties){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
            BlogUser.BlogUserBuilder builder = BlogUser.builder();
            return builder.userId(String.valueOf(claims.get("userId"))).username(String.valueOf(claims.get("username"))).build();
        }catch (ExpiredJwtException e) {
            log.error("token已经过期啦!");
            throw new BlogException(BlogErrorCode.TOKEN_EXPIRE);
        }catch (SignatureException e) {
            log.error("token不合法");
            throw new BlogException(BlogErrorCode.TOKEN_NOT_LEGAL);
        }
    }
}
