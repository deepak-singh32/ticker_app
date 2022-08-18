package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticker.adapters.RouteAdapter;
import com.example.ticker.adapters.StopAdapter;
import com.example.ticker.classes.Preferences;
import com.example.ticker.models.Bus;
import com.example.ticker.models.Route;
import com.example.ticker.models.Routes;
import com.example.ticker.models.Stop;
import com.example.ticker.models.User;
import com.example.ticker.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusActivity extends AppCompatActivity {

    AutoCompleteTextView busRoute, startStop;
    TextView busHeading, busNumber;
    final String TAG = BusActivity.class.getSimpleName();
    int previousLength = 0;
    int previousLengthStops = 0;
    Bus data = null;
    Route selectedItem = null;
    List<Route> routeData = new ArrayList<>();
    Stop stopData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();
        String empStr = bundle.getString("bus_object");
        Gson gson = new Gson();
        Type type = new TypeToken<Bus>() {
        }.getType();
        data = gson.fromJson(empStr, type);


        busRoute = findViewById(R.id.textView7);
        startStop = findViewById(R.id.textView10);
        busHeading = findViewById(R.id.bus_heading);
        busNumber = findViewById(R.id.dl_number);

        getRoutes();


//
        startStop.setAdapter(new StopAdapter(this, R.layout.stop_item_layout, data.getStops()));

        busRoute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previousLength = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 10) busHeading.setText(s.toString());

                Boolean backSpace = previousLength > s.length();

                if (backSpace) {
                    busRoute.setText("");
                    // do your stuff ...

                }
            }
        });

        busRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = routeData.get(position);
                // here is your selected item
            }
        });

        startStop.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView name = (TextView)view.findViewById(R.id.id_text);
                for(Stop s:data.getStops()){
                    if(s.getStation_name().equals(name.getText().toString())){
                        stopData = s;
                        break;
                    }
                }
            }
        });

        startStop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previousLengthStops = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Boolean backSpace = previousLengthStops > s.length();

                if (backSpace) {
                    startStop.setText("");
                    // do your stuff ...

                }
            }
        });


        busRoute.setText(data.getRoute_name() + " - " + data.getEnding_station_name());
        selectedItem = new Route(data.getRoute_id(), data.getRoute_name(), data.getStarting_station_id(), data.getStarting_station_name(), data.getEnding_station_id(), data.getEnding_station_name());
        startStop.setText(data.getStops().get(0).getStation_name());
        stopData = data.getStops().get(0);
        busNumber.setText(data.getBus_number().substring(0, 5) + "\n" + data.getBus_number().substring(5, 9));


    }

    private void getRoutes() {

        routeData.clear();
        Call<Routes> call = RetrofitClient.getInstance().getMyApi().getAllRoutes();
        call.enqueue(new Callback<Routes>() {
            @Override
            public void onResponse(Call<Routes> call, Response<Routes> response) {
                Log.e(TAG, "onResponse: " + response.code());
                if (response.isSuccessful() && response.code() == 200) {
                    Routes data = response.body();
                    routeData = data.getRoutes();

                    busRoute.setAdapter(new RouteAdapter(BusActivity.this, R.layout.route_item_layout, data.getRoutes()));


//                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Routes> call, Throwable t) {
                Toast.makeText(BusActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onclickChange(View view) {
    }

    public void onClickContinue(View view) {

        Intent intent = new Intent(this, BookActivity.class);
        if (selectedItem != null) {
            String busRoute = new Gson().toJson(selectedItem);
            Log.e(TAG, "onClickContinue: "+stopData.getStation_name());

            Integer index = data.getStops().indexOf(stopData);
            List<Stop> remList = new ArrayList<>();
            for(int i=index;i<data.getStops().size();i++) remList.add(data.getStops().get(i));

            String stop = new Gson().toJson(remList);

//            String stop = new Gson().toJson(remStops);

            intent.putExtra("route", busRoute);
            intent.putExtra("bus", data.getBus_number());
            intent.putExtra("bus_id",data.getBus_id());
            intent.putExtra("stop",stop);
            startActivity(intent);
        }

    }
}