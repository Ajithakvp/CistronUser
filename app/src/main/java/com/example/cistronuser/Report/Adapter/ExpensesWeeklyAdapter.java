package com.example.cistronuser.Report.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.UserDailyExpensesWMModel;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.ReportExpenseAdapter;

import java.util.ArrayList;

public class ExpensesWeeklyAdapter extends RecyclerView.Adapter<ExpensesWeeklyAdapter.ViewHolder> {


    //Monthly

    Activity activity;
    public ArrayList<UserDailyExpensesWMModel>userDailyExpensesWMModels;

    public ExpensesWeeklyAdapter(Activity activity, ArrayList<UserDailyExpensesWMModel> userDailyExpensesWMModels) {
        this.activity = activity;
        this.userDailyExpensesWMModels = userDailyExpensesWMModels;
    }

    @NonNull
    @Override
    public ExpensesWeeklyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklyreportview_mw, parent, false);
        return new ExpensesWeeklyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesWeeklyAdapter.ViewHolder holder, int position) {

        holder.tvEmpIDTag.setText("EmpID -"+userDailyExpensesWMModels.get(position).getEmpid());
        holder.tvNameTag.setText(userDailyExpensesWMModels.get(position).getName());
        holder.tvDateTag.setText(userDailyExpensesWMModels.get(position).getDate());
        holder.tvReportTag.setText(userDailyExpensesWMModels.get(position).getWorkreport());




        holder.tvTotalTag.setText(userDailyExpensesWMModels.get(position).getExp_sum());

        if (userDailyExpensesWMModels.get(position).getC_amo().trim().equals("0")){

            holder.tvc_amountag.setVisibility(View.GONE);
        }else {
            holder.tvc_amountag.setVisibility(View.VISIBLE);
            holder.tvc_amountag.setText("Conveyance -"+userDailyExpensesWMModels.get(position).getC_amo());

        }


        if (userDailyExpensesWMModels.get(position).getT_amo().trim().equals("0")){

            holder.tvt_amountag.setVisibility(View.GONE);
        }else {
            holder.tvt_amountag.setVisibility(View.VISIBLE);
            holder.tvt_amountag.setText("Travel -"+userDailyExpensesWMModels.get(position).getT_amo());

        }


        if (userDailyExpensesWMModels.get(position).getO_amo().trim().equals("0")){

            holder.tvo_amountag.setVisibility(View.GONE);
        }else {
            holder.tvo_amountag.setVisibility(View.VISIBLE);
            holder.tvo_amountag.setText("Others -"+userDailyExpensesWMModels.get(position).getO_amo());

        }

        if (userDailyExpensesWMModels.get(position).getL_amo().trim().equals("0")){

            holder.tvl_amountag.setVisibility(View.GONE);
        }else {
            holder.tvl_amountag.setVisibility(View.VISIBLE);
            holder.tvl_amountag.setText("Lodging -"+userDailyExpensesWMModels.get(position).getL_amo());

        }



        if (userDailyExpensesWMModels.get(position).getAdj_amt().trim().equals("0")){

            holder.tva_amountag.setVisibility(View.GONE);
        }else {
            holder.tva_amountag.setVisibility(View.VISIBLE);
            holder.tva_amountag.setText("Adjustment -"+userDailyExpensesWMModels.get(position).getAdj_amt());

        }



    }

    @Override
    public int getItemCount() {
        return userDailyExpensesWMModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEmpIDTag,tvNameTag,tvDateTag,tvReportTag,tvc_amountag,tvt_amountag,tvl_amountag,tvo_amountag,tva_amountag,tvTotalTag;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTotalTag=itemView.findViewById(R.id.tvTotalTag);
            tvEmpIDTag=itemView.findViewById(R.id.tvEmpIDTag);
            tvNameTag=itemView.findViewById(R.id.tvNameTag);
            tvDateTag=itemView.findViewById(R.id.tvDateTag);
            tvReportTag=itemView.findViewById(R.id.tvReportTag);
            tvc_amountag=itemView.findViewById(R.id.tvc_amountag);
            tvt_amountag=itemView.findViewById(R.id.tvt_amountag);
            tvl_amountag=itemView.findViewById(R.id.tvl_amountag);
            tvo_amountag=itemView.findViewById(R.id.tvo_amountag);
            tva_amountag=itemView.findViewById(R.id.tva_amountag);

        }
    }
}
