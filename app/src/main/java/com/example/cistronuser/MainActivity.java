package com.example.cistronuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (PreferenceManager.isLogged(MainActivity.this)){

                    Intent dash=new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(dash);


                }else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }


                finish();
            }
        }, 5000);

    }
}