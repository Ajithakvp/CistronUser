package com.example.cistronuser.Common;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.cistronuser.R;

public class ConnectionRecevier extends BroadcastReceiver {

    Context mcontext;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (isConnected(context)) {
//            AlertDialog.Builder builder=new AlertDialog.Builder(context);
//
//            View view=LayoutInflater.from(context).inflate(R.layout.network,null);
//            builder.setView(view);
//            AlertDialog alertDialog=builder.create();
//            alertDialog.show();
           Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
        } else {

            AlertDialog.Builder builder=new AlertDialog.Builder(context);

            View view=LayoutInflater.from(context).inflate(R.layout.network,null);
            builder.setView(view);
            AlertDialog alertDialog=builder.create();
            alertDialog.show();


        }

    }


    private boolean isConnected(Context context) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }

}
