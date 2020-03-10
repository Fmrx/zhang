package com.example.demo.common.websocket.message;

import lombok.Data;

@Data
public class SendToUserRequest implements Message {

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    private String msgId;

    private String content;
}
