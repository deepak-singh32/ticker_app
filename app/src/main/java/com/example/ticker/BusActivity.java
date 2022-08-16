package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticker.adapters.RouteAdapter;
import com.example.ticker.models.Bus;
import com.example.ticker.models.Route;
import com.example.ticker.retrofit.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusActivity extends AppCompatActivity {

    AutoCompleteTextView busRoute,startStop;
    TextView busHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        busRoute = findViewById(R.id.textView7);
        startStop = findViewById(R.id.textView10);
        busHeading = findViewById(R.id.bus_heading);

       ArrayList<Route> days = new ArrayList<>();
       days.add(new Route("511a","badarpur","daula kaun"));
        days.add(new Route("511","badarpur","daula kaun"));
        days.add(new Route("433","badarpur","Old delhi"));

        busRoute.setAdapter(new RouteAdapter(this, R.layout.route_item_layout,days));

        busRoute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 10 ) busHeading.setText(s.toString());
            }
        });

        Call<Bus> call = RetrofitClient.getInstance().getMyApi().getBusDetails("DL1PC5308");

        call.enqueue(new Callback<Bus>() {
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
                Bus data = response.body();
                Log.d("BusActivity", "onResponse: " + response.body());
                busRoute.setText(data.getRoute_name() + " - " + data.getEnding_station_name());



                // Create adapter passing in the sample user data
                // Attach the adapter to the recyclerview to populate items
                // Set layout manager to position the items

            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
                Log.e("BusActivity", "onFailure: "+t.getMessage());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void onclickChange(View view) {
    }

    public void onClickContinue(View view) {
        startActivity(new Intent(this,BookActivity.class));
    }
}