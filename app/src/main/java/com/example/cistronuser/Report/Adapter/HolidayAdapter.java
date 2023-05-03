package com.example.cistronuser.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.HolidaylistModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<HolidaylistModel>holidaylistModels;

    public HolidayAdapter(Activity activity, ArrayList<HolidaylistModel> holidaylistModels) {
        this.activity = activity;
        this.holidaylistModels = holidaylistModels;
    }

    @NonNull
    @Override
    public HolidayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_adapter, parent, false);
        return new HolidayAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.ViewHolder holder, int position) {

        holder.tvHoliday.setText(holidaylistModels.get(position).getText());
        holder.tvDate.setText(holidaylistModels.get(position).getDatein());
        String branch=holidaylistModels.get(position).getBranch();
        if (branch.trim().equals("1")){
            holder.tvAPTS.setVisibility(View.VISIBLE);
            holder.tvTn.setVisibility(View.GONE);
            holder.tvOthers.setVisibility(View.GONE);

        }else if (branch.trim().equals("2")){
            holder.tvAPTS.setVisibility(View.GONE);
            holder.tvTn.setVisibility(View.VISIBLE);
            holder.tvOthers.setVisibility(View.GONE);
        }else {
            holder.tvAPTS.setVisibility(View.GONE);
            holder.tvTn.setVisibility(View.GONE);
            holder.tvOthers.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return holidaylistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHoliday,tvDate,tvTn,tvAPTS,tvOthers;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoliday=itemView.findViewById(R.id.tvEmpName);
            tvDate=itemView.findViewById(R.id.tvEmpId);
            tvTn=itemView.findViewById(R.id.tvTn);
            tvAPTS=itemView.findViewById(R.id.tvAPTS);
            tvOthers=itemView.findViewById(R.id.tvOthers);

        }
    }
}
