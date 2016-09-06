package com.example.jamesrondina.cardcounter.interfaces;

import com.example.jamesrondina.cardcounter.models.Card;
import com.example.jamesrondina.cardcounter.models.Deck;
import com.example.jamesrondina.cardcounter.models.NewDeck;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by jamesrondina on 8/29/16.
 */
public interface DeckService {

    @GET
    Call<Deck> callDeck(@Url String url);

}
