package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.cistronuser.R;

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