package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CancelSpareRequestCoInterface;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.SpareReqPendingReportViewDetailsCOInterface;
import com.example.cistronuser.API.Model.SpareReqPendingReportCoModel;
import com.example.cistronuser.API.Model.SpareReqPendingReportViewDetailsCOModel;
import com.example.cistronuser.API.Response.CancelSpareRequestCoResponse;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.SpareReqPendingReportViewDetailsCOResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareRequestAdapter extends RecyclerView.Adapter<SpareRequestAdapter.ViewHolder> {

    public ArrayList<SpareReqPendingReportCoModel> spareReqPendingReportCoModels;
    Activity activity;
    //SpareRecycleView
    TextView tvCr, tvHospital, tvProduct, tvCallType, tvCancelReq;
    RecyclerView rvReqDetails;
    ImageView ivBack;
    String LinkReqId, ReqId, Opt;
    RelativeLayout rlCr, rlDetails;
    SpareReqpendingViewAdapter spareReqpendingViewAdapter;
    ArrayList<SpareReqPendingReportViewDetailsCOModel> spareReqPendingReportViewDetailsCOModels = new ArrayList<>();

    public SpareRequestAdapter(Activity activity, ArrayList<SpareReqPendingReportCoModel> spareReqPendingReportCoModels) {
        this.activity = activity;
        this.spareReqPendingReportCoModels = spareReqPendingReportCoModels;
    }

    @NonNull
    @Override
    public SpareRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_req_pending_co_adapter, parent, false);
        return new SpareRequestAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareRequestAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvSerialNo.setText(spareReqPendingReportCoModels.get(position).getSerno());
        holder.tvPurpose.setText(spareReqPendingReportCoModels.get(position).getPurpose());
        holder.tvReqDate.setText(spareReqPendingReportCoModels.get(position).getDate());

        if (spareReqPendingReportCoModels.get(position).getUser().trim().equals("admin")){
            holder.tvEmpidTag.setVisibility(View.VISIBLE);
            holder.tvEmpid.setVisibility(View.VISIBLE);
            holder.tvEmpid.setText(spareReqPendingReportCoModels.get(position).getReqBy());
        }else {
            holder.tvEmpidTag.setVisibility(View.GONE);
            holder.tvEmpid.setVisibility(View.GONE);
        }

        holder.ivView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.spare_req_pending_co_view_dialolg_recycleview);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                tvCancelReq = dialog.findViewById(R.id.tvCancelReq);
                tvHospital = dialog.findViewById(R.id.tvHospital);
                tvCallType = dialog.findViewById(R.id.tvCallType);
                tvProduct = dialog.findViewById(R.id.tvProduct);
                tvCr = dialog.findViewById(R.id.tvCr);
                ivBack = dialog.findViewById(R.id.ivBack);
                rvReqDetails = dialog.findViewById(R.id.rvReqDetails);
                rlDetails = dialog.findViewById(R.id.rlDetails);
                rlCr = dialog.findViewById(R.id.rlCr);

                CallViewReqDetails(spareReqPendingReportCoModels.get(position).getReqid(), spareReqPendingReportCoModels.get(position).getOpt());
                spareReqpendingViewAdapter = new SpareReqpendingViewAdapter(activity, spareReqPendingReportViewDetailsCOModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvReqDetails.setAdapter(spareReqpendingViewAdapter);
                rvReqDetails.setLayoutManager(linearLayoutManager);

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                tvCancelReq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                        builder.setMessage("Are you sure you want to cancel  ?");
                        builder.setTitle("cancel!");
                        builder.setIcon(R.drawable.ic_baseline_cancel_24);
                        builder.setCancelable(false);
                        builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final ProgressDialog progressDialog = new ProgressDialog(activity,R.style.ProgressBarDialog);
                                progressDialog.setMessage("Loading...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                CancelSpareRequestCoInterface cancelSpareRequestCoInterface=APIClient.getClient().create(CancelSpareRequestCoInterface.class);
                                cancelSpareRequestCoInterface.CallCancel("cancelSpareReq",ReqId,LinkReqId,Opt).enqueue(new Callback<CancelSpareRequestCoResponse>() {
                                    @Override
                                    public void onResponse(Call<CancelSpareRequestCoResponse> call, Response<CancelSpareRequestCoResponse> response) {
                                        try {
                                            if (response.isSuccessful()){
                                                progressDialog.dismiss();
                                               // Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                activity.finish();
                                                activity.overridePendingTransition(0, 0);
                                                activity.startActivity(activity.getIntent());
                                                activity.overridePendingTransition(0, 0);
                                            }

                                        }catch (Exception e){

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CancelSpareRequestCoResponse> call, Throwable t) {

                                    }
                                });



                            }
                        }));
                        builder.setNegativeButton("No", (new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }));
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                });


            }
        });
    }

    private void CallViewReqDetails(String reqid, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SpareReqPendingReportViewDetailsCOInterface spareReqPendingReportViewDetailsCOInterface = APIClient.getClient().create(SpareReqPendingReportViewDetailsCOInterface.class);
        spareReqPendingReportViewDetailsCOInterface.CallView("viewSpareReqDetail", reqid, opt).enqueue(new Callback<SpareReqPendingReportViewDetailsCOResponse>() {
            @Override
            public void onResponse(Call<SpareReqPendingReportViewDetailsCOResponse> call, Response<SpareReqPendingReportViewDetailsCOResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();

                        if (response.body().getCallType().trim().equals("Stock")) {
                            rlCr.setVisibility(View.GONE);
                            rlDetails.setVisibility(View.GONE);
                        } else {
                            rlCr.setVisibility(View.VISIBLE);
                            rlDetails.setVisibility(View.VISIBLE);
                        }
                        tvCr.setText("CR#" + response.body().getPurpose());
                        tvCallType.setText(response.body().getCallType());
                        tvHospital.setText(response.body().getHospital());
                        tvProduct.setText(response.body().getProduct());
                        LinkReqId = response.body().getLinkReqId();
                        ReqId = response.body().getReqId();
                        Opt = response.body().getOpt();

                        spareReqpendingViewAdapter.spareReqPendingReportViewDetailsCOModels = response.body().getSpareReqPendingReportViewDetailsCOModels();
                        spareReqpendingViewAdapter.notifyDataSetChanged();


                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpareReqPendingReportViewDetailsCOResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public int getItemCount() {
        return spareReqPendingReportCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSerialNo, tvReqDate, tvPurpose,tvEmpidTag,tvEmpid;
        ImageView ivView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivView = itemView.findViewById(R.id.ivView);
            tvSerialNo = itemView.findViewById(R.id.tvSerialNo);
            tvReqDate = itemView.findViewById(R.id.tvReqDate);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
            tvEmpid=itemView.findViewById(R.id.tvEmpid);
            tvEmpidTag=itemView.findViewById(R.id.tvEmpidTag);

        }
    }
}
