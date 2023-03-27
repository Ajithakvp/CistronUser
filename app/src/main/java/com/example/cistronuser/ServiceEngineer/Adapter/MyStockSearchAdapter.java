package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.MyStockListSEModel;
import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class MyStockSearchAdapter extends RecyclerView.Adapter<MyStockSearchAdapter.ViewHolder> {

    public ArrayList<MyStockSESearchModel> myStockListSEModels;
    Activity activity;
    //Search
    ArrayList<MyStockSESearchModel> tempMystockList = new ArrayList<>();
    ArrayList<String> strMyStockList = new ArrayList<>();
    public MyStockSearchAdapter(ArrayList<MyStockSESearchModel> myStockListSEModels, Activity activity) {
        this.myStockListSEModels = myStockListSEModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyStockSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mystock_search_adapter, parent, false);
        return new MyStockSearchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStockSearchAdapter.ViewHolder holder, int position) {
        holder.tvpartName.setText(myStockListSEModels.get(position).getName());
        holder.tvMyQty.setText(myStockListSEModels.get(position).getQuantity());
        holder.tvPartId.setText(myStockListSEModels.get(position).getId());
        holder.tvPartNo.setText(myStockListSEModels.get(position).getPart_no());
        holder.tvUnitPrice.setText(myStockListSEModels.get(position).getPrice());

        if (myStockListSEModels.get(position).getLabel().trim().equals("cis")) {
            holder.tvPartcisIdName.setVisibility(View.VISIBLE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);

        } else if (myStockListSEModels.get(position).getLabel().trim().equals("cspl")) {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.VISIBLE);
        } else {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.VISIBLE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setText(myStockListSEModels.get(position).getLabel());
        }
    }

    @Override
    public int getItemCount() {
        return myStockListSEModels.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        myStockListSEModels.clear();
        if (charText.length() == 0) {
            myStockListSEModels.addAll(tempMystockList);
        } else {
            for (String searchedValue : strMyStockList) {

                if (searchedValue.toLowerCase().contains(charText)) {
                    for (int i = 0; i < tempMystockList.size(); i++) {

                        if (tempMystockList.get(i).getName().equalsIgnoreCase(searchedValue)) {
                            myStockListSEModels.add(tempMystockList.get(i));
                            break;
                        }
                    }

                }
            }


        }
        notifyDataSetChanged();
    }

    public void searchAdapter(ArrayList<MyStockSESearchModel> data) {
        tempMystockList.addAll(data);
        strMyStockList.clear();
        for (int i = 0; i < tempMystockList.size(); i++) {
            strMyStockList.add(tempMystockList.get(i).getName());
        }
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartId, tvPartcisIdName, tvPartotherIdName, tvPartcsplIdName, tvPartNo, tvUnitPrice, tvMyQty, tvpartName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartId = itemView.findViewById(R.id.tvPartId);
            tvPartcisIdName = itemView.findViewById(R.id.tvPartcisIdName);
            tvPartcsplIdName = itemView.findViewById(R.id.tvPartcsplIdName);
            tvPartotherIdName = itemView.findViewById(R.id.tvPartotherIdName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
            tvMyQty = itemView.findViewById(R.id.tvMyQty);
            tvpartName = itemView.findViewById(R.id.tvpartName);
        }
    }
}
