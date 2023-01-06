package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class UpcomingCallAdapter extends RecyclerView.Adapter<UpcomingCallAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<UpcomingCallListModel>upcomingCallReportlistModels;

    public UpcomingCallAdapter(Activity activity, ArrayList<UpcomingCallListModel> upcomingCallReportlistModels) {
        this.activity = activity;
        this.upcomingCallReportlistModels = upcomingCallReportlistModels;
    }

    @NonNull
    @Override
    public UpcomingCallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_call_list, parent, false);
        return new UpcomingCallAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingCallAdapter.ViewHolder holder, int position) {
        holder.tvCR.setText(upcomingCallReportlistModels.get(position).getCrNo());
        holder.tvName.setText(upcomingCallReportlistModels.get(position).getHospital().replace("\\n","\n"));
        holder.tvDate.setText(upcomingCallReportlistModels.get(position).getDate());
        holder.tvContactDetails.setText(upcomingCallReportlistModels.get(position).getContact().replace("\\n","\n"));
        holder.tvLocation.setText(upcomingCallReportlistModels.get(position).getAddress().replace("\\n","\n"));
        holder.tvProduct.setText(upcomingCallReportlistModels.get(position).getProduct());
        holder.tvIssueDetails.setText(upcomingCallReportlistModels.get(position).getIssue());
        holder.tvCallCreateBy.setText(upcomingCallReportlistModels.get(position).getCreatedBy());
        holder.tvMobileNo.setText(upcomingCallReportlistModels.get(position).getMobile());

        String MobileNo=upcomingCallReportlistModels.get(position).getMobile();

        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+MobileNo));
                activity.startActivity(intent);
            }
        });

        holder.tvCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(activity);
                dialog.setContentView(R.layout.cr_service_list);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return upcomingCallReportlistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvDate,tvContactDetails,tvLocation,tvProduct,tvCR,tvIssueDetails,tvCallCreateBy,tvMobileNo;

        ImageView ivCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvContactDetails=itemView.findViewById(R.id.tvContactDetails);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            tvCR=itemView.findViewById(R.id.tvCR);
            tvIssueDetails=itemView.findViewById(R.id.tvIssueDetails);
            tvCallCreateBy=itemView.findViewById(R.id.tvCallCreateBy);
            ivCall=itemView.findViewById(R.id.ivCall);
            tvMobileNo=itemView.findViewById(R.id.tvMobileNo);
        }
    }
}
