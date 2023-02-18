package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReturnReqPendingReportCoInterface;
import com.example.cistronuser.API.Model.ReturnReqPendingReportCoModel;
import com.example.cistronuser.API.Response.ReturnReqPendingReportCoResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.ReturnReqPendingCoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnReqPendingCoActivity extends AppCompatActivity {

    RecyclerView rvReturnPendig;
    ImageView ivBack;

    ReturnReqPendingCoAdapter returnReqPendingCoAdapter;
    ArrayList<ReturnReqPendingReportCoModel>returnReqPendingReportCoModels=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_req_pending_co);

        rvReturnPendig=findViewById(R.id.rvReturnPendig);
        ivBack=findViewById(R.id.ivBack);

        CallReturnDetails();
        returnReqPendingCoAdapter=new ReturnReqPendingCoAdapter(this,returnReqPendingReportCoModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvReturnPendig.setAdapter(returnReqPendingCoAdapter);
        rvReturnPendig.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void CallReturnDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Return Request Pending Report ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ReturnReqPendingReportCoInterface returnReqPendingReportCoInterface= APIClient.getClient().create(ReturnReqPendingReportCoInterface.class);
        returnReqPendingReportCoInterface.CallReport("returnReqPendingReport", PreferenceManager.getEmpID(this)).enqueue(new Callback<ReturnReqPendingReportCoResponse>() {
            @Override
            public void onResponse(Call<ReturnReqPendingReportCoResponse> call, Response<ReturnReqPendingReportCoResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        returnReqPendingCoAdapter.returnReqPendingReportCoModels=response.body().getReturnReqPendingReportCoModels();
                        returnReqPendingCoAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ReturnReqPendingReportCoResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}