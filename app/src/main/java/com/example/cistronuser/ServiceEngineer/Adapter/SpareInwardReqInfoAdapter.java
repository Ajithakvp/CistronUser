package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareInwardReqInfoModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SpareInwardReqInfoAdapter extends RecyclerView.Adapter<SpareInwardReqInfoAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareInwardReqInfoModel>spareInwardReqInfoModels;

    public SpareInwardReqInfoAdapter(Activity activity, ArrayList<SpareInwardReqInfoModel> spareInwardReqInfoModels) {
        this.activity = activity;
        this.spareInwardReqInfoModels = spareInwardReqInfoModels;
    }

    @NonNull
    @Override
    public SpareInwardReqInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_inward_req_info_adapter, parent, false);
        return new SpareInwardReqInfoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareInwardReqInfoAdapter.ViewHolder holder, int position) {

        holder.tvPartName.setText(spareInwardReqInfoModels.get(position).getPartName());
        holder.tvPartNo.setText(spareInwardReqInfoModels.get(position).getPartNo());
        holder.tvReqQty.setText(spareInwardReqInfoModels.get(position).getReqQty());
        holder.tvallocattedQty.setText(spareInwardReqInfoModels.get(position).getAllocatedQty());

    }

    @Override
    public int getItemCount() {
        return spareInwardReqInfoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartName,tvPartNo,tvReqQty,tvallocattedQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvReqQty=itemView.findViewById(R.id.tvReqQty);
            tvallocattedQty=itemView.findViewById(R.id.tvallocattedQty);

        }
    }
}
