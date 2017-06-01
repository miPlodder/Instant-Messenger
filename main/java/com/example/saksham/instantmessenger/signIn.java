package com.example.saksham.instantmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;

public class signIn extends AppCompatActivity {

    private String serverURL = "http://192.168.43.88/instantMessenger/signIn.php";
    private EditText mail, pass;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Log.e("inisede", "inside main");
        //Linking xml to java objects

        mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);

    }

    public void doSomething(View v) throws JSONException {

        final String email, password;

        email = mail.getText().toString();
        password = pass.getText().toString();

        Toast.makeText(this, email + password, Toast.LENGTH_SHORT).show();
        Log.e("inside", "inside dosomething");
        //creating request
        singeltonClass queue = new singeltonClass(this);

        //creating request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            Log.e("inside ","sending data request onresponse");
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject obj = jsonArray.getJSONObject(0);
                            String res= obj.getString("id");


                            if (res.equals("failed")) {

                                //same activity
                                Toast.makeText(signIn.this, "LogIn Failed, Try Again", Toast.LENGTH_SHORT).show();


                            } else {


                                //moving to next activity

                                Intent i = new Intent(signIn.this, chatList.class);
                                i.putExtra("userId", res);
                                startActivity(i);

                            }
                            Log.e("", "inside exception");

                        } //catch (JSONException e) {
                        catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signIn.this, "inside exception end dosomething"+error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("", "inside exception"+error.getMessage());

            }

        }) {

            protected Map<String, String> getParams() {

                Log.e("inside params","sending data");
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                Log.e("inside params","ending sending data");
                return params;

            }
        };


        queue.getRequestQueue().add(stringRequest);


    }

    public JSONObject get2SendData(final String email, final String password) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject;

    }


}