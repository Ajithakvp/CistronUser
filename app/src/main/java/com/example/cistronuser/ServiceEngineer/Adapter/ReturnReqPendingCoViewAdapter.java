package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.ReturnRequestViewCoModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ReturnReqPendingCoViewAdapter extends RecyclerView.Adapter<ReturnReqPendingCoViewAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<ReturnRequestViewCoModel>returnRequestViewCoModels;

    public ReturnReqPendingCoViewAdapter(Activity activity, ArrayList<ReturnRequestViewCoModel> returnRequestViewCoModels) {
        this.activity = activity;
        this.returnRequestViewCoModels = returnRequestViewCoModels;
    }

    @NonNull
    @Override
    public ReturnReqPendingCoViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.return_req_co_view_dialolg, parent, false);
        return new ReturnReqPendingCoViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnReqPendingCoViewAdapter.ViewHolder holder, int position) {
        holder.tvPartName.setText(returnRequestViewCoModels.get(position).getPartName());
        holder.tvPartNo.setText(returnRequestViewCoModels.get(position).getPartNo());
        holder.tvReqQty.setText(returnRequestViewCoModels.get(position).getRetQty());
    }

    @Override
    public int getItemCount() {
        return returnRequestViewCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartName,tvPartNo,tvReqQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvReqQty=itemView.findViewById(R.id.tvReqQty);
        }
    }
}
