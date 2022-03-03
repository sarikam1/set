package com.example.bullshitsetbackend.domain;
import com.example.bullshitsetbackend.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="game_id", nullable = false)
    private Game game;

    //https://stackoverflow.com/questions/59820972/foreign-key-mapping-to-embeddable-class
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "player_id", insertable = false, updatable = false),
            @JoinColumn(name = "game_id", insertable = false, updatable = false)
    })
    private Participant participantId;

    @ManyToOne
    @JoinColumn(name="set_id", nullable = false)
    private Set set;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name="move_type_id", nullable = false)
    private MoveType moveType;




}
