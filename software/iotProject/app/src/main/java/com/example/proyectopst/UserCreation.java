package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserCreation extends AppCompatActivity {
    EditText newUsername, newPassword, newEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);
        newUsername = (EditText) findViewById(R.id.inputNewUser);
        newPassword = (EditText) findViewById(R.id.inputNewPassword);
        newEmail = (EditText) findViewById(R.id.inputNewEmail);
    }

    public void mainMenu(View v) {
        String strNewUsername = newUsername.getText().toString();
        String strNewPassword = newPassword.getText().toString();
        String strNewEmail = newEmail.getText().toString();

        if (!(strNewUsername.equals("") || strNewPassword.equals("") || strNewEmail.equals(""))) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

        Toast.makeText(UserCreation.this,
                "No fue posible crear su usuario",
                Toast.LENGTH_SHORT).show();

    }
    public void logInMenu(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}