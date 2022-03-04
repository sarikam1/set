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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "card_color", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardColor color;

    @Column(name = "card_shading", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardShading shading;

    @Column(name = "card_shape", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardShape shape;

    @Column(name = "card_number", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardNumber number;

    @Column(name = "card_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name="deck_id", nullable = true)
    private Deck deck;

    @OneToOne
    @JoinColumn(name="set_id", nullable = true)
    private Set set;

}
