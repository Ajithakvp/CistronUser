package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SpareReqPendingReportCoInterface;
import com.example.cistronuser.API.Model.SpareReqPendingReportCoModel;
import com.example.cistronuser.API.Response.SpareReqPendingReportCoResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.SpareRequestAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareReqPendingCOActivity extends AppCompatActivity {

    RecyclerView rvSpareRequest;
    ImageView ivBack;

    SpareRequestAdapter spareRequestAdapter;
    ArrayList<SpareReqPendingReportCoModel>spareReqPendingReportCoModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_req_pending_coactivity);


        rvSpareRequest=findViewById(R.id.rvSpareRequest);
        ivBack=findViewById(R.id.ivBack);


        CallSpareReq();
        spareRequestAdapter=new SpareRequestAdapter(this,spareReqPendingReportCoModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSpareRequest.setLayoutManager(linearLayoutManager);
        rvSpareRequest.setAdapter(spareRequestAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void CallSpareReq() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Spare Request pending Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SpareReqPendingReportCoInterface spareReqPendingReportCoInterface= APIClient.getClient().create(SpareReqPendingReportCoInterface.class);
        spareReqPendingReportCoInterface.CallReport("spareReqPendingReport", PreferenceManager.getEmpID(this)).enqueue(new Callback<SpareReqPendingReportCoResponse>() {
            @Override
            public void onResponse(Call<SpareReqPendingReportCoResponse> call, Response<SpareReqPendingReportCoResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        spareRequestAdapter.spareReqPendingReportCoModels=response.body().getSpareReqPendingReportCoModels();
                        spareRequestAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<SpareReqPendingReportCoResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}