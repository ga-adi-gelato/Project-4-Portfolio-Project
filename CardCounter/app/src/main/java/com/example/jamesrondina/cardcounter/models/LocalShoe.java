package com.example.jamesrondina.cardcounter.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesrondina on 9/11/16.
 */
public class LocalShoe {
    List<LocalDeck> decks = new ArrayList<>();

    public List<LocalDeck> getDecks() {
        return decks;
    }

    public void setDecks(List<LocalDeck> decks) {
        this.decks = decks;
    }

    public void loadShoe() {

        //creates 6 decks and shuffles them, then
        //loads 6 decks into the shoe, 6 is the standard for blackjack

        for (int i = 0; i < 6; i++) {
            LocalDeck deck = new LocalDeck();
            deck.loadCards();
            deck.shuffle();
            decks.add(deck);
        }
    }
}
