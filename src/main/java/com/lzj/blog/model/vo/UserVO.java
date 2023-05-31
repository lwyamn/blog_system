package com.lzj.blog.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.model.VO.response
 * @Date : 2023/2/17 15:29
 * @Author : linzj
 * @Description :
 */
@Getter
@Setter
@ToString
public class UserVO {
    private String id;
    private String username;
    private Integer userType;
    private String email;
    private String avatar;
    private String phone;
    private String personalProfile;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}