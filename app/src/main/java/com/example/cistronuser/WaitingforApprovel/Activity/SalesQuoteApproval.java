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
import com.example.cistronuser.API.Interface.SalesQuoteApprovalListInterface;
import com.example.cistronuser.API.Model.SalesQuoteApprovalListModel;
import com.example.cistronuser.API.Response.SalesQuoteApprovalListResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.SalesQuoteApprovalAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesQuoteApproval extends AppCompatActivity {

    RecyclerView rvSalesQuoteList;
    ImageView ivBack;

    //ApprovalList
    SalesQuoteApprovalAdapter salesQuoteApprovalAdapter;
    ArrayList<SalesQuoteApprovalListModel>salesQuoteApprovalListModels=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_quote_approval);

        ivBack=findViewById(R.id.ivBack);
        rvSalesQuoteList=findViewById(R.id.rvSalesQuoteList);


        //ApprovalList
        CallApprovalList();
        salesQuoteApprovalAdapter=new SalesQuoteApprovalAdapter(this,salesQuoteApprovalListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSalesQuoteList.setLayoutManager(linearLayoutManager);
        rvSalesQuoteList.setAdapter(salesQuoteApprovalAdapter);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void CallApprovalList() {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("SalesQuote Approval list...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteApprovalListInterface salesQuoteApprovalListInterface= APIClient.getClient().create(SalesQuoteApprovalListInterface.class);
        salesQuoteApprovalListInterface.CallApprovalList("getOrderCallforApproval").enqueue(new Callback<SalesQuoteApprovalListResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteApprovalListResponse> call, Response<SalesQuoteApprovalListResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        salesQuoteApprovalAdapter.salesQuoteApprovalListModels=response.body().getSalesQuoteApprovalListModels();
                        salesQuoteApprovalAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteApprovalListResponse> call, Throwable t) {

            }
        });

    }
}