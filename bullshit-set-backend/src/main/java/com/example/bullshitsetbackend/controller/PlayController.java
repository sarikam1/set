package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.DTO.Greeting;
import com.example.bullshitsetbackend.DTO.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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


    @Autowired //not necessary after Spring 4
    public PlayController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.connectedParticipants = new HashSet();
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    //message payload bound to HelloMessage object
    public Greeting greeting(HelloMessage message) throws Exception {
        LOGGER.info("Inside /hello method of ws test");

        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/joinGame")
    //@SendToUser("/queue/newMember")
    //message payload bound to HelloMessage object
    public void joinGame(MessageHeaders messageHeaders, @Payload String username, @Header(name = "simpSessionId") String sessionID) throws Exception {
        LOGGER.info("Inside /joinGame");
        if (!connectedParticipants.contains(username)) {
            connectedParticipants.add(username);
            LOGGER.info("Adding " + username + " to game!");
            LOGGER.info("sessionId is " + sessionID);
            LOGGER.info("Returning " + connectedParticipants);
            List<String> toReturn = new ArrayList<String>();
            toReturn.addAll(connectedParticipants);
            simpMessagingTemplate.convertAndSend("/queue/newMember-" + username, toReturn);
        }
    }

    @MessageMapping("/leaveGame")
   //@SendToUser("/queue/leftMember")
    //message payload bound to HelloMessage object
    public String leaveGame(String username) throws Exception {
        LOGGER.info("Inside /leaveGame");
        if (connectedParticipants.contains(username)) {
            connectedParticipants.remove(username);
            LOGGER.info("Removing " + username + " from game!");
            simpMessagingTemplate.convertAndSendToUser(username,"/topic/newMember", username);
            return "Successfully left Game";
        } else {
            return "Not in game, cannot leave";
        }
    }

}
