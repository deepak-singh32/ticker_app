package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.ticker.classes.BottomSheet;
import com.example.ticker.classes.HomeSheet;

public class HomeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getSupportActionBar() != null) getSupportActionBar().hide();




        HomeSheet bottomSheet = new HomeSheet();
        bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
    }
}