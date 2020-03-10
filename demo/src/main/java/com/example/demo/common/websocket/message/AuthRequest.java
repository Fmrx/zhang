package com.example.demo.common.websocket.message;

import lombok.Data;

@Data
public class AuthRequest implements Message{

    public static final String TYPE = "AUTH_REQUEST";

    private String accessToken;
}
