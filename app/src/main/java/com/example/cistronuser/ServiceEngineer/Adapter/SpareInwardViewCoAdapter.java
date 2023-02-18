package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareInwardViewCoModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SpareInwardViewCoAdapter extends RecyclerView.Adapter<SpareInwardViewCoAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareInwardViewCoModel>spareInwardViewCoModels;

    public SpareInwardViewCoAdapter(Activity activity, ArrayList<SpareInwardViewCoModel> spareInwardViewCoModels) {
        this.activity = activity;
        this.spareInwardViewCoModels = spareInwardViewCoModels;
    }

    @NonNull
    @Override
    public SpareInwardViewCoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_inward_view_dialog_adapter, parent, false);
        return new SpareInwardViewCoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareInwardViewCoAdapter.ViewHolder holder, int position) {

        holder.tvPartName.setText(spareInwardViewCoModels.get(position).getPartName());
        holder.tvPartNo.setText(spareInwardViewCoModels.get(position).getPartNo());
        holder.tvReqQty.setText(spareInwardViewCoModels.get(position).getReqQty());
    }

    @Override
    public int getItemCount() {
        return spareInwardViewCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartName,tvPartNo,tvReqQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvReqQty=itemView.findViewById(R.id.tvReqQty);
        }
    }
}
