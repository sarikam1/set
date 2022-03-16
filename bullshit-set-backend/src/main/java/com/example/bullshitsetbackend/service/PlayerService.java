package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.domain.Player;
import com.example.bullshitsetbackend.repository.PlayerRepo;
import com.example.bullshitsetbackend.security.ContextUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
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

    //LO: find out how this works
    public Player getLoggedUser() {
        ContextUser contextUser = (ContextUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerRepo.findPlayerByUserName(contextUser.getPlayer().getUserName());
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

    //provides token upon login
//    public String login(String username, String password) {
//        Optional player = playerRepo.login(username, password);
//        if(player.isPresent()) {
//            String token = UUID.randomUUID().toString();
//            Player realPlayer = (Player) player.get();
//            realPlayer.setToken(token);
//            playerRepo.save(realPlayer);
//            return token;
//
//        }
//        else {
//            return "";
//        }
//    }

    //validates customer based on provided token
//    public Optional findByToken(String token) {
//        Optional player= playerRepo.findPlayerByToken(token);
//        if(player.isPresent()){
//            Player realPlayer = (Player) player.get();
//            User user= new ContextUser(realPlayer);
//            return Optional.of(user);
//        }
//        return  Optional.empty();
//    }

//    public String getToken(String username, String password){
//        String token = login(username,password);
//        if(token.isEmpty()){
//            return "no token found";
//        }
//        return token;
//    }




}
