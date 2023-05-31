package com.lzj.blog.mongo.converter;

import com.lzj.blog.model.vo.ChatMessageVO;
import com.lzj.blog.mongo.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/27 18:25
 */
@Mapper
public interface ChatMessageConverter {
    ChatMessageConverter INSTANCE = Mappers.getMapper(ChatMessageConverter.class);
    ChatMessageVO chatMessageTOChatMessageVO(ChatMessage chatMessage);
    List<ChatMessageVO> chatMessageListTOChatMessageVOList(List<ChatMessage> chatMessageList);
}
