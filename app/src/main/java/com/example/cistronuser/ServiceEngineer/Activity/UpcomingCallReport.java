package com.example.cistronuser.ServiceEngineer.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CallReportComplaintSubCategoryInterface;
import com.example.cistronuser.API.Interface.UpcomingCallReportInterface;
import com.example.cistronuser.API.Model.CallReportComplaintSubCategoryModel;
import com.example.cistronuser.API.Model.CallStatusModel;
import com.example.cistronuser.API.Model.CallTypeModel;
import com.example.cistronuser.API.Model.ComplaintCategoryModel;
import com.example.cistronuser.API.Response.CallReportComplaintSubCategoryResponse;
import com.example.cistronuser.API.Response.UpcomingCallReportResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Activity.FinalizeNow;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingCallReport extends AppCompatActivity  {

    ImageView ivBack;
    Spinner spCallType, spCallStatus;
    TextView tvCusInvoice, tvFollowUpDate, tvStartingTime, tvEndTime, tvserviceReportAttach, tvSubmit;
    EditText edName, edMobile, edWorkdone, edEngineerAdvice, edReason, edPendingReason;
    RadioGroup rbGrp;
    TextInputLayout tvReason, tvPendingReason;
    RadioButton rbYes, rbNo;
    CheckBox cbAttach;
    RatingBar ratingBar;

    String strRating, strSerAttach, strCusInvoiceAttach;
    File fileservice, fileinvoice;

    //Customer Details
    TextView tvCusDetails, tvProdDetails, tvProdSerial, tvCreated, tvReportby;

    //Complaint & subComplaint
    RelativeLayout rlComplaint;
    Spinner spComplaint, spSubComplaint;
    ArrayList<ComplaintCategoryModel> complaintCategoryModels = new ArrayList<>();
    ArrayList<CallReportComplaintSubCategoryModel> callReportComplaintSubCategoryModels = new ArrayList<>();
    ArrayList<String> strSubCom = new ArrayList<>();
    ArrayList<String> strComplaint = new ArrayList<>();
    ArrayAdapter complaintAdapter, subComplaintAdapter;


    //CallType
    ArrayList<CallTypeModel> callTypeModels = new ArrayList<>();
    ArrayList<String> strType = new ArrayList<>();
    ArrayAdapter callTypeAdapter;
    String selected;

    //Call Status
    ArrayList<CallStatusModel> callStatusModels = new ArrayList<>();
    ArrayList<String> strStatus = new ArrayList<>();
    ArrayAdapter callStatusAdapter;

    TimePicker timePicker;
    boolean mIgnoreEvent=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_call_report);

        ivBack=findViewById(R.id.ivBack);
        spCallType = findViewById(R.id.spCallType);
        spCallStatus = findViewById(R.id.spCallStatus);
        tvCusInvoice = findViewById(R.id.tvCusInvoice);
        tvFollowUpDate = findViewById(R.id.tvFollowUpDate);
        tvStartingTime = findViewById(R.id.tvStartingTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvserviceReportAttach = findViewById(R.id.tvserviceReportAttach);
        tvReason = findViewById(R.id.tvReason);
        edName = findViewById(R.id.edName);
        edMobile = findViewById(R.id.edMobile);
        edWorkdone = findViewById(R.id.edWorkdone);
        edEngineerAdvice = findViewById(R.id.edEngineerAdvice);
        edReason = findViewById(R.id.edReason);
        rbGrp = findViewById(R.id.rbGrp);
        rbYes = findViewById(R.id.rbYes);
        tvPendingReason = findViewById(R.id.tvPendingReason);
        edPendingReason = findViewById(R.id.edPendingReason);
        rbNo = findViewById(R.id.rbNo);
        cbAttach = findViewById(R.id.cbAttach);
        ratingBar = findViewById(R.id.ratingBar);
        tvSubmit = findViewById(R.id.tvSubmit);
        tvCusDetails = findViewById(R.id.tvCusDetails);
        tvProdDetails = findViewById(R.id.tvProdDetails);
        tvProdSerial = findViewById(R.id.tvProdSerial);
        tvCreated = findViewById(R.id.tvCreated);
        tvReportby = findViewById(R.id.tvReportby);
        rlComplaint = findViewById(R.id.rlComplaint);
        spComplaint = findViewById(R.id.spComplaint);
        spSubComplaint = findViewById(R.id.spSubComplaint);


        // ************ File Access Permission ***********//
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
        // ************ File Access Permission End ***********//

        // *********** GetString **********//
        String id = getIntent().getStringExtra("id");
        // *********** GetString End **********//

        //********Customer Detalils ******************//
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Call Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UpcomingCallReportInterface upcomingCallReportInterface = APIClient.getClient().create(UpcomingCallReportInterface.class);
        upcomingCallReportInterface.CallUpcomingCallReport("callReporting", id).enqueue(new Callback<UpcomingCallReportResponse>() {
            @Override
            public void onResponse(Call<UpcomingCallReportResponse> call, Response<UpcomingCallReportResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        tvCusDetails.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getCustDetail());
                        tvProdDetails.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getProdDetail());
                        tvProdSerial.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getProSerial());
                        tvCreated.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getCreatedBy());
                        tvReportby.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getReportBy());

                        if (response.body().getUpcomingCallReportModel().getCompliantRequired().trim().equals("1")) {
                            rlComplaint.setVisibility(View.VISIBLE);
                        } else {
                            rlComplaint.setVisibility(View.GONE);
                        }

                        // ***********  Call Type ******** //


                        callTypeModels = response.body().getUpcomingCallReportModel().getCallTypeModels();
                        for (int i = 0; i < callTypeModels.size(); i++) {
                            strType.add(callTypeModels.get(i).getText());
                            selected = String.valueOf(callTypeModels.get(i).getSelected().trim().equals("1"));
                            if (callTypeModels.get(i).getSelected().trim().equals("1")) {
                                spCallType.setSelection(i);
                            }
                        }

                        callTypeAdapter.notifyDataSetChanged();
                        // ***********  Call Type End ******** //


                        // ***********  Call Status ******** //

                        callStatusModels = response.body().getUpcomingCallReportModel().getCallStatusModels();
                        for (int i = 0; i < callStatusModels.size(); i++) {
                            strStatus.add(callStatusModels.get(i).getText());
                        }
                        callStatusAdapter.notifyDataSetChanged();
                        // ***********  Call Status End ******** //

                        // ***********  Complaint Category  ******** //

                        complaintCategoryModels = response.body().getUpcomingCallReportModel().getComplaintCategoryModels();
                        for (int i = 0; i < complaintCategoryModels.size(); i++) {
                            strComplaint.add(complaintCategoryModels.get(i).getText());
                        }
                        complaintAdapter.notifyDataSetChanged();
                        // ***********  Complaint Category End  ******** //
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<UpcomingCallReportResponse> call, Throwable t) {

            }
        });
        //********Customer Detalils End ******************//


        //Complaint Details

        complaintAdapter = new ArrayAdapter(this, R.layout.spinner_item, strComplaint);
        complaintAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spComplaint.setAdapter(complaintAdapter);
        spComplaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ComplaintID = complaintCategoryModels.get(position).getId();
                CallSubComplaint(ComplaintID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CallType
        callTypeAdapter = new ArrayAdapter(this, R.layout.spinner_item, strType);
        callTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCallType.setAdapter(callTypeAdapter);
        spCallType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Call Status
        callStatusAdapter = new ArrayAdapter(this, R.layout.spinner_item, strStatus);
        callStatusAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCallStatus.setAdapter(callStatusAdapter);
        spCallStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (callStatusModels.get(position).getText().trim().equals("Pending")){
                    tvPendingReason.setVisibility(View.VISIBLE);
                    tvFollowUpDate.setVisibility(View.VISIBLE);
                }else {
                    tvPendingReason.setVisibility(View.GONE);
                    tvFollowUpDate.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SubComplaint
        subComplaintAdapter = new ArrayAdapter(this, R.layout.spinner_item, strSubCom);
        subComplaintAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spSubComplaint.setAdapter(subComplaintAdapter);
        spSubComplaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvserviceReportAttach.setVisibility(View.VISIBLE);
                    tvReason.setVisibility(View.GONE);
                } else {
                    tvReason.setVisibility(View.VISIBLE);
                    tvserviceReportAttach.setVisibility(View.GONE);
                }
            }
        });


        tvCusInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
                } catch (Exception e) {

                }
            }
        });

        tvserviceReportAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 2);
                } catch (Exception e) {

                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

     tvFollowUpDate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Calendar calendar = Calendar.getInstance();
             int year = calendar.get(Calendar.YEAR);
             int month = calendar.get(Calendar.MONTH);
             int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
             calendar.add(Calendar.DATE,1);
             DatePickerDialog datePickerDialog = new DatePickerDialog(UpcomingCallReport.this, new DatePickerDialog.OnDateSetListener() {
                 @Override
                 public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                     String moth, dt;

                     moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                     dt = (day > 9) ? "" + day : ("0" + day);


                     String strDate = year + "-" + moth + "-" +dt;
                     tvFollowUpDate.setText(strDate);

                 }

             }, year, month, dayOfMonth);

               datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


             datePickerDialog.show();



         }
     });

     tvStartingTime.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             final Calendar c = Calendar.getInstance();
             int mHour = c.get(Calendar.HOUR_OF_DAY);
             int mMinute = c.get(Calendar.MINUTE);

             // Launch Time Picker Dialog
             TimePickerDialog timePickerDialog = new TimePickerDialog(UpcomingCallReport.this,
                     new TimePickerDialog.OnTimeSetListener() {

                         @Override
                         public void onTimeSet(TimePicker view, int hourOfDay,
                                               int minute) {

                             tvStartingTime.setText(hourOfDay + ":" + minute);
                         }
                     }, mHour, mMinute, false);
             timePickerDialog.show();
         }
     });


        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpcomingCallReport.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                tvEndTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

    }


    private void CallSubComplaint(String complaintID) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        CallReportComplaintSubCategoryInterface callReportComplaintSubCategoryInterface = APIClient.getClient().create(CallReportComplaintSubCategoryInterface.class);
        callReportComplaintSubCategoryInterface.CallSubCat("getCompliantSubcategory", complaintID).enqueue(new Callback<CallReportComplaintSubCategoryResponse>() {
            @Override
            public void onResponse(Call<CallReportComplaintSubCategoryResponse> call, Response<CallReportComplaintSubCategoryResponse> response) {
                try {
                    if (response.body().getCallReportComplaintSubCategoryModels().size() > 0) {
                        progressDialog.dismiss();
                        callReportComplaintSubCategoryModels = response.body().getCallReportComplaintSubCategoryModels();
                        strSubCom.clear();
                        for (int i = 0; i < callReportComplaintSubCategoryModels.size(); i++) {
                            strSubCom.add(callReportComplaintSubCategoryModels.get(i).getText());
                        }
                        subComplaintAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CallReportComplaintSubCategoryResponse> call, Throwable t) {

            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strCusInvoiceAttach = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strCusInvoiceAttach, Toast.LENGTH_SHORT).show();

                    try {
                        if (strCusInvoiceAttach.length() > 0) {
//                            String myStr = strCusInvoiceAttach;
//                            int index=myStr.lastIndexOf(".");
//                            String extension = myStr.substring(index);
//                            System.out.println(extension);
//                            if(extension.equals(".pdf") || extension.equals(".jpeg")  || extension.equals(".png")){
                            tvCusInvoice.setText(strCusInvoiceAttach);
//                            }else{
//                                AlertDialog.Builder builder=new AlertDialog.Builder(this);
//                                builder.setMessage("Please Select Pdf and Image File Only ..");
//                                AlertDialog dialog=builder.create();
//                                dialog.show();
//                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileinvoice = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strSerAttach = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strSerAttach, Toast.LENGTH_SHORT).show();

                    try {
                        if (strSerAttach.length() > 0) {
//                            String myStr = strSerAttach;
//                            int index=myStr.lastIndexOf(".");
//                            String extension = myStr.substring(index);
//                            if(extension.equals(".pdf") || extension.equals(".jpeg")  || extension.equals(".png")){
                            tvserviceReportAttach.setText(strSerAttach);
//                            }else{
//                                AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.AlertDialogCustom);
//                                builder.setMessage("Please Select Pdf and Image File Only ..");
//                                AlertDialog dialog=builder.create();
//                                dialog.show();
//                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileservice = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }


}