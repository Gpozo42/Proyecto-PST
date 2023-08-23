package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = (EditText) findViewById(R.id.inputUsername);
        password = (EditText) findViewById(R.id.inputPassword);
    }

    public void mainMenu(View v) {
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        if (!(str_username.equals("") || str_password.equals(""))) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

        Toast.makeText(LogIn.this,
                "El email o contraseña son incorrectos",
                Toast.LENGTH_SHORT).show();
    }
    public void signInMenu(View v) {
        Intent intent = new Intent(this, UserCreation.class);
        startActivity(intent);
    }
}