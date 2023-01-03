package com.example.cistronuser.Report.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
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
import com.example.cistronuser.API.Interface.ReportExpenseWMInterface;
import com.example.cistronuser.API.Interface.ReportWeeklyExpensInterface;
import com.example.cistronuser.API.Interface.SelectWeekDateInterface;
import com.example.cistronuser.API.Interface.UserMonthlyExpensesReportMW;
import com.example.cistronuser.API.Interface.UserWeeklyReportExpenseMW;
import com.example.cistronuser.API.Model.ReportTypeWMselectedModel;
import com.example.cistronuser.API.Model.UserDailyExpensesWMModel;
import com.example.cistronuser.API.Model.UserWeeklyReportExpensesMWModel;
import com.example.cistronuser.API.Response.ReportExpenseWMResponses;
import com.example.cistronuser.API.Response.ReportExpenseWeeklyResponse;
import com.example.cistronuser.API.Response.SelectWeekResponse;
import com.example.cistronuser.API.Response.UserWeeklyReportExpenseMWResponses;
import com.example.cistronuser.Activity.ExpensesActivity;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.ExpensesWeeklyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseReportWM extends AppCompatActivity {


    ImageView ivfilter,ivBack;
    RelativeLayout  rlfilter;

    Spinner spUser, spreportType, spUserMonthy;
    TextView tvStartDateTag, tvStartDate, tvEndDateTag, tvMonthyearTag, tvMonthyear, tvSubmit,tvWeellySubmit, tvMonthlyUserTag, tvUserTag,tvGrandsumDocTag;


    //Reporttype
    ArrayList<String> strType = new ArrayList<>();
    ArrayList<ReportTypeWMselectedModel> reportTypeWMselectedModels = new ArrayList<>();
    ArrayAdapter typeAdapter;
    String ReportTyee;
    int mDay, mMonth, mYear;

    //Recycleview
    RecyclerView rvuserDailyweekExpense,rvuserMonthlyExpense;
    ExpensesWeeklyAdapter weeklyAdapter;
    ExpensesWeeklyAdapter monthlyAdapter;
    ArrayList<UserDailyExpensesWMModel> userDailyExpensesWMModels = new ArrayList<>();

    //WeeklyUser and MonthlyUser
    ArrayList<UserWeeklyReportExpensesMWModel> userWeeklyReportExpensesMWModels = new ArrayList<>();
    ArrayAdapter weeklyuser;
    ArrayAdapter monthlyuser;
    ArrayList<String> strWeekly = new ArrayList<>();
    ArrayList<String> strmonthly = new ArrayList<>();
    String Monthlyid, WeeklyId;

    //Dailyreport
    RelativeLayout rlMonthly;
    TextView tvGrandsumDoc,tvNodata;

    //Weekly
    TextView tvselectDate,tvto,tvEnddate;




    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_report_wm);

        tvSubmit = findViewById(R.id.tvSubmit);
        spUser = findViewById(R.id.spUser);
        spreportType = findViewById(R.id.spreportType);
        tvStartDateTag = findViewById(R.id.tvStartDateTag);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDateTag = findViewById(R.id.tvEndDateTag);
        tvMonthyearTag = findViewById(R.id.tvMonthyearTag);
        tvMonthyear = findViewById(R.id.tvMonthyear);
        rvuserDailyweekExpense = findViewById(R.id.rvuserDailyweekExpense);
        spUserMonthy = findViewById(R.id.spUserMonthy);
        tvUserTag = findViewById(R.id.tvUserTag);
        tvMonthlyUserTag = findViewById(R.id.tvMonthlyUserTag);
        rlMonthly=findViewById(R.id.rlMonthly);
        rlfilter=findViewById(R.id.rlfilter);
        ivfilter=findViewById(R.id.ivfilter);
        ivBack=findViewById(R.id.ivBack);
        tvGrandsumDoc=findViewById(R.id.tvGrandsumDoc);
        tvWeellySubmit=findViewById(R.id.tvWeellySubmit);
        tvNodata=findViewById(R.id.tvNodata);
        tvEnddate=findViewById(R.id.tvEnddate);
        tvselectDate=findViewById(R.id.tvselectDate);
        tvto=findViewById(R.id.tvto);
        rvuserMonthlyExpense=findViewById(R.id.rvuserMonthlyExpense);
        tvGrandsumDocTag=findViewById(R.id.tvGrandsumDocTag);



//        Date d = new Date();
//        CharSequence s = DateFormat.format("yyyy-MM-dd ", d.getTime());
//        tvDate.setText(s);


        //Month
        String myFormat = "MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mcurrentDate.set(Calendar.YEAR, mYear);
        mcurrentDate.set(Calendar.MONTH, mMonth);
        tvMonthyear.setText(sdf.format(mcurrentDate.getTime()));




        //RecycleView
        weeklyAdapter = new ExpensesWeeklyAdapter(this, userDailyExpensesWMModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvuserDailyweekExpense.setLayoutManager(linearLayoutManager);
        rvuserDailyweekExpense.setAdapter(weeklyAdapter);


        monthlyAdapter = new ExpensesWeeklyAdapter(this, userDailyExpensesWMModels);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        rvuserMonthlyExpense.setLayoutManager(linearLayoutManager1);
        rvuserMonthlyExpense.setAdapter(monthlyAdapter);







        ReportTypeWMselectedModel select = new ReportTypeWMselectedModel();
        select.setReporttype("--Select--");
        reportTypeWMselectedModels.add(select);


        ReportTypeWMselectedModel week = new ReportTypeWMselectedModel();
        week.setId("1");
        week.setReporttype("weekly");
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

                if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("weekly")) {

                    tvStartDateTag.setVisibility(View.VISIBLE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.VISIBLE);
                    tvUserTag.setVisibility(View.VISIBLE);
                    spUser.setVisibility(View.VISIBLE);
                    tvWeellySubmit.setVisibility(View.VISIBLE);
                    rvuserDailyweekExpense.setVisibility(View.VISIBLE);


                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    spUserMonthy.setVisibility(View.GONE);
                    tvMonthlyUserTag.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.GONE);
                    rvuserMonthlyExpense.setVisibility(View.GONE);


                   // CallWeeklyUser();

                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("monthly")) {

                    tvMonthyearTag.setVisibility(View.VISIBLE);
                    tvMonthyear.setVisibility(View.VISIBLE);
                    spUserMonthy.setVisibility(View.VISIBLE);
                    tvMonthlyUserTag.setVisibility(View.VISIBLE);
                    rvuserMonthlyExpense.setVisibility(View.VISIBLE);

                    tvSubmit.setVisibility(View.VISIBLE);

                    tvStartDateTag.setVisibility(View.GONE);

                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);
                    tvUserTag.setVisibility(View.GONE);
                    spUser.setVisibility(View.GONE);
                    tvselectDate.setVisibility(View.GONE);
                    tvWeellySubmit.setVisibility(View.GONE);
                    tvto.setVisibility(View.GONE);
                    tvEnddate.setVisibility(View.GONE);
                    rvuserDailyweekExpense.setVisibility(View.GONE);

                  //  callMonthlyuser();
                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("--Select--")) {

                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    tvStartDateTag.setVisibility(View.GONE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        weeklyuser = new ArrayAdapter(this, R.layout.spinner_item, strWeekly);
        weeklyuser.setDropDownViewResource(R.layout.spinner_dropdown);
        spUser.setAdapter(weeklyuser);
        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                   // WeeklyId = userWeeklyReportExpensesMWModels.get(position).getEmployee();
                    rvuserDailyweekExpense.setVisibility(View.GONE);
                    tvGrandsumDocTag.setVisibility(View.GONE);
                    tvGrandsumDoc.setVisibility(View.GONE);
                  //  Log.e(TAG, "onItemSelected: "+spUser.getSelectedItem() );
                    tvWeellySubmit.setVisibility(View.VISIBLE);

                }catch (Exception e){

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        monthlyuser = new ArrayAdapter(this, R.layout.spinner_item, strmonthly);
        monthlyuser.setDropDownViewResource(R.layout.spinner_dropdown);
        spUserMonthy.setAdapter(monthlyuser);
        spUserMonthy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {

                  //  Monthlyid = userWeeklyReportExpensesMWModels.get(position).getEmployee();
                    rvuserMonthlyExpense.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.VISIBLE);
                    tvGrandsumDocTag.setVisibility(View.GONE);
                    tvGrandsumDoc.setVisibility(View.GONE);
                }catch (Exception e){
                    Log.e(TAG, "onItemSelected: "+e.getMessage() );
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvMonthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvuserMonthlyExpense.setVisibility(View.GONE);
                tvGrandsumDoc.setVisibility(View.GONE);
                tvGrandsumDocTag.setVisibility(View.GONE);
                Calendar mcurrentDate = Calendar.getInstance();

                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                String myFormat = "MMMM yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                DatePickerDialog monthDatePickerDialog = new DatePickerDialog(ExpenseReportWM.this,
                        AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mcurrentDate.set(Calendar.YEAR, year);
                        mcurrentDate.set(Calendar.MONTH, month);
                        tvMonthyear.setText(sdf.format(mcurrentDate.getTime()));
                        mDay = dayOfMonth;
                        mMonth = month;
                        mYear = year;
                        callMonthlyuser();

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

        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvuserDailyweekExpense.setVisibility(View.GONE);
                tvGrandsumDocTag.setVisibility(View.GONE);
                tvGrandsumDoc.setVisibility(View.GONE);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExpenseReportWM.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvStartDate.setText(strDate);


                        CallWeeklyUser();
                        final ProgressDialog progressDialog = new ProgressDialog(ExpenseReportWM.this,R.style.ProgressBarDialog);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        SelectWeekDateInterface selectWeekDateInterface = APIClient.getClient().create(SelectWeekDateInterface.class);
                        selectWeekDateInterface.CallSelectWeek("viewWeeklyExpenses", tvStartDate.getText().toString(),PreferenceManager.getEmpID(ExpenseReportWM.this)).enqueue(new Callback<SelectWeekResponse>() {
                            @Override
                            public void onResponse(Call<SelectWeekResponse> call, Response<SelectWeekResponse> response) {
                                try {
                                    if (response.isSuccessful()) {

                                        progressDialog.dismiss();
                                        tvselectDate.setText(response.body().getStartdate());
                                        tvEnddate.setText(response.body().getEnddate());
                                        tvEnddate.setVisibility(View.VISIBLE);
                                        tvto.setVisibility(View.VISIBLE);
                                        tvselectDate.setVisibility(View.VISIBLE);
                                        CallWeeklyUser();


                                    }

                                } catch (Exception e) {

                                }

                            }

                            @Override
                            public void onFailure(Call<SelectWeekResponse> call, Throwable t) {

                            }
                        });
                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();


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
                //Monthly
                callUserWeeklyMW();

                rlfilter.setVisibility(View.GONE);

            }
        });
        tvWeellySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rlfilter.setVisibility(View.GONE);
               CallWeeklyMW();



            }
        });


    }

    private void CallWeeklyMW() {
        final ProgressDialog progressDialog = new ProgressDialog(ExpenseReportWM.this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        // Log.e(TAG, "onClick: "+WeeklyId+spUser.getSelectedItem().toString());
        WeeklyId = spUser.getSelectedItem().toString();
        ReportTyee = spreportType.getSelectedItem().toString();

        ReportWeeklyExpensInterface reportWeeklyExpensInterface=APIClient.getClient().create(ReportWeeklyExpensInterface.class);
        reportWeeklyExpensInterface.callWeekly("expensesReport",ReportTyee,WeeklyId,tvselectDate.getText().toString(),tvEnddate.getText().toString()).enqueue(new Callback<ReportExpenseWeeklyResponse>() {
            @Override
            public void onResponse(Call<ReportExpenseWeeklyResponse> call, Response<ReportExpenseWeeklyResponse> response) {

                if (response.isSuccessful()){
                    // Log.e(TAG, "onResponse: "+response.body().getEmployee() );
                    rvuserDailyweekExpense.setVisibility(View.VISIBLE);
                    tvGrandsumDocTag.setVisibility(View.VISIBLE);
                    tvGrandsumDoc.setVisibility(View.VISIBLE);
                    rlMonthly.setVisibility(View.VISIBLE);
                    tvGrandsumDoc.setText(response.body().getExpenses_sum());
                    weeklyAdapter.userDailyExpensesWMModels=response.body().getUserDailyExpensesWMModels();
                    weeklyAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }

                //  Toast.makeText(ExpenseReportWM.this, response.body().getExpenses_sum(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ReportExpenseWeeklyResponse> call, Throwable t) {

            }
        });

    }


    private void callMonthlyuser() {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        UserMonthlyExpensesReportMW userMonthlyExpensesReportMW = APIClient.getClient().create(UserMonthlyExpensesReportMW.class);
        userMonthlyExpensesReportMW.callMonthly("userListForExpenses", ReportTyee, tvMonthyear.getText().toString(), PreferenceManager.getEmpuser(this),PreferenceManager.getEmpID(this)).enqueue(new Callback<UserWeeklyReportExpenseMWResponses>() {
            @Override
            public void onResponse(Call<UserWeeklyReportExpenseMWResponses> call, Response<UserWeeklyReportExpenseMWResponses> response) {

                try {
                    if (response.body().getWeeklyReportExpenseMWS().size()>0) {
                        progressDialog.dismiss();


                        userWeeklyReportExpensesMWModels = response.body().getWeeklyReportExpenseMWS();

                        strmonthly.clear();
                        for (int i = 0; i < userWeeklyReportExpensesMWModels.size(); i++) {
                                strmonthly.add(userWeeklyReportExpensesMWModels.get(i).getEmployee());


                        }
                        monthlyuser.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<UserWeeklyReportExpenseMWResponses> call, Throwable t) {

            }
        });
    }

    private void CallWeeklyUser() {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UserWeeklyReportExpenseMW userWeeklyReportExpenseMW = APIClient.getClient().create(UserWeeklyReportExpenseMW.class);
        userWeeklyReportExpenseMW.callWeekly("userListForExpenses", ReportTyee, tvselectDate.getText().toString(), tvEnddate.getText().toString(), PreferenceManager.getEmpuser(this),PreferenceManager.getEmpID(this)).enqueue(new Callback<UserWeeklyReportExpenseMWResponses>() {
            @Override
            public void onResponse(Call<UserWeeklyReportExpenseMWResponses> call, Response<UserWeeklyReportExpenseMWResponses> response) {

                try {
                    if (response.body().getWeeklyReportExpenseMWS().size()>0) {
                        progressDialog.dismiss();
                        userWeeklyReportExpensesMWModels = response.body().getWeeklyReportExpenseMWS();
                        strWeekly.clear();
                        for (int i = 0; i < userWeeklyReportExpensesMWModels.size(); i++) {
                            strWeekly.add(userWeeklyReportExpensesMWModels.get(i).getEmployee());
                        }

                        weeklyuser.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<UserWeeklyReportExpenseMWResponses> call, Throwable t) {

            }
        });



    }

    private void callUserWeeklyMW() {

        //monthly
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Monthlyid = spUserMonthy.getSelectedItem().toString();
        ReportTyee = spreportType.getSelectedItem().toString();
        ReportExpenseWMInterface reportExpenseWMInterface = APIClient.getClient().create(ReportExpenseWMInterface.class);
        reportExpenseWMInterface.callMonthly("expensesReport", ReportTyee, Monthlyid, tvMonthyear.getText().toString()).enqueue(new Callback<ReportExpenseWMResponses>() {
            @Override
            public void onResponse(Call<ReportExpenseWMResponses> call, Response<ReportExpenseWMResponses> response) {


                Log.e(TAG, "expense: "+response.body().getUserDailyExpensesWMModels().get(0).getEmpid() );
                try {

                    if (response.isSuccessful()) {


                        rvuserMonthlyExpense.setVisibility(View.VISIBLE);
                        tvGrandsumDocTag.setVisibility(View.VISIBLE);
                        tvGrandsumDoc.setVisibility(View.VISIBLE);

                        Log.e(TAG, "onResponse: "+response.body().getExpenses_sum() );
                        tvGrandsumDoc.setText(response.body().getExpenses_sum());
                        monthlyAdapter.userDailyExpensesWMModels = response.body().getUserDailyExpensesWMModels();
                        monthlyAdapter.notifyDataSetChanged();
                        rlMonthly.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ReportExpenseWMResponses> call, Throwable t) {
               // rlMonthly.setVisibility(View.GONE);
                progressDialog.dismiss();

            }
        });
    }
}