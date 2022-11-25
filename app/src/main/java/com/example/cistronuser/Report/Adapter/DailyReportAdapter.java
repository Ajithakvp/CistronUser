package com.example.cistronuser.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.DailyReportAttendanceModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class DailyReportAdapter extends RecyclerView.Adapter<DailyReportAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<DailyReportAttendanceModel>dailyReportAttendanceModels;

    public DailyReportAdapter(Activity activity, ArrayList<DailyReportAttendanceModel> dailyReportAttendanceModels) {
        this.activity = activity;
        this.dailyReportAttendanceModels = dailyReportAttendanceModels;
    }

    @NonNull
    @Override
    public DailyReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_report_attendance, parent, false);
        return new DailyReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyReportAdapter.ViewHolder holder, int position) {

        holder.tvsNo.setText(dailyReportAttendanceModels.get(position).getSno());
        holder.tvName.setText(dailyReportAttendanceModels.get(position).getEmp());
        holder.tvStatus.setText(dailyReportAttendanceModels.get(position).getStatus());
        holder.tvCity.setText(dailyReportAttendanceModels.get(position).getCity());


    }

    @Override
    public int getItemCount() {
        return dailyReportAttendanceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvsNo,tvName,tvStatus,tvCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCity=itemView.findViewById(R.id.tvCity);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvName=itemView.findViewById(R.id.tvName);
            tvsNo=itemView.findViewById(R.id.tvsNo);

        }
    }
}
