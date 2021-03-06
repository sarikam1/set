package com.example.bullshitsetbackend.domain;
import com.example.bullshitsetbackend.enums.*;
import com.example.bullshitsetbackend.repository.ParticipantRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Game {

    //all fields except end_time


    public Game(Player createdBy, Timestamp createdTime, int numPlayers, GameStatus gameStatus) {
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.numPlayers = numPlayers;
        this.gameStatus = gameStatus;
    }

    public void addParticipant(Participant toAdd) {
        this.participants.add(toAdd);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="created_by_id", nullable = false)
    private Player createdBy;

    @Column(name = "created_time", nullable = false)
    private Timestamp createdTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "num_players")
    private int numPlayers;

    @Column(name = "game_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToOne
    @JoinColumn(name="deck_id")
    private Deck deck;

    @OneToMany(mappedBy="game")
    private List<Participant> participants = new ArrayList<Participant>();
}
