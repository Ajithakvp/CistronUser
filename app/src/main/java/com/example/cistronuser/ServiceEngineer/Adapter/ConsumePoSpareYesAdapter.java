package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.CustomerPoSpareRecordModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ConsumePoSpareYesAdapter extends RecyclerView.Adapter<ConsumePoSpareYesAdapter.ViewHolder> {
    Activity activity;

    public ArrayList<CustomerPoSpareRecordModel>customerPoSpareRecordModels;

    public ConsumePoSpareYesAdapter(Activity activity, ArrayList<CustomerPoSpareRecordModel> customerPoSpareRecordModels) {
        this.activity = activity;
        this.customerPoSpareRecordModels = customerPoSpareRecordModels;
    }

    @NonNull
    @Override
    public ConsumePoSpareYesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_po_spare_dialog_yes, parent, false);
        return new ConsumePoSpareYesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumePoSpareYesAdapter.ViewHolder holder, int position) {
        holder.tvPartName.setText(customerPoSpareRecordModels.get(position).getPartName());
        holder.tvPartNo.setText(customerPoSpareRecordModels.get(position).getPartNo());
        holder.tvqtyToConsume.setText(customerPoSpareRecordModels.get(position).getQtyToConsume());

    }

    @Override
    public int getItemCount() {
        return customerPoSpareRecordModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartName,tvPartNo,tvqtyToConsume;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvqtyToConsume=itemView.findViewById(R.id.tvqtyToConsume);

        }
    }
}
