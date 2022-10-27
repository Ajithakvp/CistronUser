package com.example.cistronuser.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.R;

public class LeaveDetailsAdapter extends RecyclerView.Adapter<LeaveDetailsAdapter.ViewHolder> {

    Activity activity;


    public LeaveDetailsAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public LeaveDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveDetailsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvDay, tvLeaveType, tvReason, tvfullday, tvStatusApproved, tvStatusPending, tvStatusDelate, tvStatusRejected;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvDate = itemView.findViewById(R.id.tvDate);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvfullday = itemView.findViewById(R.id.tvfullday);
            tvStatusApproved = itemView.findViewById(R.id.tvStatusApproved);
            tvStatusPending = itemView.findViewById(R.id.tvStatusPending);
            tvStatusDelate = itemView.findViewById(R.id.tvStatusDelate);
            tvStatusRejected = itemView.findViewById(R.id.tvStatusRejected);

        }

    }
}
