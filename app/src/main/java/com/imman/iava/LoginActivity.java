package com.imman.iava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.imman.iava.Network.Checker;
import com.imman.iava.Network.Login;
import com.imman.iava.Network.NetworkRequest;

public class LoginActivity extends AppCompatActivity {
    TextView username_et, password_et;
    Button login_btn;
    LinearLayout holdupscreen;
    NetworkRequest networkRequest;
    Context t;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username_et = findViewById(R.id.redg_no_et);
//        username_et.setPaintFlags(View.INVISIBLE);
        password_et = findViewById(R.id.password_et);
//        password_et.setPaintFlags(View.INVISIBLE);
        login_btn = findViewById(R.id.login_btn);
        holdupscreen = findViewById(R.id.blur_screen);
        networkRequest = new NetworkRequest(this);
        t=this;
        login_btn.setOnClickListener(v -> {
            if(Checker.isNetworkAvailable(this))
                login();
            else{
                Snackbar.make(findViewById(R.id.root_main_page), "Network not available!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
    void login() {
        //get password an username
        username = username_et.getText().toString();
        password = password_et.getText().toString();

        //If Empty then default
        if(username.equals(""))
            username = "2101229079";
        if(password.equals(""))
            password = "god is with us";

        //if username is valid
        if(username.length()!=10){
            Toast.makeText(getApplicationContext(),"Wrong Registration number!",Toast.LENGTH_SHORT).show();
        }

        //if password is valid
        else if(password.length()<7 || password.length()>20) {
            Toast.makeText(getApplicationContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
        }

        //if all valid then login
        else {
            Toast.makeText(getApplicationContext(), "Logging In...", Toast.LENGTH_SHORT).show();
            new asynclogin().execute();
        }
    }

    //Async Login
    class asynclogin extends AsyncTask<Void, Void, String> {
        boolean is_logged_in = false;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            holdupscreen.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(Void... arg0)
        {
            //send request to Login.authenticate function
            is_logged_in = (new Login().authenticate(username,password,networkRequest));

            return "";
        }

        @Override
        protected void onPostExecute(String result)
        {
            holdupscreen.setVisibility(View.INVISIBLE);

            //if login successful then move to main content or stay in this page.
            if(is_logged_in){
                Intent intent = new Intent(t,MainActivity.class);
                intent.putExtra("Logged_in_now",true);
                startActivity(intent);
                finish();
            }
            super.onPostExecute(result);

        }
    }
}