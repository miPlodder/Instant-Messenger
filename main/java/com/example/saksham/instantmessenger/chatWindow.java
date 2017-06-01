package com.example.saksham.instantmessenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class chatWindow extends AppCompatActivity {


    String user, receiver, phoneNumberR;
    Button send;
    EditText message;
    ListView lv;
    JSONArray array;

    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        user = getIntent().getExtras().getString("user");
        receiver = getIntent().getExtras().getString("receiver");
        phoneNumberR = getIntent().getExtras().getString("phoneNumberR");

        Log.e(user,receiver+"phoneNUmber"+phoneNumberR);
        Toast.makeText(this,user+"receiver"+receiver,Toast.LENGTH_SHORT).show();

        //phoneNumberR = getIntent().getExtras().getString("phoneNumberR");
        Toast.makeText(chatWindow.this, user + receiver, Toast.LENGTH_SHORT).show();

        //linkage process
        send = (Button) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        lv = (ListView) findViewById(R.id.lv);
        doSomething(send);

        //make dosomething on button click
        //listview layout for chat object
        //make new class to send request to the localhost

    }

    public void doSomething(View v) {

        String serverURL = "http://192.168.43.88/instantMessenger/chatFunc.php";
        singeltonClass queue = new singeltonClass(this);


            msg = message.getText().toString();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL,


                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                           Log.e("chatWindow","inside response");
                            Log.e("","inside onresponse below try caqtch");
                           array = new JSONArray(response);
                            Log.e("","after array"+array.get(0));

                            //setting up the custom adapter
                            customAdapter c = new customAdapter();
                            lv.setAdapter(c);
                            Log.e("","inside onresponse below try caqtch");

                            message.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("",e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(user,receiver);

                Log.e("chatWindow","inside error----------------------------------------------");

            }
        }) {

                protected Map<String, String> getParams() {

                Log.e("inside params","sending data");

                Map<String, String> params = new HashMap<>();

                params.put("sender", user);
                params.put("phoneNumberR", phoneNumberR);

                        params.put("msg",msg);

                    Log.e("inside params","ending sending data");

                    return params;

            }

        };

        //queue.add(jsonArrayRequest);
        queue.addToRequestQueue(stringRequest);



    }



    //----------------------------NEW CLASS

    public class customAdapter extends BaseAdapter {


        @Override
        public int getCount() {

            Log.e("inside count","inside getcount methosd");
            return array.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = getLayoutInflater().inflate(R.layout.messagerow, null);
            TextView msg = (TextView) view.findViewById(R.id.msg);

            try {

                Log.e("","inside custom adapter ");
                JSONObject jsonObject = array.getJSONObject(position);

                msg.setText(jsonObject.get("msg").toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return view;
        }
    }
}
