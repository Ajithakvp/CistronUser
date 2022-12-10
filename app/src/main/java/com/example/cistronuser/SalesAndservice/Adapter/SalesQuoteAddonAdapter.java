package com.example.cistronuser.SalesAndservice.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.SalesQuoteProductsAddonModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class SalesQuoteAddonAdapter extends RecyclerView.Adapter<SalesQuoteAddonAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SalesQuoteProductsAddonModel>salesQuoteProductsAddonModels;

    public SalesQuoteAddonAdapter(Activity activity, ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels) {
        this.activity = activity;
        this.salesQuoteProductsAddonModels = salesQuoteProductsAddonModels;
    }

    @NonNull
    @Override
    public SalesQuoteAddonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_product_list, parent, false);
        return new SalesQuoteAddonAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteAddonAdapter.ViewHolder holder, int position) {

        holder.tvAddon.setText(salesQuoteProductsAddonModels.get(position).getAddonName());

        String Postion=salesQuoteProductsAddonModels.get(position).getAddonId();




        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {

                   // Log.e(TAG, "onClick: "+salesQuoteProductsAddonModels.get(position).getAddonId() );

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return salesQuoteProductsAddonModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CheckBox checkbox;
        TextView tvAddon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox=itemView.findViewById(R.id.checkbox);
            tvAddon=itemView.findViewById(R.id.tvAddon);


        }
    }
}
