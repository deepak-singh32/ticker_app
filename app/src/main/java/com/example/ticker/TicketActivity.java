package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ticker.databinding.ActivityTicketBinding;
import com.example.ticker.models.BusTicket;
import com.example.ticker.models.Route;
import com.example.ticker.models.Stop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TicketActivity extends AppCompatActivity {

    final String TAG = TicketActivity.class.getSimpleName();
    ActivityTicketBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if(getSupportActionBar() != null) getSupportActionBar().hide();


        Bundle bundle = getIntent().getExtras();
        String empStr = bundle.getString("ticket");
        Type type = new TypeToken<BusTicket>() {}.getType();
        Gson gson = new Gson();
        BusTicket ticket = gson.fromJson(empStr, type);

        binding.amount.setText("\u20B9 "+ticket.getFare().toString());
        binding.dlNumber.setText(ticket.getBus_number());
        binding.fare.setText("\u20B9 "+ticket.getFare().toString());
        binding.ticketNum.setText(ticket.getNum_ticket().toString());
        binding.starting.setText(ticket.getStarting_station().toString());
        binding.ending.setText(ticket.getEnding_station().toString());
        binding.timestamp.setText(ticket.getBooking_time());


        Log.e(TAG, "onCreate: "+ticket.getBooking_time());
    }

    public void onClickCancel(View view) {
        finish();
    }
}