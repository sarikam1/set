package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.DTO.Greeting;
import com.example.bullshitsetbackend.DTO.HelloMessage;
import com.example.bullshitsetbackend.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

//https://stackoverflow.com/questions/52497612/how-to-properly-manage-sockets-for-simultaneous-battles-in-a-multiplayer-strateg
//https://stackoverflow.com/questions/45936545/i-cant-get-my-head-around-websockets-via-socket-io-and-node-js
//https://stackoverflow.com/questions/50891098/websocket-application-with-multiple-users

@Controller
public class PlayController {
    private final static Logger LOGGER = Logger.getLogger(PlayController.class.getName());
    private final SimpMessagingTemplate simpMessagingTemplate;
    //https://stackoverflow.com/questions/26110182/should-all-members-shared-between-websocket-sessions-be-synchronized
    //https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedSet-java.util.Set-
    private final Set connectedParticipants;
    private PlayService playService;


    @Autowired //not necessary after Spring 4
    public PlayController(SimpMessagingTemplate simpMessagingTemplate, PlayService playService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.connectedParticipants = new HashSet();
        this.playService = playService;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    //message payload bound to HelloMessage object
    public Greeting greeting(HelloMessage message) throws Exception {
        LOGGER.info("Inside /hello method of ws test");

        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/joinGame/{gameId}")
    public List<String> joinGame(@DestinationVariable String gameId, MessageHeaders messageHeaders, @Payload String username, @Header(name = "simpSessionId") String sessionID) throws Exception {
        LOGGER.info("Inside /joinGame");
        List<String> toReturn = new ArrayList<String>();
        if (!connectedParticipants.contains(username)) {
            connectedParticipants.add(username);
            LOGGER.info("Adding " + username + " to game!");
            LOGGER.info("Returning " + connectedParticipants);
            //simpMessagingTemplate.convertAndSend("/queue/newMember-" + username, toReturn);
        }
        toReturn.addAll(connectedParticipants);
        return toReturn;
    }

    @MessageMapping("/leaveGame/{gameId}")
    public List<String> leaveGame(@DestinationVariable String gameId, String username) throws Exception {
        LOGGER.info("Inside /leaveGame");
        List<String> toReturn = new ArrayList<String>();
        if (connectedParticipants.contains(username)) {
            connectedParticipants.remove(username);
            LOGGER.info("Removing " + username + " from game!");
        }
        toReturn.addAll(connectedParticipants);
        return toReturn;
    }

}
