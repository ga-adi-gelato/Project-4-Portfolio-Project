package com.example.ubun17.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mArr == null) {
            Log.d("aaa", "////////////////////////////////null");
        } else {
            Log.d("sss", "no null....................");
        }
    }
}
