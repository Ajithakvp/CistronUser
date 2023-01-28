package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cistronuser.R;


import java.util.concurrent.ExecutionException;

public class PendicallActivity extends AppCompatActivity {

    RecyclerView rvPendingCall;
    ImageView ivBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendicall);

        ivBack=findViewById(R.id.ivBack);
        rvPendingCall=findViewById(R.id.rvPendingCall);
    }
}