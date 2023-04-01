package com.example.cistronuser.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Model.LeavedetailsModel;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;
import com.example.cistronuser.Activity.LeaveActivity;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.Common.WebviewPage;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.ViewHolder> {

    Activity activity;
    Context context;

    public ArrayList<LeavedetailsModel>leavedetailsModels;

    public ApprovedAdapter(Activity activity, ArrayList<LeavedetailsModel> leavedetailsModels) {
        this.activity = activity;
        this.leavedetailsModels = leavedetailsModels;
    }

    @NonNull
    @Override
    public ApprovedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_details, parent, false);
        return new ApprovedAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvDate.setText(leavedetailsModels.get(position).getDate());
        holder.tvDay.setText(leavedetailsModels.get(position).getDay());
        holder.tvfullday.setText(leavedetailsModels.get(position).getFd_hd());
        holder.tvLeaveType.setText(leavedetailsModels.get(position).getLeave_type());
        holder.tvReason.setText(leavedetailsModels.get(position).getReason());
        holder.tvattach.setText(leavedetailsModels.get(position).getAttachment());

        if (leavedetailsModels.get(position).getCancel().trim().equals("")){

            holder.ivCancel.setVisibility(View.GONE);

        }else {
            holder.ivCancel.setVisibility(View.VISIBLE);
        }


        holder.ivfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, WebviewPage.class);
                intent.putExtra("pdf",leavedetailsModels.get(position).getAttachment());
                Log.e(TAG, "onClick: "+leavedetailsModels.get(position).getAttachment() );
                activity.startActivity(intent);
            }
        });



        try {
            if (leavedetailsModels.get(position).getAttachment().trim().equals("")){
                holder.tvStatusTag.setVisibility(View.GONE);
                holder.ivfile.setVisibility(View.GONE);
            }else {
                holder.tvStatusTag.setVisibility(View.VISIBLE);
                holder.ivfile.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }




        holder.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to cancel ?");
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
                        DeletedAPIInterface deletedAPIInterface= APIClient.getClient().create(DeletedAPIInterface.class);
                        deletedAPIInterface.CallDetails("cancelLeave", PreferenceManager.getEmpID(activity),leavedetailsModels.get(position).getId()).enqueue(new Callback<DeleteResponse>() {
                            @Override
                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        activity.overridePendingTransition(0, 0);
                                        activity.startActivity(activity.getIntent());
                                        activity.overridePendingTransition(0, 0);
                                        activity.finish();
                                    }

                                }catch (Exception e){

                                }
                            }

                            @Override
                            public void onFailure(Call<DeleteResponse> call, Throwable t) {

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

    @Override
    public int getItemCount() {
        return leavedetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvDay, tvLeaveType, tvReason, tvfullday, tvattach,tvStatusTag;
        ImageView ivCancel,ivfile;
        ProgressBar simpleProgressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvDate = itemView.findViewById(R.id.tvDate);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvfullday = itemView.findViewById(R.id.tvfullday);
            tvattach = itemView.findViewById(R.id.tvStatusPending);
            ivCancel = itemView.findViewById(R.id.ivCancel);
            simpleProgressBar=itemView.findViewById(R.id.simpleProgressBar);
            tvStatusTag=itemView.findViewById(R.id.tvStatusTag);
            ivfile=itemView.findViewById(R.id.ivfile);

        }

    }
}
