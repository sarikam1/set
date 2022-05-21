package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.DTO.GameDTO;
import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Participant;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.enums.GameStatus;
import com.example.bullshitsetbackend.repository.GameRepo;
import com.example.bullshitsetbackend.repository.ParticipantRepo;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class GameService {
    GameRepo gameRepo;

    DeckService deckService;

    PlayerService playerService;

    ParticipantRepo participantRepo;

    private final static Logger LOGGER = Logger.getLogger(GameService.class.getName());


    @Autowired
    public GameService(GameRepo gameRepo, ParticipantRepo participantRepo, DeckService deckService, PlayerService playerService) {
        this.participantRepo = participantRepo;
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

    public Game findGameById(Long id) {
        return gameRepo.findGameById(id);
    }

    public List<Game> getWaitingGames(String username) {
        //to-do: filter by games currently not enrolled inF
        Player currentPlayer = playerService.getPlayerByUsername(username);
        LOGGER.info("Current player is " + username);

        return gameRepo.findByGameStatus(GameStatus.WAITING).stream().filter(game -> game.getCreatedBy() != currentPlayer).collect(Collectors.toList());
    }


    public Game createNewGame(Player creator) {
        Game newGame = new Game();
        Long gameId = newGame.getId();
        Deck newDeck = deckService.createDeck();
        newGame.setCreatedBy(creator);
        newGame.setCreatedTime(Timestamp.from(Instant.now()));
        newGame.setGameStatus(GameStatus.WAITING);
        newGame.setDeck(newDeck);
        Participant creatorAsParticipant = new Participant(creator, newGame, 0);
        LOGGER.info("newParticipant is " + creatorAsParticipant);
        newGame.addParticipant(creatorAsParticipant); //update on both ends
        newGame.setNumPlayers(1); //creator is a player too
        gameRepo.save(newGame);
        participantRepo.save(creatorAsParticipant); //update on both ends
        return newGame;
    }

    //https://thorben-janssen.com/best-practices-many-one-one-many-associations-mappings/
    public Participant joinGame(String username, long gameId) {
        Player currentPlayer = playerService.getPlayerByUsername(username);
        Game currentGame = this.findGameById(gameId);

        if(currentPlayer == null || currentGame == null) {
            return null;
        }

        LOGGER.info("joinGame: Current game, player is " + currentGame + currentPlayer);

        Participant newParticipant = new Participant(currentPlayer, currentGame, 0);
        participantRepo.save(newParticipant); //update on both ends
        currentGame.addParticipant(newParticipant); //update on both ends
        gameRepo.save(currentGame);
        return newParticipant;

    }

    public List<Participant> getWaitingParticipants(long gameId) {
        Game currentGame = this.findGameById(gameId);
        if(currentGame.getGameStatus() != GameStatus.WAITING) {
            return null;
        }
        return currentGame.getParticipants();

    }


}

