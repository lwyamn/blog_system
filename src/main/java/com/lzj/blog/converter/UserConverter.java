package com.lzj.blog.converter;

import com.lzj.blog.entity.User;
import com.lzj.blog.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @BelongsProject : blog
 * @BelongsPackage : com.lzj.blog.converter
 * @Date : 2023/2/17 15:38
 * @Author : linzj
 * @Description :
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVO userTOUserVO(User user);

    List<UserVO> userListTOUserVOList(List<User> users);
}
