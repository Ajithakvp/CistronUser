package com.example.cistronuser.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Activity.LeaveActivity;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.Common.WebviewPage;
import com.example.cistronuser.LoginActivity;
import com.example.cistronuser.R;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {


    public ArrayList<LeavedetailsModel> leavedetailsModels;
    Activity activity;

    public PendingAdapter(Activity activity, ArrayList<LeavedetailsModel> leavedetailsModels) {
        this.activity = activity;
        this.leavedetailsModels = leavedetailsModels;
    }

    @NonNull
    @Override
    public PendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_adapter, parent, false);
        return new PendingAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvDate.setText(leavedetailsModels.get(position).getDate());
        holder.tvDay.setText(leavedetailsModels.get(position).getDay());
        holder.tvfullday.setText(leavedetailsModels.get(position).getFd_hd());
        holder.tvLeaveType.setText(leavedetailsModels.get(position).getLeave_type());
        holder.tvReason.setText(leavedetailsModels.get(position).getReason());
        holder.tvattach.setText(leavedetailsModels.get(position).getAttachment());


        holder.ivfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WebviewPage.class);
                intent.putExtra("pdf", leavedetailsModels.get(position).getAttachment());
                activity.startActivity(intent);
            }
        });
        try {

            if (leavedetailsModels.get(position).getAttachment().trim().equals(null)) {
                Log.e(TAG, "onBindViewHolder: " + leavedetailsModels.get(position).getAttachment().trim().equals("null"));
                holder.tvStatusTag.setVisibility(View.GONE);
                holder.ivfile.setVisibility(View.GONE);
            } else {

                holder.tvStatusTag.setVisibility(View.VISIBLE);
                holder.ivfile.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {

        }


        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.simpleProgressBar.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure you want to delete this leave request?");
                builder.setTitle("Deleted!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        DeletedAPIInterface deletedAPIInterface = APIClient.getClient().create(DeletedAPIInterface.class);
                        deletedAPIInterface.CallDetails("deleteLeave", PreferenceManager.getEmpID(activity), leavedetailsModels.get(position).getId()).enqueue(new Callback<DeleteResponse>() {
                            @Override
                            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        activity.finish();
                                        activity.overridePendingTransition(0, 0);
                                        activity.startActivity(activity.getIntent());
                                        activity.overridePendingTransition(0, 0);
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

        TextView tvDate, tvDay, tvLeaveType, tvReason, tvfullday, tvattach, tvStatusTag;
        ImageView ivDelete, ivfile;
        ProgressBar simpleProgressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvfullday = itemView.findViewById(R.id.tvfullday);
            tvattach = itemView.findViewById(R.id.tvStatusPending);
            simpleProgressBar = itemView.findViewById(R.id.simpleProgressBar);
            tvStatusTag = itemView.findViewById(R.id.tvStatusTag);
            ivfile = itemView.findViewById(R.id.ivfile);
        }


    }
}
