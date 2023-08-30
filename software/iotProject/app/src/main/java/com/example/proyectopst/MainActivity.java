package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart() {
        super.onStart();

    }

    public void logInMenu(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}