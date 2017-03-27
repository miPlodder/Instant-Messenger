//this class will be used to generate the id of any operation

package com.example.saksham.instantmessenger;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ContentHandler;
import java.util.HashMap;
import java.util.Map;

import serverConnectivity.singeltonClass;

/**
 * Created by saksham on 26/3/17.
 */

public class chatIdGenerator {

    String serverURL ;
    Context context;

    public void chatIdGenerator(Context context){

        this.context = context;

    }

    public void getChatId(){


        singeltonClass queue = new singeltonClass(context);

        //creating request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }

        }) {

            protected Map<String, String> getParams() {

                Log.e("inside params","sending data");
                Map<String, String> params = new HashMap<>();
                //params.put("email", email);
                //params.put("password", password);
                Log.e("inside params","ending sending data");
                return params;

            }
        };


        queue.getRequestQueue().add(stringRequest);







    }





}
