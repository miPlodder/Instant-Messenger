package com.example.saksham.instantmessenger;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import serverConnectivity.singeltonClass;

public class chatList extends AppCompatActivity {

    String serverURL = "http://192.168.43.88/instantMessenger/getContacts.php";
    singeltonClass queue ;
    //RequestQueue queue;
    JSONArray array;
    ListView contacts;

    String userId ; //userId is the id of the current logined user detail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        userId = getIntent().getExtras().getString("userId");

        //queue = Volley.newRequestQueue(this.getApplicationContext());
        queue = new singeltonClass(this);

        Toast.makeText(this, "userId is "+userId, Toast.LENGTH_SHORT).show();
        //queue = new singeltonClass(this);

        //JSONObjectRequest jsonArray =
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverURL, (String) null,


                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        array = response;
                        contacts = (ListView) findViewById(R.id.contacts);
                        //add customAdapter to the contacts listview

                        customAdapter c = new customAdapter();
                        contacts.setAdapter(c);
                        //Toast.makeText(chatList.this, "inside chatList", Toast.LENGTH_SHORT).show();
                        //queue.addToRequestQueue(jsonArrayRequest);
                        //Toast.makeText(chatList.this, "inside chatList", Toast.LENGTH_SHORT).show();

                        contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String receiver = ((TextView)view.findViewById(R.id.username)).getText().toString();

                                String phone = ((TextView)view.findViewById(R.id.phoneNumber)).getText().toString();
                                Log.e("itemclick",receiver+phone);

                                Intent i = new Intent(chatList.this,chatWindow.class) ;
                                i.putExtra("user",userId);
                                i.putExtra("receiver",receiver);
                                i.putExtra("phoneNumberR",phone);
                                startActivity(i);


                            }
                        });


                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {


        };

        //queue.add(jsonArrayRequest);
        queue.addToRequestQueue(jsonArrayRequest);
        Toast.makeText(chatList.this, "inside chatList", Toast.LENGTH_SHORT).show();




    }

    public class customAdapter extends BaseAdapter{


        @Override
        public int getCount() {
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

            view = getLayoutInflater().inflate(R.layout.row,null);
            TextView tv1 = (TextView) view.findViewById(R.id.username);
            TextView tv2 = (TextView) view.findViewById(R.id.phoneNumber);

            //tv1.setText("name");
            //tv2.setText("phoneNUmber");
            try {

                JSONObject jsonObject = array.getJSONObject(position);
                tv1.setText(jsonObject.get("username").toString());
                tv2.setText(jsonObject.get("phoneNumber").toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }



            return view;
        }
    }


}
