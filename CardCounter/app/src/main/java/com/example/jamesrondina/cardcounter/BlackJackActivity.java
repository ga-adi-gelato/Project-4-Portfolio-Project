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

import com.example.jamesrondina.cardcounter.models.Card;
import com.example.jamesrondina.cardcounter.models.Hand;
import com.example.jamesrondina.cardcounter.models.LocalShoe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class BlackJackActivity extends AppCompatActivity {

    public static final String TAG = "BlackJackActivity";

    private ImageView faceDown, dCard0, dCard1, dCard2, dCard3, dCard4, dCard5, dCard6, dCard7, dCard8, dCard9, dCard10, dCard11, dCard12,
    pCard0, pCard1, pCard2, pCard3, pCard4, pCard5, pCard6, pCard7, pCard8, pCard9, pCard10, pCard11, pCard12;
    private Button hitButton, standButton, dealButton;
    private TextView winner, cont, dValue, pValue;

    private List<ImageView> playerViews, dealerViews;

    private LocalShoe shoe;
    private Hand pHand, dHand;

    private boolean stand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);

        setObjects();

        shoe = new LocalShoe();
        shoe.loadShoe();
        pHand = new Hand();
        dHand = new Hand();

        //debug stuff
        //pHand.getCards().add(draw());
        //dHand.getCards().add(draw());
        //Log.d(TAG, "onCreate: value " + pHand.getCards().get(0).getBjackVal() + " " + dHand.getCards().get(0).getBjackVal());
        //dCard0.setImageResource(dHand.getCards().get(0).getResId());
        //dCard0.setVisibility(View.VISIBLE);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.hitButton:
                        draw(pHand,playerViews);
                        Log.i(TAG, "hit: current hand: " + pHand.value());
                        break;
                    case R.id.standButton:
                        toggleButton(hitButton);
                        stand = true;
                        dealerMove();
                        break;
                    case R.id.dealButton:
                        faceDown.setVisibility(View.VISIBLE);
                        deal();
                        break;
                }

                checkOutcome();

            }
        };

        hitButton.setOnClickListener(listener);
        standButton.setOnClickListener(listener);
        dealButton.setOnClickListener(listener);

    }

    private void setObjects(){
        faceDown = (ImageView) findViewById(R.id.facedown);
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
        
        playerViews = new ArrayList<>();
        dealerViews = new ArrayList<>();

        dealerViews.add(dCard0);
        dealerViews.add(dCard1);
        dealerViews.add(dCard2);
        dealerViews.add(dCard3);
        dealerViews.add(dCard4);
        dealerViews.add(dCard5);
        dealerViews.add(dCard6);
        dealerViews.add(dCard7);
        dealerViews.add(dCard8);
        dealerViews.add(dCard9);
        dealerViews.add(dCard10);
        dealerViews.add(dCard11);
        dealerViews.add(dCard12);
        
        playerViews.add(pCard0);
        playerViews.add(pCard1);
        playerViews.add(pCard2);
        playerViews.add(pCard3);
        playerViews.add(pCard4);
        playerViews.add(pCard5);
        playerViews.add(pCard6);
        playerViews.add(pCard7);
        playerViews.add(pCard8);
        playerViews.add(pCard9);
        playerViews.add(pCard10);
        playerViews.add(pCard11);
        playerViews.add(pCard12);

        hitButton = (Button) findViewById(R.id.hitButton);
        standButton = (Button) findViewById(R.id.standButton);
        dealButton = (Button) findViewById(R.id.dealButton);

        cont = (TextView) findViewById(R.id.continueText);
        winner = (TextView) findViewById(R.id.outcomeText);

        toggleButton(hitButton);
        toggleButton(standButton);

        dValue = (TextView) findViewById(R.id.dNum);
        pValue = (TextView) findViewById(R.id.pNum);

    }

    private void draw(Hand hand, List<ImageView> views) {

        Card newCard = shoe.draw();
        
        //add card to hand and display in appropriate places
        hand.add(newCard);

        int currentView = hand.size() - 1;
        views.get(currentView).setImageResource(newCard.getResId());
        views.get(currentView).setVisibility(View.VISIBLE);


    }

    private void deal() {

        hideWinner();
        resetCardViews();

        toggleButton(hitButton);
        toggleButton(standButton);
        toggleButton(dealButton);

        //initial 2 cards for player and dealer
        draw(pHand, playerViews);
        draw(pHand, playerViews);

        Log.i(TAG, "deal: current hand: " + pHand.value());

        //sets one of dealers card as facedown

        draw(dHand, dealerViews);
        draw(dHand, dealerViews);

    }

    private void dealerMove() {
        //TODO: dealer decides to hit or stand
        if (dHand.value() < 17) {
            flipUp();
            draw(dHand, dealerViews);
            dealerMove();
        }

    }

    private void checkOutcome(){

        //Win conditions:
        //Player wins if hand has:
        //1. Blackjack and dealer doesn't have blackjack
        //2. Dealer busts
        //3. Player stands and has a higher total than delaer without going over 21

        if (pHand.isBust()) {
            dealerWin();
        }

        if (pHand.isBlackJack() && dHand.isBlackJack()){
            push();
        }

        if ((pHand.isBlackJack() && !dHand.isBlackJack())|| dHand.isBust()) {
            flipUp();
            playerWin();
        }

        if(stand){
            if (pHand.value() > dHand.value()) {
                playerWin();
            }
            else if(pHand.value() == dHand.value()) {
                push();
            }
            else {
                flipUp();
                dealerWin();
            }
        }


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

    private void playerWin(){
        Log.i(TAG, "playerWin: " + pHand.value() + " vs " + dHand.value());
        showWinner("Player Wins!");
        reset();
    }

    private void dealerWin(){
        Log.i(TAG, "dealerWin: " + dHand.value() + " vs " + pHand.value());
        showWinner("Dealer Wins!");
        reset();
    }

    private void push() {
        Log.i(TAG, "push: " + pHand.value());
        showWinner("Push");
        reset();
    }

    private void reset() {
        resetButtons();
        resetHands();
        stand = false;
    }

    private void resetHands(){
        pHand.empty();
        dHand.empty();

    }

    private void resetCardViews() {

        flipDown();

        //reset card views after round is played
        for (ImageView view: dealerViews
                ) {
            view.setVisibility(View.INVISIBLE);
        }

        for (ImageView view: playerViews
                ) {
            view.setVisibility(View.INVISIBLE);
        }

    }

    private void resetButtons() {
        //set buttons back to default state after round is played
        hitButton.setEnabled(false);
        hitButton.setAlpha(0.5f);
        standButton.setEnabled(false);
        standButton.setAlpha(0.5f);
        dealButton.setEnabled(true);
        dealButton.setAlpha(1);
    }

    private void flipDown() {

        faceDown.setVisibility(View.VISIBLE);
        dCard0.setVisibility(View.INVISIBLE);

    }

    private void flipUp(){

        faceDown.setVisibility(View.INVISIBLE);
        dCard0.setVisibility(View.VISIBLE);

    }

    private void showWinner(String outcome) {
        winner.setText(outcome);
        winner.setVisibility(View.VISIBLE);
        cont.setVisibility(View.VISIBLE);

    }

    private void hideWinner() {
        winner.setVisibility(View.INVISIBLE);
        cont.setVisibility(View.INVISIBLE);
    }

}
