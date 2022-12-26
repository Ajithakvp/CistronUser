package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CompOffDeletedReqinterface;
import com.example.cistronuser.API.Interface.CompOffReqRejectedInterface;
import com.example.cistronuser.API.Interface.CompoffReqApprovalInterface;
import com.example.cistronuser.API.Interface.LeaveApprovalRejectedInterface;
import com.example.cistronuser.API.Model.CompOffRequestModel;
import com.example.cistronuser.API.Response.CompOffDeleteReqResponse;
import com.example.cistronuser.API.Response.CompOffreqApporvalResponse;
import com.example.cistronuser.API.Response.CompoffReqRejectedResponse;
import com.example.cistronuser.API.Response.LeaveApprovalRejectedResponse;
import com.example.cistronuser.Adapter.ApprovedAdapter;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompOffReqAdapter extends RecyclerView.Adapter<CompOffReqAdapter.Viewholder> {

    public ArrayList<CompOffRequestModel> compOffRequestModels;
    Activity activity;

    public CompOffReqAdapter(Activity activity, ArrayList<CompOffRequestModel> compOffRequestModels) {
        this.activity = activity;
        this.compOffRequestModels = compOffRequestModels;
    }

    @NonNull
    @Override
    public CompOffReqAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.compoff_adapter, parent, false);
        return new CompOffReqAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompOffReqAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {


        holder.tvName.setText(compOffRequestModels.get(position).getEmpname());
        holder.tvAppliedDate.setText(compOffRequestModels.get(position).getAppliedDt());
        holder.tvEmpId.setText(compOffRequestModels.get(position).getEmpid());
        holder.tvAppliedTime.setText(compOffRequestModels.get(position).getAppliedTime());
        holder.tvStatus.setText(compOffRequestModels.get(position).getStatus());
        holder.tvDay.setText(compOffRequestModels.get(position).getDay());
        holder.tvcisorsuki.setText(compOffRequestModels.get(position).getOpt_user());



        holder.tvDelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Deleted this leave request?");
                builder.setTitle("Deleted!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        CompOffDeletedReqinterface compOffDeletedReqinterface=APIClient.getClient().create(CompOffDeletedReqinterface.class);
                        compOffDeletedReqinterface.CalDeleteReq("deleteCompoffForApproval",compOffRequestModels.get(position).getCompoffId(),compOffRequestModels.get(position).getEmpid()).enqueue(new Callback<CompOffDeleteReqResponse>() {
                            @Override
                            public void onResponse(Call<CompOffDeleteReqResponse> call, Response<CompOffDeleteReqResponse> response) {


                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        if (response.body().getSuccess().trim().equals("1")){
                                            Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        }else {

                                            AlertDialog.Builder msg=new AlertDialog.Builder(activity);
                                            msg.setMessage(response.body().getMessage());
                                            msg.setTitle(" Failed !");
                                            msg.setIcon(R.drawable.oops);
                                            AlertDialog alertDialog = msg.create();
                                            alertDialog.show();

                                        }


                                    }

                                }catch (Exception e){

                                }
                            }

                            @Override
                            public void onFailure(Call<CompOffDeleteReqResponse> call, Throwable t) {

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


        holder.tvRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Reject this leave request?");
                builder.setTitle("Rejected!");
                builder.setIcon(R.drawable.ic_baseline_dangerous_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        CompOffReqRejectedInterface compOffReqRejectedInterface = APIClient.getClient().create(CompOffReqRejectedInterface.class);
                        compOffReqRejectedInterface.CalRejectedReq("rejectCompoffForApproval", compOffRequestModels.get(position).getCompoffId(), compOffRequestModels.get(position).getEmpid(), compOffRequestModels.get(position).getAppliedDt()).enqueue(new Callback<CompoffReqRejectedResponse>() {
                            @Override
                            public void onResponse(Call<CompoffReqRejectedResponse> call, Response<CompoffReqRejectedResponse> response) {

                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        if (response.body().getSuccess().trim().equals("1")){
                                            Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        }else {

                                            AlertDialog.Builder msg=new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                                            msg.setMessage(response.body().getMessage());
                                            msg.setTitle(" Failed !");
                                            msg.setIcon(R.drawable.oops);
                                            AlertDialog alertDialog = msg.create();
                                            alertDialog.show();

                                        }


                                    }

                                }catch (Exception e){

                                }

                            }

                            @Override
                            public void onFailure(Call<CompoffReqRejectedResponse> call, Throwable t) {

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


        holder.tvApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                CompoffReqApprovalInterface compoffReqApprovalInterface = APIClient.getClient().create(CompoffReqApprovalInterface.class);
                compoffReqApprovalInterface.CalApprovalReq("approveCompoffForApproval", compOffRequestModels.get(position).getCompoffId(), compOffRequestModels.get(position).getEmpid()).enqueue(new Callback<CompOffreqApporvalResponse>() {
                    @Override
                    public void onResponse(Call<CompOffreqApporvalResponse> call, Response<CompOffreqApporvalResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();

                                if (response.body().getSuccess().trim().equals("1")) {
                                    Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    activity.finish();
                                    activity.overridePendingTransition(0, 0);
                                    activity.startActivity(activity.getIntent());
                                    activity.overridePendingTransition(0, 0);
                                } else {

                                    AlertDialog.Builder msg = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                                    msg.setMessage(response.body().getMessage());
                                    msg.setTitle(" Failed !");
                                    msg.setIcon(R.drawable.oops);
                                    AlertDialog alertDialog = msg.create();
                                    alertDialog.show();

                                }


                            }

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<CompOffreqApporvalResponse> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return compOffRequestModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmpId, tvAppliedDate, tvAppliedTime, tvStatus, tvDay, tvApproval, tvRejected, tvDelected,tvcisorsuki;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvAppliedDate = itemView.findViewById(R.id.tvLeaveType);
            tvAppliedTime = itemView.findViewById(R.id.tvReason);
            tvStatus = itemView.findViewById(R.id.tvFDHD);
            tvDay = itemView.findViewById(R.id.tvAppliedDate);
            tvApproval = itemView.findViewById(R.id.tvApproved);
            tvRejected = itemView.findViewById(R.id.tvRejected);
            tvDelected = itemView.findViewById(R.id.tvDeleted);
            tvcisorsuki=itemView.findViewById(R.id.tvcisorsuki);

        }
    }
}
