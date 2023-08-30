package com.example.proyectopst;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class UserData extends AppCompatActivity {
    private TextView consulta;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        consulta = (TextView) findViewById(R.id.txtTest);
    }

    public void onStart() {
        super.onStart();
        db = FirebaseFirestore.getInstance();
    }

    public void conectar(View v) {
        getUsers();
    }

    public void getUsers() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                               @Override
                                                               public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                   if (task.isSuccessful()) {
                                                                       for (QueryDocumentSnapshot document : task.getResult()) {
                                                                           Log.d(TAG, document.getId() + " => " + document.getData());
                                                                       }
                                                                   } else {
                                                                       Log.w(TAG, "Error getting documents.", task.getException());
                                                                   }
                                                               }
                                                           }

        );
    }
}