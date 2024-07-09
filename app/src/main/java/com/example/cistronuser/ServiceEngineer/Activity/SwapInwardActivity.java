package com.example.cistronuser.ServiceEngineer.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SwapInwardmainInterface;
import com.example.cistronuser.API.Model.SwapInwardmainModel;
import com.example.cistronuser.API.Response.SwapInwardmainResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.SwapInward_adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwapInwardActivity extends AppCompatActivity {

    RecyclerView rvswapinward;
    ImageView ivBack;

    ArrayList<SwapInwardmainModel>swapInwardmainModels=new ArrayList<>();
    SwapInward_adapter swapInwardAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swap_inward);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivBack=findViewById(R.id.ivBack);
        rvswapinward=findViewById(R.id.rvswapinward);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CallInwardview();
        swapInwardAdapter=new SwapInward_adapter(swapInwardmainModels,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvswapinward.setAdapter(swapInwardAdapter);
        rvswapinward.setLayoutManager(linearLayoutManager);
    }

    private void CallInwardview() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Swap Inward...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SwapInwardmainInterface swapInwardmainInterface= APIClient.getClient().create(SwapInwardmainInterface.class);
        swapInwardmainInterface.MainResponse(PreferenceManager.getEmpID(this)).enqueue(new Callback<SwapInwardmainResponse>() {
            @Override
            public void onResponse(Call<SwapInwardmainResponse> call, Response<SwapInwardmainResponse> response) {
                try {
                    if(response.isSuccessful()){
                        swapInwardAdapter.swapInwardmainModels=response.body().getSwapInwardmainModels();
                        swapInwardAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SwapInwardmainResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }
}