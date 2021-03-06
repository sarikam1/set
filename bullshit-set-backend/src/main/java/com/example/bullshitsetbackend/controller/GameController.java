package com.example.bullshitsetbackend.controller;

import com.example.bullshitsetbackend.DTO.GameDTO;
import com.example.bullshitsetbackend.DTO.WaitingRoomDTO;
import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Participant;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.service.DeckService;
import com.example.bullshitsetbackend.service.GameService;
import com.example.bullshitsetbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Game> getWaitingGames(@RequestParam("username") String username) {
        List<Game> games = gameService.getWaitingGames(username);
        LOGGER.info("Waiting games list is " + games);
        return games;
    }

    @GetMapping("/create")
    public Game createNewGame() {
        Player player = playerService.getLoggedUser();
        LOGGER.info("current player is " + player.getUserName());
        Game newGame = gameService.createNewGame(player);
        //this.deckService.printDeck(newGame.getDeck());
        httpSession.setAttribute("currentGameId", newGame.getId());
        LOGGER.info("current game is " + newGame);
        return newGame;
    }

    @GetMapping("/can-create-game")
    public boolean canCreateGame() {
        Player player = playerService.getLoggedUser();
        boolean can_create = gameService.canCreateGame(player.getUserName());
        LOGGER.info("can create is " + can_create);
        return can_create;
    }

    @GetMapping("/current-game")
    public int currentGameId() {
        return (int) httpSession.getAttribute("currentgameId");
    }

    @GetMapping("/join-game")
    public Participant joinGame(@RequestParam("username") String username, @RequestParam("gameId") long gameId) {
        return gameService.joinGame(username, gameId);
    }

    @GetMapping("/get-waiting-info")
    public WaitingRoomDTO getWaitingRoomInfo(@RequestParam("gameId") long gameId) {
        return gameService.getWaitingRoomInfo(gameId);
    }

    @GetMapping("/start-game")
    public void startGame(@RequestParam("gameId") long gameId) {
        LOGGER.info("start-game");
        gameService.startGame(gameId);
    }

    @GetMapping("/get-creator-username")
    public String getGameCreatorUsername(@RequestParam("gameId") long gameId) {
        return gameService.getGameCreatorUsername(gameId);
    }




}
