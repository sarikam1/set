package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//https://stackoverflow.com/questions/26549379/when-use-responseentityt-and-restcontroller-for-spring-restful-applications
@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        return players;
    }

    @GetMapping("/find/{id}")
    public Player getPlayerById(@PathVariable("id") Long id) {
        Player employee = playerService.findPlayerById(id);
        return employee;
    }

    @PostMapping("/add")
    public Player addEmployee(@RequestBody Player employee) {
        Player newEmployee = playerService.addPlayer(employee);
        return newEmployee;
    }

    @PutMapping("/update")
    public Player updateEmployee(@RequestBody Player employee) {
        Player updateEmployee = playerService.updatePlayer(employee);
        return updateEmployee;
    }

    @DeleteMapping ("/delete/{id}")
    public void updateEmployee(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
    }




}
