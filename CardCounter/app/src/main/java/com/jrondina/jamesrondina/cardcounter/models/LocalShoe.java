package com.jrondina.jamesrondina.cardcounter.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesrondina on 9/11/16.
 * Blackjack doesn't really need an API, but I had to include one as part of the grading requirements
 * This class is for the offline component, which makes the app better and is more fun to code than
 * making API calls all day in my opinion
 */
public class LocalShoe {
    List<LocalDeck> decks = new ArrayList<>();

    public List<LocalDeck> getDecks() {
        return decks;
    }

    public void setDecks(List<LocalDeck> decks) {
        this.decks = decks;
    }

    public Card draw(){
        if (size() < 1) {
            //if the shoe is empty, make a new shoe
            loadShoe();
            draw(); //recursive call after new shoe is created
        }

        if (decks.get(0).size() < 1){
            //if deck is empty, remove that deck and go on to the next one
            decks.remove(0);
            draw(); //recursive call to draw from next deck
        }

        //once we get past flow control statements we can draw the first card

        Card dealCard;

        dealCard = decks.get(0).getCards().get(0);
        decks.get(0).getCards().remove(0);

        return dealCard;
    }

    public int size() {
        return decks.size();
    }

    public void remove() {
        decks.remove(0);
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
