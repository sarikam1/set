package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.BullshitSetBackendApplication;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.security.UserDetailsServiceImpl;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.logging.Logger;

@Transactional
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final static Logger LOGGER = Logger.getLogger(UserProfileController.class.getName());

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/{id}",produces = "application/json")
    public Player getUserDetail(@PathVariable Long id){
        return playerService.findPlayerById(id);
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Principal> user(Principal user) {
        LOGGER.info("Here! User is " + user);
        return new ResponseEntity<Principal>(user, HttpStatus.OK);
    }


}