package com.example.cistronuser.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ExpenseInterface;
import com.example.cistronuser.API.Interface.ViewExpenseListInterface;
import com.example.cistronuser.API.Model.WeeklyExpensesModel;
import com.example.cistronuser.API.Response.ViewExpenseResponse;
import com.example.cistronuser.API.Response.response;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesViewWeeklyAdapter extends RecyclerView.Adapter<ExpensesViewWeeklyAdapter.ViewHolder> {

    Activity activity;
    String baseurl;
    public ArrayList<WeeklyExpensesModel>weeklyExpensesModels;

    public ExpensesViewWeeklyAdapter(Activity activity, ArrayList<WeeklyExpensesModel> weeklyExpensesModels) {
        this.activity = activity;
        this.weeklyExpensesModels = weeklyExpensesModels;
    }

    @NonNull
    @Override
    public ExpensesViewWeeklyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklyexpensereport, parent, false);
        return new ExpensesViewWeeklyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewWeeklyAdapter.ViewHolder holder, int position) {
        holder.tvDate.setText(weeklyExpensesModels.get(position).getDate());
        holder.tvWorkreport.setText(weeklyExpensesModels.get(position).getWorkreport());

        if (weeklyExpensesModels.get(position).getC_amo().trim().equals("0")){
            holder.tvConvencyamtTag.setVisibility(View.GONE);
            holder.tvConvencyamt.setVisibility(View.GONE);
            holder.ivConvencyView.setVisibility(View.GONE);
        }else if (!weeklyExpensesModels.get(position).getFilename_all().trim().equals("")){

            holder.ivConvencyView.setVisibility(View.VISIBLE);
        }


        if (weeklyExpensesModels.get(position).getT_amo().trim().equals("0")){
            holder.ivTicketView.setVisibility(View.GONE);
            holder.tvTicketamtTag.setVisibility(View.GONE);
            holder.tvTicketamt.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_t().trim().equals("")) {
            holder.ivTicketView.setVisibility(View.VISIBLE);
        }

        if (weeklyExpensesModels.get(position).getL_amo().trim().equals("0")){
            holder.ivLodgingView.setVisibility(View.GONE);
            holder.tvLodgingamtTag.setVisibility(View.GONE);
            holder.tvLodgingamt.setVisibility(View.GONE);
        }else if (!weeklyExpensesModels.get(position).getFilename_l().trim().equals("")) {
            holder.ivLodgingView.setVisibility(View.VISIBLE);
        }


        if (weeklyExpensesModels.get(position).getO_amo().trim().equals("0")){

            holder.ivOtherView.setVisibility(View.GONE);
            holder.tvOtheramtTag.setVisibility(View.GONE);
            holder.tvOtheramt.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_o().trim().equals("")) {
            holder.ivOtherView.setVisibility(View.VISIBLE);
        }




        holder.tvConvencyamt.setText(weeklyExpensesModels.get(position).getC_amo());
        holder.tvTicketamt.setText(weeklyExpensesModels.get(position).getT_amo());
        holder.tvOtheramt.setText(weeklyExpensesModels.get(position).getO_amo());
        holder.tvLodgingamt.setText(weeklyExpensesModels.get(position).getL_amo());
        holder.tvTotalamt.setText(weeklyExpensesModels.get(position).getSum());

        String fileConvency=weeklyExpensesModels.get(position).getFilename_all();
        String Ticket=weeklyExpensesModels.get(position).getFilename_t();
        String Lodging=weeklyExpensesModels.get(position).getFilename_l();
        String Others=weeklyExpensesModels.get(position).getFilename_o();




//
//        if (weeklyExpensesModels.get(position).getFilename_all().trim().equals("")){
//            holder.ivConvencyView.setVisibility(View.GONE);
//
//        }else {
//            holder.ivConvencyView.setVisibility(View.VISIBLE);
//
//        }
//
//        if (weeklyExpensesModels.get(position).getFilename_l().trim().equals("")){
//            holder.ivLodgingView.setVisibility(View.GONE);
//
//        }else {
//            holder.ivLodgingView.setVisibility(View.VISIBLE);
//
//        }
//
//        if (weeklyExpensesModels.get(position).getFilename_o().trim().equals("")){
//            holder.ivOtherView.setVisibility(View.GONE);
//
//        }else {
//            holder.ivOtherView.setVisibility(View.VISIBLE);
//
//        }
//
//        if (weeklyExpensesModels.get(position).getFilename_t().trim().equals("")){
//            holder.ivTicketView.setVisibility(View.GONE);
//
//        }else {
//            holder.ivTicketView.setVisibility(View.VISIBLE);
//
//        }



        ViewExpenseListInterface viewExpenseListInterface = APIClient.getClient().create(ViewExpenseListInterface.class);
        viewExpenseListInterface.Callweek("viewWeeklyExpenses","", PreferenceManager.getEmpID(activity)).enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                baseurl=response.body().getAttachBaseUrl();
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

            }
        });




        holder.ivConvencyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callAttach(baseurl+fileConvency);
                Log.e(TAG, "onClick: 1"+baseurl+fileConvency );


            }
        });

        holder.ivTicketView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAttach(baseurl+Ticket);
                Log.e(TAG, "onClick: 2"+baseurl+Ticket );

            }
        });

        holder.ivLodgingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAttach(baseurl+Lodging);
                Log.e(TAG, "onClick: 3"+baseurl+Lodging );
            }
        });

        holder.ivOtherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAttach(baseurl+Others);
                Log.e(TAG, "onClick: 4"+baseurl+Others );
            }
        });














    }

    void callAttach(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return weeklyExpensesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate,tvWorkreport,tvConvencyamt,tvTicketamt,tvLodgingamt,tvOtheramt,tvTotalamt;
        TextView tvOtheramtTag,tvLodgingamtTag,tvTicketamtTag,tvConvencyamtTag;
        ImageView ivConvencyView,ivTicketView ,ivLodgingView,ivOtherView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate=itemView.findViewById(R.id.tvDate);
            tvWorkreport=itemView.findViewById(R.id.tvWorkreport);
            tvConvencyamt=itemView.findViewById(R.id.tvConvencyamt);
            tvTicketamt=itemView.findViewById(R.id.tvTicketamt);
            tvLodgingamt=itemView.findViewById(R.id.tvLodgingamt);
            tvOtheramt=itemView.findViewById(R.id.tvOtheramt);
            ivConvencyView=itemView.findViewById(R.id.ivConvencyView);
            ivTicketView=itemView.findViewById(R.id.ivTicketView);
            ivLodgingView=itemView.findViewById(R.id.ivLodgingView);
            ivOtherView=itemView.findViewById(R.id.ivOtherView);
            tvTotalamt=itemView.findViewById(R.id.tvTotalamt);

            tvConvencyamtTag=itemView.findViewById(R.id.tvConvencyamtTag);
            tvOtheramtTag=itemView.findViewById(R.id.tvOtheramtTag);
            tvLodgingamtTag=itemView.findViewById(R.id.tvLodgingamtTag);
            tvTicketamtTag=itemView.findViewById(R.id.tvTicketamtTag);

        }
    }
}
