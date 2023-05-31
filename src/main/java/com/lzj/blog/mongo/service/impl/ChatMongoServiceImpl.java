package com.lzj.blog.mongo.service.impl;

import com.lzj.blog.constant.ChatMsg;
import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import com.lzj.blog.model.dto.AddChatUserDTO;
import com.lzj.blog.model.dto.QueryChatMessageDTO;
import com.lzj.blog.model.dto.request.MessageReq;
import com.lzj.blog.model.vo.ChatMessageVO;
import com.lzj.blog.mongo.converter.ChatMessageConverter;
import com.lzj.blog.mongo.entity.ChatMessage;
import com.lzj.blog.mongo.repository.ChatRepository;
import com.lzj.blog.mongo.service.ChatMongoService;
import com.lzj.blog.service.UserService;
import com.lzj.blog.utils.JsonUtils;
import com.lzj.blog.webSocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 11:19
 */
@Slf4j
@Service
public class ChatMongoServiceImpl implements ChatMongoService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private UserService userService;
    @Override
    public ChatMessageVO sendMessage(MessageReq messageReq) {
        if(StringUtils.isEmpty(messageReq.getChatUserId())){
            throw new BlogException(BlogErrorCode.NOT_FIND_CHAT_USER);
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(messageReq.getContent());
        chatMessage.setChatUserId(messageReq.getChatUserId());
        chatMessage.setUserId(messageReq.getUserId());
        chatMessage.setType(ChatMsg.ChatType.SINGLE_CHAT);
        try {
            webSocket.sendOneMessage(messageReq.getChatUserId(), JsonUtils.toString(chatMessage));
            chatRepository.insert(chatMessage);
            AddChatUserDTO addChatUserDTO = new AddChatUserDTO();
            addChatUserDTO.setUserId(messageReq.getChatUserId());
            addChatUserDTO.setChatUserId(messageReq.getUserId());
            userService.addUserChatList(addChatUserDTO);
        } catch (Exception e) {
            throw new BlogException(BlogErrorCode.SEND_MESSAGE_ERROR);
        }
        return ChatMessageConverter.INSTANCE.chatMessageTOChatMessageVO(chatMessage);
    }

    @Override
    public ChatMessageVO sendAllMessage(MessageReq messageReq) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(messageReq.getContent());
        chatMessage.setUserId(messageReq.getUserId());
        chatMessage.setType(ChatMsg.ChatType.MESSAGE_CHAT);
        webSocket.sendAllMessage(JsonUtils.toString(chatMessage));
        chatRepository.insert(chatMessage);
        return ChatMessageConverter.INSTANCE.chatMessageTOChatMessageVO(chatMessage);
    }

    @Override
    public List<ChatMessageVO> queryChatMessage(QueryChatMessageDTO chatMessageDTO) {
        List<ChatMessage> chatMessages = chatRepository.searchChatMessageByChatUserIdAndAndUserId(chatMessageDTO.getChatUserId(), chatMessageDTO.getUserId());
        if(Objects.equals(chatMessageDTO.getChatUserId(),chatMessageDTO.getUserId())){
            return ChatMessageConverter.INSTANCE.chatMessageListTOChatMessageVOList(chatMessages.stream().sorted(Comparator.comparing(ChatMessage::getCreateTime)).toList());
        }
        List<ChatMessage> allChatMessages = chatRepository.searchChatMessageByChatUserIdAndAndUserId(chatMessageDTO.getUserId(),chatMessageDTO.getChatUserId());
        allChatMessages.addAll(chatMessages);
        return ChatMessageConverter.INSTANCE.chatMessageListTOChatMessageVOList(allChatMessages.stream().sorted(Comparator.comparing(ChatMessage::getCreateTime)).toList());
    }

}
