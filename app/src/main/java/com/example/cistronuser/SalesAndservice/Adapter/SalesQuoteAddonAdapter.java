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
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Activity.SalesQuote;

import java.util.ArrayList;

public class SalesQuoteAddonAdapter extends RecyclerView.Adapter<SalesQuoteAddonAdapter.ViewHolder> {

    public  ArrayList<String>strCheckedproduct=new ArrayList<>();

    public ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels;
    Activity activity;

    @NonNull
    private OnItemCheckListener onItemClick;

    public SalesQuoteAddonAdapter(@NonNull OnItemCheckListener onItemClick, Activity activity, ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels) {
        this.onItemClick = onItemClick;
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
    public void onBindViewHolder(@NonNull SalesQuoteAddonAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvAddon.setText(salesQuoteProductsAddonModels.get(position).getAddonName());
        SalesQuoteProductsAddonModel id = salesQuoteProductsAddonModels.get(position);
        holder.checkbox.setChecked(salesQuoteProductsAddonModels.get(position).isSelected());
        holder.checkbox.setTag(salesQuoteProductsAddonModels.get(position));


        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {

                    onItemClick.onItemCheck(id);

                   strCheckedproduct.add(id.getAddonId());

                    PreferenceManager.setAddOn(activity,strCheckedproduct.toString().replace("[","").replace("]",""));




                } else {

                    onItemClick.onItemUncheck(id);
                    strCheckedproduct.remove(id.getAddonId());

                }


            }
        });


    }

    @Override
    public int getItemCount() {

        return (salesQuoteProductsAddonModels == null) ? 0 : salesQuoteProductsAddonModels.size();
    }

    public interface OnItemCheckListener {
        void onItemCheck(SalesQuoteProductsAddonModel item);

        void onItemUncheck(SalesQuoteProductsAddonModel item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        CheckBox checkbox;
        TextView tvAddon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            tvAddon = itemView.findViewById(R.id.tvAddon);


        }


    }
}
