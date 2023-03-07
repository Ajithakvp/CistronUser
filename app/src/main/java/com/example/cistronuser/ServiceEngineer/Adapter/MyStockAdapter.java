package com.example.cistronuser.ServiceEngineer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.R;

public class MyStockAdapter extends RecyclerView.Adapter<MyStockAdapter.ViewHolder> {
    @NonNull
    @Override
    public MyStockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_stock_adapter, parent, false);
        return new MyStockAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStockAdapter.ViewHolder holder, int position) {

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
