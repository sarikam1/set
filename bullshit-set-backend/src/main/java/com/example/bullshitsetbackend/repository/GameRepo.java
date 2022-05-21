package com.example.bullshitsetbackend.repository;

import com.example.bullshitsetbackend.domain.Game;
import com.example.bullshitsetbackend.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
    List<Game> findByGameStatus(GameStatus gameStatus);

    Game findGameById(Long id);



}
