package com.example.demo.common.websocket.message;

import lombok.Data;

@Data
public class SendResponse  implements Message{

    public static final String TYPE = "SEND_RESPONSE";

    private String msgId;

    private Integer code;

    private String message;
}
