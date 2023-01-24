package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareReqViewModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SpareReqViewDialogAdapter extends RecyclerView.Adapter<SpareReqViewDialogAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareReqViewModel>spareReqViewModels;


    public SpareReqViewDialogAdapter(Activity activity, ArrayList<SpareReqViewModel> spareReqViewModels) {
        this.activity = activity;
        this.spareReqViewModels = spareReqViewModels;
    }

    @NonNull
    @Override
    public SpareReqViewDialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_req_view_dialog, parent, false);
        return new SpareReqViewDialogAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareReqViewDialogAdapter.ViewHolder holder, int position) {

        holder.tvCRNo.setText(spareReqViewModels.get(position).getCrNo());
        holder.tvPart.setText(spareReqViewModels.get(position).getPartNo());
        holder.tvPartName.setText(spareReqViewModels.get(position).getPartName());
        holder.tvReqQty.setText(spareReqViewModels.get(position).getReqQty());

    }

    @Override
    public int getItemCount() {
        return spareReqViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCRNo,tvPart,tvPartName,tvReqQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCRNo=itemView.findViewById(R.id.tvCRNo);
            tvPart=itemView.findViewById(R.id.tvPart);
            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvReqQty=itemView.findViewById(R.id.tvReqQty);

        }
    }
}
