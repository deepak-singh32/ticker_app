package com.example.ticker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class OtpActivity extends AppCompatActivity {

    EditText first,second,third,fourth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);

        nextFocus(first, second);
        nextFocus(second,third);
        nextFocus(third,fourth);

        fourth.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if((first.getText().toString()+second.getText().toString()+third.getText().toString()+fourth.getText().toString()).length() == 4){
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                    return true;
                }
                return false;
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