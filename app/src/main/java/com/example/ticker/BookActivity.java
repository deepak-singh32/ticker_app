package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticker.classes.BottomSheet;
import com.example.ticker.classes.Preferences;
import com.example.ticker.models.Bus;
import com.example.ticker.models.Route;
import com.example.ticker.models.Stop;
import com.example.ticker.models.Ticket;
import com.example.ticker.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {

    TextView routename,towards,busNumber,endingStop,startingStop;
    final String TAG = BookActivity.class.getSimpleName();
    TextView first,second,third,firstse,secondse,thirdse,amount;
    List<Stop>  stopList;
    boolean five=false,ten=false,fiveteen =false;

    Integer busId;
    Route route;
    Integer endingIndex = 0;
    String busNUmber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        routename = findViewById(R.id.textView3);
        towards = findViewById(R.id.textView4);
        busNumber = findViewById(R.id.bus);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        firstse = findViewById(R.id.firstse);
        secondse = findViewById(R.id.secondse);
        thirdse = findViewById(R.id.thirdse);
        endingStop = findViewById(R.id.textView14);
        startingStop = findViewById(R.id.textView13);
        amount = findViewById(R.id.textView21);

        Bundle bundle = getIntent().getExtras();
        String empStr = bundle.getString("route");
        busNUmber = bundle.getString("bus");
        String stops = bundle.getString("stop");
        busId = bundle.getInt("bus_id");
        Gson gson = new Gson();
        Type type = new TypeToken<Route>() {}.getType();
        Type stopType= new TypeToken<List<Stop>>() {}.getType();
        route = gson.fromJson(empStr, type);
        stopList = gson.fromJson(stops, stopType);

        for(Stop s:stopList){
            Log.e(TAG, "onCreate: "+s.getStation_name());
        }

        routename.setText(route.getName());
        startingStop.setText(stopList.get(0).getStation_name());
        towards.setText("towards "+route.getEndStop());
        busNumber.setText(busNUmber);
        calculateFare();
    }

    public Integer calculateAmout(){

        int multipier = 0;
        if(five) multipier = 5;
        if(ten) multipier = 10;
        if(fiveteen) multipier = 15;
        return multipier*Integer.parseInt(secondse.getText().toString());
    }

    public void calculateFare(){
        first.setOnClickListener((v)->{

            if(stopList.size() > 0){
                Log.e(TAG, "calculateFare: "+stopList.size());
                if(stopList.size() < 5){
                    endingStop.setText(stopList.get(stopList.size()-1).getStation_name());
                    endingIndex = stopList.size()-1;
                }
                else{
                    endingStop.setText(stopList.get(4).getStation_name());
                    endingIndex = 4;
                }
                five = true;
                ten = false;
                fiveteen = false;

                amount.setText("\u20B9 "+calculateAmout().toString());
//
                first.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.ColorLightTeal));
                second.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                third.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
            }


        });

        second.setOnClickListener((v)->{
            if(stopList.size() > 5){
                if(stopList.size() < 10) {
                    endingStop.setText(stopList.get(stopList.size()-1).getStation_name());
                    endingIndex = stopList.size()-1;
                }
                else {
                    endingStop.setText(stopList.get(9).getStation_name());
                    endingIndex = 9;
                }

                five = false;
                ten = true;
                fiveteen = false;

                amount.setText("\u20B9 "+calculateAmout().toString());

                first.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                second.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.ColorLightTeal));
                third.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
            }else{
                Toast.makeText(this, "Maximum fare is 5", Toast.LENGTH_SHORT).show();
            }
        });

        third.setOnClickListener((v)->{
            if(stopList.size() > 10){
                five = false;
                ten = false;
                fiveteen = true;
                endingStop.setText(stopList.get(stopList.size()-1).getStation_name());
                endingIndex = stopList.size()-1;
                amount.setText("\u20B9 "+calculateAmout().toString());
                first.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white ));
                second.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                third.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.ColorLightTeal));
            }else{
                Toast.makeText(this, "Maximum fare is 10", Toast.LENGTH_SHORT).show();
            }
        });

        firstse.setOnClickListener((v)->{
            int get = Integer.parseInt(secondse.getText().toString());
            if(get > 1){
                get = get - 1;
                secondse.setText(String.valueOf(get));
                amount.setText("\u20B9 "+calculateAmout().toString());
            }
        });

        thirdse.setOnClickListener((v)->{
            int get = Integer.parseInt(secondse.getText().toString()) + 1;
            secondse.setText(String.valueOf(get));
            amount.setText("\u20B9 "+calculateAmout().toString());
        });
    }

    public void onclickPay(View view) {
//        param :user_id, Integer, required: true
//        param :bus_id, Integer, required: true
//        param :route_id, Integer, required: true
//        param :starting_station_id, Integer, required: true
//        param :ending_station_id, Integer, required: true
//        param :num_ticket, Integer, required: true
//        param :fare, Integer, required: true

        if(five || ten || fiveteen){

            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("");
            progressDialog.setMessage("Please wait!");
            progressDialog.show();

            int user_id = Preferences.getInteger(this,"user_id");
            HashMap<String,Integer> SendData =new HashMap<>();
            Integer amount = calculateAmout();
            SendData.put("user_id",user_id);
            SendData.put("bus_id",busId);
            SendData.put("route_id",route.getRoute_id());
            SendData.put("starting_station_id",Integer.parseInt(stopList.get(0).getStation_id()));
            SendData.put("ending_station_id",Integer.parseInt(stopList.get(endingIndex).getStation_id()));
            SendData.put("num_ticket",Integer.parseInt(secondse.getText().toString()));
            SendData.put("fare",amount);

            Log.e(TAG, "onclickPay: "+SendData);

            Call<Ticket> call = RetrofitClient.getInstance().getMyApi().bookTicket(SendData);
            call.enqueue(new Callback<Ticket>() {
                @Override
                public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                    Log.e(TAG, "onResponse: "+response.code());
                    if(response.isSuccessful() && response.code() == 200){
                        Ticket data = response.body();
                        Log.e(TAG, "onResponse: "+data.getMsg());



                        Intent intent = new Intent(BookActivity.this,HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Ticket> call, Throwable t) {
                    Toast.makeText(BookActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });


        }else{
            Toast.makeText(this, "Please select the fare of ticket", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBack(View view) {
        finish();
    }
}