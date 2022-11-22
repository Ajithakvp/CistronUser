package com.example.cistronuser.WaitingforApprovel.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReportExpensesInterface;
import com.example.cistronuser.API.Model.ReportExpensesModel;
import com.example.cistronuser.API.Response.ReportExpensesResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.ReportExpenseAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesReport extends AppCompatActivity {

    RecyclerView rvExpenseReport;
    ArrayList<ReportExpensesModel>reportExpensesModels=new ArrayList<>();
    ReportExpenseAdapter reportExpenseAdapter;

    ImageView ivBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_report);
        rvExpenseReport=findViewById(R.id.rvExpenseReport);
        ivBack=findViewById(R.id.ivBack);


        callReportExpenses();
        reportExpenseAdapter=new ReportExpenseAdapter(reportExpensesModels,ExpensesReport.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvExpenseReport.setAdapter(reportExpenseAdapter);
        rvExpenseReport.setLayoutManager(linearLayoutManager);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void callReportExpenses() {
        ReportExpensesInterface reportExpensesInterface= APIClient.getClient().create(ReportExpensesInterface.class);
        reportExpensesInterface.callSubmittedOn("submittedExpensesReport").enqueue(new Callback<ReportExpensesResponse>() {
            @Override
            public void onResponse(Call<ReportExpensesResponse> call, Response<ReportExpensesResponse> response) {
                try {
                    if (response.isSuccessful()){
                        reportExpenseAdapter.reportExpensesModels=response.body().getReportExpensesModels();
                        reportExpenseAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }


            }

            @Override
            public void onFailure(Call<ReportExpensesResponse> call, Throwable t) {

            }
        });
    }
}