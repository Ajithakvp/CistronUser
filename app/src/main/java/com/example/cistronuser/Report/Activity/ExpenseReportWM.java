package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.API.Model.ReportTypeWMselectedModel;
import com.example.cistronuser.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ExpenseReportWM extends AppCompatActivity {

    Spinner spUser, spreportType;
    TextView tvStartDateTag, tvStartDate, tvEndDateTag, tvEndDate, tvMonthyearTag, tvMonthyear, tvSubmit;


    //Reporttype
    ArrayList<String> strType = new ArrayList<>();
    ArrayList<ReportTypeWMselectedModel> reportTypeWMselectedModels = new ArrayList<>();
    ArrayAdapter typeAdapter;
    String ReportTyee;
    int mDay,mMonth,mYear;

    @SuppressLint("MissingInflatedId")
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


        ReportTypeWMselectedModel select = new ReportTypeWMselectedModel();
        select.setReporttype("--Select--");
        reportTypeWMselectedModels.add(select);


        ReportTypeWMselectedModel week = new ReportTypeWMselectedModel();
        week.setId("1");
        week.setReporttype("Weekly");
        reportTypeWMselectedModels.add(week);

        ReportTypeWMselectedModel Month = new ReportTypeWMselectedModel();
        Month.setId("2");
        Month.setReporttype("Monthly");
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

                if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("Weekly")) {

                    tvStartDateTag.setVisibility(View.VISIBLE);
                    tvEndDateTag.setVisibility(View.VISIBLE);
                    tvStartDate.setVisibility(View.VISIBLE);
                    tvEndDate.setVisibility(View.VISIBLE);

                    tvMonthyearTag.setVisibility(View.GONE);
                    tvMonthyear.setVisibility(View.GONE);

                } else if (reportTypeWMselectedModels.get(position).getReporttype().trim().equals("Monthly")) {

                    tvMonthyearTag.setVisibility(View.VISIBLE);
                    tvMonthyear.setVisibility(View.VISIBLE);

                    tvStartDateTag.setVisibility(View.GONE);
                    tvEndDateTag.setVisibility(View.GONE);
                    tvStartDate.setVisibility(View.GONE);
                    tvEndDate.setVisibility(View.GONE);
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


        tvMonthyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
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
                    }
                }, mYear, mMonth, mDay){
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                    }
                };
                monthDatePickerDialog.setTitle("Select month And Year");
                monthDatePickerDialog.show();
            }

        });


    }
}