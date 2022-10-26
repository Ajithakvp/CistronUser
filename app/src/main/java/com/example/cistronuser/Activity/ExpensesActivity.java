package com.example.cistronuser.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.Common.Filepath;
import com.example.cistronuser.R;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class ExpensesActivity extends Activity {



    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int ALL_FILE_REQUEST = 102;
    ImageView ivBack;

    RelativeLayout rlUpload,rlUploadTicket,rlUploadother,rlUploadLodging;
TextView tvDate,tvConveyanceDoc,tvTicketDoc,tvLodgingDoc,tvOtherDoc;
EditText edWorkReport,edConveyance,tvTicket,tvLodging,edOther;


String all_file_path;
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
        tvConveyanceDoc=findViewById(R.id.tvConveyanceDoc);
        tvTicketDoc=findViewById(R.id.tvTicketDoc);
        tvLodgingDoc=findViewById(R.id.tvLodgingDoc);
        tvOtherDoc=findViewById(R.id.tvOtherDoc);




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
                    intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
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
                    intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
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
                    intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
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
                    intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Successfull", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ALL_FILE_REQUEST) {
                if (data == null) {
                    return;
                }

                Uri uri = data.getData();
                String paths = Filepath.getFilePath(this, uri);
                Log.d("File Path : ", "" + paths);
                if (paths != null) {
                    tvConveyanceDoc.setText("" + new File(paths).getName());
                }
                all_file_path = paths;
            }
        }

    }
}