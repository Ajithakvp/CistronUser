package com.example.cistronuser.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.AttendanceInsert;
import com.example.cistronuser.API.Interface.AttendanceMessage;
import com.example.cistronuser.API.Model.AttendanceMessageModel;
import com.example.cistronuser.API.Response.AttendanceResponse;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends Activity {


    //Internet

    BroadcastReceiver broadcastReceiver;

    ImageView ivBack;
    TextInputLayout placeLayout;
    TextInputEditText edtPlace;
    RadioButton rbLocal, rbOutstation, rbExstation, rbRegular, rbTraining, rbMeeting;
    Button btnSubmit;
    RadioGroup rbGroup;
    ProgressBar simpleProgressBar;
    int placeId;
    TextView tvMsg, tvcat;
    RelativeLayout rlmsg;
    LinearLayout rlattendance;
    SwipeRefreshLayout srRefresh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        ivBack = findViewById(R.id.ivBack);
        edtPlace = findViewById(R.id.edtPlace);
        rbLocal = findViewById(R.id.rbLocal);
        rbOutstation = findViewById(R.id.rbOutstation);
        rbExstation = findViewById(R.id.rbExstation);
        rbRegular = findViewById(R.id.rbRegular);
        rbTraining = findViewById(R.id.rbTraining);
        rbMeeting = findViewById(R.id.rbMeeting);
        btnSubmit = findViewById(R.id.btnSubmit);
        placeLayout = findViewById(R.id.placeLayout);
        rbGroup = findViewById(R.id.rbGroup);
        tvMsg = findViewById(R.id.tvMsg);
        tvcat = findViewById(R.id.tvcat);
        rlmsg = findViewById(R.id.rlmsg);
        rlattendance = findViewById(R.id.rlattendance);
        simpleProgressBar = findViewById(R.id.simpleProgressBar);
        // srRefresh=findViewById(R.id.srRefresh);
//
//        srRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                srRefresh.setRefreshing(false);
//
//            }
//        });


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        callAttendmsgAPI();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isfilled=true;
                if (rbGroup.getCheckedRadioButtonId() == -1) {
                    isfilled=false;
                    Toast.makeText(AttendanceActivity.this, "Please select Any one of the options ", Toast.LENGTH_SHORT).show();
                } else if (rbRegular.isChecked()) {
                    if (edtPlace.getText().toString().trim().equals("")){
                        isfilled=false;
                        edtPlace.setError("Enter the place");
                        edtPlace.requestFocus();
                    }

                }
                if (isfilled){
                    btnSubmit.setEnabled(false);
                    callAttendance();
                    simpleProgressBar.setVisibility(View.VISIBLE);
                }


            }
        });


//RadioGroup
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                View view = rbGroup.findViewById(i);

                int rb = rbGroup.indexOfChild(view);

                switch (rb) {
                    case 0:
                        placeId = 1;


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
                        placeId = 2;

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
                        placeId = 11;

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
                        placeId = 4;

                        placeLayout.setVisibility(View.VISIBLE);
                        rbRegular.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                       // Toast.makeText(AttendanceActivity.this, "Enter The Place", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:

                        //Training

                        placeId = 5;

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
                        placeId = 6;

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

    private void callAttendance() {

        simpleProgressBar.setVisibility(View.VISIBLE);
        AttendanceInsert attendanceInsert = APIClient.getClient().create(AttendanceInsert.class);
        attendanceInsert.callAttendInsert(PreferenceManager.getEmpID(this), placeId, edtPlace.getText().toString(), "new_attendance").enqueue(new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        callAttendmsgAPI();
                        simpleProgressBar.setVisibility(View.GONE);
                        rlattendance.setVisibility(View.GONE);
                        rlmsg.setVisibility(View.VISIBLE);
                        Toast.makeText(AttendanceActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {

            }
        });
    }


    private void callAttendmsgAPI() {
        AttendanceMessage attendanceMessage = APIClient.getClient().create(AttendanceMessage.class);
        attendanceMessage.CallAttendMsg(PreferenceManager.getEmpID(this), "check_attend").enqueue(new Callback<AttendanceMessageModel>() {
            @Override
            public void onResponse(Call<AttendanceMessageModel> call, Response<AttendanceMessageModel> response) {
                try {
                    if (response.isSuccessful()) {

                        // Toast.makeText(AttendanceActivity.this,""+(response.body().getCategory()=="no attendance"), Toast.LENGTH_SHORT).show();
                        if (response.body().getCategory().trim().equals("no attendance")) {
                            rlattendance.setVisibility(View.VISIBLE);
                            rlmsg.setVisibility(View.GONE);
                            simpleProgressBar.setVisibility(View.GONE);
                        } else {
                            tvMsg.setText(response.body().getMessage());
                            tvcat.setText(response.body().getCategory());
                            rlattendance.setVisibility(View.GONE);
                            rlmsg.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AttendanceMessageModel> call, Throwable t) {

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
