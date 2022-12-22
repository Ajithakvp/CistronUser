package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SalesQuoteApprovalListModel;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Activity.SalesQuoteApproval;

import java.util.ArrayList;

public class SalesQuoteApprovalAdapter extends RecyclerView.Adapter<SalesQuoteApprovalAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SalesQuoteApprovalListModel>salesQuoteApprovalListModels;

    public SalesQuoteApprovalAdapter(Activity activity, ArrayList<SalesQuoteApprovalListModel> salesQuoteApprovalListModels) {
        this.activity = activity;
        this.salesQuoteApprovalListModels = salesQuoteApprovalListModels;
    }

    @NonNull
    @Override
    public SalesQuoteApprovalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_approval_list, parent, false);
        return new SalesQuoteApprovalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteApprovalAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(salesQuoteApprovalListModels.get(position).getUser());
        holder.tvDistrict.setText(salesQuoteApprovalListModels.get(position).getDistrict());
        holder.tvProduct.setText(salesQuoteApprovalListModels.get(position).getProduct());
        holder.tvHospital.setText(salesQuoteApprovalListModels.get(position).getHospital());
        String QuotePdf=salesQuoteApprovalListModels.get(position).getQuotePdf();

        holder.ivPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(QuotePdf);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return salesQuoteApprovalListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvHospital,tvDistrict,tvProduct;
        ImageView ivPdf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvHospital=itemView.findViewById(R.id.tvHospital);
            tvDistrict=itemView.findViewById(R.id.tvDistrict);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            ivPdf=itemView.findViewById(R.id.ivPdf);

        }
    }
}
