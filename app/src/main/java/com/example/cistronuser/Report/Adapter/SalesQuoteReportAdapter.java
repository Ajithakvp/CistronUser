package com.example.cistronuser.Report.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SalesQuoteReportModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SalesQuoteReportAdapter extends RecyclerView.Adapter<SalesQuoteReportAdapter.ViewHolder> {

    Activity activity;

    public ArrayList<SalesQuoteReportModel>salesQuoteReportModels;

    public SalesQuoteReportAdapter(Activity activity, ArrayList<SalesQuoteReportModel> salesQuoteReportModels) {
        this.activity = activity;
        this.salesQuoteReportModels = salesQuoteReportModels;
    }

    @NonNull
    @Override
    public SalesQuoteReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_report_adapter, parent, false);
        return new SalesQuoteReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteReportAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvName.setText(salesQuoteReportModels.get(position).getUser());
        holder.tvHospital.setText(salesQuoteReportModels.get(position).getQuoteCust());
        holder.tvProduct.setText(salesQuoteReportModels.get(position).getQuoteProd());
        holder.tvDate.setText(salesQuoteReportModels.get(position).getQuoteDt());
        holder.tvQuote.setText(salesQuoteReportModels.get(position).getQuoteRef());
        String QuotePdf=salesQuoteReportModels.get(position).getQuotePdf();


        if (salesQuoteReportModels.get(position).getStatus().trim().equals("")){
            holder.tvStatus.setVisibility(View.GONE);
            holder.tvStatusOther.setVisibility(View.GONE);
            holder.tvStatusTag.setVisibility(View.GONE);
        }

        if (salesQuoteReportModels.get(position).getStatus().trim().equals("Finalized")){
            holder.tvStatus.setVisibility(View.VISIBLE);
            holder.tvStatusOther.setVisibility(View.GONE);
            holder.tvStatus.setText(salesQuoteReportModels.get(position).getStatus());
            holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.quote_report_details);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    TextView tvDate=dialog.findViewById(R.id.tvDate);
                    TextView tvUser=dialog.findViewById(R.id.tvUser);
                    TextView tvStatus=dialog.findViewById(R.id.tvStatus);
                    TextView tvRemark=dialog.findViewById(R.id.tvRemark);
                    ImageView ivClose=dialog.findViewById(R.id.ivClose);

                    tvDate.setText(salesQuoteReportModels.get(position).getSalesQuoteReportDetails().getDate());
                    tvUser.setText(salesQuoteReportModels.get(position).getSalesQuoteReportDetails().getPerson());
                    tvStatus.setText(salesQuoteReportModels.get(position).getSalesQuoteReportDetails().getStatus());
                    tvRemark.setText(salesQuoteReportModels.get(position).getSalesQuoteReportDetails().getRemarks());
                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            });

        }else {
            holder.tvStatus.setVisibility(View.GONE);
            holder.tvStatusOther.setVisibility(View.VISIBLE);
            holder.tvStatusOther.setText(salesQuoteReportModels.get(position).getStatus());
        }

        holder.ivQuotePdf.setOnClickListener(new View.OnClickListener() {
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
        return salesQuoteReportModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvHospital,tvDate,tvQuote,tvProduct,tvStatus,tvStatusOther,tvStatusTag;
        ImageView ivQuotePdf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvHospital=itemView.findViewById(R.id.tvHospital);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvQuote=itemView.findViewById(R.id.tvQuote);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvStatusOther=itemView.findViewById(R.id.tvStatusOther);
            ivQuotePdf=itemView.findViewById(R.id.ivQuotePdf);
            tvStatusTag=itemView.findViewById(R.id.tvStatusTag);


        }
    }
}
