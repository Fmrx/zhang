package com.example.demo.common.websocket.message;

import lombok.Data;

@Data
public class SendToOneRequest implements Message{

    public static final String TYPE = "SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;

    /**
     * 消息编号
     */
    private String msgId;

    private String content;
}
