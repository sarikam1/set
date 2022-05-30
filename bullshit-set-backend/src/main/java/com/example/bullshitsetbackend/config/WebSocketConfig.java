package com.example.bullshitsetbackend.config;

import com.example.bullshitsetbackend.controller.PlayController;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@Controller
class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/stream");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/gs-guide-websocket").setAllowedOriginPatterns("*");
    }




//    @Autowired
//    PlayController playController;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler((WebSocketHandler) playController, "api/websocket").
//                setAllowedOrigins("http://localhost:4200","http://localhost:8080").withSockJS();
//
//    }
}
