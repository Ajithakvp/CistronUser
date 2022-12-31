package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SalesQuoteReportInterface;
import com.example.cistronuser.API.Interface.VisitEntryReportUserIListnterface;
import com.example.cistronuser.API.Model.SalesQuoteReportModel;
import com.example.cistronuser.API.Model.VisitEntryReportUserIListModel;
import com.example.cistronuser.API.Response.SalesQuoteReportResponse;
import com.example.cistronuser.API.Response.VisitEntryReportUserIListResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.SalesQuoteReportAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesQuoteReport extends AppCompatActivity {

    TextView tvStartDate, tvEndDate, tvSubmit, tvNodata;
    Spinner spUser;
    ImageView ivBack,ivfilter;
    RecyclerView rvSalesQuoteReport;
    RelativeLayout rlData,rlfilter;
    LinearLayout llUser;


    //UserList
    ArrayList<VisitEntryReportUserIListModel> visitEntryReportUserIListModels = new ArrayList<>();
    ArrayList<String> strUserList = new ArrayList<>();
    ArrayAdapter UserAdapter;
    String UserID;
    String userid;

    //RecycleViewAdapter
    SalesQuoteReportAdapter salesQuoteReportAdapter;
    ArrayList<SalesQuoteReportModel> salesQuoteReportModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_quote_report);

        ivBack = findViewById(R.id.ivBack);
        spUser = findViewById(R.id.spUser);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        tvSubmit = findViewById(R.id.tvSubmit);
        tvNodata = findViewById(R.id.tvNodata);
        rlData = findViewById(R.id.rlData);
        rvSalesQuoteReport = findViewById(R.id.rvSalesQuoteReport);
        llUser = findViewById(R.id.llUser);
        rlfilter=findViewById(R.id.rlfilter);
        ivfilter=findViewById(R.id.ivfilter);


        Date Start = new Date();
        CharSequence SDate = DateFormat.format("yyyy-MM-dd ", Start.getTime());
        tvStartDate.setText(SDate);

        Date End = new Date();
        CharSequence EDate = DateFormat.format("yyyy-MM-dd ", End.getTime());
        tvEndDate.setText(EDate);


        //Recycleview
        salesQuoteReportAdapter = new SalesQuoteReportAdapter(this, salesQuoteReportModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSalesQuoteReport.setLayoutManager(linearLayoutManager);
        rvSalesQuoteReport.setAdapter(salesQuoteReportAdapter);


        //UserCheck

        String user = PreferenceManager.getEmpuser(SalesQuoteReport.this);
        if (user.equals("admin")) {
            userid = UserID;
            llUser.setVisibility(View.VISIBLE);
        } else {
            userid = PreferenceManager.getEmpID(SalesQuoteReport.this);
            llUser.setVisibility(View.GONE);
        }
        //User Check End


        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesQuoteReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvStartDate.setText(strDate);
                        CallUserList();


                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


                datePickerDialog.show();
            }

        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesQuoteReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvEndDate.setText(strDate);

                        CallUserList();


                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


                datePickerDialog.show();
            }


        });


        CallUserList();
        UserAdapter = new ArrayAdapter(this, R.layout.spinner_item, strUserList);
        UserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUser.setAdapter(UserAdapter);
        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);
                UserID = visitEntryReportUserIListModels.get(position).getEmpid();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlfilter.setVisibility(View.VISIBLE);
            }
        });


        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = PreferenceManager.getEmpuser(SalesQuoteReport.this);
                if (user.equals("admin")) {
                    userid = UserID;
                } else {
                    userid = PreferenceManager.getEmpID(SalesQuoteReport.this);
                }
                CallSalesQuoteReport(userid);
            }
        });

    }

    private void CallSalesQuoteReport(String userid) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Quote Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteReportInterface salesQuoteReportInterface = APIClient.getClient().create(SalesQuoteReportInterface.class);
        salesQuoteReportInterface.CallQuoteReport("getQuoteReport", tvStartDate.getText().toString(), tvEndDate.getText().toString(), userid, PreferenceManager.getEmpuser(this)).enqueue(new Callback<SalesQuoteReportResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteReportResponse> call, Response<SalesQuoteReportResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        rlfilter.setVisibility(View.GONE);
                        if (response.body().getSalesQuoteReportModels().size() > 0) {
                            tvNodata.setVisibility(View.GONE);
                            rlData.setVisibility(View.VISIBLE);
                            salesQuoteReportAdapter.salesQuoteReportModels = response.body().getSalesQuoteReportModels();
                            salesQuoteReportAdapter.notifyDataSetChanged();

                        } else if (response.body().getSalesQuoteReportModels().equals(null)){
                            tvNodata.setVisibility(View.VISIBLE);
                            rlData.setVisibility(View.GONE);
                        }else  {

                            tvNodata.setVisibility(View.VISIBLE);
                            rlData.setVisibility(View.GONE);
                        }

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteReportResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }

    private void CallUserList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryReportUserIListnterface visitEntryReportUserIListnterface = APIClient.getClient().create(VisitEntryReportUserIListnterface.class);
        visitEntryReportUserIListnterface.CallUserList("getEmpWithVisitEntry", tvStartDate.getText().toString(), tvEndDate.getText().toString()).enqueue(new Callback<VisitEntryReportUserIListResponse>() {
            @Override
            public void onResponse(Call<VisitEntryReportUserIListResponse> call, Response<VisitEntryReportUserIListResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getVisitEntryReportUserIListModels().size() > 0) {
                            visitEntryReportUserIListModels = response.body().getVisitEntryReportUserIListModels();
                            strUserList.clear();
                            for (int i = 0; i < visitEntryReportUserIListModels.size(); i++) {
                                strUserList.add(visitEntryReportUserIListModels.get(i).getName());
                            }
                            UserAdapter.notifyDataSetChanged();


                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<VisitEntryReportUserIListResponse> call, Throwable t) {

            }
        });
    }
}