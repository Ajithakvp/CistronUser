package com.example.cistronuser.ServiceEngineer.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ServiceDetailsInterFace;
import com.example.cistronuser.API.Model.ServiceDetailsInterModel;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.ServiceDetailsResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Activity.UpcomingCallReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingCallAdapter extends RecyclerView.Adapter<UpcomingCallAdapter.ViewHolder> {

    public ArrayList<UpcomingCallListModel> upcomingCallReportlistModels;
    Activity activity;
    RecyclerView rvServiceDetails;
    ImageView ivClose;
    ServiceDetailsAdapter serviceDetailsAdapter;
    ArrayList<ServiceDetailsInterModel> serviceDetailsInterModels = new ArrayList<>();

    public UpcomingCallAdapter(Activity activity, ArrayList<UpcomingCallListModel> upcomingCallReportlistModels) {
        this.activity = activity;
        this.upcomingCallReportlistModels = upcomingCallReportlistModels;
    }

    @NonNull
    @Override
    public UpcomingCallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_call_list, parent, false);
        return new UpcomingCallAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingCallAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvCR.setText(upcomingCallReportlistModels.get(position).getCrNo());
        holder.tvName.setText(upcomingCallReportlistModels.get(position).getHospital().replace("\\n", "\n"));
        holder.tvDate.setText(upcomingCallReportlistModels.get(position).getDate());
        holder.tvContactDetails.setText(upcomingCallReportlistModels.get(position).getContact().replace("\\n", "\n"));
        holder.tvLocation.setText(upcomingCallReportlistModels.get(position).getAddress().replace("\\n", "\n"));
        holder.tvProduct.setText(upcomingCallReportlistModels.get(position).getProduct());
        holder.tvIssueDetails.setText(upcomingCallReportlistModels.get(position).getIssue());
        holder.tvCallCreateBy.setText(upcomingCallReportlistModels.get(position).getCreatedBy());
        holder.tvMobileNo.setText(upcomingCallReportlistModels.get(position).getMobile());

        String MobileNo = upcomingCallReportlistModels.get(position).getMobile();

        String TimeCheck = upcomingCallReportlistModels.get(position).getDate();



        try{
            if(upcomingCallReportlistModels.get(position).getCheckApproval().trim().equals("0")){
                holder.tvCallReportButton.setVisibility(View.GONE);
                holder.tvCallReport.setVisibility(View.VISIBLE);
            }else{
                holder.tvCallReportButton.setVisibility(View.VISIBLE);
                holder.tvCallReport.setVisibility(View.GONE);

            }
        }catch (Exception e){

        }




        //E378 CallTimeCheck(TimeCheck,holder.tvCallReportButton,holder.tvIssue4hr);

        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + MobileNo));
                activity.startActivity(intent);
            }
        });

        holder.tvCallReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpcomingCallReport.class);
                intent.putExtra("id", upcomingCallReportlistModels.get(position).getButton());
                intent.putExtra("crid", upcomingCallReportlistModels.get(position).getCrId());
                intent.putExtra("PM", upcomingCallReportlistModels.get(position).getIssue());
                activity.startActivity(intent);
            }
        });

        holder.tvCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.serivice_details_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ivClose = dialog.findViewById(R.id.ivClose);
                rvServiceDetails = dialog.findViewById(R.id.rvServiceDetails);
                dialog.show();

                CallServiceDetails(upcomingCallReportlistModels.get(position).getCrId());
                serviceDetailsAdapter = new ServiceDetailsAdapter(activity, serviceDetailsInterModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvServiceDetails.setAdapter(serviceDetailsAdapter);
                rvServiceDetails.setLayoutManager(linearLayoutManager);

                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

    }

    private void CallTimeCheck(String timeCheck, TextView tvCallReportButton, TextView tvIssue4hr) {


        // TimeCheck = TimeCheck.substring(11); 28-02-2023 10:00:00
        // Log.e(TAG, "onResponse: " + TimeCheck);
        timeCheck = "04-03-2023 16:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        //Log.e(TAG, "onResponse: "+currentDateandTime );


        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

        Date TimeDate = null;
        Date CurrentTimeDate = null;
        try {
            TimeDate = format.parse(timeCheck);
            CurrentTimeDate = format.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long now = CurrentTimeDate.getTime();
        long then = TimeDate.getTime();

       // long diff = TimeDate.getTime() - CurrentTimeDate.getTime();

        long seconds = (now - then) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;


//        long diffSeconds = diff / 1000;
//        long diffMinutes = diff / (60 * 1000);
//        long diffHours = diff / (60 * 60 * 1000);
        Log.e(TAG, ": check " + hours + "---" + minutes);

        if (hours < 4) {
            Log.e(TAG, "TimeCheck: in " + hours);
            tvCallReportButton.setVisibility(View.VISIBLE);
            tvIssue4hr.setVisibility(View.GONE);

        } else {
            tvCallReportButton.setVisibility(View.GONE);
            tvIssue4hr.setVisibility(View.VISIBLE);
            Log.e(TAG, "TimeCheck: out " + hours);
        }
        // ************** Time Check End ************** //


    }

    private void CallServiceDetails(String crId) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Service Details...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceDetailsInterFace serviceDetailsInterFace = APIClient.getClient().create(ServiceDetailsInterFace.class);
        serviceDetailsInterFace.CallServiceDetails("getServiceDetail", crId).enqueue(new Callback<ServiceDetailsResponse>() {
            @Override
            public void onResponse(Call<ServiceDetailsResponse> call, Response<ServiceDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        serviceDetailsAdapter.serviceDetailsInterModels = response.body().getServiceDetailsInterModels();
                        ;
                        serviceDetailsAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ServiceDetailsResponse> call, Throwable t) {

            }
        });
    }



    @Override
    public int getItemCount() {
        return upcomingCallReportlistModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCallReport,tvIssue4hr, tvName, tvDate, tvContactDetails, tvLocation, tvProduct, tvCR, tvIssueDetails, tvCallCreateBy, tvMobileNo, tvCallReportButton;

        ImageView ivCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCallReport = itemView.findViewById(R.id.tvCallReport);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContactDetails = itemView.findViewById(R.id.tvContactDetails);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvCR = itemView.findViewById(R.id.tvCR);
            tvIssueDetails = itemView.findViewById(R.id.tvIssueDetails);
            tvCallCreateBy = itemView.findViewById(R.id.tvCallCreateBy);
            ivCall = itemView.findViewById(R.id.ivCall);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
            tvCallReportButton = itemView.findViewById(R.id.tvCallReportButton);
            tvIssue4hr = itemView.findViewById(R.id.tvIssue4hr);
        }
    }
}
