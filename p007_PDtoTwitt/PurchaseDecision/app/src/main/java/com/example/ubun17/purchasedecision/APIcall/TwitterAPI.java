package com.example.ubun17.purchasedecision.APIcall;

import android.util.Log;

import com.example.ubun17.purchasedecision.ResponseObject.TwitterObject.TweetsSearch;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ubun17 on 9/1/16.
 */
public class TwitterAPI {
    String mSearch;

    public TwitterAPI(String str) {
        mSearch = str;
    }

    public void TwitterCalling() {
        String searchURL = "https://api.twitter.com/1.1/search/tweets.json?q="+mSearch;
        OkHttpClient twittClient = new OkHttpClient();

        final Request twittRequest = new Request.Builder()
                .addHeader("Authorization", "Bearer " +
                        "AAAAAAAAAAAAAAAAAAAAAPjFuAAAAAAAw4DhWE0PW1fC%2FNu9IqlACrmkceQ%3DAfrebWvQJeZg6ttJrEEMWod9Wa7qGSyTM05dsFzae39UE5W4ZW")
                .url(searchURL).build();

        twittClient.newCall(twittRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TwittCall", "Does not work//////////");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){

                } else {
                    Log.d("twitter", "search////////////////////////////////////");
                    String stResponse = response.body().string();
                    Gson gson = new Gson();
                    TweetsSearch tSearch = gson.fromJson(stResponse,TweetsSearch.class);
                    int tSize = tSearch.getStatuses().size();
                    for (int i = 0; i < tSize; i ++) {
                        String test = tSearch.getStatuses().get(i).getText();
                        Log.d("twitt;  ", test);
                    }



//                    int maxLogSize = 1000;
//                    String veryLongString = stResponse;
//
//                    for(int i=0; i<= veryLongString.length()/maxLogSize; i++){
//                        int start = i * maxLogSize;
//                        int end = (i+1)*maxLogSize;
//                        end = end>veryLongString.length()?veryLongString.length():end;
//                        Log.v("}}}}}}}}}", veryLongString.substring(start,end));
//                    }
                    //Log.d("Twitt text", stResponse);
                }

            }
        });
    }
}
