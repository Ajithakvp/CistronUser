package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.CustomerPoResponseModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class CustomerPoAdapter extends RecyclerView.Adapter<CustomerPoAdapter.ViewHolder> {

    Activity activity;

    public ArrayList<CustomerPoResponseModel>customerPoResponseModels;

    public CustomerPoAdapter(Activity activity, ArrayList<CustomerPoResponseModel> customerPoResponseModels) {
        this.activity = activity;
        this.customerPoResponseModels = customerPoResponseModels;
    }

    @NonNull
    @Override
    public CustomerPoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_po_adapter, parent, false);
        return new CustomerPoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPoAdapter.ViewHolder holder, int position) {

        holder.tvPONo.setText(customerPoResponseModels.get(position).getPoNo());
        holder.tvPoDate.setText(customerPoResponseModels.get(position).getPoDt());
        holder.tvrbId.setText(customerPoResponseModels.get(position).getId());
        holder.tvPoRef.setText(customerPoResponseModels.get(position).getPoRef());

        String Pdf=customerPoResponseModels.get(position).getPoRefLink();

        holder.rlPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(Pdf);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                activity.startActivity(intent);
            }
        });

        if (customerPoResponseModels.get(position).getStatus().trim().equals("5")){
            holder.rlId.setVisibility(View.VISIBLE);
        }
        if (customerPoResponseModels.get(position).getStatus().trim().equals("7")){
            holder.rlId.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return customerPoResponseModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlId,rlPdf;
        RadioButton rbId;
        TextView tvrbId,tvPONo,tvPoDate,tvPoRef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            rlId=itemView.findViewById(R.id.rlId);
            rbId=itemView.findViewById(R.id.rbId);
            tvPoRef=itemView.findViewById(R.id.tvPoRef);
            tvrbId=itemView.findViewById(R.id.tvrbId);
            tvPONo=itemView.findViewById(R.id.tvPONo);
            tvPoDate=itemView.findViewById(R.id.tvPoDate);
            rlPdf=itemView.findViewById(R.id.rlPdf);

        }
    }
}
