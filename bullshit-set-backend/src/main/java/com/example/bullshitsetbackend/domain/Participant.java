package com.example.bullshitsetbackend.domain;
import com.example.bullshitsetbackend.enums.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Participant {

    @EmbeddedId
    ParticipantKey id;

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name="game_id")
    private Game game;

    @Column(name = "score", nullable = false)
    private int score;


}
