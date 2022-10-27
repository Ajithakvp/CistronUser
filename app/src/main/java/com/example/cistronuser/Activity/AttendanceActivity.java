package com.example.cistronuser.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AttendanceActivity extends Activity {


    //Internet

    BroadcastReceiver broadcastReceiver;

    ImageView ivBack;
    TextInputLayout placeLayout;
    TextInputEditText edtPlace;
    RadioButton rbLocal,rbOutstation,rbExstation,rbRegular,rbTraining,rbMeeting;
    Button btnSubmit;
    RadioGroup rbGroup;
    int placeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        ivBack=findViewById(R.id.ivBack);
        edtPlace=findViewById(R.id.edtPlace);
        rbLocal=findViewById(R.id.rbLocal);
        rbOutstation=findViewById(R.id.rbOutstation);
        rbExstation=findViewById(R.id.rbExstation);
        rbRegular=findViewById(R.id.rbRegular);
        rbTraining=findViewById(R.id.rbTraining);
        rbMeeting=findViewById(R.id.rbMeeting);
        btnSubmit=findViewById(R.id.btnSubmit);
        placeLayout=findViewById(R.id.placeLayout);
        rbGroup=findViewById(R.id.rbGroup);


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // callAttendance();
            }
        });



//RadioGroup
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                View view=rbGroup.findViewById(i);

                int rb=rbGroup.indexOfChild(view);

                switch (rb){
                    case 0:
                        placeId=1;


                        placeLayout.setVisibility(View.GONE);
                        rbLocal.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 1:



                        //Out Station
                        placeId=2;

                        placeLayout.setVisibility(View.GONE);
                        rbOutstation.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        break;
                    case 2:

                        //Ex station
                        placeId=11;

                        placeLayout.setVisibility(View.GONE);
                        rbExstation.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 3:

                        //Office Regular
                        placeId=4;

                        placeLayout.setVisibility(View.VISIBLE);
                        rbRegular.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        Toast.makeText(AttendanceActivity.this, "Enter The Place", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:

                        //Training

                        placeId=5;

                        placeLayout.setVisibility(View.GONE);
                        rbTraining.setTextColor(Color.RED);
                        rbLocal.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 6:

                        //Meeting
                        placeId=6;

                        placeLayout.setVisibility(View.GONE);
                        rbMeeting.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                }
            }
        });




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
