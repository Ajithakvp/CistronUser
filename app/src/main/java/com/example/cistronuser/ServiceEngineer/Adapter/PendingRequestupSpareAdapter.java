package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareReqViewModel;
import com.example.cistronuser.API.Model.SpareRequestsRecordModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class PendingRequestupSpareAdapter extends RecyclerView.Adapter<PendingRequestupSpareAdapter.ViewHolder> {


    //Dialog
    RecyclerView rvView;
    SpareReqViewDialogAdapter spareReqViewDialogAdapter;

    Activity activity;
    public ArrayList<SpareRequestsRecordModel>spareRequestsRecordModels;

    public PendingRequestupSpareAdapter(Activity activity, ArrayList<SpareRequestsRecordModel> spareRequestsRecordModels) {
        this.activity = activity;
        this.spareRequestsRecordModels = spareRequestsRecordModels;
    }

    @NonNull
    @Override
    public PendingRequestupSpareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_request_upspare_adapter, parent, false);
        return new PendingRequestupSpareAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingRequestupSpareAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvSeialNo.setText(spareRequestsRecordModels.get(position).getReqNo());
        holder.tvDate.setText(spareRequestsRecordModels.get(position).getReqDt());
        holder.tvPurpose.setText(spareRequestsRecordModels.get(position).getReqPurpose());

        holder.cvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog=new Dialog(activity);
                dialog.setContentView(R.layout.spare_req_view_dialog_recycleview);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                rvView=dialog.findViewById(R.id.rvView);
                ImageView ivClose=dialog.findViewById(R.id.ivClose);


                spareReqViewDialogAdapter=new SpareReqViewDialogAdapter(activity,spareRequestsRecordModels.get(position).getSpareReqViewModels());
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvView.setAdapter(spareReqViewDialogAdapter);
                rvView.setLayoutManager(linearLayoutManager);





                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }
        });



    }

    @Override
    public int getItemCount() {
        return spareRequestsRecordModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSeialNo,tvPurpose,tvDate;
        CardView cvView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSeialNo=itemView.findViewById(R.id.tvSeialNo);
            tvPurpose=itemView.findViewById(R.id.tvPurpose);
            tvDate=itemView.findViewById(R.id.tvDate);
            cvView=itemView.findViewById(R.id.cvView);

        }
    }
}
