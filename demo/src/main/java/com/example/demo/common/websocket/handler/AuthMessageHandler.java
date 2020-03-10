package com.example.demo.common.websocket.handler;

import com.example.demo.common.websocket.message.AuthRequest;
import com.example.demo.common.websocket.message.AuthResponse;
import com.example.demo.common.websocket.message.UserJoinNoticeRequest;
import com.example.demo.common.websocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.Session;

@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest>{

    @Override
    public void execute(Session session, AuthRequest message) {
        //accessToken判空
        if(StringUtils.isEmpty(message.getAccessToken())) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("accessToken为空");
            authResponse.setCode(1);
            WebSocketUtil.send(session, AuthResponse.TYPE, authResponse);
        }
        //添加到WebSocketUtil
        WebSocketUtil.addSession(session, message.getAccessToken());
        
        AuthResponse authResponse = new AuthResponse();
        authResponse.setCode(0);
        WebSocketUtil.send(session, AuthResponse.TYPE, authResponse);

        //通知多人
        UserJoinNoticeRequest userJoinNoticeRequest = new UserJoinNoticeRequest();
        userJoinNoticeRequest.setNickname(message.getAccessToken());
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE, userJoinNoticeRequest);
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
