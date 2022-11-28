package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DailyAttendanceUserInterface;
import com.example.cistronuser.API.Interface.DailyReportAttendanceInterFace;
import com.example.cistronuser.API.Interface.MonthlyReportAttendanceInterFace;
import com.example.cistronuser.API.Model.DailyReportAttendanceModel;
import com.example.cistronuser.API.Model.DailyReportUserAttendanceModel;
import com.example.cistronuser.API.Model.MonthlyReportAttendanceModel;
import com.example.cistronuser.API.Model.ReportTypeWMselectedModel;
import com.example.cistronuser.API.Response.DailyReportAttendanceResponse;
import com.example.cistronuser.API.Response.DailyReportUserAttendanceResponse;
import com.example.cistronuser.API.Response.MonthlyReportAttendanceResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.DailyReportAdapter;
import com.example.cistronuser.Report.Adapter.ExpensesWeeklyAdapter;
import com.example.cistronuser.Report.Adapter.MonthlyReportAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceReports extends AppCompatActivity {

    Spinner spUser, spreportType, spUserMonthy;
    TextView tvStartDateTag, tvStartDate, tvEndDateTag, tvEndDate, tvMonthyearTag, tvMonthyear, tvMonthlySubmit,tvWeellySubmit, tvMonthlyUserTag, tvUserTag;
    ImageView ivfilter,ivBack;
    RelativeLayout  rlfilter;

    //Dailyreport
    RelativeLayout rlMonthly;

    //ReportType
    ArrayList<String> strType = new ArrayList<>();
    ArrayList<ReportTypeWMselectedModel> reportTypeWMselectedModels = new ArrayList<>();
    ArrayAdapter typeAdapter;
    String ReportTyee;
    int mDay, mMonth, mYear;

    //DailyReport
    RecyclerView rvDailyattendanceReport,rvMonthlyattendanceReport;
    ArrayList<DailyReportAttendanceModel>dailyReportAttendanceModels=new ArrayList<>();
    DailyReportAdapter dailyReportAdapter;

    //DailyUser
    ArrayList<DailyReportUserAttendanceModel>dailyReportUserAttendanceModels=new ArrayList<>();
    ArrayAdapter DailyUserAdapter,monthlyuserAdapter;
    ArrayList<String >strDailyUser=new ArrayList<>();
    String Dailyuser;

    //Monthly
    TextView tvName,tvspace,tvEmpId;
    MonthlyReportAdapter monthlyReportAdapter;
    ArrayList<String >strmonthlyUser=new ArrayList<>();
    ArrayList<MonthlyReportAttendanceModel>monthlyReportAttendanceModels=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_reports);

        tvMonthlySubmit = findViewById(R.id.tvMonthlySubmit);
        spUser = findViewById(R.id.spUser);
        spreportType = findViewById(R.id.spreportType);
        tvStartDateTag = findViewById(R.id.tvStartDateTag);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDateTag = findViewById(R.id.tvEndDateTag);
        tvEndDate = findViewById(R.id.tvEndDate);
        tvMonthyearTag = findViewById(R.id.tvMonthyearTag);
        tvMonthyear = findViewById(R.id.tvMonthyear);
        spUserMonthy = findViewById(R.id.spUserMonthy);
        tvUserTag = findViewById(R.id.tvUserTag);
        tvMonthlyUserTag = findViewById(R.id.tvMonthlyUserTag);
        rlMonthly=findViewById(R.id.rlMonthly);
        rlfilter=findViewById(R.id.rlfilter);
        ivfilter=findViewById(R.id.ivfilter);
        ivBack=findViewById(R.id.ivBack);
        tvWeellySubmit=findViewById(R.id.tvWeellySubmit);
        rvDailyattendanceReport=findViewById(R.id.rvDailyattendanceReport);
        ivBack=findViewById(R.id.ivBack);
        rvMonthlyattendanceReport=findViewById(R.id.rvMonthlyattendanceReport);
        tvEmpId=findViewById(R.id.tvEmpId);
        tvName=findViewById(R.id.tvName);
        tvspace=findViewById(R.id.tvspace);



//        Date d = new Date();
//        CharSequence s = DateFormat.format("yyyy-MM-dd ", d.getTime());
//        tvStartDate.setText(s);







        dailyReportAdapter=new DailyReportAdapter(this,dailyReportAttendanceModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvDailyattendanceReport.setAdapter(dailyReportAdapter);
        rvDailyattendanceReport.setLayoutManager(linearLayoutManager);

        monthlyReportAdapter=new MonthlyReportAdapter(this,monthlyReportAttendanceModels);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        rvMonthlyattendanceReport.setAdapter(monthlyReportAdapter);
        rvMonthlyattendanceReport.setLayoutManager(linearLayoutManager1);




        DailyUserAdapter=new ArrayAdapter(this,R.layout.spinner_item,strDailyUser);
        DailyUserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUser.setAdapter(DailyUserAdapter);

        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Dailyuser=dailyReportUserAttendanceModels.get(position).getEmployee();
               // callDailyReport();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        monthlyuserAdapter=new ArrayAdapter(this,R.layout.spinner_item,strmonthlyUser);
        monthlyuserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUserMonthy.setAdapter(monthlyuserAdapter);

        spUserMonthy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Dailyuser=dailyReportUserAttendanceModels.get(position).getEmployee();

//                if (position>0){
//                callMonthlyReport();}else {
//                    callMonthlyReport();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        ReportTypeWMselectedModel week = new ReportTypeWMselectedModel();
        week.setId("1");
        week.setReporttype("Daily");
        reportTypeWMselectedModels.add(week);

        ReportTypeWMselectedModel Month = new ReportTypeWMselectedModel();
        Month.setId("2");
        Month.setReporttype("monthly");
        reportTypeWMselectedModels.add(Month);

        for (int i = 0; i < reportTypeWMselectedModels.size(); i++) {
            strType.add(reportTypeWMselectedModels.get(i).getReporttype());
        }

        typeAdapter = new ArrayAdapter(this, R.layout.spinner_item, strType);
        typeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spreportType.setAdapter(typeAdapter);

        spreportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               ReportTyee = reportTypeWMselectedModels.get(position).getReporttype();

                if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("Daily")) {

                    tvStartDateTag.setVisibility(View.VISIBLE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.VISIBLE);
                    tvEndDate.setVisibility(View.GONE);
                    tvUserTag.setVisibility(View.VISIBLE);
                    spUser.setVisibility(View.VISIBLE);
                    rvDailyattendanceReport.setVisibility(View.VISIBLE);
                    tvWeellySubmit.setVisibility(View.VISIBLE);

                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    spUserMonthy.setVisibility(View.GONE);
                    tvName.setVisibility(View.GONE);
                    tvEmpId.setVisibility(View.GONE);
                    tvspace.setVisibility(View.GONE);
                    tvMonthlyUserTag.setVisibility(View.GONE);
                    rvMonthlyattendanceReport.setVisibility(View.GONE);
                    tvMonthlySubmit.setVisibility(View.GONE);
                  //  CallDailyUser();



                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("monthly")) {

                    tvMonthyearTag.setVisibility(View.VISIBLE);
                    tvMonthyear.setVisibility(View.VISIBLE);
                    spUserMonthy.setVisibility(View.VISIBLE);
                    tvMonthlyUserTag.setVisibility(View.VISIBLE);
                    rvMonthlyattendanceReport.setVisibility(View.VISIBLE);
                    tvMonthlySubmit.setVisibility(View.VISIBLE);

                    tvStartDateTag.setVisibility(View.GONE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);
                    tvEndDate.setVisibility(View.GONE);
                    tvUserTag.setVisibility(View.GONE);
                    spUser.setVisibility(View.GONE);
                    rvDailyattendanceReport.setVisibility(View.GONE);
                    tvWeellySubmit.setVisibility(View.GONE);

                    //callMonthlyUser();



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvWeellySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDailyReport();
            }
        });

        tvMonthlySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spUserMonthy.getSelectedItem().toString().trim().equals("All")){
                    Toast.makeText(AttendanceReports.this, "Please select user", Toast.LENGTH_SHORT).show();
                }else {
                    callMonthlyReport();
                }
            }
        });

        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlfilter.setVisibility(View.VISIBLE);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvStartDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        String monthname=(String)android.text.format.DateFormat.format("MMMM - yyyy", new Date());
                        tvMonthyear.setText(monthname );
                        DatePickerDialog datePickerDialog = new DatePickerDialog(AttendanceReports.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String moth, dt;

                                moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                                dt = (day > 9) ? "" + day : ("0" + day);


                                String strDate = year + "-" + moth + "-" + dt;
                                tvStartDate.setText(strDate);

                                CallDailyUser();
                               // callDailyReport();
                            }

                        }, year, month, dayOfMonth);

                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                        datePickerDialog.show();


                    }
                });
            }
        });

        tvMonthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callMonthlyUser();
                Calendar mcurrentDate = Calendar.getInstance();

                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                String myFormat = "MMMM yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                DatePickerDialog monthDatePickerDialog = new DatePickerDialog(AttendanceReports.this,
                        AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mcurrentDate.set(Calendar.YEAR, year);
                        mcurrentDate.set(Calendar.MONTH, month);
                        tvMonthyear.setText(sdf.format(mcurrentDate.getTime()));
                        mDay = dayOfMonth;
                        mMonth = month;
                        mYear = year;

                        callMonthlyUser();
                        //callMonthlyReport();


                    }
                }, mYear, mMonth, mDay) {

                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                    }
                };
                monthDatePickerDialog.getDatePicker().getYear();
                monthDatePickerDialog.setTitle("Select month And Year");
                monthDatePickerDialog.show();
            }

        });
            }

    private void callMonthlyUser() {
        DailyAttendanceUserInterface dailyAttendanceUserInterface=APIClient.getClient().create(DailyAttendanceUserInterface.class);
        dailyAttendanceUserInterface.callDailyUser("getEmployeeRecord",PreferenceManager.getEmpuser(this),PreferenceManager.getEmpID(this)).enqueue(new Callback<DailyReportUserAttendanceResponse>() {
            @Override
            public void onResponse(Call<DailyReportUserAttendanceResponse> call, Response<DailyReportUserAttendanceResponse> response) {
                try {
                    if (response.body().getDailyReportUserAttendanceModels().size()>0){
                        dailyReportUserAttendanceModels=response.body().getDailyReportUserAttendanceModels();
                        strmonthlyUser.clear();
                        for (int i=0;i<dailyReportUserAttendanceModels.size();i++){
                            strmonthlyUser.add(dailyReportUserAttendanceModels.get(i).getEmployee());
                        }
                        monthlyuserAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<DailyReportUserAttendanceResponse> call, Throwable t) {

            }
        });
    }

    private void callMonthlyReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ReportTyee = spreportType.getSelectedItem().toString();
        Dailyuser=spUserMonthy.getSelectedItem().toString();


        MonthlyReportAttendanceInterFace monthlyReportAttendanceInterFace=APIClient.getClient().create(MonthlyReportAttendanceInterFace.class);
        monthlyReportAttendanceInterFace.callMonthlyReport("monthlyAttendanceReport",tvMonthyear.getText().toString(),Dailyuser,ReportTyee).enqueue(new Callback<MonthlyReportAttendanceResponse>() {
            @Override
            public void onResponse(Call<MonthlyReportAttendanceResponse> call, Response<MonthlyReportAttendanceResponse> response) {
                try {
                    if (response.isSuccessful()){
                        tvName.setVisibility(View.VISIBLE);
                        tvEmpId.setVisibility(View.VISIBLE);
                        tvspace.setVisibility(View.VISIBLE);
                        tvName.setText(response.body().getName());
                        tvEmpId.setText(response.body().getEmpid());
                        monthlyReportAdapter.monthlyReportAttendanceModels=response.body().getMonthlyReportAttendanceModels();
                        monthlyReportAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<MonthlyReportAttendanceResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private void CallDailyUser() {
        DailyAttendanceUserInterface dailyAttendanceUserInterface=APIClient.getClient().create(DailyAttendanceUserInterface.class);
        dailyAttendanceUserInterface.callDailyUser("getEmployeeRecord", PreferenceManager.getEmpuser(this),PreferenceManager.getEmpID(this)).enqueue(new Callback<DailyReportUserAttendanceResponse>() {
            @Override
            public void onResponse(Call<DailyReportUserAttendanceResponse> call, Response<DailyReportUserAttendanceResponse> response) {
                try {
                    if (response.body().getDailyReportUserAttendanceModels().size()>0){
                        dailyReportUserAttendanceModels=response.body().getDailyReportUserAttendanceModels();
                        strDailyUser.clear();
                        for (int i=0;i<dailyReportUserAttendanceModels.size();i++){
                            strDailyUser.add(dailyReportUserAttendanceModels.get(i).getEmployee());
                        }
                        DailyUserAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<DailyReportUserAttendanceResponse> call, Throwable t) {

            }
        });
    }

    private void callDailyReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ReportTyee = spreportType.getSelectedItem().toString();
        Dailyuser=spUser.getSelectedItem().toString();
        DailyReportAttendanceInterFace dailyReportAttendanceInterFace= APIClient.getClient().create(DailyReportAttendanceInterFace.class);
        dailyReportAttendanceInterFace.callDailyReport("dailyAttendanceReport",tvStartDate.getText().toString(),Dailyuser,ReportTyee).enqueue(new Callback<DailyReportAttendanceResponse>() {
            @Override
            public void onResponse(Call<DailyReportAttendanceResponse> call, Response<DailyReportAttendanceResponse> response) {

                try {
                    if (response.isSuccessful()){
                        dailyReportAdapter.dailyReportAttendanceModels=response.body().getDailyReportAttendanceModels();
                        dailyReportAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                        tvName.setVisibility(View.GONE);
                        tvEmpId.setVisibility(View.GONE);
                        tvspace.setVisibility(View.GONE);
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<DailyReportAttendanceResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }


}