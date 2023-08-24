package com.example.proyectopst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UserData extends AppCompatActivity {
    private String server = "192.168.100.19";
    private int port = 8080;
    private TextView consulta;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        consulta = (TextView) findViewById(R.id.txtTest);
        requestQueue = Volley.newRequestQueue(this);
    }


    public void conectar(View view) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://" + server + ":" + port + "/android",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null)
                            consulta.setText(response);
                        else
                            consulta.setText("Error desconocido en la consulta");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        consulta.setText("Error en conexion");

                        String message = null;
                        if (error instanceof NetworkError) {
                            message = "Cannot connect to Internet...Please check your connection! (Network)";
                        } else if (error instanceof ServerError) {
                            message = "The server could not be found. Please try again after some time!!";
                        } else if (error instanceof AuthFailureError) {
                            message = "Cannot connect to Internet...Please check your connection! (AuthFailure)";
                        } else if (error instanceof ParseError) {
                            message = "Parsing error! Please try again after some time!!";
                        } else if (error instanceof NoConnectionError) {
                            message = "Cannot connect to Internet...Please check your connection! (NoConnection)";
                        } else if (error instanceof TimeoutError) {
                            message = "Connection TimeOut! Please check your internet connection.";
                        }
                        Toast.makeText(UserData.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(request);
    }
}