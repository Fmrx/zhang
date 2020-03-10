package com.example.demo.common.websocket.handler;

import com.example.demo.common.websocket.message.Message;

import javax.websocket.Session;

public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     * @param session
     * @param message
     */
    void execute(Session session, T message);

    /**
     *
     * @return 消息类型 TYPE字段
     */
    String getType();
}
