package com.example.cistronuser.ServiceEngineer.Activity;

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
import com.example.cistronuser.API.Interface.SwapOutwardMainInterface;
import com.example.cistronuser.API.Model.SwapOutwardMainModel;
import com.example.cistronuser.API.Response.SwapOutwardmainResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.Swapoutward_adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwapOutwardActivity extends AppCompatActivity {

    ImageView ivBack;
    RecyclerView rvswapoutward;

    Swapoutward_adapter swapoutwardAdapter;
    ArrayList<SwapOutwardMainModel>swapOutwardMainModels=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swap_outward);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvswapoutward=findViewById(R.id.rvswapoutward);
        ivBack=findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Callswapoutlist();
        swapoutwardAdapter=new Swapoutward_adapter(swapOutwardMainModels,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvswapoutward.setLayoutManager(linearLayoutManager);
        rvswapoutward.setAdapter(swapoutwardAdapter);


    }

    private void Callswapoutlist() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Swap Outward...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SwapOutwardMainInterface swapOutwardMainInterface= APIClient.getClient().create(SwapOutwardMainInterface.class);
        swapOutwardMainInterface.MainResponse(PreferenceManager.getEmpID(this)).enqueue(new Callback<SwapOutwardmainResponse>() {
            @Override
            public void onResponse(Call<SwapOutwardmainResponse> call, Response<SwapOutwardmainResponse> response) {
                try {
                    if(response.isSuccessful()){
                        swapoutwardAdapter.swapOutwardMainModels=response.body().getSwapOutwardMainModels();
                        swapoutwardAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SwapOutwardmainResponse> call, Throwable t) {
                progressDialog.dismiss();


            }
        });
    }
}