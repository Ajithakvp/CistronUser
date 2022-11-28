package com.example.cistronuser.WaitingforApprovel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CompOffRequestInterface;
import com.example.cistronuser.API.Model.CompOffRequestModel;
import com.example.cistronuser.API.Response.CompOffRequestResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.CompOffReqAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompOffRequest extends AppCompatActivity {

    ArrayList<CompOffRequestModel>compOffRequestModels=new ArrayList<>();
    CompOffReqAdapter compOffReqAdapter;
    RecyclerView rvCompOffReq;

    ImageView ivBack1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_off_request);
        rvCompOffReq=findViewById(R.id.rvCompOffReq);
        ivBack1=findViewById(R.id.ivBack1);


        callCompoff();
        compOffReqAdapter=new CompOffReqAdapter(this,compOffRequestModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvCompOffReq.setAdapter(compOffReqAdapter);
        rvCompOffReq.setLayoutManager(linearLayoutManager);

        ivBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void callCompoff() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        CompOffRequestInterface compOffRequestInterface= APIClient.getClient().create(CompOffRequestInterface.class);
        compOffRequestInterface.CallCompoff("compoffForApproval").enqueue(new Callback<CompOffRequestResponse>() {
            @Override
            public void onResponse(Call<CompOffRequestResponse> call, Response<CompOffRequestResponse> response) {
                try {
                    if (response.isSuccessful()){

                        compOffReqAdapter.compOffRequestModels=response.body().getCompOffRequestModels();
                        compOffReqAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<CompOffRequestResponse> call, Throwable t) {

            }
        });
    }
}