package com.example.proyectopst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserCreation extends AppCompatActivity {
    EditText newUsername, newPassword, newEmail;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);
        newUsername = (EditText) findViewById(R.id.inputNewUser);
        newPassword = (EditText) findViewById(R.id.inputNewPassword);
        newEmail = (EditText) findViewById(R.id.inputNewEmail);
        db = FirebaseFirestore.getInstance();
    }

    public void mainMenu(View v) {
        String strNewUsername = newUsername.getText().toString();
        String strNewPassword = newPassword.getText().toString();
        String strNewEmail = newEmail.getText().toString();

        if (!(TextUtils.isEmpty(strNewUsername) || TextUtils.isEmpty(strNewPassword) || TextUtils.isEmpty(strNewEmail))) {
            createUser(strNewUsername, strNewPassword, strNewEmail);
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

    }
    public void logInMenu(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void createUser(String name, String password, String email) {
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("admin", false);
        newUser.put("email", email);
        newUser.put("password", password);

        db.collection("users").document(name).set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UserCreation.this, "Nuevo usuario creado!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserCreation.this, "El usuario no pudo ser creado", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}