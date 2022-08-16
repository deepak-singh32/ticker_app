package com.example.ticker.retrofit;


import com.example.ticker.models.Bus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://shortify.tech/api/";
    //v2/everything?q=tech&apiKey=0b146da42a50460697392774f1b715c1/
    @GET("bus/get_bus_detail")
    Call<Bus> getBusDetails(@Query("bus_number") String busNumber);
}
