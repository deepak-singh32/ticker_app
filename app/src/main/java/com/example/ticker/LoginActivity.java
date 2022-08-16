package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null) getSupportActionBar().hide();
    }

    public void onclickNext(View view) {

        startActivity(new Intent(view.getContext(),MainActivity.class));
    }
}