package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/api/users/user/{id}",produces = "application/json")
    public Player getUserDetail(@PathVariable Long id){
        return playerService.findPlayerById(id);
    }
}