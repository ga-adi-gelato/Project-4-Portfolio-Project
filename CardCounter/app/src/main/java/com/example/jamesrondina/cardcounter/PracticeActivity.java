package com.example.jamesrondina.cardcounter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jamesrondina.cardcounter.models.Card;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class PracticeActivity extends AppCompatActivity {

    private Button mUp, mDown, mShow;
    private ImageView mCard;
    private TextView mYourNum, mRealNum;
    private View mRealCount;

    private int userCount = 0;
    private int realCount = 0;
    private int timeDelay = 3000;

    private Context context = PracticeActivity.this;
    private Retrofit retrofit;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        retrofit = APIFunctions.retrofitInit(context);

        mUp = (Button) findViewById(R.id.increaseButton);
        mDown = (Button) findViewById(R.id.decreaseButton);
        mShow = (Button) findViewById(R.id.showButton);
        mYourNum = (TextView) findViewById(R.id.yourNum);
        mRealCount = findViewById(R.id.realLayout);
        mCard = (ImageView) findViewById(R.id.card);

        handler = new Handler();
        handler.postDelayed(cardCycle, timeDelay);

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
                        break;
                }
            }
        };
        mUp.setOnClickListener(listener);
        mDown.setOnClickListener(listener);
        mShow.setOnClickListener(listener);
    }

    private Runnable cardCycle = new Runnable()
    {
        public void run()
        {
            APIFunctions.drawCard(retrofit,context,mCard);

            handler.postDelayed(this, timeDelay);
        }
    };

}
