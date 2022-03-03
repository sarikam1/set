package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService  {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player updatePlayer(Player player) {
        return playerRepo.save(player);
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findPlayerById(id).
                orElseThrow(() -> new UsernameNotFoundException("User by id " + id + " was not found"));
    }

    public Player deletePlayer(Long id) {
        return playerRepo.deletePlayerById(id);
    }


}
