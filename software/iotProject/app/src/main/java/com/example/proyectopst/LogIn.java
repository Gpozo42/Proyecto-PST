package com.example.proyectopst;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LogIn extends AppCompatActivity {
    EditText username, password;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = (EditText) findViewById(R.id.inputUsername);
        password = (EditText) findViewById(R.id.inputPassword);
        db = FirebaseFirestore.getInstance();
    }

    public void mainMenu(View v) {
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        if (!(TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_password))) {
            compareUser(str_username, str_password);
        }
        else {
            Toast.makeText(LogIn.this,
                    "El usuario o contraseña son incorrectos",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void compareUser(String username, String pass) {
        DocumentReference docRef = db.collection("users").document(username);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc != null && doc.exists()) {
                        if(doc.getString("password").equals(pass)) {
                            Toast.makeText(LogIn.this, "Por favor espere", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogIn.this, MainMenu.class);
                            startActivity(intent);
                        }
                        Log.d(TAG, doc.getString("password"));
                    }
                    else {
                        Toast.makeText(LogIn.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void signInMenu(View v) {
        Intent intent = new Intent(this, UserCreation.class);
        startActivity(intent);
    }
}