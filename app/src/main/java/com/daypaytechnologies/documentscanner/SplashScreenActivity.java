package com.daypaytechnologies.documentscanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash_screen);
        startTimer();
    }

    private void startTimer(){
        CountDownTimer countDownTimer = new CountDownTimer(2500, 500) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                Intent intent=new Intent(SplashScreenActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }
}
