package com.example.jamesrondina.cardcounter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesrondina.cardcounter.models.LocalShoe;

import retrofit2.Retrofit;

public class BlackJackActivity extends AppCompatActivity {

    public static final String TAG = "BlackJackActivity";

    private ImageView dCard0, dCard1, dCard2, dCard3, dCard4, dCard5, dCard6, dCard7, dCard8, dCard9, dCard10, dCard11, dCard12,
    pCard0, pCard1, pCard2, pCard3, pCard4, pCard5, pCard6, pCard7, pCard8, pCard9, pCard10, pCard11, pCard12;
    private Button hitButton, standButton, dealButton;
    private TextView dValue, pValue;

    private ImageView[] dealerViews = {dCard0, dCard1, dCard2, dCard3, dCard4, dCard5, dCard6, dCard7, dCard8, dCard9, dCard10, dCard11, dCard12};
    private ImageView[] playerViews = {pCard0, pCard1, pCard2, pCard3, pCard4, pCard5, pCard6, pCard7, pCard8, pCard9, pCard10, pCard11, pCard12};

    private LocalShoe shoe;

    private int pCards, dCards;

    Context context = BlackJackActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        setObjects();

        shoe = new LocalShoe();
        shoe.loadShoe();

        //debug stuff

        Log.d(TAG, "card functions check");
        Log.d(TAG, "First card First deck value" + shoe.getDecks().get(0).getCards().get(0).getBjackVal());
        Log.d(TAG, "2nd card 2nd deck value" + shoe.getDecks().get(1).getCards().get(1).getBjackVal());
        Log.d(TAG, "3rd card 3rd deck value" + shoe.getDecks().get(2).getCards().get(2).getBjackVal());
        Log.d(TAG, "4th card 4th deck value" + shoe.getDecks().get(3).getCards().get(3).getBjackVal());
        Log.d(TAG, "5th card 5th deck value" + shoe.getDecks().get(4).getCards().get(4).getBjackVal());
        Log.d(TAG, "last card last deck value" + shoe.getDecks().get(5).getCards().get(51).getBjackVal());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.hitButton:
                        //TODO: On hit, deal card
                        break;
                    case R.id.standButton:
                        //TODO: On stand, check dealer face down card, if under 17 hit, calculate blackjack or bust
                        break;
                    case R.id.dealButton:
                        //TODO: Deal one facedown card and one faceup card for the dealer, and two faceup cards for the player
                        //TODO: Check for blackjack for both dealer and player
                        toggleButton(dealButton);
                        toggleButton(hitButton);
                        toggleButton(standButton);
                        break;

                }

            }
        };

        hitButton.setOnClickListener(listener);
        standButton.setOnClickListener(listener);
        dealButton.setOnClickListener(listener);

    }

    private void setObjects(){
        dCard0 = (ImageView) findViewById(R.id.dCard0);
        dCard1 = (ImageView) findViewById(R.id.dCard1);
        dCard2 = (ImageView) findViewById(R.id.dCard2);
        dCard3 = (ImageView) findViewById(R.id.dCard3);
        dCard4 = (ImageView) findViewById(R.id.dCard4);
        dCard5 = (ImageView) findViewById(R.id.dCard5);
        dCard6 = (ImageView) findViewById(R.id.dCard6);
        dCard7 = (ImageView) findViewById(R.id.dCard7);
        dCard8 = (ImageView) findViewById(R.id.dCard8);
        dCard9 = (ImageView) findViewById(R.id.dCard9);
        dCard10 = (ImageView) findViewById(R.id.dCard10);
        dCard11 = (ImageView) findViewById(R.id.dCard11);
        dCard12 = (ImageView) findViewById(R.id.dCard12);
        pCard0 = (ImageView) findViewById(R.id.pCard0);
        pCard1 = (ImageView) findViewById(R.id.pCard1);
        pCard2 = (ImageView) findViewById(R.id.pCard2);
        pCard3 = (ImageView) findViewById(R.id.pCard3);
        pCard4 = (ImageView) findViewById(R.id.pCard4);
        pCard5 = (ImageView) findViewById(R.id.pCard5);
        pCard6 = (ImageView) findViewById(R.id.pCard6);
        pCard7 = (ImageView) findViewById(R.id.pCard7);
        pCard8 = (ImageView) findViewById(R.id.pCard8);
        pCard9 = (ImageView) findViewById(R.id.pCard9);
        pCard10 = (ImageView) findViewById(R.id.pCard10);
        pCard11 = (ImageView) findViewById(R.id.pCard11);
        pCard12 = (ImageView) findViewById(R.id.pCard12);

        hitButton = (Button) findViewById(R.id.hitButton);
        standButton = (Button) findViewById(R.id.standButton);
        dealButton = (Button) findViewById(R.id.dealButton);

        toggleButton(hitButton);
        toggleButton(standButton);

        dValue = (TextView) findViewById(R.id.dNum);
        pValue = (TextView) findViewById(R.id.pNum);

    }

    private void toggleButton(Button button) {
        if(button.getAlpha() == 0.5f) {
            button.setEnabled(true);
            button.setAlpha(1);
        }
        else {
            button.setEnabled(false);
            button.setAlpha(0.5f);
        }
    }

    private void resetCardViews() {

        for (int i = 0; i < dealerViews.length; i++) {
            dealerViews[i].setVisibility(View.INVISIBLE);
            playerViews[i].setVisibility(View.INVISIBLE);
        }

        pCards = 0;
        dCards = 0;

    }

    private void resetButtons() {
        hitButton.setEnabled(false);
        hitButton.setAlpha(0.5f);
        standButton.setEnabled(false);
        standButton.setAlpha(0.5f);
        dealButton.setEnabled(true);
        dealButton.setAlpha(1);
    }
}
