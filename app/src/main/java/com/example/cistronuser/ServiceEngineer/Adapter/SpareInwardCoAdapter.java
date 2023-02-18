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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SpareInwardViewCoInteface;
import com.example.cistronuser.API.Model.SpareInwardCoModel;
import com.example.cistronuser.API.Model.SpareInwardViewCoModel;
import com.example.cistronuser.API.Response.SpareInwardViewCoResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareInwardCoAdapter extends RecyclerView.Adapter<SpareInwardCoAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareInwardCoModel>spareInwardCoModels;

    //SpareinwardViewRecycleView
    TextView tvCr, tvHospital, tvProduct, tvCallType;
    RecyclerView rvReqDetails;
    ImageView ivBack;
    RelativeLayout rlCr, rlDetails;
    SpareInwardViewCoAdapter spareInwardViewCoAdapter;
    ArrayList<SpareInwardViewCoModel>spareInwardViewCoModels=new ArrayList<>();

    public SpareInwardCoAdapter(Activity activity, ArrayList<SpareInwardCoModel> spareInwardCoModels) {
        this.activity = activity;
        this.spareInwardCoModels = spareInwardCoModels;
    }

    @NonNull
    @Override
    public SpareInwardCoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_inward_co_adapter, parent, false);
        return new SpareInwardCoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareInwardCoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvSerialNo.setText(spareInwardCoModels.get(position).getReqNo());
        holder.tvPurpose.setText(spareInwardCoModels.get(position).getPurpose());
        holder.tvallocatted.setText(spareInwardCoModels.get(position).getAllocatedDt());
        holder.tvReqDate.setText(spareInwardCoModels.get(position).getReqDt());

        if (spareInwardCoModels.get(position).getUser().trim().equals("admin")){
            holder.tvEmpidTag.setVisibility(View.VISIBLE);
            holder.tvEmpid.setVisibility(View.VISIBLE);
            holder.tvEmpid.setText(spareInwardCoModels.get(position).getReqBy());
        }else {
            holder.tvEmpidTag.setVisibility(View.GONE);
            holder.tvEmpid.setVisibility(View.GONE);
        }
        
        holder.ivView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.spare_inward_co_view_dialolg_recycleview);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                tvHospital = dialog.findViewById(R.id.tvHospital);
                tvCallType = dialog.findViewById(R.id.tvCallType);
                tvProduct = dialog.findViewById(R.id.tvProduct);
                tvCr = dialog.findViewById(R.id.tvCr);
                ivBack = dialog.findViewById(R.id.ivBack);
                rvReqDetails = dialog.findViewById(R.id.rvReqDetails);
                rlDetails = dialog.findViewById(R.id.rlDetails);
                rlCr = dialog.findViewById(R.id.rlCr);
                
                CallViewDetails(spareInwardCoModels.get(position).getReqid(),spareInwardCoModels.get(position).getOpt());
                spareInwardViewCoAdapter=new SpareInwardViewCoAdapter(activity,spareInwardViewCoModels);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvReqDetails.setLayoutManager(linearLayoutManager);
                rvReqDetails.setAdapter(spareInwardViewCoAdapter);

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                
                
            }
        });


    }

    private void CallViewDetails(String reqid, String opt) {

        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        SpareInwardViewCoInteface spareInwardViewCoInteface= APIClient.getClient().create(SpareInwardViewCoInteface.class);
        spareInwardViewCoInteface.CallReport("viewSpareInwardDetail",reqid,opt).enqueue(new Callback<SpareInwardViewCoResponse>() {
            @Override
            public void onResponse(Call<SpareInwardViewCoResponse> call, Response<SpareInwardViewCoResponse> response) {
                try {
                    progressDialog.dismiss();

                    if (response.body().getCallType().trim().equals("Stock")) {
                        rlCr.setVisibility(View.GONE);
                        rlDetails.setVisibility(View.GONE);
                    } else {
                        rlCr.setVisibility(View.GONE);
                        rlDetails.setVisibility(View.VISIBLE);
                    }

                    tvCallType.setText(response.body().getCallType());
                    tvHospital.setText(response.body().getHospital());
                    tvProduct.setText(response.body().getProduct());

                    spareInwardViewCoAdapter.spareInwardViewCoModels=response.body().getSpareInwardViewCoModels();
                    spareInwardViewCoAdapter.notifyDataSetChanged();

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<SpareInwardViewCoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return spareInwardCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerialNo, tvReqDate, tvPurpose,tvEmpidTag,tvEmpid,tvallocatted;
        ImageView ivView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivView = itemView.findViewById(R.id.ivView);
            tvSerialNo = itemView.findViewById(R.id.tvSerialNo);
            tvReqDate = itemView.findViewById(R.id.tvReqDate);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
            tvEmpid=itemView.findViewById(R.id.tvEmpid);
            tvEmpidTag=itemView.findViewById(R.id.tvEmpidTag);
            tvallocatted=itemView.findViewById(R.id.tvallocatted);
        }
    }
}
