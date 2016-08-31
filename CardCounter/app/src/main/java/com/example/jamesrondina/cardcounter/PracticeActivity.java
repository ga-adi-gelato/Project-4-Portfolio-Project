package com.example.jamesrondina.cardcounter;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PracticeActivity extends AppCompatActivity {

    private Button mUp, mDown, mShow;
    private ImageView mCard;
    private TextView mYourNum, mRealNum;
    private View mRealCount;
    private int userCount = 0;
    private int realCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        int timeDelay = 1000;

        mUp = (Button) findViewById(R.id.increaseButton);
        mDown = (Button) findViewById(R.id.decreaseButton);
        mShow = (Button) findViewById(R.id.showButton);
        mYourNum = (TextView) findViewById(R.id.yourNum);
        mRealCount = findViewById(R.id.realLayout);
        
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
}
