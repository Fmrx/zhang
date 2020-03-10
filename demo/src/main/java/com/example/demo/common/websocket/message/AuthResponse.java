package com.example.demo.common.websocket.message;


import lombok.Data;

@Data
public class AuthResponse implements Message{

    public static final String TYPE = "AUTH_RESPONSE";

    private Integer code;

    private String message;
}
