package com.example.cistronuser.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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
import com.example.cistronuser.API.Interface.UserLocationLatLngInterface;
import com.example.cistronuser.API.Model.AttendanceMessageModel;
import com.example.cistronuser.API.Response.AttendanceResponse;
import com.example.cistronuser.API.Response.UserLocationLatLngResponse;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.LocationBackgroundService;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

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

    int placeId;
    TextView tvMsg, tvcat;
    RelativeLayout rlmsg;
    LinearLayout rlattendance;

    LocationBroadcastReceiver receiver;
    double Latitude;
    double Longtitude;


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


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //************* User Location Lat lng **************** //
        receiver = new LocationBroadcastReceiver();
        startBackgroundService();
        UserLocationLatLngInterface userLocationLatLngInterface = APIClient.getClient().create(UserLocationLatLngInterface.class);
        userLocationLatLngInterface.CallLatLng("getHomeLocation", PreferenceManager.getEmpID(this)).enqueue(new Callback<UserLocationLatLngResponse>() {
            @Override
            public void onResponse(Call<UserLocationLatLngResponse> call, Response<UserLocationLatLngResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        PreferenceManager.saveLat(AttendanceActivity.this, response.body().getLat());
                        PreferenceManager.saveLng(AttendanceActivity.this, response.body().getLng());
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<UserLocationLatLngResponse> call, Throwable t) {

            }
        });

        //************* User Location Lat lng end **************** //

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

                boolean isfilled = true;
                if (rbGroup.getCheckedRadioButtonId() == -1) {
                    isfilled = false;
                    Toast.makeText(AttendanceActivity.this, "Please select Any one of the options ", Toast.LENGTH_SHORT).show();
                } else if (rbRegular.isChecked() || rbExstation.isChecked() || rbLocal.isChecked() || rbOutstation.isChecked()) {
                    if (edtPlace.getText().toString().trim().equals("")) {
                        isfilled = false;
                        edtPlace.setError("Enter the place");
                        edtPlace.requestFocus();
                    }

                }
                if (isfilled) {
                    btnSubmit.setEnabled(false);
                    callAttendance();
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

                        // ***** Local ***** //

                        placeId = 1;

                        if (PreferenceManager.getLat(AttendanceActivity.this).trim().equals("") && PreferenceManager.getLng(AttendanceActivity.this).trim().equals("")) {
                            PreferenceManager.saveLat(AttendanceActivity.this, String.valueOf(Latitude));
                            PreferenceManager.saveLng(AttendanceActivity.this, String.valueOf(Longtitude));
                        }

                        placeLayout.setVisibility(View.VISIBLE);
                        rbLocal.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        // ***** Out Station ***** //

                        PreferenceManager.saveLat(AttendanceActivity.this, String.valueOf(Latitude));
                        PreferenceManager.saveLng(AttendanceActivity.this, String.valueOf(Longtitude));
                        placeId = 2;

                        placeLayout.setVisibility(View.VISIBLE);
                        rbOutstation.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        // *****  Ex station ***** //

                        PreferenceManager.saveLat(AttendanceActivity.this, String.valueOf(Latitude));
                        PreferenceManager.saveLng(AttendanceActivity.this, String.valueOf(Longtitude));


                        placeId = 11;

                        placeLayout.setVisibility(View.VISIBLE);
                        rbExstation.setTextColor(Color.RED);
                        rbTraining.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbLocal.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 3:
                        // ***** Office Regular ***** //

                        PreferenceManager.saveLat(AttendanceActivity.this, String.valueOf(Latitude));
                        PreferenceManager.saveLng(AttendanceActivity.this, String.valueOf(Longtitude));


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
                    case 4:

                        // ***** Training ***** //

                        placeId = 5;

                        placeLayout.setVisibility(View.GONE);
                        rbTraining.setTextColor(Color.RED);
                        rbLocal.setTextColor(Color.BLACK);
                        rbMeeting.setTextColor(Color.BLACK);
                        rbRegular.setTextColor(Color.BLACK);
                        rbExstation.setTextColor(Color.BLACK);
                        rbOutstation.setTextColor(Color.BLACK);
                        break;
                    case 5:

                        // *******  Meeting ****** //
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
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Attendance Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        AttendanceInsert attendanceInsert = APIClient.getClient().create(AttendanceInsert.class);
        attendanceInsert.callAttendInsert(PreferenceManager.getEmpID(this), placeId, edtPlace.getText().toString(), "new_attendance").enqueue(new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        callAttendmsgAPI();
                        progressDialog.dismiss();
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
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
                            progressDialog.dismiss();
                        } else {
                            tvMsg.setText(response.body().getMessage());
                            tvcat.setText(response.body().getCategory());
                            progressDialog.dismiss();
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

    private void startBackgroundService() {

        IntentFilter filter = new IntentFilter("Location");
        registerReceiver(receiver, filter);
        Intent intent = new Intent(AttendanceActivity.this, LocationBackgroundService.class);
        startService(intent);

    }

    public class LocationBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Location")) {

                Latitude = intent.getDoubleExtra("latitude", 0f);
                Longtitude = intent.getDoubleExtra("longitude", 0f);
                // String Address = intent.getStringExtra("Address");

            }
        }
    }

}
