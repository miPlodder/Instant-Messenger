package com.example.saksham.instantmessenger;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.saksham.instantmessenger.*;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;

public class signUp extends AppCompatActivity {

    private EditText userName, pass, email, phonenumber;
    private Button signUp;
    private String serverURL = "http://192.168.43.88/instantMessenger/signUp.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //mapping the xml widgets to java objects
        userName = (EditText) findViewById(R.id.userName);
        pass = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phonenumber = (EditText) findViewById(R.id.phoneNumber);
        signUp = (Button) findViewById(R.id.signUp);

    }

    public void doSomething(View v) {

        final String username, password, mail, phoneNumber;

        username = userName.getText().toString();
        password = pass.getText().toString();
        mail = email.getText().toString();
        phoneNumber = phonenumber.getText().toString();

        singeltonClass queue = new singeltonClass(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,

                new Response.Listener<String>() {




                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(signUp.this, response, Toast.LENGTH_SHORT).show();

                        //opening new activity
                        Intent i = new Intent(signUp.this, chatList.class);
                        startActivity(i);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {

            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("userName", username);
                params.put("password", password);
                params.put("email", mail);
                params.put("phoneNumber", phoneNumber);

                return params;

            }

        };

        queue.getRequestQueue().add(stringRequest);


    }
}
