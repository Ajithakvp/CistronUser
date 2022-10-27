package com.example.cistronuser.Activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpensesActivity extends Activity {


    //Internet
    BroadcastReceiver broadcastReceiver;

    private static final int PERMISSION_REQUEST_CODE = 1;
    File file;
    ImageView ivBack;

    RelativeLayout rlUpload, rlUploadTicket, rlUploadother, rlUploadLodging;
    TextView tvDate, tvConveyanceDoc, tvTicketDoc, tvLodgingDoc, tvOtherDoc;
    EditText edWorkReport, edConveyance, tvTicket, tvLodging, edOther;


    String strConvenyance,strtickDoc,strLodgingDoc,strotherDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        ivBack = findViewById(R.id.ivBack);
        rlUploadother = findViewById(R.id.rlUploadother);
        rlUpload = findViewById(R.id.rlUpload);
        rlUploadTicket = findViewById(R.id.rlUploadTicket);
        rlUploadLodging = findViewById(R.id.rlUploadLodging);
        tvDate = findViewById(R.id.tvDate);
        edWorkReport = findViewById(R.id.edWorkReport);
        edConveyance = findViewById(R.id.edConveyance);
        tvTicket = findViewById(R.id.tvTicket);
        tvLodging = findViewById(R.id.tvLodging);
        edOther = findViewById(R.id.edOther);
        tvConveyanceDoc = findViewById(R.id.tvConveyanceDoc);
        tvTicketDoc = findViewById(R.id.tvTicketDoc);
        tvLodgingDoc = findViewById(R.id.tvLodgingDoc);
        tvOtherDoc = findViewById(R.id.tvOtherDoc);


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));



        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        Date d = new Date();
        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
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
                try {
                   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
                } catch (Exception e) {

                }
                }
        });


        rlUploadTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  Intent  intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 2);
                } catch (Exception e) {

                }
            }
        });


        rlUploadother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 3);
                } catch (Exception e) {

                }
            }
        });


        rlUploadLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 4);
                } catch (Exception e) {

                }


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
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strConvenyance = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name"+ strConvenyance, Toast.LENGTH_SHORT).show();

                    try {
                        if (strConvenyance.length() > 0) {
                            tvConveyanceDoc.setText(strConvenyance);
                        }

                    } catch (Exception e) {

                    }


                    try {
                        file = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;



            case 2:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strtickDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name"+ strtickDoc, Toast.LENGTH_SHORT).show();

                    try {
                        if (strtickDoc.length() > 0) {
                            tvTicketDoc.setText(strtickDoc);

                        }

                    } catch (Exception e) {

                    }


                    try {
                        file = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;


            case 4:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strLodgingDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name"+ strLodgingDoc, Toast.LENGTH_SHORT).show();

                    try {
                        if (strLodgingDoc.length() > 0) {
                            tvLodgingDoc.setText(strLodgingDoc);

                        }

                    } catch (Exception e) {

                    }


                    try {
                        file = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;




            case 3:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strotherDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name"+ strotherDoc, Toast.LENGTH_SHORT).show();

                    try {
                        if (strotherDoc.length() > 0) {
                            tvOtherDoc.setText(strotherDoc);

                        }

                    } catch (Exception e) {

                    }


                    try {
                        file = FileUtli.from(this, contentUri);
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



    protected void unregBroadcast() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
        unregBroadcast();
    }
}