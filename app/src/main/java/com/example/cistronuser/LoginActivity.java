package com.example.cistronuser;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LoginInterFace;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    BroadcastReceiver broadcastReceiver;
    Button login_btn;
    TextInputEditText edName, edPass;
    TextView tvfailed;

    //Map
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;
    String strDeviceName, AddressLine;
    double Latitude;
    double Longtitude;
    ArrayList<LoginuserModel> loginuserModel = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edPass = findViewById(R.id.edPass);
        edName = findViewById(R.id.edName);
        tvfailed=findViewById(R.id.tvfailed);
        login_btn = findViewById(R.id.login_btn);
        String EmpID = edName.getText().toString();
        String Pass = edPass.getText().toString();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Map();


        //network
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //DeviceName
        if (!android.os.Build.MODEL.isEmpty() || android.os.Build.MODEL.length() > 0) {
            strDeviceName = android.os.Build.MODEL;

        } else {
            strDeviceName = "";

        }

        EnableGPSIfPossible();


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CallLogin(EmpID, Pass, Latitude, Longtitude, AddressLine);

            }
        });

    }

    public void Map() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                }, 100);
            }
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    Geocoder geocoder = new Geocoder(LoginActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        AddressLine=addresses.get(0).getAddressLine(0);
                        Latitude=addresses.get(0).getLatitude();
                        Longtitude=addresses.get(0).getLongitude();

                        Log.e(TAG, "onSuccess: "+addresses.get(0).getAddressLine(0) );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        }
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


    private void CallLogin(String empID, String pass, double latitude, double longtitude, String addressLine) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Employee Login...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LoginInterFace loginInterFace=APIClient.getClient().create(LoginInterFace.class);
        loginInterFace.getUserLogin(edName.getText().toString(),edPass.getText().toString(),latitude,longtitude,addressLine,strDeviceName).enqueue(new Callback<LoginuserModel>() {
            @Override
            public void onResponse(Call<LoginuserModel> call, Response<LoginuserModel> response) {
               try {
                   if (response.isSuccessful()){
                       progressDialog.dismiss();
                       PreferenceManager.setLoggedStatus(LoginActivity.this,true);
                       PreferenceManager.saveData(LoginActivity.this,loginuserModel);
                       PreferenceManager.setEmpID(LoginActivity.this,response.body().getEmpid());
                       PreferenceManager.setEmpName(LoginActivity.this,response.body().getName());
                       PreferenceManager.setEmpbranch(LoginActivity.this,response.body().getBranch());
                       PreferenceManager.setEmpMobile(LoginActivity.this,response.body().getMobile());
                       PreferenceManager.setEmpdob(LoginActivity.this,response.body().getDob());
                       PreferenceManager.setEmpdoj(LoginActivity.this,response.body().getDoj());
                       PreferenceManager.setEmpdesignation(LoginActivity.this,response.body().getDesignation());
                       PreferenceManager.setEmpphoto(LoginActivity.this,response.body().getPhoto());
                       PreferenceManager.setEmpemail(LoginActivity.this,response.body().getEmail());
                       PreferenceManager.setEmpteamleader(LoginActivity.this,response.body().getTeamleader());
                       Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
                       intent.putExtra("Pass",pass);
                       intent.putExtra("EmpID",response.body().getEmpid());
                       intent.putExtra("Name",response.body().getName());
                       intent.putExtra("Branch",response.body().getBranch());
                       intent.putExtra("Des",response.body().getDesignation());
                       intent.putExtra("Email",response.body().getEmail());
                       intent.putExtra("Mob",response.body().getMobile());
                       intent.putExtra("TL",response.body().getTeamleader());
                       intent.putExtra("DOJ",response.body().getDoj());
                       intent.putExtra("DOB",response.body().getDob());
                       intent.putExtra("Photo",response.body().getPhoto());
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   }

               }catch (Exception e){

               }
            }

            @Override
            public void onFailure(Call<LoginuserModel> call, Throwable t) {
                progressDialog.dismiss();



                if (edName.getText().toString().trim().length() == 0) {
                    edName.setError("Enter the Employee ID");
                    edName.requestFocus();
                    tvfailed.setVisibility(View.GONE);


                } else if (edPass.getText().toString().trim().length() == 0) {
                    edPass.setError("Enter the Password");
                    edPass.requestFocus();
                    tvfailed.setVisibility(View.GONE);
                }else {
                    tvfailed.setVisibility(View.VISIBLE);
                   // Toast.makeText(LoginActivity.this, "Incorrect Employee ID and Password", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            } else {
                Map();
                Toast.makeText(LoginActivity.this, "not Access", Toast.LENGTH_SHORT).show();
            }
        }
    }

}