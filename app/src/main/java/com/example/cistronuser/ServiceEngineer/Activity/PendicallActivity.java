package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.UpcomingCallListInterface;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.UpcomingCallAdapter;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendicallActivity extends AppCompatActivity {

    RecyclerView rvPendingCall;
    ImageView ivBack;


    UpcomingCallAdapter upcomingCallAdapter;
    ArrayList<UpcomingCallListModel> upcomingCallListModels=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendicall);

        ivBack=findViewById(R.id.ivBack);
        rvPendingCall=findViewById(R.id.rvPendingCall);


        CallUpcomingCallList();
        upcomingCallAdapter=new UpcomingCallAdapter(this,upcomingCallListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvPendingCall.setAdapter(upcomingCallAdapter);
        rvPendingCall.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void CallUpcomingCallList() {  
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("PendingCall Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UpcomingCallListInterface upcomingCallListInterface= APIClient.getClient().create(UpcomingCallListInterface.class);
        upcomingCallListInterface.CallUpcomingCallReport("getCallsRecords", PreferenceManager.getEmpID(this),"pending").enqueue(new Callback<UpcomingCallListResponse>() {
            @Override
            public void onResponse(Call<UpcomingCallListResponse> call, Response<UpcomingCallListResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        upcomingCallAdapter.upcomingCallReportlistModels=response.body().getUpcomingCallListModels();
                        upcomingCallAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<UpcomingCallListResponse> call, Throwable t) {

            }
        });

    }
}