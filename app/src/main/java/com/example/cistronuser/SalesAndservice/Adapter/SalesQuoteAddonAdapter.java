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

    public ArrayList<String> strCheckedproduct = new ArrayList<>();

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


        holder.checkbox.setTag(salesQuoteProductsAddonModels.get(position));
        holder.checkbox.setTag(position);

        strCheckedproduct.clear();


        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (holder.checkbox.isChecked()) {


                    try {
                        if (id.getAddonId().trim().equals("565")) {
                            salesQuoteProductsAddonModels.get(2).setSelected(isChecked);
                            if (salesQuoteProductsAddonModels.get(3).getAddonId().trim().equals("564")) {
                                if (salesQuoteProductsAddonModels.get(3).isSelected() == false) {
                                    salesQuoteProductsAddonModels.get(3).setSelected(isChecked);
                                    notifyDataSetChanged();
                                }
                            }

                        }
                        if (id.getAddonId().trim().equals("645")) {
                            salesQuoteProductsAddonModels.get(1).setSelected(isChecked);
                            if (salesQuoteProductsAddonModels.get(3).getAddonId().trim().equals("564")) {
                                if (salesQuoteProductsAddonModels.get(3).isSelected() == false) {
                                    salesQuoteProductsAddonModels.get(3).setSelected(isChecked);
                                    notifyDataSetChanged();
                                }
                            }

                        }


                    } catch (Exception e) {

                    }
                    onItemClick.onItemCheck(id);

                    strCheckedproduct.add(id.getAddonId());

//                    Toast.makeText(activity, " Test0- " + salesQuoteProductsAddonModels.get(0).isSelected() + " Test1- " + salesQuoteProductsAddonModels.get(1).isSelected() + " Test2- " + salesQuoteProductsAddonModels.get(2).isSelected() + " Test3- " + salesQuoteProductsAddonModels.get(3).isSelected(), Toast.LENGTH_LONG).show();


                } else {

                    try {
                        if (id.getAddonId().trim().equals("565")) {
                            salesQuoteProductsAddonModels.get(2).setSelected(isChecked);
                            notifyDataSetChanged();

                        }
                        if (id.getAddonId().trim().equals("645")) {
                            salesQuoteProductsAddonModels.get(1).setSelected(isChecked);
                            notifyDataSetChanged();


                        }


                        if (id.getAddonId().trim().equals("564")) {

                            if (salesQuoteProductsAddonModels.get(2).isSelected() == true || salesQuoteProductsAddonModels.get(1).isSelected() == true) {
                                salesQuoteProductsAddonModels.get(3).setSelected(true);
                                notifyDataSetChanged();
                            } else {
                                salesQuoteProductsAddonModels.get(3).setSelected(false);
                                notifyDataSetChanged();
                            }


                        }


                        if (id.getAddonId().trim().equals("565")) {
                            salesQuoteProductsAddonModels.get(2).setSelected(isChecked);
                            if (salesQuoteProductsAddonModels.get(1).isSelected() == false) {
                                if (salesQuoteProductsAddonModels.get(3).getAddonId().trim().equals("564")) {
                                    if (salesQuoteProductsAddonModels.get(3).isSelected() == true) {
                                        salesQuoteProductsAddonModels.get(3).setSelected(isChecked);
                                        notifyDataSetChanged();
                                    }

                                }
                            }


                        }
                        if (id.getAddonId().trim().equals("645")) {
                            salesQuoteProductsAddonModels.get(1).setSelected(isChecked);
                            if (salesQuoteProductsAddonModels.get(2).isSelected() == false) {
                                if (salesQuoteProductsAddonModels.get(3).getAddonId().trim().equals("564")) {
                                    if (salesQuoteProductsAddonModels.get(3).isSelected() == true) {
                                        salesQuoteProductsAddonModels.get(3).setSelected(isChecked);
                                        notifyDataSetChanged();
                                    }
                                }
                            }

                        }


                    } catch (Exception e) {

                    }


                    onItemClick.onItemUncheck(id);
                    strCheckedproduct.remove(id.getAddonId());

//                    Toast.makeText(activity, " Test0- " + salesQuoteProductsAddonModels.get(0).isSelected() + " Test1- " + salesQuoteProductsAddonModels.get(1).isSelected() + " Test2- " + salesQuoteProductsAddonModels.get(2).isSelected() + " Test3- " + salesQuoteProductsAddonModels.get(3).isSelected(), Toast.LENGTH_LONG).show();


                }


            }
        });

        holder.checkbox.setChecked(salesQuoteProductsAddonModels.get(position).isSelected());


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
