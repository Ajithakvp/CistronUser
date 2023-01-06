package com.example.cistronuser.ServiceEngineer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.R;

public class PendingCallAdapter extends RecyclerView.Adapter<PendingCallAdapter.ViewHolder> {
    @NonNull
    @Override
    public PendingCallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_call_list, parent, false);
        return new PendingCallAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingCallAdapter.ViewHolder holder, int position) {

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
