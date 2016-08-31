
package com.example.jamesrondina.cardcounter.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deck {

    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("deck_id")
    @Expose
    private String deckId;
    @SerializedName("cards")
    @Expose
    private List<Card> cards = new ArrayList<Card>();
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * 
     * @return
     *     The remaining
     */
    public Integer getRemaining() {
        return remaining;
    }

    /**
     * 
     * @param remaining
     *     The remaining
     */
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    /**
     * 
     * @return
     *     The deckId
     */
    public String getDeckId() {
        return deckId;
    }

    /**
     * 
     * @param deckId
     *     The deck_id
     */
    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    /**
     * 
     * @return
     *     The cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * 
     * @param cards
     *     The cards
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * 
     * @return
     *     The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
