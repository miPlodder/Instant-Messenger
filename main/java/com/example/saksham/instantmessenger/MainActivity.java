package com.example.saksham.instantmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button signUp, signIn;
    private TextView display, tandc, forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking xml and java objects
        signUp = (Button) findViewById(R.id.signUp);
        signIn = (Button) findViewById(R.id.signIn);
        display = (TextView) findViewById(R.id.display);
        tandc = (TextView) findViewById(R.id.tandc);
        forgotPass = (TextView) findViewById(R.id.forgotPassword);
    }

    public void doSomething(View v) {

        Toast t = new Toast(this);
        Intent i;
        if (v.getId() == signUp.getId()) {

            t.makeText(this, "Please Wait, Connecting to Server", Toast.LENGTH_LONG).show();
            i = new Intent(this, signUp.class);
            startActivity(i);

        } else if (v.getId() == signIn.getId()) {

            t.makeText(this,"Please Wait, Connecting to Server",Toast.LENGTH_LONG).show();
            i = new Intent(this,signIn.class);
            startActivity(i);

        } else if (v.getId() == tandc.getId()){

            t.makeText(this,"Please Wait, Connecting to Server",Toast.LENGTH_LONG).show();
            i = new Intent(this,tandc.class);
            startActivity(i);

        }  else if (v.getId() == forgotPass.getId()){

            t.makeText(this,"Please Wait, Connecting to Server",Toast.LENGTH_LONG).show();
            i = new Intent(this,forgotPassword.class);
            startActivity(i);

        }


    }
}