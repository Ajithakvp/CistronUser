package com.example.cistronuser.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.MonthlyReportAttendanceModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class MonthlyReportAdapter extends RecyclerView.Adapter<MonthlyReportAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<MonthlyReportAttendanceModel>monthlyReportAttendanceModels;

    public MonthlyReportAdapter(Activity activity, ArrayList<MonthlyReportAttendanceModel> monthlyReportAttendanceModels) {
        this.activity = activity;
        this.monthlyReportAttendanceModels = monthlyReportAttendanceModels;
    }

    @NonNull
    @Override
    public MonthlyReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_report_attendance, parent, false);
        return new MonthlyReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyReportAdapter.ViewHolder holder, int position) {

        holder.tvCity.setText(monthlyReportAttendanceModels.get(position).getCity());
        holder.tvDate.setText(monthlyReportAttendanceModels.get(position).getDate());
        holder.tvsNo.setText("#"+monthlyReportAttendanceModels.get(position).getSno());
        holder.tvStatus.setText(monthlyReportAttendanceModels.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return monthlyReportAttendanceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvsNo,tvName,tvStatus,tvCity,tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCity=itemView.findViewById(R.id.tvCity);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvName=itemView.findViewById(R.id.tvName);
            tvsNo=itemView.findViewById(R.id.tvsNo);
            tvDate=itemView.findViewById(R.id.tvDate);
        }
    }
}
