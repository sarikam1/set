package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.DTO.GameDTO;
import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.DeckService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/game")
public class GameController {
    GameService gameService;

    HttpSession httpSession;

    PlayerService playerService;

    DeckService deckService;

    private final static Logger LOGGER = Logger.getLogger(GameController.class.getName());

    //constructor injection >> field injection
    @Autowired
    public GameController(GameService gameService, HttpSession httpSession, PlayerService playerService, DeckService deckService) {
        this.gameService = gameService;
        this.httpSession = httpSession;
        this.playerService = playerService;
        this.deckService = deckService;
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        List<Game> games = gameService.findAllGames();
        return games;
    }

    @GetMapping("/waiting")
    public List<Game> getWaitingGames(String username) {
        List<Game> games = gameService.getWaitingGames(username);
        LOGGER.info("Waiting games list is " + games);
        return games;
    }

    @GetMapping("/create")
    public Game createNewGame() {
        Player player = playerService.getLoggedUser();
        LOGGER.info("current player is " + player.getUserName());
        Game newGame = gameService.createNewGame(player);
        this.deckService.printDeck(newGame.getDeck());

        httpSession.setAttribute("currentGameId", newGame.getId());
        LOGGER.info("current game is " + newGame);
        return newGame;
    }

    @GetMapping("/current-game")
    public int currentGameId() {
        return (int) httpSession.getAttribute("currentgameId");
    }


}
