package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.UpcomingCallListInterface;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.UpcomingCallAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComingCallActivity extends AppCompatActivity {
    RecyclerView rvUpcomingCall;
    ImageView ivBack;

    UpcomingCallAdapter upcomingCallAdapter;
    ArrayList<UpcomingCallListModel>upcomingCallListModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_coming_call);

        ivBack=findViewById(R.id.ivBack);
        rvUpcomingCall=findViewById(R.id.rvUpcomingCall);


        CallUpcomingCallList();
        upcomingCallAdapter=new UpcomingCallAdapter(this,upcomingCallListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvUpcomingCall.setAdapter(upcomingCallAdapter);
        rvUpcomingCall.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void CallUpcomingCallList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("UpcomingCall Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UpcomingCallListInterface upcomingCallListInterface= APIClient.getClient().create(UpcomingCallListInterface.class);
        upcomingCallListInterface.CallUpcomingCallReport("getUpcomingCallsRecord", PreferenceManager.getEmpID(this),"upcoming").enqueue(new Callback<UpcomingCallListResponse>() {
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