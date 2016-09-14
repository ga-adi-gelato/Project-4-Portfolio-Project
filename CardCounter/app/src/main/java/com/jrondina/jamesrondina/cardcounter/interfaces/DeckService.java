package com.jrondina.jamesrondina.cardcounter.interfaces;

import com.jrondina.jamesrondina.cardcounter.models.Deck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jamesrondina on 8/29/16.
 */
public interface DeckService {

    @GET
    Call<Deck> callDeck(@Url String url);

}
