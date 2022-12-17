package com.example.cistronuser.SalesAndservice.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FinalizeNow extends AppCompatActivity {


    File file;
    String filename;
    CheckBox cbDeposited,cbSameAddress;
    TextView tvDepositedDate, tvPODate, tvAttach, tvAddress, tvQty, tvDeliveySchedule, tvSubmit;
    EditText edPOR, edName, edMobile, edQus, edOrderValue, edAdvanceValue, edpaymentBforeDispatch, edpaymentaterDispatch, edpaymentOnInstalltion,
            edRemarkPayment, edWarrenty, edSpecialRemark;
    Spinner spPerson;
    LinearLayout layout_list;
    ImageView ivAdd;

    //AddEditText
    EditText edAmount;
    ImageView ivRemove;

    //ContactPerson
    ArrayAdapter personContact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_now);

        cbDeposited = findViewById(R.id.cbDeposited);
        tvDepositedDate = findViewById(R.id.tvDepositedDate);
        tvPODate = findViewById(R.id.tvPODate);
        tvAttach = findViewById(R.id.tvAttach);
        tvAddress = findViewById(R.id.tvAddress);
        tvQty = findViewById(R.id.tvQty);
        edPOR = findViewById(R.id.edPOR);
        edName = findViewById(R.id.edName);
        edMobile = findViewById(R.id.edMobile);
        edQus = findViewById(R.id.edQus);
        edOrderValue = findViewById(R.id.edOrderValue);
        edAdvanceValue = findViewById(R.id.edAdvanceValue);
        edpaymentBforeDispatch = findViewById(R.id.edpaymentBforeDispatch);
        edpaymentaterDispatch = findViewById(R.id.edpaymentaterDispatch);
        edpaymentOnInstalltion = findViewById(R.id.edpaymentOnInstalltion);
        edRemarkPayment = findViewById(R.id.edRemarkPayment);
        edWarrenty = findViewById(R.id.edWarrenty);
        edSpecialRemark = findViewById(R.id.edSpecialRemark);
        spPerson = findViewById(R.id.spPerson);
        layout_list = findViewById(R.id.layout_list);
        ivAdd = findViewById(R.id.ivAdd);
        tvDeliveySchedule = findViewById(R.id.tvDeliveySchedule);
        tvSubmit = findViewById(R.id.tvSubmit);
        cbSameAddress=findViewById(R.id.cbSameAddress);


        //File Access Permission
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        //Contact Person

        String[] contact = {"Dr", "Mr", "Ms", "Mrs"};
        personContact = new ArrayAdapter(this, R.layout.spinner_item, contact);
        personContact.setDropDownViewResource(R.layout.spinner_dropdown);
        spPerson.setAdapter(personContact);


        cbDeposited.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvDepositedDate.setVisibility(View.VISIBLE);
                } else {
                    tvDepositedDate.setVisibility(View.GONE);
                }
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEditText();
            }
        });
        tvDepositedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(FinalizeNow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDeliveySchedule.setText(strDate);

                    }

                }, year, month, dayOfMonth);

//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();
            }
        });

        tvPODate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(FinalizeNow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvPODate.setText(strDate);

                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();
            }
        });
        tvDeliveySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(FinalizeNow.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDeliveySchedule.setText(strDate);

                    }

                }, year, month, dayOfMonth);

//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();
            }
        });

        tvAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] mimeTypes = {"image/*", "application/pdf"};
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*|application/pdf");
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {

                }
            }
        });
        cbSameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(FinalizeNow.this, "Same Address", Toast.LENGTH_SHORT).show();
                }else {
                    Dialog dialog=new Dialog(FinalizeNow.this);
                    dialog.setContentView(R.layout.another_address_dialog);
                    ImageView ivClose=dialog.findViewById(R.id.ivClose);
                    dialog.show();

                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

    }

    private void AddEditText() {

        final View addEditText = getLayoutInflater().inflate(R.layout.row_add_amount_finalize, null, false);
        edAmount = addEditText.findViewById(R.id.edAmount);
        ivRemove = addEditText.findViewById(R.id.ivRemove);


        ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_list.removeView(addEditText);
            }
        });


        layout_list.addView(addEditText);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    filename = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + filename, Toast.LENGTH_SHORT).show();
                    tvAttach.setVisibility(View.VISIBLE);

                    try {
                        if (filename.length() > 0) {
                            tvAttach.setText(filename);
                        }

                    } catch (Exception e) {

                    }

                }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }
}