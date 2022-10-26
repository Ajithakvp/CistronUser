package com.example.cistronuser.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cistronuser.R;

import java.util.Calendar;
import java.util.Date;

public class ExpensesActivity extends Activity {

    ImageView ivBack;

    RelativeLayout rlUpload,rlUploadTicket,rlUploadother,rlUploadLodging;
TextView tvDate;
EditText edWorkReport,edConveyance,tvTicket,tvLodging,edOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);



        ivBack=findViewById(R.id.ivBack);
        rlUploadother=findViewById(R.id.rlUploadother);
        rlUpload=findViewById(R.id.rlUpload);
        rlUploadTicket=findViewById(R.id.rlUploadTicket);
        rlUploadLodging=findViewById(R.id.rlUploadLodging);
        tvDate=findViewById(R.id.tvDate);
        edWorkReport=findViewById(R.id.edWorkReport);
        edConveyance=findViewById(R.id.edConveyance);
        tvTicket=findViewById(R.id.tvTicket);
        tvLodging=findViewById(R.id.tvLodging);
        edOther=findViewById(R.id.edOther);




        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        Date d = new Date();
        CharSequence s  = DateFormat.format("d /MM/yyyy ", d.getTime());
        tvDate.setText(s);


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallDate();
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rlUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                }
                intent.setType("*/*");
               startActivity(intent);

            }
        });


        rlUploadTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                }
                intent.setType("*/*");
                startActivity(intent);

            }
        });


        rlUploadother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                }
                intent.setType("*/*");
                startActivity(intent);

            }
        });


        rlUploadLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                }
                intent.setType("*/*");
                startActivity(intent);

            }
        });
    }

    private void CallDate() {
        Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH);
       int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
       DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}