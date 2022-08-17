package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ticker.models.Bus;
import com.example.ticker.models.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BookActivity extends AppCompatActivity {

    TextView routename,towards,busNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        routename = findViewById(R.id.textView3);
        towards = findViewById(R.id.textView4);
        busNumber = findViewById(R.id.bus);

        Bundle bundle = getIntent().getExtras();
        String empStr = bundle.getString("route");
        String busNUmber = bundle.getString("bus");
        Gson gson = new Gson();
        Type type = new TypeToken<Route>() {}.getType();
        Route route = gson.fromJson(empStr, type);

        routename.setText(route.getName());
        towards.setText("towards "+route.getEndStop());
        busNumber.setText(busNUmber);
    }

    public void onclickPay(View view) {
    }
}