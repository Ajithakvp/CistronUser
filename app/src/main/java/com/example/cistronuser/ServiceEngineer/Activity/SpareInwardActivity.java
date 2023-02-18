package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SpareInwardCoInterface;
import com.example.cistronuser.API.Model.SpareInwardCoModel;
import com.example.cistronuser.API.Response.SpareInwardCoResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.SpareInwardCoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareInwardActivity extends AppCompatActivity {

    RecyclerView rvSpareInward;
    ImageView ivBack;

    SpareInwardCoAdapter spareInwardCoAdapter;
    ArrayList<SpareInwardCoModel>spareInwardCoModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_inward);

        ivBack=findViewById(R.id.ivBack);
        rvSpareInward=findViewById(R.id.rvSpareInward);

        CallSpareInwardDetails();
        spareInwardCoAdapter=new SpareInwardCoAdapter(this,spareInwardCoModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSpareInward.setAdapter(spareInwardCoAdapter);
        rvSpareInward.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void CallSpareInwardDetails() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Spare Inward...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SpareInwardCoInterface spareInwardCoInterface= APIClient.getClient().create(SpareInwardCoInterface.class);
        spareInwardCoInterface.CallReport("spareInwardReport", PreferenceManager.getEmpID(this)).enqueue(new Callback<SpareInwardCoResponse>() {
            @Override
            public void onResponse(Call<SpareInwardCoResponse> call, Response<SpareInwardCoResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        spareInwardCoAdapter.spareInwardCoModels=response.body().getSpareInwardCoModels();
                        spareInwardCoAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<SpareInwardCoResponse> call, Throwable t) {

            }
        });
    }
}