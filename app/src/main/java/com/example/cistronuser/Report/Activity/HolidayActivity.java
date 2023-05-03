package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.HolidaylistInterface;
import com.example.cistronuser.API.Model.HolidaylistModel;
import com.example.cistronuser.API.Response.HolidaylistResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.HolidayAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayActivity extends AppCompatActivity {

    ImageView ivBack;

    HolidayAdapter holidayAdapter;
    ArrayList<HolidaylistModel>holidaylistModels=new ArrayList<>();
    RecyclerView rvHoliday;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);
        ivBack=findViewById(R.id.ivBack);
        rvHoliday=findViewById(R.id.rvHoliday);


        CallHolidayList();
        holidayAdapter=new HolidayAdapter(this,holidaylistModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvHoliday.setLayoutManager(linearLayoutManager);
        rvHoliday.setAdapter(holidayAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void CallHolidayList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        HolidaylistInterface holidaylistInterface= APIClient.getClient().create(HolidaylistInterface.class);
        holidaylistInterface.callReport().enqueue(new Callback<HolidaylistResponse>() {
            @Override
            public void onResponse(Call<HolidaylistResponse> call, Response<HolidaylistResponse> response) {

                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        holidayAdapter.holidaylistModels=response.body().getHolidaylistModels();
                        holidayAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<HolidaylistResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }
}