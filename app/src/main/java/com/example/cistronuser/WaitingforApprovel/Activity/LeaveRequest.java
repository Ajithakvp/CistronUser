package com.example.cistronuser.WaitingforApprovel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LeaveApprovelInterface;
import com.example.cistronuser.API.Model.LeaveApprovelModel;
import com.example.cistronuser.API.Response.leaveApprovelResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.LeaveApprovalAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveRequest extends AppCompatActivity {

    RecyclerView rvLeaveReq;
    LeaveApprovalAdapter leaveApprovalAdapter;
    ArrayList<LeaveApprovelModel> leaveApprovelModels = new ArrayList<>();

    String BaseUrl;
    ImageView ivBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        rvLeaveReq = findViewById(R.id.rvLeaveReq);
        ivBack.findViewById(R.id.ivBack);

        callLeaveApprovalList();

        leaveApprovalAdapter = new LeaveApprovalAdapter(leaveApprovelModels,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvLeaveReq.setLayoutManager(linearLayoutManager);
        rvLeaveReq.setAdapter(leaveApprovalAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void callLeaveApprovalList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveApprovelInterface leaveApprovelInterface = APIClient.getClient().create(LeaveApprovelInterface.class);
        leaveApprovelInterface.callLeaveApprovel("leaveForApproval").enqueue(new Callback<leaveApprovelResponse>() {
            @Override
            public void onResponse(Call<leaveApprovelResponse> call, Response<leaveApprovelResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        BaseUrl = response.body().getAttchBaseUrl();
                        leaveApprovalAdapter.leaveApprovelModels = response.body().getLeaveApprovelModels();
                        leaveApprovalAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<leaveApprovelResponse> call, Throwable t) {

            }
        });
    }
}