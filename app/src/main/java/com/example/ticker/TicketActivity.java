package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TicketActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        if(getSupportActionBar() != null) getSupportActionBar().hide();
    }
}