package com.example.cistronuser.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.LeaveReportMonthlyModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class LeaveReportAdapter extends RecyclerView.Adapter<LeaveReportAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<LeaveReportMonthlyModel>leaveReportMonthlyModels;

    public LeaveReportAdapter(Activity activity, ArrayList<LeaveReportMonthlyModel> leaveReportMonthlyModels) {
        this.activity = activity;
        this.leaveReportMonthlyModels = leaveReportMonthlyModels;
    }

    @NonNull
    @Override
    public LeaveReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_report_adapter, parent, false);
        return new LeaveReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveReportAdapter.ViewHolder holder, int position) {


        holder.tvDate.setText(leaveReportMonthlyModels.get(position).getDate());
        holder.tvFDHD.setText(leaveReportMonthlyModels.get(position).getFdhd());
        holder.tvleavetype.setText(leaveReportMonthlyModels.get(position).getLeavetype());
        holder.tvReason.setText(leaveReportMonthlyModels.get(position).getReason());

        if (leaveReportMonthlyModels.get(position).getLeave_info().trim().equals("")){
            holder.tvCount.setVisibility(View.GONE);

        }else {
            holder.tvCount.setVisibility(View.VISIBLE);
            holder.tvCount.setText(leaveReportMonthlyModels.get(position).getLeave_info());

        }

        if (leaveReportMonthlyModels.get(position).getEmployee().trim().equals("")){
            holder.tvName.setVisibility(View.GONE);
        }else {
            holder.tvName.setVisibility(View.VISIBLE);
            holder.tvName.setText(leaveReportMonthlyModels.get(position).getEmployee());
        }

        if (leaveReportMonthlyModels.get(position).getTotal().trim().equals("")){
            holder.tvTotal.setVisibility(View.GONE);

        }else {
            holder.tvTotal.setVisibility(View.VISIBLE);
            holder.tvTotal.setText(leaveReportMonthlyModels.get(position).getTotal());
        }



    }

    @Override
    public int getItemCount() {
        return leaveReportMonthlyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDate,tvFDHD,tvleavetype,tvReason,tvCount,tvTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvReason=itemView.findViewById(R.id.tvReason);
            tvName=itemView.findViewById(R.id.tvName);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvFDHD=itemView.findViewById(R.id.tvFDHD);
            tvleavetype=itemView.findViewById(R.id.tvleavetype);
            tvCount=itemView.findViewById(R.id.tvCount);
            tvTotal=itemView.findViewById(R.id.tvTotal);

        }
    }
}
