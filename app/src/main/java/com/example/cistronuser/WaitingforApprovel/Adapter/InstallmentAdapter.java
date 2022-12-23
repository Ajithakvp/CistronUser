package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SalesQuoteApprovalViewInstallmentModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentAdapter.ViewHolder> {
    Activity activity;
    public ArrayList<SalesQuoteApprovalViewInstallmentModel>viewInstallmentModels;

    public InstallmentAdapter(Activity activity, ArrayList<SalesQuoteApprovalViewInstallmentModel> viewInstallmentModels) {
        this.activity = activity;
        this.viewInstallmentModels = viewInstallmentModels;
    }

    @NonNull
    @Override
    public InstallmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.installment_view, parent, false);
        return new InstallmentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstallmentAdapter.ViewHolder holder, int position) {

        holder.tvAmt.setText(viewInstallmentModels.get(position).getInstalment());

    }

    @Override
    public int getItemCount() {
        return viewInstallmentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmtTag,tvAmt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmtTag=itemView.findViewById(R.id.tvAmtTag);
            tvAmt=itemView.findViewById(R.id.tvAmt);
        }
    }
}
