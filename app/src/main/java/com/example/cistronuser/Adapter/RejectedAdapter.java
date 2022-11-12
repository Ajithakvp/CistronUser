package com.example.cistronuser.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.LeavedetailsModel;
import com.example.cistronuser.Common.WebviewPage;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class RejectedAdapter extends RecyclerView.Adapter<RejectedAdapter.ViewHolder> {

    Activity activity;

    public ArrayList<LeavedetailsModel> leavedetailsModels;

    public RejectedAdapter(Activity activity, ArrayList<LeavedetailsModel> leavedetailsModels) {
        this.activity = activity;
        this.leavedetailsModels = leavedetailsModels;
    }

    @NonNull
    @Override
    public RejectedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewleave, parent, false);
        return new RejectedAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RejectedAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

    }

    @Override
    public int getItemCount() {
        return leavedetailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvDay, tvLeaveType, tvReason, tvfullday, tvattach,tvStatusTag;
        ImageView ivfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvfullday = itemView.findViewById(R.id.tvfullday);
            tvattach = itemView.findViewById(R.id.tvStatusPending);
            tvStatusTag=itemView.findViewById(R.id.tvStatusTag);
            ivfile=itemView.findViewById(R.id.ivfile);
        }
    }
}
