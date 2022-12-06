package com.example.cistronuser.Report.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LeaveReportDailyInterface;
import com.example.cistronuser.API.Model.LeaveReportDailyModel;
import com.example.cistronuser.API.Response.LeaveReportDailyResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveReportDailyAdapter extends RecyclerView.Adapter<LeaveReportDailyAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<LeaveReportDailyModel>leaveReportDailyModels;

    String Baseurl;

    public LeaveReportDailyAdapter(Activity activity, ArrayList<LeaveReportDailyModel> leaveReportDailyModels) {
        this.activity = activity;
        this.leaveReportDailyModels = leaveReportDailyModels;

    }

    @NonNull
    @Override
    public LeaveReportDailyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_report_daily_adapter, parent, false);
        return new LeaveReportDailyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveReportDailyAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(leaveReportDailyModels.get(position).getEmployee());
        holder.tvleavetype.setText(leaveReportDailyModels.get(position).getLeavetype());


        String File=leaveReportDailyModels.get(position).getMedicalattach();


        if (leaveReportDailyModels.get(position).getReason().trim().equals("")){
            holder.tvReason.setVisibility(View.GONE);
            holder.tvStatusTag.setVisibility(View.GONE);
        }else {
            holder.tvStatusTag.setVisibility(View.VISIBLE);
            holder.tvReason.setText("-"+leaveReportDailyModels.get(position).getReason());
        }

        if (leaveReportDailyModels.get(position).getFdhd().trim().equals("")){
            holder.tvDay.setVisibility(View.GONE);
            holder.tvFDHD.setVisibility(View.GONE);

        }else {
            holder.tvDay.setVisibility(View.VISIBLE);
            holder.tvFDHD.setText(leaveReportDailyModels.get(position).getFdhd());
        }



        LeaveReportDailyInterface leaveReportDailyInterface= APIClient.getClient().create(LeaveReportDailyInterface.class);
        leaveReportDailyInterface.callDaily("getDailyLeaveRecord","2022-12-18","","Cistron").enqueue(new Callback<LeaveReportDailyResponse>() {
            @Override
            public void onResponse(Call<LeaveReportDailyResponse> call, Response<LeaveReportDailyResponse> response) {
                Baseurl=response.body().getAttachBaseUrl();
            }

            @Override
            public void onFailure(Call<LeaveReportDailyResponse> call, Throwable t) {

            }
        });

        if (leaveReportDailyModels.get(position).getMedicalattach().trim().equals("")){
            holder.ivFileAttach.setVisibility(View.GONE);
            holder.tvFileAttachTag.setVisibility(View.GONE);
        }else {
            holder.ivFileAttach.setVisibility(View.VISIBLE);
            holder.tvFileAttachTag.setVisibility(View.VISIBLE);
        }

        holder.ivFileAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callView(Baseurl+File);
                Log.e(TAG, "onClick: "+Baseurl+File );
            }
        });



    }
    void callView(String s) {
        Uri uri=Uri.parse(s);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return leaveReportDailyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvleavetype,tvReason,tvFDHD,tvFileAttachTag,tvStatusTag,tvDay;
        ImageView ivFileAttach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvleavetype=itemView.findViewById(R.id.tvleavetype);
            tvReason=itemView.findViewById(R.id.tvReason);
            tvFDHD=itemView.findViewById(R.id.tvFDHD);
            tvFileAttachTag=itemView.findViewById(R.id.tvFileAttachTag);
            ivFileAttach=itemView.findViewById(R.id.ivFileAttach);
            tvStatusTag=itemView.findViewById(R.id.tvStatusTag);
            tvDay=itemView.findViewById(R.id.tvCityTag);

        }
    }
}
