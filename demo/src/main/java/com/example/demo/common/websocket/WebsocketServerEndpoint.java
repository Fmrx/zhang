package com.example.demo.common.websocket;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.websocket.message.AuthRequest;
import com.example.demo.common.websocket.message.Message;
import com.example.demo.common.websocket.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.websocket.*;
import com.example.demo.common.websocket.handler.MessageHandler;
import org.springframework.util.CollectionUtils;

import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@ServerEndpoint("/")
@Slf4j
public class WebsocketServerEndpoint implements InitializingBean {

    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("【onOpen】【session({})接入】", session);

        //解析accessToken
        List<String> accessTokenValues = session.getRequestParameterMap().get("accessToken");
        String accessToken = !CollectionUtils.isEmpty(accessTokenValues) ? accessTokenValues.get(0) : null;

        //创建AuthRequest消息类型
        AuthRequest authRequest = new AuthRequest();
        authRequest.setAccessToken(accessToken);

        //获得消息处理器
        MessageHandler<AuthRequest> messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if(messageHandler == null) {
            log.error("【onOpen】【认证消息类型,不存在消息处理器】");
            return;
        }
        messageHandler.execute(session, authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("【onMessage】【session({})】 接受一条消息({})", session, message);
        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            //获取消息类型
            String messageType = jsonObject.getString("type");

            //获取消息处理器
            MessageHandler messageHandler = HANDLERS.get(messageType);
            if(messageHandler == null) {
                log.error("【onMessage】【消息类型({}), 不存在消息处理器】", messageType);
                return;
            }

            //解析消息
            Class<? extends Message> messageClass = this.getMessageClass(messageHandler);
            //处理消息
            Message messageObj = JSONObject.parseObject(jsonObject.getString("body"), messageClass);
            messageHandler.execute(session, messageObj);
        }catch (Throwable throwable) {
            log.info("【onMessage】【session({}) message({}) 发生异常】", session, throwable);
        }

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("【onClose】【session({}) 连接关闭。关闭原因是({})】", session, closeReason);
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("【onClose】【session({}) 发生异常】", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(), messageHandler));
        log.info("【afterPropertiesSet】【消息处理器数量:{}】", HANDLERS.size());
    }

    private Class<? extends Message> getMessageClass(MessageHandler handler) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) &&
            Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if(Objects.nonNull(interfaces)) {
            for (Type type : interfaces) {
                if(type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if(Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        if(Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获取不到消息类型", handler));
    }
}
