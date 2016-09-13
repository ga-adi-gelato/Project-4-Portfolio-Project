package com.example.jamesrondina.cardcounter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jamesrondina.cardcounter.models.Card;
import com.example.jamesrondina.cardcounter.models.LocalShoe;

import retrofit2.Retrofit;

public class PracticeActivity extends AppCompatActivity {

    private Button mUp, mDown, mShow;
    private ImageView mCard;
    private TextView mYourNum, mRealNum;
    private View mRealCount;
    private Switch mSpeed;

    private static final int SLOW = 2000;
    private static final int FAST = 900;
    private int userCount = 0;
    private int realCount = 0;
    private int timeDelay = SLOW;
    private int remaining = 312;

    private LocalShoe shoe;
    private Card newCard;

    //uncomment to make app use API
    //private Context context = PracticeActivity.this;
    //private Retrofit retrofit;

    private Handler handler;

    //TODO: Handle no network

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        //uncomment to make app use API
        //retrofit = APIFunctions.retrofitInit(context);

        mUp = (Button) findViewById(R.id.increaseButton);
        mDown = (Button) findViewById(R.id.decreaseButton);
        mShow = (Button) findViewById(R.id.showButton);
        mYourNum = (TextView) findViewById(R.id.yourNum);
        mRealCount = findViewById(R.id.realLayout);
        mCard = (ImageView) findViewById(R.id.card);
        mRealNum = (TextView) findViewById(R.id.realNum);
        mSpeed = (Switch) findViewById(R.id.speedSwitch);

        shoe = new LocalShoe(); //comment this out if you will be using API instead of local objects

        mSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean fast) {
                if(fast){
                    timeDelay = FAST;
                }
                else {
                    timeDelay = SLOW;
                }
            }
        });


        shoe.loadShoe(); //comment this out if you don't want to use the local objects
        drawCard(); //comment this out if you don't want to use the local objects

        //uncomment to make app use API
        //APIFunctions.getDeck(retrofit, context); //prepare deck at start of activity
        //APIFunctions.drawCard(retrofit,context,mCard,mRealNum); //prepare initial card

        handler = new Handler();

        //button row functionality

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.increaseButton:
                        userCount++;
                        if(userCount > 0){
                            mYourNum.setText("+" + String.valueOf(userCount));
                        }
                        else {
                            mYourNum.setText(String.valueOf(userCount));
                        }
                        break;
                    case R.id.decreaseButton:
                        userCount--;
                        if(userCount > 0){
                            mYourNum.setText("+" + String.valueOf(userCount));
                        }
                        else {
                            mYourNum.setText(String.valueOf(userCount));
                        }
                        break;
                    case R.id.showButton:
                        if(mRealCount.getVisibility() == View.INVISIBLE) {
                            mRealCount.setVisibility(View.VISIBLE);
                        }
                        else {
                            mRealCount.setVisibility(View.INVISIBLE);
                        }

                        if(mRealCount.getVisibility() == View.INVISIBLE) {
                            mShow.setText("SHOW COUNT");
                        }
                        else {
                            mShow.setText("HIDE COUNT");
                        }
                        break;
                }
            }
        };
        mUp.setOnClickListener(listener);
        mDown.setOnClickListener(listener);
        mShow.setOnClickListener(listener);

        //TODO: When shoe is empty, create a dialog box to show score, and give option to quit or try again
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //start the loop
        handler.postDelayed(cardCycle, timeDelay);
    }
    //stop the loop when you leave the activity
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Practice", "onPause: Quitting");
        handler.removeCallbacks(cardCycle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("Practice", "onDestroy: Quitting");
        handler.removeCallbacks(cardCycle);
    }

    private Runnable cardCycle = new Runnable()
    {
        public void run()
        {

            drawCard(); //comment out if you don't want to use local objects

            //uncomment to make app use API
            //APIFunctions.drawCard(retrofit,context,mCard,mRealNum);
            handler.postDelayed(this, timeDelay);
        }
    };

    private void drawCard() {
        newCard = shoe.draw();
        updateCard();
        updateCount();
    }

    private void updateCard() {
        mCard.setImageResource(newCard.getResId());
    }

    private void updateCount() {

        realCount += newCard.countVal();
        if(realCount > 0) {
            mRealNum.setText("+" + String.valueOf(realCount));
        }
        else {
            mRealNum.setText(String.valueOf(realCount));
        }

    }

}