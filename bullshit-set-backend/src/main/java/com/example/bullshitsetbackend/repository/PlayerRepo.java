package com.example.bullshitsetbackend.repository;

import com.example.bullshitsetbackend.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {


    Player deletePlayerById(Long id);

    Player findPlayerByUserName(String username);

    Optional<Player> findPlayerById(Long id);

    @Query(value = "SELECT u FROM Player u where u.userName = ?1 and u.password = ?2 ")
    Optional<Player> login(String username,String password);

//    Optional<Player>  findPlayerByToken(String token);
}
