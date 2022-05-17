package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.DTO.GameDTO;
import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.enums.GameStatus;
import com.example.bullshitsetbackend.repository.GameRepo;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService  {
    GameRepo gameRepo;

    DeckService deckService;

    PlayerService playerService;


    @Autowired
    public GameService(GameRepo gameRepo, DeckService deckService, PlayerService playerService) {
        this.gameRepo = gameRepo;
        this.deckService = deckService;
        this.playerService = playerService;
    }

    public Game addGame(Game game) {
        return gameRepo.save(game);
    }

    public List<Game> findAllGames() {
        return gameRepo.findAll();
    }

    public List<Game> getWaitingGames(String username) {
        //to-do: filter by games currently not enrolled in
        Player currentPlayer = playerService.getPlayerByUsername(username);
        return gameRepo.findByGameStatus(GameStatus.WAITING).stream().filter(game -> game.getCreatedBy() != currentPlayer).collect(Collectors.toList());
    }


    public Game createNewGame(Player creator) {
        Game newGame = new Game();
        //TO:DO--populate deck cards
        Deck newDeck = deckService.createDeck();
        newGame.setCreatedBy(creator);
        newGame.setCreatedTime(Timestamp.from(Instant.now()));
        newGame.setNumPlayers(0); //0 players before creator is added as participant
        newGame.setGameStatus(GameStatus.WAITING);
        newGame.setDeck(newDeck);
        //can only add participants after game has been created
        gameRepo.save(newGame);
        return newGame;
        }
}
