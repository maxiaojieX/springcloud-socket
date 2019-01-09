package com.socket.controller;

import com.socket.bean.Message;
import com.socket.bean.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaojie.Ma on 2019/1/3.
 */
@Controller
public class WebSocketController {

//    /**
//     * 浏览器发送请求通过@messageMapping 映射/welcome 这个地址
//     * @param message
//     * @return
//     * @throws Exception
//     */
//    @MessageMapping("/welcome")
//    /**
//     * 服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息
//     */
//    @SendTo("/topic/getResponse")
//    public String say(String msg) throws Exception {
//        Thread.sleep(1000);
//
//        return msg;
//    }

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("te")
    @ResponseBody
    public String test() {
        template.convertAndSend("/topic/getResponse", "aa");
        return "ok";
    }

}
