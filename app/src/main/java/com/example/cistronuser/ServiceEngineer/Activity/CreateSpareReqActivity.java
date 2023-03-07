package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.example.cistronuser.R;

public class CreateSpareReqActivity extends AppCompatActivity {

    EditText edSearch;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spare_req);
        edSearch=findViewById(R.id.edSearch);


    }
}