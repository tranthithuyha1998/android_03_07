package com.example.selfdrivingcar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int Counter = 5;
    private int timer =10;

    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);

        Info.setText("Please Login To Use Application! \nTry: 5");

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context=view.getContext();
                if (isConnectedToNetwork((context))) {
                    validate(Name.getText().toString(), Password.getText().toString());
                    Log.d("test", "btnLogin");
                }
                else {
                    Toast.makeText(LoginActivity.this, "Please check network connection !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /*---- CHECK NETWORK CONNECTION ----*/
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = false;
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }
        return isConnected;
    }
    /*---- Login to use application----*/
    private void validate(String userName, String userPassword) {
        if ((userName.equals("Luan van")) && (userPassword.equals("2020"))) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Counter--;

            Info.setText("Please Login To Use Application!\nTry: " + String.valueOf(Counter));
            if (Counter == 0) {
                Login.setEnabled(false);
                new CountDownTimer(11000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        Info.setText("Please wait "+Integer.toString(timer));
                        timer--;
                    }

                    public void onFinish() {
                        timer=10;
                        Counter=6;
                        Login.setEnabled(true);
                    }
                }.start();

            }
        }

    }



}

