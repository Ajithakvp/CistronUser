package com.example.cistronuser.Report.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReportExpensesViewInterface;
import com.example.cistronuser.API.Model.ReportExpensesModel;
import com.example.cistronuser.API.Model.ReportExpensesViewModel;
import com.example.cistronuser.API.Response.ReportExpensesViewResponses;
import com.example.cistronuser.Adapter.ApprovedAdapter;
import com.example.cistronuser.Adapter.ExpensesViewWeeklyAdapter;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportExpenseAdapter extends RecyclerView.Adapter<ReportExpenseAdapter.ViewHolder> {

    public ArrayList<ReportExpensesModel> reportExpensesModels;
    Activity activity;
    ReportViewWeeklyAdapter reportViewWeeklyAdapter;
    ArrayList<ReportExpensesViewModel> reportExpensesViewModels = new ArrayList<>();

    //BottomView
    RecyclerView rvExpenseweeklyView;
    TextView tvGrandsumDoc;
    String  weekpreview;
    String BaseUrl;

    ImageView ivBack, ivWeekPreview;
    RelativeLayout rlAdjustmentLayout;
    TextView tvOperterTag,tvAmtTag,tvReasoTag,tvUpdate;
    ImageView ivDown,ivUp;
    Spinner spOperator;
    EditText edAmt,edReason;


    //Reset
    TextView tvResetTag;
    SwitchCompat stReset;

    //Hardcopy
    SwitchCompat sthardcopy;
    TextView tvDateTag,tvDate,tvRpTag,tvRp,tvhcSubmit;

    //Payment
    SwitchCompat stPay;
    TextView tvPayDateTag,tvPayDate,tvPaySubmit;

    //Paid

    SwitchCompat stPaid;
    TextView tvPaidDateTag,tvPaidDate,tvPaidSubmit;



    public ReportExpenseAdapter(ArrayList<ReportExpensesModel> reportExpensesModels, Activity activity) {
        this.reportExpensesModels = reportExpensesModels;
        this.activity = activity;
      
    }

    @NonNull
    @Override
    public ReportExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_submitted_report, parent, false);
        return new ReportExpenseAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportExpenseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvEmpId.setText(reportExpensesModels.get(position).getEmpid());
        holder.tvName.setText(reportExpensesModels.get(position).getName());
        holder.tvStartDate.setText(reportExpensesModels.get(position).getStartdate());
        holder.tvEndDate.setText(reportExpensesModels.get(position).getEnddate());
        holder.tvSubmitDate.setText(reportExpensesModels.get(position).getTimestamp());

        holder.tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
                bottomSheetDialog.setContentView(R.layout.expenses_viewweekly_report);
                bottomSheetDialog.show();
                bottomSheetDialog.setCancelable(false);
                rvExpenseweeklyView = bottomSheetDialog.findViewById(R.id.rvExpenseweeklyView);
                tvGrandsumDoc = bottomSheetDialog.findViewById(R.id.tvGrandsumDoc);
                ivBack = bottomSheetDialog.findViewById(R.id.ivBack);
                ivWeekPreview = bottomSheetDialog.findViewById(R.id.ivWeekPreview);


                //Adjustment
                rlAdjustmentLayout=bottomSheetDialog.findViewById(R.id.rlAdjustmentLayout);
                ivDown=bottomSheetDialog.findViewById(R.id.ivDown);
                ivUp=bottomSheetDialog.findViewById(R.id.ivUp);
                tvOperterTag=bottomSheetDialog.findViewById(R.id.tvOperterTag);
                tvAmtTag=bottomSheetDialog.findViewById(R.id.tvAmtTag);
                tvReasoTag=bottomSheetDialog.findViewById(R.id.tvReasoTag);
                tvUpdate=bottomSheetDialog.findViewById(R.id.tvUpdate);
                spOperator=bottomSheetDialog.findViewById(R.id.spOperator);
                edAmt=bottomSheetDialog.findViewById(R.id.edAmt);
                edReason=bottomSheetDialog.findViewById(R.id.edReason);

                //Reset
                stReset=bottomSheetDialog.findViewById(R.id.stReset);

                //Hardcopy
                sthardcopy=bottomSheetDialog.findViewById(R.id.sthardcopy);
                tvDateTag=bottomSheetDialog.findViewById(R.id.tvDateTag);
                tvDate=bottomSheetDialog.findViewById(R.id.tvDate);
                tvRpTag=bottomSheetDialog.findViewById(R.id.tvRpTag);
                tvRp=bottomSheetDialog.findViewById(R.id.tvRp);
                tvhcSubmit=bottomSheetDialog.findViewById(R.id.tvhcSubmit);

                //Payment
                stPay=bottomSheetDialog.findViewById(R.id.stPay);
                tvPayDateTag=bottomSheetDialog.findViewById(R.id.tvPayDateTag);
                tvPayDate=bottomSheetDialog.findViewById(R.id.tvPayDate);
                tvPaySubmit=bottomSheetDialog.findViewById(R.id.tvPaySubmit);

                //Paid
                stPaid=bottomSheetDialog.findViewById(R.id.stPaid);
                tvPaidDateTag=bottomSheetDialog.findViewById(R.id.tvPaidDateTag);
                tvPaidDate=bottomSheetDialog.findViewById(R.id.tvPaidDate);
                tvPaidSubmit=bottomSheetDialog.findViewById(R.id.tvPaidSubmit);







                reportViewWeeklyAdapter = new ReportViewWeeklyAdapter(activity, BaseUrl,reportExpensesViewModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvExpenseweeklyView.setAdapter(reportViewWeeklyAdapter);
                rvExpenseweeklyView.setLayoutManager(linearLayoutManager);

               

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                ivWeekPreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        weekpreview(BaseUrl + weekpreview);

                    }
                });


                ivDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivUp.setVisibility(View.VISIBLE);
                        tvOperterTag.setVisibility(View.VISIBLE);
                        tvAmtTag.setVisibility(View.VISIBLE);
                        tvReasoTag.setVisibility(View.VISIBLE);
                        tvUpdate.setVisibility(View.VISIBLE);
                        spOperator.setVisibility(View.VISIBLE);
                        edAmt.setVisibility(View.VISIBLE);
                        edReason.setVisibility(View.VISIBLE);
                        ivDown.setVisibility(View.GONE);


                    }
                });
                ivUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivUp.setVisibility(View.GONE);
                        tvOperterTag.setVisibility(View.GONE);
                        tvAmtTag.setVisibility(View.GONE);
                        tvReasoTag.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.GONE);
                        spOperator.setVisibility(View.GONE);
                        edAmt.setVisibility(View.GONE);
                        edReason.setVisibility(View.GONE);
                        ivDown.setVisibility(View.VISIBLE);
                    }
                });
                stReset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                        Toast.makeText(activity, "Reset "+isChecked, Toast.LENGTH_SHORT).show();

                    }
                });

                stPaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            tvPaidDateTag.setVisibility(View.VISIBLE);
                            tvPaidDate.setVisibility(View.VISIBLE);
                            tvPaidSubmit.setVisibility(View.VISIBLE);
                        }else {

                            tvPaidDateTag.setVisibility(View.GONE);
                            tvPaidDate.setVisibility(View.GONE);
                            tvPaidSubmit.setVisibility(View.GONE);

                        }



                        Toast.makeText(activity, "Reset "+isChecked, Toast.LENGTH_SHORT).show();

                    }
                });

                stPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            tvPayDateTag.setVisibility(View.VISIBLE);
                            tvPayDate.setVisibility(View.VISIBLE);
                            tvPaySubmit.setVisibility(View.VISIBLE);
                        }else {

                            tvPayDateTag.setVisibility(View.GONE);
                            tvPayDate.setVisibility(View.GONE);
                            tvPaySubmit.setVisibility(View.GONE);

                        }

                        Toast.makeText(activity, "Reset "+isChecked, Toast.LENGTH_SHORT).show();

                    }
                });

                sthardcopy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked){
                            tvhcSubmit.setVisibility(View.VISIBLE);
                            tvDateTag.setVisibility(View.VISIBLE);
                            tvDate.setVisibility(View.VISIBLE);
                            tvRpTag.setVisibility(View.VISIBLE);
                            tvRp.setVisibility(View.VISIBLE);
                        }else {

                            tvhcSubmit.setVisibility(View.GONE);
                            tvDateTag.setVisibility(View.GONE);
                            tvDate.setVisibility(View.GONE);
                            tvRpTag.setVisibility(View.GONE);
                            tvRp.setVisibility(View.GONE);

                        }

                        Toast.makeText(activity, "Reset "+isChecked, Toast.LENGTH_SHORT).show();

                    }
                });


                ReportExpensesViewInterface reportExpensesViewInterface = APIClient.getClient().create(ReportExpensesViewInterface.class);
                reportExpensesViewInterface.callreportviewexpenses("viewsubmittedExpenses", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate()).enqueue(new Callback<ReportExpensesViewResponses>() {
                    @Override
                    public void onResponse(Call<ReportExpensesViewResponses> call, Response<ReportExpensesViewResponses> response) {

                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                tvGrandsumDoc.setText(response.body().getGrandSum());
                                BaseUrl = response.body().getAttachBaseUrl();
                                weekpreview = response.body().getFilename_r();
                                reportViewWeeklyAdapter.weeklyExpensesModels = response.body().getReportExpensesViewModels();
                                reportViewWeeklyAdapter.notifyDataSetChanged();
                                Log.e(TAG, "onResponse: 5" + response.body().getFilename_r());
                                Log.e(TAG, "onResponse: 5" + response.body().getAttachBaseUrl());
                            }

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<ReportExpensesViewResponses> call, Throwable t) {
                        progressDialog.dismiss();


                    }
                });


            }
        });

    }

    void weekpreview(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return reportExpensesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvName, tvEmpId, tvStartDate, tvEndDate, tvSubmitDate, tvView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvView = itemView.findViewById(R.id.tvView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);
            tvSubmitDate = itemView.findViewById(R.id.tvSubmitDate);


        }
    }
}
