package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.example.cistronuser.API.Interface.LeaveReportDailyInterface;
import com.example.cistronuser.API.Interface.LeaveReportMonthlyInterface;
import com.example.cistronuser.API.Model.DailyReportUserAttendanceModel;
import com.example.cistronuser.API.Model.LeaveReportDailyModel;
import com.example.cistronuser.API.Model.LeaveReportMonthlyModel;
import com.example.cistronuser.API.Model.MonthlyReportAttendanceModel;
import com.example.cistronuser.API.Model.ReportTypeWMselectedModel;
import com.example.cistronuser.API.Response.DailyReportUserAttendanceResponse;
import com.example.cistronuser.API.Response.LeaveReportDailyResponse;
import com.example.cistronuser.API.Response.LeaveReportMonthlyResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.LeaveReportAdapter;
import com.example.cistronuser.Report.Adapter.LeaveReportDailyAdapter;
import com.example.cistronuser.Report.Adapter.MonthlyReportAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveReport extends AppCompatActivity {


    Spinner spUser, spreportType, spUserMonthy;
    TextView tvDateTag, tvDate, tvMonthyearTag, tvMonthyear, tvMonthlySubmit, tvDailySubmit, tvMonthlyUserTag, tvUserTag;
    ImageView ivfilter, ivBack;
    RelativeLayout rlfilter;

    //leaveType
    ArrayList<String> strType = new ArrayList<>();
    ArrayList<ReportTypeWMselectedModel> reportTypeWMselectedModels = new ArrayList<>();
    ArrayAdapter typeAdapter;
    String ReportTyee;
    int mDay, mMonth, mYear;

    //Daily User
    ArrayList<DailyReportUserAttendanceModel> dailyReportUserAttendanceModels = new ArrayList<>();
    ArrayAdapter DailyUserAdapter, monthlyuserAdapter;
    ArrayList<String> strDailyUser = new ArrayList<>();
    ArrayList<String>strmonthlyUser=new ArrayList<>();
    String Dailyuser, Monthlyuser;
    String Baseurl;

    //RecycleViewDaily
    TextView tvDailyDate,tvDailySpace,tvDailyDay;
    LeaveReportDailyAdapter leaveReportDailyAdapter;
    ArrayList<LeaveReportDailyModel>leaveReportDailyModels=new ArrayList<>();
    RecyclerView rvLeaveReport;

    //RecycleViewMonthly
    RecyclerView rvLeaveReportMonthly;
    LeaveReportAdapter leaveReportAdapter;
    ArrayList<LeaveReportMonthlyModel>leaveReportMonthlyModels=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_report);


        tvMonthlySubmit = findViewById(R.id.tvMonthlySubmit);
        spUser = findViewById(R.id.spUser);
        spreportType = findViewById(R.id.spreportType);
        tvMonthyearTag = findViewById(R.id.tvMonthyearTag);
        tvMonthyear = findViewById(R.id.tvMonthyear);
        spUserMonthy = findViewById(R.id.spUserMonthy);
        tvUserTag = findViewById(R.id.tvUserTag);
        tvMonthlyUserTag = findViewById(R.id.tvMonthlyUserTag);
        tvDateTag = findViewById(R.id.tvDateTag);
        tvDate = findViewById(R.id.tvDate);
        rlfilter = findViewById(R.id.rlfilter);
        ivfilter = findViewById(R.id.ivfilter);
        ivBack = findViewById(R.id.ivBack);
        rvLeaveReport=findViewById(R.id.rvLeaveReport);
        tvDailySubmit = findViewById(R.id.tvWeellySubmit);
        rvLeaveReportMonthly=findViewById(R.id.rvLeaveReportMonthly);



        //Dailydate
        tvDailyDay=findViewById(R.id.tvDailyDay);
        tvDailyDate=findViewById(R.id.tvDailyDate);
        tvDailySpace=findViewById(R.id.tvDailySpace);


        //Recycleview
        leaveReportDailyAdapter=new LeaveReportDailyAdapter(this,leaveReportDailyModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvLeaveReport.setLayoutManager(linearLayoutManager);
        rvLeaveReport.setAdapter(leaveReportDailyAdapter);


        //monthlyRecycleview
        leaveReportAdapter=new LeaveReportAdapter(this,leaveReportMonthlyModels);
        LinearLayoutManager monthly=new LinearLayoutManager(this);
        monthly.setOrientation(RecyclerView.VERTICAL);
        rvLeaveReportMonthly.setAdapter(leaveReportAdapter);
        rvLeaveReportMonthly.setLayoutManager(monthly);





        //Dailyuser

        DailyUserAdapter = new ArrayAdapter(this, R.layout.spinner_item, strDailyUser);
        DailyUserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUser.setAdapter(DailyUserAdapter);

        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       //Monthly
        monthlyuserAdapter = new ArrayAdapter(this, R.layout.spinner_item, strmonthlyUser);
        monthlyuserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUserMonthy.setAdapter(monthlyuserAdapter);

        spUserMonthy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //LeaveType


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

                    tvUserTag.setVisibility(View.VISIBLE);
                    spUser.setVisibility(View.VISIBLE);
                    tvDateTag.setVisibility(View.VISIBLE);
                    tvDate.setVisibility(View.VISIBLE);
                    tvDailySubmit.setVisibility(View.VISIBLE);
                    rvLeaveReport.setVisibility(View.VISIBLE);


                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    spUserMonthy.setVisibility(View.GONE);
                    tvMonthlyUserTag.setVisibility(View.GONE);
                    tvMonthlySubmit.setVisibility(View.GONE);
                    rvLeaveReportMonthly.setVisibility(View.GONE);


                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("monthly")) {

                    tvMonthyearTag.setVisibility(View.VISIBLE);
                    tvMonthyear.setVisibility(View.VISIBLE);
                    spUserMonthy.setVisibility(View.VISIBLE);
                    tvMonthlyUserTag.setVisibility(View.VISIBLE);
                    tvMonthlySubmit.setVisibility(View.VISIBLE);
                    rvLeaveReportMonthly.setVisibility(View.VISIBLE);



                    tvDailyDate.setVisibility(View.GONE);
                    tvDailyDay.setVisibility(View.GONE);
                    tvDailySpace.setVisibility(View.GONE);
                    tvUserTag.setVisibility(View.GONE);
                    spUser.setVisibility(View.GONE);
                    tvDateTag.setVisibility(View.GONE);
                    tvDate.setVisibility(View.GONE);
                    tvDailySubmit.setVisibility(View.GONE);


                }


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

        tvDailySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDailyLeave();
                rlfilter.setVisibility(View.GONE);
            }
        });
        tvMonthlySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMonthlyLeave();
                rlfilter.setVisibility(View.GONE);
            }
        });

        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlfilter.setVisibility(View.VISIBLE);
            }
        });


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                String monthname = (String) android.text.format.DateFormat.format("MMMM - yyyy", new Date());
                tvMonthyear.setText(monthname);
                DatePickerDialog datePickerDialog = new DatePickerDialog(LeaveReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDate.setText(strDate);

                        CallDailyUser();

                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();
            }
        });

        tvMonthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();

                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                String myFormat = "MMMM yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                DatePickerDialog monthDatePickerDialog = new DatePickerDialog(LeaveReport.this,
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

    private void callMonthlyLeave() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Monthlyuser=spUserMonthy.getSelectedItem().toString();
        LeaveReportMonthlyInterface leaveReportMonthlyInterface=APIClient.getClient().create(LeaveReportMonthlyInterface.class);
        leaveReportMonthlyInterface.callMonthly("getMonthlyLeaveRecord",tvMonthyear.getText().toString(),Monthlyuser).enqueue(new Callback<LeaveReportMonthlyResponse>() {
            @Override
            public void onResponse(Call<LeaveReportMonthlyResponse> call, Response<LeaveReportMonthlyResponse> response) {
                try {
                    if (response.isSuccessful()){

                        leaveReportAdapter.leaveReportMonthlyModels=response.body().getLeaveReportMonthlyModels();
                        leaveReportAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<LeaveReportMonthlyResponse> call, Throwable t) {

            }
        });

    }

    private void callDailyLeave() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Dailyuser=spUser.getSelectedItem().toString();
        LeaveReportDailyInterface leaveReportDailyInterface=APIClient.getClient().create(LeaveReportDailyInterface.class);
        leaveReportDailyInterface.callDaily("getDailyLeaveRecord",tvDate.getText().toString(),Dailyuser).enqueue(new Callback<LeaveReportDailyResponse>() {
            @Override
            public void onResponse(Call<LeaveReportDailyResponse> call, Response<LeaveReportDailyResponse> response) {
                try {
                    if (response.isSuccessful()) {

                            tvDailyDate.setText(response.body().getDate());
                            tvDailyDay.setText(response.body().getDay());
                            tvDailyDay.setVisibility(View.VISIBLE);
                            tvDailyDate.setVisibility(View.VISIBLE);
                            tvDailySpace.setVisibility(View.VISIBLE);
                            Baseurl = response.body().getAttachBaseUrl();
                            leaveReportDailyAdapter.leaveReportDailyModels = response.body().getLeaveReportDailyModels();
                            leaveReportDailyAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();


                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<LeaveReportDailyResponse> call, Throwable t) {

            }
        });

    }


    private void callMonthlyUser() {
        DailyAttendanceUserInterface dailyAttendanceUserInterface = APIClient.getClient().create(DailyAttendanceUserInterface.class);
        dailyAttendanceUserInterface.callDailyUser("getEmployeeRecord", PreferenceManager.getEmpuser(this), PreferenceManager.getEmpID(this)).enqueue(new Callback<DailyReportUserAttendanceResponse>() {
            @Override
            public void onResponse(Call<DailyReportUserAttendanceResponse> call, Response<DailyReportUserAttendanceResponse> response) {
                try {
                    if (response.body().getDailyReportUserAttendanceModels().size() > 0) {
                        dailyReportUserAttendanceModels = response.body().getDailyReportUserAttendanceModels();
                        strmonthlyUser.clear();
                        for (int i = 0; i < dailyReportUserAttendanceModels.size(); i++) {
                            strmonthlyUser.add(dailyReportUserAttendanceModels.get(i).getEmployee());
                        }
                        monthlyuserAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<DailyReportUserAttendanceResponse> call, Throwable t) {

            }
        });
    }


    private void CallDailyUser() {
        DailyAttendanceUserInterface dailyAttendanceUserInterface = APIClient.getClient().create(DailyAttendanceUserInterface.class);
        dailyAttendanceUserInterface.callDailyUser("getEmployeeRecord", PreferenceManager.getEmpuser(this), PreferenceManager.getEmpID(this)).enqueue(new Callback<DailyReportUserAttendanceResponse>() {
            @Override
            public void onResponse(Call<DailyReportUserAttendanceResponse> call, Response<DailyReportUserAttendanceResponse> response) {
                try {
                    if (response.body().getDailyReportUserAttendanceModels().size() > 0) {
                        dailyReportUserAttendanceModels = response.body().getDailyReportUserAttendanceModels();
                        strDailyUser.clear();
                        for (int i = 0; i < dailyReportUserAttendanceModels.size(); i++) {
                            strDailyUser.add(dailyReportUserAttendanceModels.get(i).getEmployee());
                        }
                        DailyUserAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<DailyReportUserAttendanceResponse> call, Throwable t) {

            }
        });
    }
}