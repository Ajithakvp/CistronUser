package com.example.cistronuser.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cistronuser.Common.GpsListener;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DashboardActivity extends Activity {

    ImageView ivprofile, ivLogout, ivBack;
    RelativeLayout rlProfile;
    CardView cvAttendance, cvExpense, cvLeave;

    //Bottom
    TextView tvName, tvEmpId, tvDesignation, tvBranch, tvTeamLeader, tvMobile, tvEmail, tvDob, tvDoj;


    
    LottieAnimationView lottieAnimationView;
    //Gps

    private Activity activity;
    private LocationManager mlocManager;
    private LocationListener gpsListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cvLeave = findViewById(R.id.cvLeave);
        cvExpense = findViewById(R.id.cvExpense);
        cvAttendance = findViewById(R.id.cvAttendance);
        rlProfile = findViewById(R.id.rlProfile);
        lottieAnimationView = findViewById(R.id.ivLogout);
        ivprofile = findViewById(R.id.ivprofile);





        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DashboardActivity.this);
                builder.setMessage("Do you want to LogOut");
                builder.setCancelable(false);
                builder.setPositiveButton("yes",(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }));
                builder.setNegativeButton("No",(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }));
AlertDialog alertDialog=builder.create();
alertDialog.show();

            }
        });


        cvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent attend = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(attend);
            }
        });
        cvExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expense = new Intent(DashboardActivity.this, ExpensesActivity.class);
                startActivity(expense);
            }
        });

        cvLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LeaveActivity.class);
                startActivity(intent);
            }
        });


        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileView();
            }
        });


    }






    private void ProfileView() {

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.profileview);
        bottomSheetDialog.show();

        tvDoj=bottomSheetDialog.findViewById(R.id.tvDoj);
        tvName=bottomSheetDialog.findViewById(R.id.tvName);
        tvEmpId=bottomSheetDialog.findViewById(R.id.tvEmpId);
        tvDesignation=bottomSheetDialog.findViewById(R.id.tvDesignation);
        tvBranch=bottomSheetDialog.findViewById(R.id.tvBranch);
        tvTeamLeader=bottomSheetDialog.findViewById(R.id.tvTeamLeader);
        tvMobile=bottomSheetDialog.findViewById(R.id.tvMobile);
        tvEmail=bottomSheetDialog.findViewById(R.id.tvEmail);
        tvDob=bottomSheetDialog.findViewById(R.id.tvDob);
        ivBack=bottomSheetDialog.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }
}