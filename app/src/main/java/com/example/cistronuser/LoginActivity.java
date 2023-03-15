package com.example.cistronuser;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LoginInterFace;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.LocationBackgroundService;
import com.example.cistronuser.Common.PreferenceManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

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
    LocationManager locationManager;

    String strDeviceName, AddressLine;
    double Latitude;
    double Longtitude;
    ArrayList<LoginuserModel> loginuserModel = new ArrayList<>();


    // BackgroundLocation
    LocationBroadcastReceiver receiver;
    Double lat,longitude;
    String Address;


    //FingerPrint
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

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



        // *****LocationPermission ******//
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        // *****LocationPermission End ******//


        //*****  Location ******//
        receiver = new LocationBroadcastReceiver();
        startBackgroundService();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();
        //*****  Location End ******//


        //*****  Ip Address ******//
        context = getApplicationContext();
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        //  Log.e(TAG, "onCreate: "+ip );
        //*****  Ip Address End ******//


        //network
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //DeviceName
        if (!android.os.Build.MODEL.isEmpty() || android.os.Build.MODEL.length() > 0) {
            strDeviceName = android.os.Build.MODEL;

        } else {
            strDeviceName = "";

        }



        // *********Finger Print login ****************//
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
               // Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
               // Log.e("MY_APP_TAG", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
               // Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, 100);
                break;
        }
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
//                Toast.makeText(getApplicationContext(),
//                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                if (edName.getText().toString().trim().length() == 0) {
                    edName.setError("Enter the Employee ID");
                    edName.requestFocus();
                    tvfailed.setVisibility(View.GONE);
                } else if (edPass.getText().toString().trim().length() == 0) {
                    edPass.setError("Enter the Password");
                    edPass.requestFocus();
                    tvfailed.setVisibility(View.GONE);
                } else {
                    CallLogin( Latitude, Longtitude, AddressLine, ip);
                }
              //  Toast.makeText(getApplicationContext(),"Authentication succeeded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for Cistron ")
                .setSubtitle("Log in  your biometric credential")
                .setNegativeButtonText("Cancel")
                .build();


        biometricPrompt.authenticate(promptInfo);

        // *********Finger Print login end ****************//


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
                    CallLogin( Latitude, Longtitude, AddressLine, ip);

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
            new AlertDialog.Builder(LoginActivity.this,R.style.AlertDialogCustom)
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




    private void CallLogin(double latitude, double longtitude, String addressLine, String ip) {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
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
                       PreferenceManager.set_ismanager(LoginActivity.this,response.body().getIs_manager());
                       Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
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
                       startBackgroundService();
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

    private void startBackgroundService() {
        IntentFilter filter = new IntentFilter("Location");
        registerReceiver(receiver, filter);
        Intent intent = new Intent(LoginActivity.this, LocationBackgroundService.class);
        startService(intent);
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

    public class LocationBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Location")) {

                 lat = intent.getDoubleExtra("latitude", 0f);
                 longitude = intent.getDoubleExtra("longitude", 0f);
                 Address = intent.getStringExtra("Address");
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}