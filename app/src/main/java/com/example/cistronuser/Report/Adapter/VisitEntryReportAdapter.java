package com.example.cistronuser.Report.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.VisitEntryReportListModel;
import com.example.cistronuser.Common.WebviewPage;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class VisitEntryReportAdapter extends RecyclerView.Adapter<VisitEntryReportAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<VisitEntryReportListModel>visitEntryReportListModels;



    public VisitEntryReportAdapter(Activity activity, ArrayList<VisitEntryReportListModel> visitEntryReportListModels) {
        this.activity = activity;
        this.visitEntryReportListModels = visitEntryReportListModels;
    }

    @NonNull
    @Override
    public VisitEntryReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visit_entry_report_list, parent, false);
        return new VisitEntryReportAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitEntryReportAdapter.ViewHolder holder, int position) {

       String  QuotePdf=visitEntryReportListModels.get(position).getQuotePdf();
       String  OApdf=visitEntryReportListModels.get(position).getOaPdf();

        holder.tvDate.setText(visitEntryReportListModels.get(position).getEntry());
        holder.tvProduct.setText(visitEntryReportListModels.get(position).getProduct());
        holder.tvHospital.setText(visitEntryReportListModels.get(position).getHospital());



        if (visitEntryReportListModels.get(position).getName().trim().equals("") && visitEntryReportListModels.get(position).getEmpid().trim().equals("")){

            holder.rlEmployee.setVisibility(View.GONE);

        }else {
            holder.rlEmployee.setVisibility(View.VISIBLE);
            holder.tvEmpId.setText(visitEntryReportListModels.get(position).getEmpid());
            holder.tvName.setText(visitEntryReportListModels.get(position).getName());

        }


        if (visitEntryReportListModels.get(position).getQuote().trim().equals("") && visitEntryReportListModels.get(position).getQuotePdf().trim().equals("")){
            holder.rlQuote.setVisibility(View.GONE);
        }else {
            holder.rlQuote.setVisibility(View.VISIBLE);
            holder.tvQuote.setText(visitEntryReportListModels.get(position).getQuote());

        }


        if (visitEntryReportListModels.get(position).getOaNo().trim().equals("") && visitEntryReportListModels.get(position).getOaPdf().trim().equals("")){
            holder.rlOA.setVisibility(View.GONE);
        }else {
            holder.rlOA.setVisibility(View.VISIBLE);
            holder.tvOA.setText(visitEntryReportListModels.get(position).getOaNo());

        }

        if (visitEntryReportListModels.get(position).getComment().trim().equals("")){
            holder.tvCommentTag.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
        }else {
            holder.tvCommentTag.setVisibility(View.VISIBLE);
            holder.tvComment.setVisibility(View.VISIBLE);
            holder.tvComment.setText(visitEntryReportListModels.get(position).getComment());

        }

        holder.ivQuotePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CallPreview(QuotePdf);
            }
        });

        holder.ivOAPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPreview(OApdf);
            }
        });



    }

    private void CallPreview(String Pdf) {

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Pdf));
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return visitEntryReportListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlEmployee,rlQuote,rlOA;
        TextView tvEmpId,tvName,tvDate,tvHospital,tvProduct,tvQuote,tvOA,tvComment,tvCommentTag;
        ImageView ivQuotePdf,ivOAPdf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCommentTag=itemView.findViewById(R.id.tvCommentTag);
            rlEmployee=itemView.findViewById(R.id.rlEmployee);
            rlQuote=itemView.findViewById(R.id.rlQuote);
            rlOA=itemView.findViewById(R.id.rlOA);
            tvEmpId=itemView.findViewById(R.id.tvEmpId);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvName=itemView.findViewById(R.id.tvName);
            tvHospital=itemView.findViewById(R.id.tvHospital);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            tvQuote=itemView.findViewById(R.id.tvQuote);
            tvOA=itemView.findViewById(R.id.tvOA);
            tvComment=itemView.findViewById(R.id.tvComment);
            ivQuotePdf=itemView.findViewById(R.id.ivQuotePdf);
            ivOAPdf=itemView.findViewById(R.id.ivOAPdf);

        }
    }
}
