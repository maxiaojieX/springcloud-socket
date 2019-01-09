package com.socket.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by xiaojie.Ma on 2019/1/3.
 */
@Component
public class MessageChannelInInterceptor extends ChannelInterceptorAdapter {

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

        MessageHeaders headers = message.getHeaders();
        SimpMessageType messageType = SimpMessageHeaderAccessor.getMessageType(headers);

        StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
        // 排除 heartbeat 类信息
        if(sha.getCommand() == null) {
            return;
        }
        /*
         * 取出HttpSessionIdHandshakeInterceptor拦截器的存放key/value
//         */
//        Object sessionId = sha.getSessionAttributes().get(WebSocketConstant.WS_SESSION);
//        if(StringUtils.isEmpty(sessionId)){
//            return;
//        }
//        /*
//         * 判断客户端的连接状态
//         */
//        switch(sha.getCommand()) {
//            case DISCONNECT:
//                localRoute.offline(sessionId.toString());
//                sha.getSessionAttributes().remove(WebSocketConstant.WS_SESSION);
//                break;
//            case CONNECT:
////                route.online(sessionId); 游客不需要存入redis
////            	GenericMessage<byte[]> msg = new GenericMessage<byte[]>("test123".getBytes(),message.getHeaders());
////            	message = msg;
//                break;
//            case CONNECTED:
//                break;
//            default:
//                break;
//        }
    }

}
