package com.example.bullshitsetbackend.domain;
import com.example.bullshitsetbackend.enums.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Participant(Player player, Game game, int score) {
        this.id = new ParticipantKey(player.getId(), game.getId());
        this.player = player;
        this.game = game;
        this.score = score;
    }

    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name="game_id")
    @JsonIgnore //for bidirectional relationship between game & participants
    private Game game;

    @Column(name = "score", nullable = false)
    private int score;


}
