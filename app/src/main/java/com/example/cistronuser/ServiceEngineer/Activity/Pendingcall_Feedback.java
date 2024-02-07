package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.FeedbackassignInterface;
import com.example.cistronuser.API.Model.FeedbackassignModel;
import com.example.cistronuser.API.Response.FeedbackassignResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.PendingcallFb_adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pendingcall_Feedback extends AppCompatActivity {

    RecyclerView rvFeedback;
    ImageView ivBack;

    PendingcallFb_adapter pendingcallFb_adapter;
    ArrayList<FeedbackassignModel>feedbackassignModels=new ArrayList<>();

    @Override
    protected void onRestart() {
        this.recreate();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingcall_feedback);
        rvFeedback=findViewById(R.id.rvFeedback);
        ivBack=findViewById(R.id.ivBack);


        callassignfb();
        pendingcallFb_adapter=new PendingcallFb_adapter(this,feedbackassignModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFeedback.setLayoutManager(linearLayoutManager);
        rvFeedback.setAdapter(pendingcallFb_adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void callassignfb() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FeedbackassignInterface feedbackassignInterface= APIClient.getClient().create(FeedbackassignInterface.class);
        feedbackassignInterface.callRes("reportview", PreferenceManager.getEmpID(this)).enqueue(new Callback<FeedbackassignResponse>() {
            @Override
            public void onResponse(Call<FeedbackassignResponse> call, Response<FeedbackassignResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        pendingcallFb_adapter.feedbackassignModels=response.body().getFeedbackassignModels();
                        pendingcallFb_adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<FeedbackassignResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}