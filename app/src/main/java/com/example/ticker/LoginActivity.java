package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticker.classes.Preferences;
import com.example.ticker.models.User;
import com.example.ticker.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText phone;
    final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        phone = findViewById(R.id.phone);
    }

//    public void onclickNext(View view) {
//
//        startActivity(new Intent(view.getContext(),MainActivity.class));
//    }



    public void onClickName(View view) {
    }

    public void onClickPhone(View view) {
    }

    public void onclickOtp(View view) {

        Log.d("LoginActivity", "onclickOtp: "+phone.getText().toString());
            Call<User> call = RetrofitClient.getInstance().getMyApi().sendOtp(phone.getText().toString());

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if(response.isSuccessful() && response.code() == 200){
                        User data = response.body();
                        Log.e(TAG, "onResponse: "+data.getUser_id().toString());
                        Preferences.saveInteger(LoginActivity.this,"user_id",data.getUser_id());

                        Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
                        intent.putExtra("user_id",data.getUser_id());
                        intent.putExtra("phone",phone.getText().toString());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });


        }

}