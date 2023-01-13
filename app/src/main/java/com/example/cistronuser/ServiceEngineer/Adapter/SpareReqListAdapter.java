package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SpareSendReqListModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SpareReqListAdapter extends RecyclerView.Adapter<SpareReqListAdapter.ViewHolder> {
    
    Activity activity;
    
    public ArrayList<SpareSendReqListModel>spareSendReqListModels;

    public SpareReqListAdapter(Activity activity, ArrayList<SpareSendReqListModel> spareSendReqListModels) {
        this.activity = activity;
        this.spareSendReqListModels = spareSendReqListModels;
    }

    @NonNull
    @Override
    public SpareReqListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_send_request_list_dialog, parent, false);
        return new SpareReqListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareReqListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        
        holder.tvPartName.setText(spareSendReqListModels.get(position).getPartName());
        holder.tvCoQty.setText(spareSendReqListModels.get(position).getCoordQty());
        holder.tvPartNo.setText(spareSendReqListModels.get(position).getPartNo());
        holder.tvStoreQty.setText(spareSendReqListModels.get(position).getStoreQty());
        holder.tvUnitPrice.setText(spareSendReqListModels.get(position).getUnitPrice());
        
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSpareDelete(spareSendReqListModels.get(position).getId());
            }
        });
       


    }

    private void CallSpareDelete(String id) {

    }

    @Override
    public int getItemCount() {
        return spareSendReqListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartName,tvPartNo,tvUnitPrice,tvStoreQty,tvCoQty;
        EditText edMyQty;
        ImageView ivDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice=itemView.findViewById(R.id.tvUnitPrice);
            tvStoreQty=itemView.findViewById(R.id.tvStoreQty);
            tvCoQty=itemView.findViewById(R.id.tvCoQty);
            edMyQty=itemView.findViewById(R.id.edMyQty);
            ivDelete=itemView.findViewById(R.id.ivDelete);
        }
    }
}
