package com.example.cistronuser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.Output;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {


    BroadcastReceiver broadcastReceiver;


    Button login_btn;
    TextInputEditText edName, edPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edPass = findViewById(R.id.edPass);
        edName = findViewById(R.id.edName);
        login_btn = findViewById(R.id.login_btn);


        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        EnableGPSIfPossible();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CallLogin();

                if (edName.getText().toString().trim().length() == 0) {
                    edName.setError("Enter the Name");
                    edName.requestFocus();
                } else if (edPass.getText().toString().trim().length() == 0) {
                    edPass.setError("Enter the Password");
                    edPass.requestFocus();
                }
            }
        });

    }

    private void EnableGPSIfPossible() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
                .setIcon(R.drawable.ic_baseline_location_on_24)
                .setTitle(" Check Your GPS Services   ")
                .setCancelable(false)

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Toast.makeText(LoginActivity.this, "GPS Enabled", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void CallLogin() {


        //PreferenceManager.setLoggedStatus(this,true);
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);


    }


    protected void unregBroadcast() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
        unregBroadcast();
    }
}