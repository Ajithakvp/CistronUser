package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.ServiceDetailsInterModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ServiceDetailsAdapter extends RecyclerView.Adapter<ServiceDetailsAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<ServiceDetailsInterModel>serviceDetailsInterModels;

    public ServiceDetailsAdapter(Activity activity, ArrayList<ServiceDetailsInterModel> serviceDetailsInterModels) {
        this.activity = activity;
        this.serviceDetailsInterModels = serviceDetailsInterModels;
    }

    @NonNull
    @Override
    public ServiceDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cr_service_list, parent, false);
        return new ServiceDetailsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDetailsAdapter.ViewHolder holder, int position) {


        holder.tvSR.setText(serviceDetailsInterModels.get(position).getSr());
        holder.tvEngineer.setText(serviceDetailsInterModels.get(position).getEngineer());
        holder.tvAssignedDetails.setText(serviceDetailsInterModels.get(position).getAssigned());
        holder.tvAttenedDetails.setText(serviceDetailsInterModels.get(position).getAttended());
        holder.tvProblam.setText(serviceDetailsInterModels.get(position).getProblem());
        holder.tvWorkDone.setText(serviceDetailsInterModels.get(position).getWorkDone());
        holder.tvEngineerAdvice.setText(serviceDetailsInterModels.get(position).getEngAdvice());
        holder.tvCustomerFeedBack.setText(serviceDetailsInterModels.get(position).getFeedback());
        holder.tvCallStatus.setText(serviceDetailsInterModels.get(position).getStatus());
        holder.tvPendingReason.setText(serviceDetailsInterModels.get(position).getPendingReason());
        holder.tvReport.setText(serviceDetailsInterModels.get(position).getReport());
        holder.tvPaidService.setText(serviceDetailsInterModels.get(position).getPaidInvoice());
        holder.tvSpareReplace.setText(serviceDetailsInterModels.get(position).getSpareInvoice());

        if (serviceDetailsInterModels.get(position).getReport().trim().equals("")){
            holder.tvReportTag.setVisibility(View.GONE);
            holder.tvReport.setVisibility(View.GONE);
        }else {
            holder.tvReportTag.setVisibility(View.VISIBLE);
            holder.tvReport.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getWarrantycard().trim().equals("")){
            holder.ivWarrentCard.setVisibility(View.GONE);
        }else {
            holder.ivWarrentCard.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getInstallationimage1().trim().equals("")){
            holder.ivImage1.setVisibility(View.GONE);
        }else {
            holder.ivImage1.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getInstallationimage2().trim().equals("")){
            holder.ivImage2.setVisibility(View.GONE);
        }else {
            holder.ivImage2.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getInstallationimage3().trim().equals("")){
            holder.ivImage3.setVisibility(View.GONE);
        }else {
            holder.ivImage3.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getEwaybill().trim().equals("")){
            holder.ivBill.setVisibility(View.GONE);
        }else {
            holder.ivBill.setVisibility(View.VISIBLE);
        }

        if (serviceDetailsInterModels.get(position).getLr().trim().equals("")){
            holder.ivlr.setVisibility(View.GONE);
        }else {
            holder.ivlr.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return serviceDetailsInterModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSR,tvEngineer,tvAssignedDetails,tvAttenedDetails,tvProblam,tvWorkDone,tvEngineerAdvice,
                tvCustomerFeedBack,tvCallStatus,tvPendingReason,tvReport,tvPaidService,tvSpareReplace;

        TextView tvReportTag;

        ImageView ivWarrentCard,ivImage1,ivImage2,ivImage3,ivBill,ivlr;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSR=itemView.findViewById(R.id.tvSR);
            tvEngineer=itemView.findViewById(R.id.tvEngineer);
            tvAssignedDetails=itemView.findViewById(R.id.tvAssignedDetails);
            tvAttenedDetails=itemView.findViewById(R.id.tvAttenedDetails);
            tvProblam=itemView.findViewById(R.id.tvProblam);
            tvWorkDone=itemView.findViewById(R.id.tvWorkDone);
            tvEngineerAdvice=itemView.findViewById(R.id.tvEngineerAdvice);
            tvCustomerFeedBack=itemView.findViewById(R.id.tvCustomerFeedBack);
            tvCallStatus=itemView.findViewById(R.id.tvCallStatus);
            tvPendingReason=itemView.findViewById(R.id.tvPendingReason);
            tvReport=itemView.findViewById(R.id.tvReport);
            tvPaidService=itemView.findViewById(R.id.tvPaidService);
            tvSpareReplace=itemView.findViewById(R.id.tvSpareReplace);
            ivWarrentCard=itemView.findViewById(R.id.ivWarrentCard);
            ivImage1=itemView.findViewById(R.id.ivImage1);
            ivImage2=itemView.findViewById(R.id.ivImage2);
            ivImage3=itemView.findViewById(R.id.ivImage3);
            ivBill=itemView.findViewById(R.id.ivBill);
            tvReportTag=itemView.findViewById(R.id.tvReportTag);
            ivlr=itemView.findViewById(R.id.ivlr);

        }
    }
}
