package com.example.saksham.instantmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import serverConnectivity.singeltonClass;

public class checkActivity extends AppCompatActivity {

    String serverURL = "http://192.168.43.88/instantMessenger/getContacts.php";
    singeltonClass queue = new singeltonClass(this);
    JSONArray array;
    ListView contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
    }
}
