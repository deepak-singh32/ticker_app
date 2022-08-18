package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.ticker.classes.Preferences;

public class SplashActivity extends AppCompatActivity {

    final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        if(getSupportActionBar() != null) getSupportActionBar().hide();


        String token = Preferences.getString(SplashActivity.this,"token");
        Integer userId = Preferences.getInteger(SplashActivity.this,"user_id");
        Log.e(TAG, "onCreate: "+userId.toString()+" "+token);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(token != null && token != ""){

                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }else{

                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();

            }
        },1000);


    }
}