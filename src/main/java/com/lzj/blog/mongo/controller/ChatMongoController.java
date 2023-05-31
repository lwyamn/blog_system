package com.lzj.blog.mongo.controller;

import com.lzj.blog.auth.CurrentUser;
import com.lzj.blog.constant.BlogUrl;
import com.lzj.blog.model.dto.AddChatUserDTO;
import com.lzj.blog.model.dto.QueryChatMessageDTO;
import com.lzj.blog.model.dto.request.MessageReq;
import com.lzj.blog.model.vo.ChatMessageVO;
import com.lzj.blog.model.vo.UserVO;
import com.lzj.blog.model.vo.response.Result;
import com.lzj.blog.mongo.service.ChatMongoService;
import com.lzj.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/24 11:14
 */
@Slf4j
@RestController
@Tag(name = "消息Mongo接口")
public class ChatMongoController {
    @Autowired
    private ChatMongoService chatMongoService;
    @Autowired
    private UserService userService;
    @PostMapping(BlogUrl.ChatMongo.SEND_MONGO_CHAT)
    @Operation(summary = "发送单聊")
    public Result<ChatMessageVO> send(@RequestBody MessageReq messageReq, CurrentUser user){
        messageReq.setUserId(user.getId());
        return Result.success(chatMongoService.sendMessage(messageReq));
    }

    @PostMapping(BlogUrl.ChatMongo.SEND_ALL_MONGO_CHAT)
    @Operation(summary = "发送群体通知")
    public Result<ChatMessageVO> sendAll(@RequestBody MessageReq messageReq, CurrentUser user){
        messageReq.setUserId(user.getId());
        return Result.success( chatMongoService.sendAllMessage(messageReq));
    }

    @PostMapping(BlogUrl.ChatMongo.GET_CHAT_MESSAGE)
    @Operation(summary = "获取消息")
    public Result<List<ChatMessageVO>> get(@RequestBody QueryChatMessageDTO chatMessageDTO, CurrentUser user){
        chatMessageDTO.setUserId(user.getId());
        return Result.success(chatMongoService.queryChatMessage(chatMessageDTO));
    }

    @PostMapping(BlogUrl.ChatMongo.ADD_CHAT_LIST)
    @Operation(summary = "添加聊天列表")
    public Result<String> addChatList(@RequestBody AddChatUserDTO chatUserDTO, CurrentUser user){
        chatUserDTO.setUserId(user.getId());
        userService.addUserChatList(chatUserDTO);
        return Result.success("添加成功");
    }

    @GetMapping(BlogUrl.ChatMongo.GET_CHAT_LIST)
    @Operation(summary = "获取聊天列表")
    public Result<List<UserVO>> getChatList(CurrentUser user){
        return Result.success(userService.getUserChatList(user.getId()));
    }
}
