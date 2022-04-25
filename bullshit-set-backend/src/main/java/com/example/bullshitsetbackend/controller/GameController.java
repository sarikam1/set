package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.DTO.GameDTO;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.GameService;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/game")
public class GameController {
    GameService gameService;

    HttpSession httpSession;

    PlayerService playerService;

    private final static Logger LOGGER = Logger.getLogger(GameController.class.getName());

    //constructor injection >> field injection
    @Autowired
    public GameController(GameService gameService, HttpSession httpSession, PlayerService playerService) {
        this.gameService = gameService;
        this.httpSession = httpSession;
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        List<Game> games = gameService.findAllGames();
        return games;
    }
    @GetMapping("/create")
    public Game createNewGame() {
        Player player = playerService.getLoggedUser();
        LOGGER.info("current player is " + player.getUserName());
        Game newGame = gameService.createNewGame(player);
        //httpSession.setAttribute("gameId", newGame.getId());
        return newGame;
    }

}
