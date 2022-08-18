package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticker.classes.Preferences;
import com.example.ticker.models.Bus;
import com.example.ticker.models.Otp;
import com.example.ticker.models.User;
import com.example.ticker.retrofit.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    EditText first,second,third,fourth;
    TextView otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        otp = findViewById(R.id.otptext);

        String otpstring = getIntent().getStringExtra("otp");

        otp.setText("Enter your otp code sent on "+otpstring);

        nextFocus(first, second);
        nextFocus(second,third);
        nextFocus(third,fourth);

        fourth.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if((first.getText().toString()+second.getText().toString()+third.getText().toString()+fourth.getText().toString()).length() == 4){
                      verifyOtp();
                    }
                    return true;
                }
                return false;
            }
        });




    }

    private Integer getOtp(){
        return Integer.parseInt((first.getText().toString()+second.getText().toString()+third.getText().toString()+fourth.getText().toString()));
    }

   private void verifyOtp(){
       Integer userId = Preferences.getInteger(OtpActivity.this,"user_id");
       Integer inputOtp = getOtp();
       Log.e("OtpActivity", "verifyOtp: "+userId.toString()+" "+inputOtp.toString());
       HashMap<String,Integer> SendData =new HashMap<>();
       SendData.put("user_id",userId);
       SendData.put("otp",inputOtp);

       Call<Otp> call = RetrofitClient.getInstance().getMyApi().verfityOtp(SendData);

       call.enqueue(new Callback<Otp>() {
           @Override
           public void onResponse(Call<Otp> call, Response<Otp> response) {
               Log.e("OtpActivity", "onResponse: "+response.code());

               if(response.isSuccessful() && response.code() == 200){
                   Otp data = response.body();

                   Preferences.saveString(OtpActivity.this,"token",data.getToken());
                   Intent intent = new Intent(OtpActivity.this,HomeActivity.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent);
               }
           }

           @Override
           public void onFailure(Call<Otp> call, Throwable t) {
               Toast.makeText(OtpActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
           }
       });
   }

    private void nextFocus(EditText first , EditText second){
        first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (first.getText().toString().length() == 1) {

                    first.clearFocus();
                    second.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}