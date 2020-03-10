package com.example.demo.common.websocket.handler;

import com.example.demo.common.websocket.message.SendResponse;
import com.example.demo.common.websocket.message.SendToAllRequest;
import com.example.demo.common.websocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest>{
    @Override
    public void execute(Session session, SendToAllRequest message) {
        SendResponse sendResponse = new SendResponse();
        sendResponse.setMsgId(message.getMsgId());
        sendResponse.setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        SendToAllRequest sendToAllRequest = new SendToAllRequest();
        sendToAllRequest.setMsgId(message.getMsgId());
        sendToAllRequest.setContent(message.getContent());

        WebSocketUtil.broadcast(SendToAllRequest.TYPE, sendToAllRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
