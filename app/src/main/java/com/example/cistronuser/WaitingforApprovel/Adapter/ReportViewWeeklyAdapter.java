package com.example.cistronuser.WaitingforApprovel.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReportExpensesViewInterface;
import com.example.cistronuser.API.Model.ReportExpensesViewModel;
import com.example.cistronuser.API.Response.ReportExpensesViewResponses;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportViewWeeklyAdapter extends RecyclerView.Adapter<ReportViewWeeklyAdapter.ViewHolder>{

    Activity activity;
    String baseurl;
    public ArrayList<ReportExpensesViewModel>weeklyExpensesModels;


    public ReportViewWeeklyAdapter(Activity activity, String baseurl, ArrayList<ReportExpensesViewModel> weeklyExpensesModels) {
        this.activity = activity;
        this.baseurl = baseurl;
        this.weeklyExpensesModels = weeklyExpensesModels;
    }

    @NonNull
    @Override
    public ReportViewWeeklyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklyexpensereport, parent, false);
        return new ReportViewWeeklyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewWeeklyAdapter.ViewHolder holder, int position) {

        holder.tvDate.setText(weeklyExpensesModels.get(position).getDate());
        holder.tvWorkreport.setText(weeklyExpensesModels.get(position).getWorkreport());
        holder.tvConvencyamt.setText(weeklyExpensesModels.get(position).getC_amo());
        holder.tvTicketamt.setText(weeklyExpensesModels.get(position).getT_amo());
        holder.tvOtheramt.setText(weeklyExpensesModels.get(position).getO_amo());
        holder.tvLodgingamt.setText(weeklyExpensesModels.get(position).getL_amo());
        holder.tvTotalamt.setText(weeklyExpensesModels.get(position).getSum());



        if (weeklyExpensesModels.get(position).getC_amo().trim().equals("0")){
            weeklyExpensesModels.get(position).setC_amo("");
            holder.tvConvencyamt.setVisibility(View.GONE);
            holder.tvConvencyamtTag.setVisibility(View.GONE);
            holder.ivConvencyView.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_all().trim().equals("")){
            holder.ivConvencyView.setVisibility(View.VISIBLE);
        }




        if (weeklyExpensesModels.get(position).getT_amo().trim().equals("0")){
            weeklyExpensesModels.get(position).setT_amo("");
            holder.tvTicketamt.setVisibility(View.GONE);
            holder.ivTicketView.setVisibility(View.GONE);
            holder.tvTicketamtTag.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_t().trim().equals("")){
            holder.ivTicketView.setVisibility(View.VISIBLE);
        }


        if (weeklyExpensesModels.get(position).getO_amo().trim().equals("0")){
            weeklyExpensesModels.get(position).setO_amo("");
            holder.tvOtheramt.setVisibility(View.GONE);
            holder.ivOtherView.setVisibility(View.GONE);
            holder.tvOtheramtTag.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_o().trim().equals("")){
            holder.ivOtherView.setVisibility(View.VISIBLE);
        }

        if (weeklyExpensesModels.get(position).getL_amo().trim().equals("0")){
            weeklyExpensesModels.get(position).setL_amo("");
            holder.tvLodgingamt.setVisibility(View.GONE);
            holder.tvLodgingamtTag.setVisibility(View.GONE);
            holder.ivLodgingView.setVisibility(View.GONE);
        }else  if (!weeklyExpensesModels.get(position).getFilename_l().trim().equals("")){
            holder.ivLodgingView.setVisibility(View.VISIBLE);
        }




        String fileConvency=weeklyExpensesModels.get(position).getFilename_all();
        String Ticket=weeklyExpensesModels.get(position).getFilename_t();
        String Lodging=weeklyExpensesModels.get(position).getFilename_l();
        String Others=weeklyExpensesModels.get(position).getFilename_o();



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




        holder.ivConvencyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callAttach(baseurl+fileConvency);
                Log.e(TAG, "onClick: 1"+baseurl );


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

        Callbaseurl();



    }

    private void Callbaseurl() {
        ReportExpensesViewInterface reportExpensesViewInterface = APIClient.getClient().create(ReportExpensesViewInterface.class);
        reportExpensesViewInterface.callreportviewexpenses("viewsubmittedExpenses", PreferenceManager.getEmpID(activity),"2022-10-23", "2022-10-29").enqueue(new Callback<ReportExpensesViewResponses>() {
            @Override
            public void onResponse(Call<ReportExpensesViewResponses> call, Response<ReportExpensesViewResponses> response) {

                try {
                    if (response.isSuccessful()) {

                        baseurl=response.body().getAttachBaseUrl();


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<ReportExpensesViewResponses> call, Throwable t) {



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
