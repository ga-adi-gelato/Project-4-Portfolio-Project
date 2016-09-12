package com.example.jamesrondina.cardcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesrondina.cardcounter.interfaces.DeckService;
import com.example.jamesrondina.cardcounter.models.Card;
import com.example.jamesrondina.cardcounter.models.Deck;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jamesrondina on 8/29/16.
 */
public class APIFunctions {

    public static final String TAG = "API Functions";
    public static final String baseUrl = "http://deckofcardsapi.com/api/deck/";
    public static final String debugDeck = "bnnqhm4lzs6r";

    public static Retrofit retrofitInit(Context context) {

        Log.i(TAG, "beginning API Functions");

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        Retrofit retrofit = null;

        if (netInfo != null && netInfo.isConnected()) {

            //create Retrofit service

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Log.i(TAG, "Connection established");


        }
        else {
            Log.i(TAG, "Connection could not be established");
        }

        return retrofit;
    }

    public static void getDeck(Retrofit retrofit, Context context){

        Log.i(TAG, "getDeck: getting/shuffling deck");

        DeckService ds = retrofit.create(DeckService.class);

        SharedPreferences sharedPrefs =
                context.getSharedPreferences("com.example.jamesrondina.cardcounter",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPrefs.edit();


        String url;

        //if no deck exists, get a new deck
        if(!sharedPrefs.contains("deckId")) {
            url = "http://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=6";
        }

        //otherwise, shuffle the existing deck
        else{
            url = "http://deckofcardsapi.com/api/deck/" +
                    savedDeckId(context) + "/shuffle/";
        }

        Call<Deck> call = ds.callDeck(url);
        call.enqueue(new Callback<Deck>() {
            @Override
            public void onResponse(Call<Deck> call, Response<Deck> response) {
                editor.putString("deckId",response.body().getDeckId());
                editor.apply();
                Log.i(TAG, "onResponse: got deck " + response.body().getDeckId());
            }

            @Override
            public void onFailure(Call<Deck> call, Throwable t) {
                Log.e(TAG, "onFailure: could not get deck");
                t.printStackTrace();

            }
        });
    }

    public static Card dealCard(final Retrofit retrofit, final Context context) {

        DeckService ds = retrofit.create(DeckService.class);

        final Card[] card = {new Card()};

        Call<Deck> call = ds.callDeck(baseUrl + savedDeckId(context) + "/draw/" );
        call.enqueue(new Callback<Deck>() {
            @Override
            public void onResponse(Call<Deck> call, Response<Deck> response) {
                if(response.body().getRemaining() < 1) {
                    getDeck(retrofit, context);
                }

                card[0] = response.body().getCards().get(0);

                Log.i(TAG, "onResponse: Card drawn: " + card[0].getCode());
                Log.i(TAG, "onResponse: image: " + card[0].getImage());
                Log.i(TAG, "onResponse: " + response.body().getRemaining());
            }

            @Override
            public void onFailure(Call<Deck> call, Throwable t) {

                t.printStackTrace();

            }
        });

        return card[0];
    }

    public static void drawCard(final Retrofit retrofit, final Context context, final ImageView imageView, final TextView textView) {

        Log.i(TAG, "drawCard: drawing card");

        DeckService ds = retrofit.create(DeckService.class);

        final Card[] card = {new Card()};

        Call<Deck> call = ds.callDeck(baseUrl + savedDeckId(context) + "/draw/" );
        call.enqueue(new Callback<Deck>() {
            @Override
            public void onResponse(Call<Deck> call, Response<Deck> response) {

                if(response.body().getRemaining() < 1) {
                    getDeck(retrofit, context);
                }

                card[0] = response.body().getCards().get(0);

                Log.i(TAG, "onResponse: Card drawn: " + card[0].getCode());
                Log.i(TAG, "onResponse: image: " + card[0].getImage());
                Log.i(TAG, "onResponse: " + response.body().getRemaining());

                Picasso.with(context)
                        .load(card[0].getImage())
                        .into(imageView);

                //gets the number that's currently in the textView, and then modifies it based on the card drawn

                int currentCount = Integer.parseInt(textView.getText().toString());

                textView.setText(String.valueOf(currentCount + cardValue(card[0].getValue())));

                String realCount = textView.getText().toString();
                if((Integer.parseInt(realCount)) > 0 ) {
                    textView.setText("+" + realCount);
                }

            }

            @Override

            public void onFailure(Call<Deck> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    public static String savedDeckId(Context context) {

        SharedPreferences sharedPrefs =
                context.getSharedPreferences("com.example.jamesrondina.cardcounter",Context.MODE_PRIVATE);

        String deckId = sharedPrefs.getString("deckId",debugDeck);

        Log.i(TAG, "savedDeckId: " + deckId);

        return sharedPrefs.getString("deckId",debugDeck);

    }

    public static int cardValue(String value){

        //define values according to hi-lo method

        switch (value){
            case ("2"):
                return 1;
            case ("3"):
                return 1;
            case ("4"):
                return 1;
            case ("5"):
                return 1;
            case ("6"):
                return 1;
            case ("7"):
                return 0;
            case ("8"):
                return 0;
            case ("9"):
                return 0;
            case ("10"):
                return -1;
            case ("ACE"):
                return -1;
            case ("JACK"):
                return -1;
            case ("QUEEN"):
                return -1;
            case ("KING"):
                return -1;
        }
        return 0;
    }
}
