package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SparesConsumedRecordModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SparesConsumedAdapter extends RecyclerView.Adapter<SparesConsumedAdapter.Viewholder> {

    Activity activity;
    public ArrayList<SparesConsumedRecordModel> sparesConsumedRecordModels;

    public SparesConsumedAdapter(Activity activity, ArrayList<SparesConsumedRecordModel> sparesConsumedRecordModels) {
        this.activity = activity;
        this.sparesConsumedRecordModels = sparesConsumedRecordModels;
    }

    @NonNull
    @Override
    public SparesConsumedAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_consumed_adapter, parent, false);
        return new SparesConsumedAdapter.Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SparesConsumedAdapter.Viewholder holder, int position) {

        holder.tvPartName.setText(sparesConsumedRecordModels.get(position).getPartName());
        holder.tvPartNo.setText(sparesConsumedRecordModels.get(position).getPartNo());
        holder.tvDate.setText(sparesConsumedRecordModels.get(position).getDt());
        holder.tvConsumeedeQty.setText(sparesConsumedRecordModels.get(position).getQty());
        holder.tvUnitPrice.setText(sparesConsumedRecordModels.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return sparesConsumedRecordModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvPartName,tvPartNo,tvDate,tvUnitPrice,tvConsumeedeQty;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvUnitPrice=itemView.findViewById(R.id.tvUnitPrice);
            tvConsumeedeQty=itemView.findViewById(R.id.tvStoreQty);


        }
    }
}
