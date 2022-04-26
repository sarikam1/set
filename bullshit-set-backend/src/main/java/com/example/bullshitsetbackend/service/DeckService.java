package com.example.bullshitsetbackend.service;

import com.example.bullshitsetbackend.controller.GameController;
import com.example.bullshitsetbackend.domain.Card;
import com.example.bullshitsetbackend.domain.Deck;
import com.example.bullshitsetbackend.domain.Set;
import com.example.bullshitsetbackend.enums.*;
import com.example.bullshitsetbackend.repository.DeckRepo;
import com.example.bullshitsetbackend.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

@Service
public class DeckService {
    private final static Logger LOGGER = Logger.getLogger(DeckService.class.getName());
    private final DeckRepo deckRepo;

    @Autowired
    public DeckService(DeckRepo deckRepo) {
        this.deckRepo = deckRepo;
    }

    public void printDeck(Deck deck) {
        LOGGER.info("Deck id is " + deck.getId());
        for (Card card : deck.getCards()) {
            LOGGER.info("Status: " + card.getStatus() +
                    " Color: " + card.getColor() +
                    " Number: " + card.getNumber() +
                    " Shape: " + card.getShape() +
                    " Shading: " + card.getShading());
        }
    }

    public ArrayList<Card> initializeCards(Deck deck) {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (CardColor color : CardColor.values()) {
            for (CardNumber number : CardNumber.values()) {
                for (CardShading shading : CardShading.values()) {
                    for (CardShape shape : CardShape.values()) {
                        Card card = new Card(color, shading, shape, number, CardStatus.IN_DECK, deck);
                        cards.add(card);
                    }
                }
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public Deck createDeck() {
        Deck newDeck = new Deck();
        newDeck.setCards(initializeCards(newDeck));
        deckRepo.save(newDeck);
        return newDeck;
    }

}
