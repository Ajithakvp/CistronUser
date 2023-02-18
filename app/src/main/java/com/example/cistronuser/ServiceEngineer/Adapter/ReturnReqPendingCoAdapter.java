package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReturnRequestViewCoInterface;
import com.example.cistronuser.API.Model.ReturnReqPendingReportCoModel;
import com.example.cistronuser.API.Model.ReturnRequestViewCoModel;
import com.example.cistronuser.API.Response.ReturnRequestViewCoResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnReqPendingCoAdapter extends RecyclerView.Adapter<ReturnReqPendingCoAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<ReturnReqPendingReportCoModel>returnReqPendingReportCoModels;

    //Adapter
    RecyclerView rvReqDetails;
    ImageView ivBack;
    ReturnReqPendingCoViewAdapter returnReqPendingCoViewAdapter;
    ArrayList<ReturnRequestViewCoModel>returnRequestViewCoModels=new ArrayList<>();

    public ReturnReqPendingCoAdapter(Activity activity, ArrayList<ReturnReqPendingReportCoModel> returnReqPendingReportCoModels) {
        this.activity = activity;
        this.returnReqPendingReportCoModels = returnReqPendingReportCoModels;
    }

    @NonNull
    @Override
    public ReturnReqPendingCoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.return_req_co_adapter, parent, false);
        return new ReturnReqPendingCoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnReqPendingCoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvSerialNo.setText(returnReqPendingReportCoModels.get(position).getRetReqNo());
        holder.tvReqDate.setText(returnReqPendingReportCoModels.get(position).getRetReqDt());

        if (returnReqPendingReportCoModels.get(position).getUser().trim().equals("admin")){
            holder.tvEmpidTag.setVisibility(View.VISIBLE);
            holder.tvEmpid.setVisibility(View.VISIBLE);
            holder.tvEmpid.setText(returnReqPendingReportCoModels.get(position).getReqBy());
        }else {
            holder.tvEmpidTag.setVisibility(View.GONE);
            holder.tvEmpid.setVisibility(View.GONE);
        }

        holder.ivView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.return_req_co_view_dialolg_recycleview);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                ivBack = dialog.findViewById(R.id.ivBack);
                rvReqDetails = dialog.findViewById(R.id.rvReqDetails);

                CallView(returnReqPendingReportCoModels.get(position).getRetReqId(),returnReqPendingReportCoModels.get(position).getOpt());
                returnReqPendingCoViewAdapter=new ReturnReqPendingCoViewAdapter(activity,returnRequestViewCoModels);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvReqDetails.setLayoutManager(linearLayoutManager);
                rvReqDetails.setAdapter(returnReqPendingCoViewAdapter);

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    private void CallView(String retReqId, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ReturnRequestViewCoInterface returnRequestViewCoInterface= APIClient.getClient().create(ReturnRequestViewCoInterface.class);
        returnRequestViewCoInterface.CallReport("viewReturnReqPendingDetail",retReqId,opt).enqueue(new Callback<ReturnRequestViewCoResponse>() {
            @Override
            public void onResponse(Call<ReturnRequestViewCoResponse> call, Response<ReturnRequestViewCoResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        returnReqPendingCoViewAdapter.returnRequestViewCoModels=response.body().getReturnRequestViewCoModels();
                        returnReqPendingCoViewAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ReturnRequestViewCoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return returnReqPendingReportCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerialNo, tvReqDate, tvEmpidTag,tvEmpid;
        ImageView ivView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivView = itemView.findViewById(R.id.ivView);
            tvSerialNo = itemView.findViewById(R.id.tvSerialNo);
            tvReqDate = itemView.findViewById(R.id.tvReqDate);
            tvEmpid=itemView.findViewById(R.id.tvEmpid);
            tvEmpidTag=itemView.findViewById(R.id.tvEmpidTag);
        }
    }
}
