package com.example.cistronuser.ServiceEngineer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.R;

public class CreateSpareReqAdapter extends RecyclerView.Adapter<CreateSpareReqAdapter.ViewHolder> {
    @NonNull
    @Override
    public CreateSpareReqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_spare_req_adapter, parent, false);
        return new CreateSpareReqAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CreateSpareReqAdapter.ViewHolder holder, int position) {

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
