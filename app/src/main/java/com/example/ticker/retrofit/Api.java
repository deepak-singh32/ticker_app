package com.example.ticker.retrofit;


import com.example.ticker.models.Bus;
import com.example.ticker.models.Otp;
import com.example.ticker.models.Routes;
import com.example.ticker.models.Ticket;
import com.example.ticker.models.Tickets;
import com.example.ticker.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://shortify.tech/api/";
    //v2/everything?q=tech&apiKey=0b146da42a50460697392774f1b715c1/
    @GET("bus/get_bus_detail")
    Call<Bus> getBusDetails(@Query("bus_number") String busNumber);


    @FormUrlEncoded
    @POST("user/login")
    Call<User> sendOtp(@Field("number") String phone);

    @Headers("Content-Type: application/json")
    @POST("user/verify_otp")
    Call<Otp> verfityOtp(@Body HashMap payload);

    @GET("route/get_all_routes")
    Call<Routes> getAllRoutes();

    @POST("ticket/book_ticket")
    Call<Ticket> bookTicket(@Body HashMap payload);

    @GET("ticket/get_tickets")
    Call<Tickets> getTickets(@Query("user_id") Integer Userid);
}
