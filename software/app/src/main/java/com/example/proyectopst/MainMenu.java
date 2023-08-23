package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void powerOnOff(View v) {
        //Send signal
    }

    public void goToPowerConsumption(View v) {
        Intent intent = new Intent(this, PowerUsageWindow.class);
        startActivity(intent);
    }

    public void goToUserInfo(View v) {
        Intent intent = new Intent(this, UserData.class);
        startActivity(intent);
    }
}