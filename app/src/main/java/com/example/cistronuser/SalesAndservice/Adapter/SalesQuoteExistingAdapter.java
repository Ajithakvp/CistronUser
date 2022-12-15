package com.example.cistronuser.SalesAndservice.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SaleQuoteExistingUpdateModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SalesQuoteExistingAdapter extends RecyclerView.Adapter<SalesQuoteExistingAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SaleQuoteExistingUpdateModel>saleQuoteExistingUpdateModels;

    public SalesQuoteExistingAdapter(Activity activity, ArrayList<SaleQuoteExistingUpdateModel> saleQuoteExistingUpdateModels) {
        this.activity = activity;
        this.saleQuoteExistingUpdateModels = saleQuoteExistingUpdateModels;
    }

    @NonNull
    @Override
    public SalesQuoteExistingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_update_dialog, parent, false);
        return new SalesQuoteExistingAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteExistingAdapter.ViewHolder holder, int position) {
        holder.tvDate.setText(saleQuoteExistingUpdateModels.get(position).getDate());
        holder.tvStatus.setText(saleQuoteExistingUpdateModels.get(position).getStatus());
        holder.tvRemark.setText(saleQuoteExistingUpdateModels.get(position).getRemark());
        holder.tvUpdateby.setText(saleQuoteExistingUpdateModels.get(position).getUpdatedBy());

    }

    @Override
    public int getItemCount() {
        return saleQuoteExistingUpdateModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvStatus,tvRemark,tvUpdateby;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvRemark=itemView.findViewById(R.id.tvRemark);
            tvUpdateby=itemView.findViewById(R.id.tvUpdateby);
        }
    }
}
