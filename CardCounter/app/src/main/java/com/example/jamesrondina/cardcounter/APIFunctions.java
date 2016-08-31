package com.example.jamesrondina.cardcounter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jamesrondina on 8/29/16.
 */
public class APIFunctions {

    public static final String TAG = "API Functions";
    public static final String baseUrl = "http://deckofcardsapi.com/api/deck/";

    public Retrofit retrofitInit(Context context) {

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

}
