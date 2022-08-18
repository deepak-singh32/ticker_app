package com.example.ticker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.ticker.classes.BottomSheet;
import com.example.ticker.models.Bus;
import com.example.ticker.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.zxing.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    private FrameLayout frameLayout;
    BottomSheet bottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame_layout);


        DisplayMetrics displayMetrics = getApplication().getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int maxHeiht = (int) (height*0.73);


        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,maxHeiht));





        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        checkBusNumber(result.getText());
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        bottomSheet = new BottomSheet();
        bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());


    }

    private void checkBusNumber(String busNum){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("");
        progressDialog.setMessage("Please wait!");
        progressDialog.show();
        Call<Bus> call = RetrofitClient.getInstance().getMyApi().getBusDetails(busNum);

        call.enqueue(new Callback<Bus>() {
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {
                Bus data = response.body();
                Log.d("BusActivity", "onResponse: " + response.code());
                if(response.isSuccessful() && response.code() == 200) {
                    String strData = new Gson().toJson(data);
                    Intent intent = new Intent(MainActivity.this, BusActivity.class);
                    intent.putExtra("bus_object",strData);
                    startActivity(intent);
                }else if(response.code() == 404){
                    mCodeScanner.startPreview();
                    Toast.makeText(MainActivity.this, "Invalid Bus Number", Toast.LENGTH_SHORT).show();
                }else{
                    mCodeScanner.startPreview();
                    Toast.makeText(MainActivity.this, "Some error has occured, Please try again later", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
//                busRoute.setText(data.getRoute_name() + " - " + data.getEnding_station_name());

            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
                mCodeScanner.startPreview();
                progressDialog.dismiss();
                Log.e("BusActivity", "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}