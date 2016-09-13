package com.jrondina.jamesrondina.cardcounter.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamesrondina on 9/11/16.
 * Blackjack doesn't really need an API, but I had to include one as part of the grading requirements
 * This class is for the offline component, which makes the app better and is more fun to code than
 * making API calls all day in my opinion
 */
public class Hand {
    List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void empty() {
        cards.clear();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int value() {

        int value = 0;

        for (Card card: cards
             ) {
            value += card.getBjackVal();
        }

        if (value > 21 && containsAce()){
            changeAceValue();
            value -= 10;
        }

        return value;
    }

    public boolean isBlackJack(){
        return (value() == 21);
    }

    public boolean isBust(){

        //runs changeAceValue() to try to keep value below 21

        if  (value() >21 && containsAce()) {
            changeAceValue();
            isBust(); //recursive call to check new value
        }

        return (value() > 21);
    }

    public Boolean containsAce(){
        for (Card card:cards
             ) {
            if(card.getBjackVal()==11){
                return true;
            }
        }
        return false;
    }

    public void changeAceValue(){

        //changes aces from 11 to 1

        for (Card card:cards
             ) {
            if (card.getBjackVal()==11){
                card.setBjackVal(1);
                break;
            }

        }
    }

}
