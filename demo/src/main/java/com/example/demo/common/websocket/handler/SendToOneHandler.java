package com.example.demo.common.websocket.handler;

import com.example.demo.common.websocket.message.SendResponse;
import com.example.demo.common.websocket.message.SendToOneRequest;
import com.example.demo.common.websocket.message.SendToUserRequest;
import com.example.demo.common.websocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest>{

    @Override
    public void execute(Session session, SendToOneRequest message) {
        SendResponse sendResponse = new SendResponse();
        sendResponse.setCode(0);
        sendResponse.setMsgId(message.getMsgId());
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        //创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest();
        sendToUserRequest.setMsgId(message.getMsgId());
        sendToUserRequest.setContent(message.getContent());

        //广播发送
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}
