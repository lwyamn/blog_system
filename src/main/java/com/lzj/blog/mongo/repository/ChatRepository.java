package com.lzj.blog.mongo.repository;

import com.lzj.blog.mongo.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 9:14
 */
public interface ChatRepository extends MongoRepository<ChatMessage,String> {
    List<ChatMessage> searchChatMessageByChatUserIdAndAndUserId(String chatUserId,String userId);
}
