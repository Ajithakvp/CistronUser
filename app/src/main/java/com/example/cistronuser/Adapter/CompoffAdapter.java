package com.example.cistronuser.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.CompOffModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class CompoffAdapter extends RecyclerView.Adapter<CompoffAdapter.ViewHolder> {
    Activity activity;
    public ArrayList<CompOffModel>compOffModels;

    public CompoffAdapter(Activity activity, ArrayList<CompOffModel> compOffModels) {
        this.activity = activity;
        this.compOffModels = compOffModels;
    }

    @NonNull
    @Override
    public CompoffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.compoffadapter, parent, false);
        return new CompoffAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompoffAdapter.ViewHolder holder, int position) {
        holder.dateApply.setText(compOffModels.get(position).getDate_of_apply());
        holder.dateapproved.setText(compOffModels.get(position).getDate_of_approve());
        holder.status.setText(compOffModels.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return compOffModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateApply,dateapproved,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dateApply = itemView.findViewById(R.id.tvLeaveType);
            dateapproved = itemView.findViewById(R.id.tvReason);
            status = itemView.findViewById(R.id.tvfullday);
        }
    }
}
