package com.example.cistronuser.Report.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.VisitEnteyReportManagerListInterface;
import com.example.cistronuser.API.Interface.VisitEntryReportListInterface;
import com.example.cistronuser.API.Interface.VisitEntryReportUserIListnterface;
import com.example.cistronuser.API.Model.VisitEnteyReportManagerListModel;
import com.example.cistronuser.API.Model.VisitEntryReportListModel;
import com.example.cistronuser.API.Model.VisitEntryReportUserIListModel;
import com.example.cistronuser.API.Response.VisitEnteyReportManagerListResponse;
import com.example.cistronuser.API.Response.VisitEntryReportListResponse;
import com.example.cistronuser.API.Response.VisitEntryReportUserIListResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Adapter.VisitEntryReportAdapter;
import com.example.cistronuser.SalesAndservice.Activity.VisitEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitEntryReport extends AppCompatActivity {

    TextView tvStartDate,tvEndDate,tvSubmit,tvVisit,tvQuote,tvOA;
    RelativeLayout rlfilter,rlNo,rlData;
    Spinner spUser,spManager;
    ImageView ivBack,ivfilter;



    //ManagerList
    ArrayList<VisitEnteyReportManagerListModel>visitEnteyReportManagerListModels=new ArrayList<>();
    ArrayList<String>strManager=new ArrayList<>();
    ArrayAdapter managerAdapter;
    String ManagerID;


    //UserList
    ArrayList<VisitEntryReportUserIListModel>visitEntryReportUserIListModels=new ArrayList<>();
    ArrayList<String>strUserList=new ArrayList<>();
    ArrayAdapter UserAdapter;
    String UserID;

    //Recycleview
    RecyclerView rvVisitEntryReportList;
    VisitEntryReportAdapter visitEntryReportAdapter;
    ArrayList<VisitEntryReportListModel>visitEntryReportListModels=new ArrayList<>();
    String user_category, empid;
    TextView tvNodata;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_entry_report);

        spManager=findViewById(R.id.spManager);
        spUser=findViewById(R.id.spUser);
        tvStartDate=findViewById(R.id.tvStartDate);
        tvEndDate=findViewById(R.id.tvEndDate);
        tvSubmit=findViewById(R.id.tvSubmit);
        ivfilter=findViewById(R.id.ivfilter);
        ivBack=findViewById(R.id.ivBack);
        rlfilter=findViewById(R.id.rlfilter);
        rvVisitEntryReportList=findViewById(R.id.rvVisitEntryReportList);
        tvNodata=findViewById(R.id.tvNodata);
        tvOA=findViewById(R.id.tvOA);
        tvVisit=findViewById(R.id.tvVisit);
        tvQuote=findViewById(R.id.tvQuote);
        rlNo=findViewById(R.id.rlNo);
        rlData=findViewById(R.id.rlData);

        visitEntryReportAdapter=new VisitEntryReportAdapter(this,visitEntryReportListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvVisitEntryReportList.setAdapter(visitEntryReportAdapter);
        rvVisitEntryReportList.setLayoutManager(linearLayoutManager);











        Date Start = new Date();
        CharSequence SDate = DateFormat.format("yyyy-MM-dd ", Start.getTime());
        tvStartDate.setText(SDate);

        Date End = new Date();
        CharSequence EDate = DateFormat.format("yyyy-MM-dd ", End.getTime());
        tvEndDate.setText(EDate);




        //ManagerList
        CallManagerList();
        managerAdapter=new ArrayAdapter(this,R.layout.spinner_item,strManager);
        managerAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spManager.setAdapter(managerAdapter);
        spManager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);

                ManagerID=visitEnteyReportManagerListModels.get(position).getEmpid();

                spUser.setSelection(0);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //UserList
        UserAdapter=new ArrayAdapter(this,R.layout.spinner_item,strUserList);
        UserAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spUser.setAdapter(UserAdapter);
        spUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);

                UserID=visitEntryReportUserIListModels.get(position).getEmpid();
                spManager.setSelection(0);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNodata.setVisibility(View.GONE);
                rlData.setVisibility(View.GONE);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(VisitEntryReport.this, new DatePickerDialog.OnDateSetListener() {
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(VisitEntryReport.this, new DatePickerDialog.OnDateSetListener() {
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




                datePickerDialog.show();

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (spUser.getSelectedItemPosition()>0){
                    user_category="employee";
                    empid=UserID;
                }else if (spManager.getSelectedItemPosition()>0){
                    user_category="manager";
                    empid=ManagerID;
                }else {
                    user_category="all";
                    empid="all";
                }

                CallVisitEntryReportList();
                Log.e(TAG, "Empid: "+empid+" : category : "+user_category );
            }
        });

        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlfilter.setVisibility(View.VISIBLE);
            }
        });





    }



    private void CallVisitEntryReportList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        VisitEntryReportListInterface visitEntryReportListInterface=APIClient.getClient().create(VisitEntryReportListInterface.class);
        visitEntryReportListInterface.CallVisitEntryReportList("visitReportForAdmin",empid,tvStartDate.getText().toString(),tvEndDate.getText().toString(),user_category).enqueue(new Callback<VisitEntryReportListResponse>() {
            @Override
            public void onResponse(Call<VisitEntryReportListResponse> call, Response<VisitEntryReportListResponse> response) {

                try {
                    if (response.isSuccessful()){

                        if (response.body().getVisitEntryReportListModels().size()>0) {
                            rlfilter.setVisibility(View.GONE);
                            rlNo.setVisibility(View.VISIBLE);
                            rlData.setVisibility(View.VISIBLE);
                            tvVisit.setText("No of Visits - "+response.body().getVisit());
                            tvQuote.setText("No of Quotes - "+response.body().getQuote());
                            tvOA.setText("No of OA's - "+response.body().getOa());
                            visitEntryReportAdapter.visitEntryReportListModels = response.body().getVisitEntryReportListModels();
                            visitEntryReportAdapter.notifyDataSetChanged();
                            tvNodata.setVisibility(View.GONE);
                            progressDialog.dismiss();
                        }else {
                            tvNodata.setVisibility(View.VISIBLE);
                            rlData.setVisibility(View.GONE);
                            progressDialog.dismiss();
                        }

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<VisitEntryReportListResponse> call, Throwable t) {

            }
        });
    }

    private void CallUserList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryReportUserIListnterface visitEntryReportUserIListnterface=APIClient.getClient().create(VisitEntryReportUserIListnterface.class);
        visitEntryReportUserIListnterface.CallUserList("getEmpWithVisitEntry",tvStartDate.getText().toString(),tvEndDate.getText().toString()).enqueue(new Callback<VisitEntryReportUserIListResponse>() {
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

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<VisitEntryReportUserIListResponse> call, Throwable t) {

            }
        });

    }

    private void CallManagerList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEnteyReportManagerListInterface visitEnteyReportManagerListInterface= APIClient.getClient().create(VisitEnteyReportManagerListInterface.class);
        visitEnteyReportManagerListInterface.CallManagerList("getManagersFilter").enqueue(new Callback<VisitEnteyReportManagerListResponse>() {
            @Override
            public void onResponse(Call<VisitEnteyReportManagerListResponse> call, Response<VisitEnteyReportManagerListResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getVisitEnteyReportManagerListModels().size() > 0) {
                            visitEnteyReportManagerListModels = response.body().getVisitEnteyReportManagerListModels();
                            strManager.clear();
                            for (int i = 0; i < visitEnteyReportManagerListModels.size(); i++) {
                                strManager.add(visitEnteyReportManagerListModels.get(i).getName());
                            }
                            managerAdapter.notifyDataSetChanged();

                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<VisitEnteyReportManagerListResponse> call, Throwable t) {

            }
        });
    }
}