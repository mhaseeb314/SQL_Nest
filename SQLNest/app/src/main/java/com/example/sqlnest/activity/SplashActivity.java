package com.example.sqlnest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sqlnest.R;
import com.example.sqlnest.utils.Util;

public class SplashActivity extends AppCompatActivity {

    final int SPLASH_DELAY  = 3000;
    Handler handler = null;
    Runnable runnable = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Util.setTests();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                Intent nextActivity = new Intent(SplashActivity.this , MainActivity.class);
                startActivity(nextActivity);
                finish();
            }
        };
        handler.postDelayed(runnable , SPLASH_DELAY);
    }
}
