package com.lzj.blog.webSocket;


import com.lzj.blog.exception.BlogErrorCode;
import com.lzj.blog.exception.BlogException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: linzj
 * @see:
 * @since: 2023/4/23 16:45
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocket {
    private String userId;
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> links = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId")String userId){
        try{
            this.session = session;
            this.userId = userId;
            links.add(this);
            sessionPool.put(userId,session);
        }catch (Exception e){
            throw new BlogException(BlogErrorCode.FAILURE_IN_LINK);
        }
    }

    @OnClose
    public void onClose(){
        try{
            links.remove(this);
            sessionPool.remove(userId);
        }catch (Exception e){
            throw new BlogException(BlogErrorCode.FAILURE_IN_BROKEN_LINK);
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("用户Id{}发送消息错误,原因{}",userId,error.getMessage());
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        log.info("用户Id{},广播消息:{}",userId,message);
        for (WebSocket link : links) {
            try {
                if(link.session.isOpen()) {
                    link.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                log.error("发送广播消息给用户Id{}失败",link.userId);
            }
        }
    }

    /**
     * 此为单点消息
     * @param userId
     * @param message
     */
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        if (session != null&&session.isOpen()) {
            try {
                log.info("用户Id{}发送给用户Id{}单点消息:{}",this.userId,userId,message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                throw new BlogException(BlogErrorCode.SEND_CHAT_MESSAGE_ERROR);
            }
        }
    }

}
