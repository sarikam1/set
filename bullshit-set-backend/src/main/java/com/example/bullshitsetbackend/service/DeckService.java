package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.repository.DeckRepo;
import com.example.bullshitsetbackend.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DeckService {
    private final DeckRepo deckRepo;

    @Autowired
    public DeckService(DeckRepo deckRepo) {
        this.deckRepo = deckRepo;
    }

    public Deck createDeck() {
        Deck newDeck = new Deck();
        newDeck.setCards(Collections.emptyList());
        deckRepo.save(newDeck);
        return newDeck;

    }

}
