package com.example.jamesrondina.cardcounter.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewDeck {

    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("shuffled")
    @Expose
    private Boolean shuffled;
    @SerializedName("deck_id")
    @Expose
    private String deckId;
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
     *     The shuffled
     */
    public Boolean getShuffled() {
        return shuffled;
    }

    /**
     * 
     * @param shuffled
     *     The shuffled
     */
    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
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
