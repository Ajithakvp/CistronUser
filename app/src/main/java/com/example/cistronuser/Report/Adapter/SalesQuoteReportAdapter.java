package com.example.cistronuser.Report.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.R;

public class SalesQuoteReportAdapter extends RecyclerView.Adapter<SalesQuoteReportAdapter.ViewHolder> {
    @NonNull
    @Override
    public SalesQuoteReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_report_adapter, parent, false);
        return new SalesQuoteReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteReportAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
