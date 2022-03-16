package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.security.UserDetailsServiceImpl;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/users/")
public class UserProfileController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "{id}",produces = "application/json")
    public Player getUserDetail(@PathVariable Long id){
        return playerService.findPlayerById(id);
    }


}