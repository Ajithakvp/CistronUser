package com.example.cistronuser;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.AppVersionInterface;
import com.example.cistronuser.API.Response.AppVersionResponse;
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String AppVersion="cistron 1.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashscreen);


        //*******APP Version***********//
        AppVersionInterface appVersionInterface= APIClient.getClient().create(AppVersionInterface.class);
        appVersionInterface.callVersion("getAppVersion").enqueue(new Callback<AppVersionResponse>() {
            @Override
            public void onResponse(Call<AppVersionResponse> call, Response<AppVersionResponse> response) {
                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse: "+response.body().getMessage());
                   if (response.body().getMessage().trim().equals(AppVersion)){
                     CallLoginPage();
                   }else {
                       AlertDialog.Builder update=new AlertDialog.Builder(MainActivity.this);
                       update.setCancelable(false);
                       update.setTitle("Update Required !");
                       update.setMessage("You must update to Continue");
                       update.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                              String apk="https://cistronsystems.in/beta1/app/cistron.apk";
                              Uri uri= Uri.parse(apk);
                              Intent update=new Intent(Intent.ACTION_VIEW,uri);
                              startActivity(update);
                           }
                       });

                       AlertDialog dialog=update.create();
                       dialog.show();
                   }
                }

            }

            @Override
            public void onFailure(Call<AppVersionResponse> call, Throwable t) {

            }
        });






    }

    private void CallLoginPage() {

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (PreferenceManager.isLogged(MainActivity.this)){

                    Intent dash=new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(dash);


                }else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }


                finish();
            }
        }, 1000);
    }
}