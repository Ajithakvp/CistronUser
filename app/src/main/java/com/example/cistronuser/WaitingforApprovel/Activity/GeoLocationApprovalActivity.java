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
import com.example.cistronuser.API.Interface.GetGeoLocationApprovalInterface;
import com.example.cistronuser.API.Model.GetGeoLocationApprovalModel;
import com.example.cistronuser.API.Response.GetGeoLocationApprovalResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.GeoLocationAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoLocationApprovalActivity extends AppCompatActivity {

    RecyclerView rvGeoLocation;
    ImageView ivBack;
    GeoLocationAdapter geoLocationAdapter;
    ArrayList<GetGeoLocationApprovalModel>getGeoLocationApprovalModels=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_location_approval);

        ivBack=findViewById(R.id.ivBack);
        rvGeoLocation=findViewById(R.id.rvGeoLocation);

        CallGeoLocation();
        geoLocationAdapter=new GeoLocationAdapter(this,getGeoLocationApprovalModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvGeoLocation.setAdapter(geoLocationAdapter);
        rvGeoLocation.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void CallGeoLocation() {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Geo Location...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        GetGeoLocationApprovalInterface getGeoLocationApprovalInterface= APIClient.getClient().create(GetGeoLocationApprovalInterface.class);
        getGeoLocationApprovalInterface.CallGetGeo("getGeolocationApprovalReq").enqueue(new Callback<GetGeoLocationApprovalResponse>() {
            @Override
            public void onResponse(Call<GetGeoLocationApprovalResponse> call, Response<GetGeoLocationApprovalResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        geoLocationAdapter.getGeoLocationApprovalModels=response.body().getGetGeoLocationApprovalModels();
                        geoLocationAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetGeoLocationApprovalResponse> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }
}