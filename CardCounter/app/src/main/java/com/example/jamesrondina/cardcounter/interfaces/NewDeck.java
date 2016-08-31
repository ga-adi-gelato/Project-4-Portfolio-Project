package com.example.jamesrondina.cardcounter.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jamesrondina on 8/29/16.
 */
public interface NewDeck {

    @GET("new/shuffle/?deck_count=6")
    Call<NewDeck> getDeck();
}
