package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.BullshitSetBackendApplication;
import com.example.bullshitsetbackend.DTO.Greeting;
import com.example.bullshitsetbackend.DTO.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.util.logging.Logger;

@Controller
public class PlayController {
    private final static Logger LOGGER = Logger.getLogger(PlayController.class.getName());

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    //message payload bound to HelloMessage object
    public Greeting greeting(HelloMessage message) throws Exception {
        LOGGER.info("Inside /hello method of ws test");

        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }



}
