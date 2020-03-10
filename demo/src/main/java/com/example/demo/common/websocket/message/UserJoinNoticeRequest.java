package com.example.demo.common.websocket.message;

import lombok.Data;

@Data
public class UserJoinNoticeRequest implements Message{

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    private String nickname;
}
