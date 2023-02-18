package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareReqPendingReportViewDetailsCOModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SpareReqpendingViewAdapter extends RecyclerView.Adapter<SpareReqpendingViewAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareReqPendingReportViewDetailsCOModel>spareReqPendingReportViewDetailsCOModels;

    public SpareReqpendingViewAdapter(Activity activity, ArrayList<SpareReqPendingReportViewDetailsCOModel> spareReqPendingReportViewDetailsCOModels) {
        this.activity = activity;
        this.spareReqPendingReportViewDetailsCOModels = spareReqPendingReportViewDetailsCOModels;
    }

    @NonNull
    @Override
    public SpareReqpendingViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_req_pending_co_view_dialolg_adapter, parent, false);
        return new SpareReqpendingViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareReqpendingViewAdapter.ViewHolder holder, int position) {

        holder.tvPartName.setText(spareReqPendingReportViewDetailsCOModels.get(position).getPartName());
        holder.tvPartNo.setText(spareReqPendingReportViewDetailsCOModels.get(position).getPartNo());
        holder.tvReqQty.setText(spareReqPendingReportViewDetailsCOModels.get(position).getReqQty());

    }

    @Override
    public int getItemCount() {
        return spareReqPendingReportViewDetailsCOModels.size();
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
