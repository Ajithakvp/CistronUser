package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpcomingCallReport extends AppCompatActivity {

    Spinner spCallType,spCallStatus;
    TextView tvCusInvoice,tvFollowUpDate,tvStartingTime,tvEndTime,tvserviceReportAttach,tvSubmit;
    EditText edName,edMobile,edWorkdone,edEngineerAdvice,edReason,edPendingReason;
    RadioGroup rbGrp;
    TextInputLayout tvReason,tvPendingReason;
    RadioButton rbYes,rbNo;
    CheckBox cbAttach;
    RatingBar ratingBar;

    String strRating,strSerAttach,strCusInvoiceAttach;
    File fileservice,fileinvoice;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_call_report);


        spCallType=findViewById(R.id.spCallType);
        spCallStatus=findViewById(R.id.spCallStatus);
        tvCusInvoice=findViewById(R.id.tvCusInvoice);
        tvFollowUpDate=findViewById(R.id.tvFollowUpDate);
        tvStartingTime=findViewById(R.id.tvStartingTime);
        tvEndTime=findViewById(R.id.tvEndTime);
        tvserviceReportAttach=findViewById(R.id.tvserviceReportAttach);
        tvReason=findViewById(R.id.tvReason);
        edName=findViewById(R.id.edName);
        edMobile=findViewById(R.id.edMobile);
        edWorkdone=findViewById(R.id.edWorkdone);
        edEngineerAdvice=findViewById(R.id.edEngineerAdvice);
        edReason=findViewById(R.id.edReason);
        rbGrp=findViewById(R.id.rbGrp);
        rbYes=findViewById(R.id.rbYes);
        edPendingReason=findViewById(R.id.edPendingReason);
        rbNo=findViewById(R.id.rbNo);
        cbAttach=findViewById(R.id.cbAttach);
        ratingBar=findViewById(R.id.ratingBar);
        tvSubmit=findViewById(R.id.tvSubmit);




        // ************ File Access Permission ***********//
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
        // ************ File Access Permission End ***********//


        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvserviceReportAttach.setVisibility(View.VISIBLE);
                    tvReason.setVisibility(View.GONE);
                }else {
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