package com.imman.iava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.imman.iava.UserData.Profile;
import com.imman.iava.Network.Checker;
import com.imman.iava.Network.NetworkRequest;

public class MainActivity extends AppCompatActivity {
    TextView details;
    NetworkRequest networkRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        details =findViewById(R.id.details_tv);
        networkRequest = new NetworkRequest(this);
        setUserData(networkRequest.profile);
        loginSession();

    }

    //login session Handle
    void loginSession(){
        //check if username password is stored
        if(new Profile(this).is_loggedin()){

            startActivity(new Intent(this,ScheduleActivity.class));
            finish();

            //If not logged in just now then login
            if(!getIntent().getBooleanExtra("Logged_in_now",false)){
                //Login
                if(Checker.isNetworkAvailable(this))
                    new asynclogin().execute();
                else
                    Snackbar.make(findViewById(R.id.root_main_page), "Network not available!", Snackbar.LENGTH_SHORT).show();
            }
        }
        else{
            //Redirect to login page
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

    void setUserData(Profile p){
        details.setText("Name:"+p.getUser_Name()+"\nRedg.No.: "+p.getRegistration_Number()+"\nSection: "+p.getSection()+"\nBatch: "+p.getBatch()+"\nProgram: "+p.getProgram()+"\nSemisterCode: "+p.getSemester_Code()+"\nBranchCode: "+p.getBranch_Code());
    }

    //Async Login
    class asynclogin extends AsyncTask<Void, Void, String> {
        boolean is_logged_in = false;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... arg0)
        {
            return "";
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

        }
    }
}