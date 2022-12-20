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
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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

public class LoginActivity extends AppCompatActivity implements LocationListener{


    BroadcastReceiver broadcastReceiver;
    Button login_btn;
    TextInputEditText edName, edPass;
    TextView tvfailed;

    Context context;

    //Map
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationManager locationManager;

    String strDeviceName, AddressLine;
    double Latitude;
    double Longtitude;
    ArrayList<LoginuserModel> loginuserModel = new ArrayList<>();


    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edPass = findViewById(R.id.edPass);
        edName = findViewById(R.id.edName);
        tvfailed = findViewById(R.id.tvfailed);
        login_btn = findViewById(R.id.login_btn);
        String EmpID = edName.getText().toString();
        String Pass = edPass.getText().toString();

        edName.setText(PreferenceManager.getLoginEmpID(this));
        edPass.setText(PreferenceManager.getLoginPwd(this));



        //LocationPermission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        //Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();


        context = getApplicationContext();
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        //  Log.e(TAG, "onCreate: "+ip );



        //network
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //DeviceName
        if (!android.os.Build.MODEL.isEmpty() || android.os.Build.MODEL.length() > 0) {
            strDeviceName = android.os.Build.MODEL;

        } else {
            strDeviceName = "";

        }







        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edName.getText().toString().trim().length() == 0) {
                    edName.setError("Enter the Employee ID");
                    edName.requestFocus();
                    tvfailed.setVisibility(View.GONE);


                } else if (edPass.getText().toString().trim().length() == 0) {
                    edPass.setError("Enter the Password");
                    edPass.requestFocus();
                    tvfailed.setVisibility(View.GONE);
                } else {
                    CallLogin(EmpID, Pass, Latitude, Longtitude, AddressLine, ip);
                    // Toast.makeText(LoginActivity.this, "Incorrect Employee ID and Password", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Enable GPS Service")
                    .setIcon(R.drawable.ic_baseline_location_on_24)
                    .setMessage("Allow Cistron App to Access this device's location?")
                    .setCancelable(false)
                    .setPositiveButton("ALLOW", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("DENY", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }




    private void CallLogin(String empID, String pass, double latitude, double longtitude, String addressLine, String ip) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Employee Login...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Log.e(TAG, "Login: "+latitude+"--"+longtitude+"--"+addressLine );
        LoginInterFace loginInterFace=APIClient.getClient().create(LoginInterFace.class);
        loginInterFace.getUserLogin("login",edName.getText().toString(),edPass.getText().toString(),latitude,longtitude,addressLine,strDeviceName,ip).enqueue(new Callback<LoginuserModel>() {
            @Override
            public void onResponse(Call<LoginuserModel> call, Response<LoginuserModel> response) {
               try {
                   if (response.isSuccessful()){
                       progressDialog.dismiss();
                       PreferenceManager.saveLoginPwd(LoginActivity.this,edPass.getText().toString());
                       PreferenceManager.saveLoginEmpID(LoginActivity.this,edName.getText().toString());
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
                       PreferenceManager.setEmpUser(LoginActivity.this,response.body().getUser());
                       PreferenceManager.setEmpCompany(LoginActivity.this,response.body().getCompany());
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
                tvfailed.setVisibility(View.VISIBLE);






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
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Latitude=addresses.get(0).getLatitude();
            Longtitude=addresses.get(0).getLongitude();
            AddressLine=addresses.get(0).getAddressLine(0);
            //Log.e(TAG, "onLocationChanged: "+Latitude+"--"+Longtitude+"--"+AddressLine );

        } catch (Exception e) {
        }
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}