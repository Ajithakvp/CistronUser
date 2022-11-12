package com.example.cistronuser.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

        holder.ivfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, WebviewPage.class);
                intent.putExtra("pdf",leavedetailsModels.get(position).getAttachment());
                activity.startActivity(intent);
            }
        });



        try {
            if (leavedetailsModels.get(position).getAttachment().trim().equals(null)){
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
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure you want to cancel ?");
                builder.setTitle("cancel!");
                builder.setIcon(R.drawable.ic_baseline_cancel_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeletedAPIInterface deletedAPIInterface= APIClient.getClient().create(DeletedAPIInterface.class);
                        deletedAPIInterface.CallDetails("cancelLeave", PreferenceManager.getEmpID(activity),leavedetailsModels.get(position).getId()).enqueue(new Callback<LeaveDetailsResponse>() {
                            @Override
                            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                                try{
                                    if (response.isSuccessful()){

                                       activity.finish();

                                        Toast.makeText(activity, "cancel", Toast.LENGTH_SHORT).show();

                                    }

                                }catch (Exception e){

                                }

                            }

                            @Override
                            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

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
