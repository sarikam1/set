package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.repository.GameRepo;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService  {
    private final GameRepo gameRepo;

    @Autowired
    public GameService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public Game addGame(Game game) {
        return gameRepo.save(game);
    }


}
