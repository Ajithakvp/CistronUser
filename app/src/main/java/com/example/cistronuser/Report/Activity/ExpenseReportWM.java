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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ReportExpenseWMInterface;
import com.example.cistronuser.API.Interface.ReportWeeklyExpensInterface;
import com.example.cistronuser.API.Interface.UserMonthlyExpensesReportMW;
import com.example.cistronuser.API.Interface.UserWeeklyReportExpenseMW;
import com.example.cistronuser.API.Model.ReportTypeWMselectedModel;
import com.example.cistronuser.API.Model.UserDailyExpensesWMModel;
import com.example.cistronuser.API.Model.UserWeeklyReportExpensesMWModel;
import com.example.cistronuser.API.Response.ReportExpenseWMResponses;
import com.example.cistronuser.API.Response.UserWeeklyReportExpenseMWResponses;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.ExpensesWeeklyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseReportWM extends AppCompatActivity {


    ImageView ivfilter,ivBack;
    RelativeLayout  rlfilter;

    Spinner spUser, spreportType, spUserMonthy;
    TextView tvStartDateTag, tvStartDate, tvEndDateTag, tvEndDate, tvMonthyearTag, tvMonthyear, tvSubmit,tvWeellySubmit, tvMonthlyUserTag, tvUserTag;


    //Reporttype
    ArrayList<String> strType = new ArrayList<>();
    ArrayList<ReportTypeWMselectedModel> reportTypeWMselectedModels = new ArrayList<>();
    ArrayAdapter typeAdapter;
    String ReportTyee;
    int mDay, mMonth, mYear;

    //Recycleview
    RecyclerView rvuserDailyweekExpense,rvUserWeekly;
    ExpensesWeeklyAdapter weeklyAdapter;
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
    TextView tvGrandsumDoc;

    //Weekly




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
        tvEndDate = findViewById(R.id.tvEndDate);
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





        // Monthly
        //RecycleView
        weeklyAdapter = new ExpensesWeeklyAdapter(this, userDailyExpensesWMModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvuserDailyweekExpense.setLayoutManager(linearLayoutManager);
        rvuserDailyweekExpense.setAdapter(weeklyAdapter);







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
                    tvEndDateTag.setVisibility(View.VISIBLE);
                    tvStartDate.setVisibility(View.VISIBLE);
                    tvEndDate.setVisibility(View.VISIBLE);
                    tvUserTag.setVisibility(View.VISIBLE);
                    spUser.setVisibility(View.VISIBLE);

                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    spUserMonthy.setVisibility(View.GONE);
                    tvMonthlyUserTag.setVisibility(View.GONE);

                    CallWeeklyUser();

                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("monthly")) {

                    tvMonthyearTag.setVisibility(View.VISIBLE);
                    tvMonthyear.setVisibility(View.VISIBLE);
                    spUserMonthy.setVisibility(View.VISIBLE);
                    tvMonthlyUserTag.setVisibility(View.VISIBLE);

                    tvStartDateTag.setVisibility(View.GONE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);
                    tvEndDate.setVisibility(View.GONE);
                    tvUserTag.setVisibility(View.GONE);
                    spUser.setVisibility(View.GONE);

                    callMonthlyuser();
                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("--Select--")) {

                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);
                    tvStartDateTag.setVisibility(View.GONE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);
                    tvEndDate.setVisibility(View.GONE);

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
                    WeeklyId = userWeeklyReportExpensesMWModels.get(position).getEmployee();
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

                    Monthlyid = userWeeklyReportExpensesMWModels.get(position).getEmployee();
                    tvSubmit.setVisibility(View.VISIBLE);
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
                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();


            }
        });

        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        tvEndDate.setText(strDate);
                        CallWeeklyUser();
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
                rlMonthly.setVisibility(View.VISIBLE);
                rlfilter.setVisibility(View.GONE);

            }
        });
        tvWeellySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Weekly
                callOnlyuserWeekly();
                rlMonthly.setVisibility(View.VISIBLE);
                rlfilter.setVisibility(View.GONE);
            }
        });


    }

    private void callOnlyuserWeekly() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ReportWeeklyExpensInterface reportWeeklyExpensInterface=APIClient.getClient().create(ReportWeeklyExpensInterface.class);
        reportWeeklyExpensInterface.callWeekly("expensesReport",ReportTyee,WeeklyId,tvStartDate.getText().toString(),tvEndDate.getText().toString()).enqueue(new Callback<ReportExpenseWMResponses>() {
            @Override
            public void onResponse(Call<ReportExpenseWMResponses> call, Response<ReportExpenseWMResponses> response) {
                try {
                    if (response.isSuccessful()){

                        tvGrandsumDoc.setText(response.body().getExpenses_sum());
                        weeklyAdapter.userDailyExpensesWMModels=response.body().getUserDailyExpensesWMModels();
                        weeklyAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();

                    }else {
                        progressDialog.dismiss();
                        weeklyAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ReportExpenseWMResponses> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }

    private void callMonthlyuser() {

        UserMonthlyExpensesReportMW userMonthlyExpensesReportMW = APIClient.getClient().create(UserMonthlyExpensesReportMW.class);
        userMonthlyExpensesReportMW.callMonthly("userListForExpenses", ReportTyee, tvMonthyear.getText().toString()).enqueue(new Callback<UserWeeklyReportExpenseMWResponses>() {
            @Override
            public void onResponse(Call<UserWeeklyReportExpenseMWResponses> call, Response<UserWeeklyReportExpenseMWResponses> response) {

                try {
                    if (response.body().getWeeklyReportExpenseMWS().size()>0) {
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
        UserWeeklyReportExpenseMW userWeeklyReportExpenseMW = APIClient.getClient().create(UserWeeklyReportExpenseMW.class);
        userWeeklyReportExpenseMW.callWeekly("userListForExpenses", ReportTyee, tvStartDate.getText().toString(), tvEndDate.getText().toString()).enqueue(new Callback<UserWeeklyReportExpenseMWResponses>() {
            @Override
            public void onResponse(Call<UserWeeklyReportExpenseMWResponses> call, Response<UserWeeklyReportExpenseMWResponses> response) {

                try {
                    if (response.body().getWeeklyReportExpenseMWS().size()>0) {
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
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ReportExpenseWMInterface reportExpenseWMInterface = APIClient.getClient().create(ReportExpenseWMInterface.class);
        reportExpenseWMInterface.callMonthly("expensesReport", ReportTyee, Monthlyid, tvMonthyear.getText().toString()).enqueue(new Callback<ReportExpenseWMResponses>() {
            @Override
            public void onResponse(Call<ReportExpenseWMResponses> call, Response<ReportExpenseWMResponses> response) {
                try {

                    if (response.isSuccessful()) {


                        tvGrandsumDoc.setText(response.body().getExpenses_sum());
                        weeklyAdapter.userDailyExpensesWMModels = response.body().getUserDailyExpensesWMModels();
                        weeklyAdapter.notifyDataSetChanged();
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