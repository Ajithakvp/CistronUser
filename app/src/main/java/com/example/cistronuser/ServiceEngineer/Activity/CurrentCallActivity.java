package com.example.cistronuser.ServiceEngineer.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.UpcomingCallListInterface;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.Common.LocationBackgroundService;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.LoginActivity;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.UpcomingCallAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentCallActivity extends AppCompatActivity {

    RecyclerView rvTodayCall;
    ImageView ivBack;

    UpcomingCallAdapter upcomingCallAdapter;
    ArrayList<UpcomingCallListModel> upcomingCallListModels=new ArrayList<>();




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_call);

        ivBack=findViewById(R.id.ivBack);
        rvTodayCall=findViewById(R.id.rvTodayCall);





        CallUpcomingCallList();
        upcomingCallAdapter=new UpcomingCallAdapter(this,upcomingCallListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvTodayCall.setAdapter(upcomingCallAdapter);
        rvTodayCall.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void CallUpcomingCallList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Today Call Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UpcomingCallListInterface upcomingCallListInterface= APIClient.getClient().create(UpcomingCallListInterface.class);
        upcomingCallListInterface.CallUpcomingCallReport("getCallsRecords", PreferenceManager.getEmpID(this),"today").enqueue(new Callback<UpcomingCallListResponse>() {
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