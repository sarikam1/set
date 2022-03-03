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
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SetStatus status;

    @Enumerated(EnumType.STRING)
    private SetValidity validity;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "player_id", insertable = false, updatable = false),
            @JoinColumn(name = "game_id", insertable = false, updatable = false)
    })
    private Participant owner;




}
