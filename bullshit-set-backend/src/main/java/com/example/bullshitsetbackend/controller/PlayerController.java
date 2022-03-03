package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Player> getEmployeesById(@PathVariable("id") Long id) {
        Player employee = playerService.findPlayerById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addEmployee(@RequestBody Player employee) {
        Player newEmployee = playerService.addPlayer(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED); //created something on server
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updateEmployee(@RequestBody Player employee) {
        Player updateEmployee = playerService.updatePlayer(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.CREATED); //created something on server
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK); //created something on server
    }




}
