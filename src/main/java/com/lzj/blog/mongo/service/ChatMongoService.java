package com.lzj.blog.mongo.service;

import com.lzj.blog.model.dto.QueryChatMessageDTO;
import com.lzj.blog.model.dto.request.MessageReq;
import com.lzj.blog.model.vo.ChatMessageVO;

import java.util.List;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 11:19
 */
public interface ChatMongoService {
    ChatMessageVO sendMessage(MessageReq messageReq);
    ChatMessageVO sendAllMessage(MessageReq messageReq);
    List<ChatMessageVO> queryChatMessage(QueryChatMessageDTO chatMessageDTO);
}
