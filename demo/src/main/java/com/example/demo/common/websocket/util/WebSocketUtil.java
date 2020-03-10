package com.example.demo.common.websocket.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.websocket.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketUtil {

    private static final Map<Session, String> SESSION_USER_MAP = new ConcurrentHashMap<>();

    private static final Map<String, Session> USER_SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 添加session与user的映射关系
     * @param session
     * @param user
     */
    public static void addSession(Session session, String user) {
        USER_SESSION_MAP.put(user, session);
        SESSION_USER_MAP.put(session, user);
    }

    /**
     * 移除session关系
     * @param session
     */
    public static void removeSession(Session session) {
        String user = SESSION_USER_MAP.remove(session);
        if(!StringUtils.isEmpty(user)) {
            USER_SESSION_MAP.remove(user);
        }
    }

    /**
     * 广播发送消息给所有用户
     * @param type
     * @param message
     * @param <T>
     */
    public static <T extends Message> void broadcast(String type, T message) {
        //创建消息
        String messageText = buildTextMessage(type, message);
        SESSION_USER_MAP.forEach((key, value) ->{
            sendTextMessage(key, messageText);
        });
    }

    /**
     * 发送消息给单个用户session
     * @param session
     * @param type
     * @param message
     * @param <T>
     */
    public static <T extends Message> void send(Session session, String type, T message) {
        //创建消息
        String messageText = buildTextMessage(type, message);
        sendTextMessage(session, messageText);
    }

    public static <T extends Message> boolean send(String user, String type, T message) {
        Session session = USER_SESSION_MAP.get(user);
        if(session == null) {
           log.error("【send】【user({}) 不存在对应的session】", user);
           return false;
        }
        send(session, type, message);
        return true;
    }


    /**
     * 构建完整的消息
     * @param type
     * @param message
     * @param <T>
     * @return
     */
    private static <T extends Message> String buildTextMessage(String type, T message) {
        JSONObject messageObject = new JSONObject();
        messageObject.put("type", type);
        messageObject.put("body", message);
        return messageObject.toString();
    }

    /**
     * 真正发送消息的方法
     * @param session
     * @param messageText
     */
    private static void sendTextMessage(Session session, String messageText) {
        if(session == null) {
            log.error("【sendTextMessage】【session 为null】");
        }
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        if(basic == null) {
            log.error("【sendTextMessage】【basic 为null】");
        }

        try {
            basic.sendText(messageText);
        } catch (IOException e) {
            log.error("【sendTextMessage】【session({}) 发送消息{} 发生异常】", session, messageText, e);
        }
    }
}
