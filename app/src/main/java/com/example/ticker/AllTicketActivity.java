package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ticker.adapters.TicketAdapter;
import com.example.ticker.classes.Preferences;
import com.example.ticker.models.BusTicket;
import com.example.ticker.models.Routes;
import com.example.ticker.models.Ticket;
import com.example.ticker.models.Tickets;
import com.example.ticker.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTicketActivity extends AppCompatActivity {

    final String TAG = AllTicketActivity.class.getSimpleName();
    TicketAdapter ticketAdapter;
    List<BusTicket> list = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ticket);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerview);
        ticketAdapter = new TicketAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(ticketAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        getAllTicket();
    }


    private void getAllTicket(){
        list.clear();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("Please wait!");
        progressDialog.show();
        Integer userid = Preferences.getInteger(this,"user_id");
        Call<Tickets> call = RetrofitClient.getInstance().getMyApi().getTickets(userid);
        call.enqueue(new Callback<Tickets>() {
            @Override
            public void onResponse(Call<Tickets> call, Response<Tickets> response) {
                Log.e(TAG, "onResponse: "+response.code());
                if(response.isSuccessful() && response.code() == 200){
                    Tickets data = response.body();
                    list.addAll(data.getTickets());
                    ticketAdapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Tickets> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(AllTicketActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}