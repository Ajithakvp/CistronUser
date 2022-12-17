package com.example.cistronuser.SalesAndservice.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static okhttp3.RequestBody.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SalesQuoteContactPersonInterface;
import com.example.cistronuser.API.Interface.SalesQuotesUpdateFinalizeInteface;
import com.example.cistronuser.API.Model.EditTextModel;
import com.example.cistronuser.API.Response.SalesQuoteContactPersonResponse;
import com.example.cistronuser.API.Response.SalesQuotesUpdateFinalizeResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalizeNow extends AppCompatActivity {


    File filePdf;
    String filename;
    CheckBox cbDeposited, cbSameAddress;
    TextView tvProduct, tvDepositedDate, tvPODate, tvDeliveryAddress, tvAttach, tvAddress, tvQty, tvDeliveySchedule, tvSubmit;
    EditText edPOR, edName, edMobile, edQus, edOrderValue, edAdvanceValue, edpaymentBforeDispatch, edpaymentaterDispatch, edpaymentOnInstalltion,
            edRemarkPayment, edWarrenty, edSpecialRemark;
    Spinner spPerson;
    LinearLayout layout_list;
    ImageView ivAdd, ivFileVIew;

    //AddEditText
    EditText edAmount;
    ImageView ivRemove;

    //ContactPerson
    ArrayAdapter personContact;
    String PersonId;
    String QuotePDF;
    String Product;
    String quoteID;


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
        cbSameAddress = findViewById(R.id.cbSameAddress);
        ImageView ivBack = findViewById(R.id.ivBack);
        ivFileVIew = findViewById(R.id.ivFileVIew);
        tvDeliveryAddress = findViewById(R.id.tvDeliveryAddress);
        tvProduct = findViewById(R.id.tvProduct);


        //File Access Permission
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        //*********Contact person ********//
        String QuoteID = getIntent().getStringExtra("QuoteID");
        SalesQuoteContactPersonInterface contactPersonInterface = APIClient.getClient().create(SalesQuoteContactPersonInterface.class);
        contactPersonInterface.callContact("viewSalesQuote", QuoteID).enqueue(new Callback<SalesQuoteContactPersonResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteContactPersonResponse> call, Response<SalesQuoteContactPersonResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        QuotePDF = response.body().getQuote_pdf();
                        Product = response.body().getProduct();
                        Log.e(TAG, "onResponse: "+Product );
                        quoteID = response.body().getQuoteId();
                        String per=response.body().getCon_prefix();
                        spPerson.setSelection(Integer.parseInt(per));
                        edName.setText(response.body().getCon_person());
                        edMobile.setText(response.body().getMobile());
                        tvProduct.setText(Product);
                        tvAddress.setText(response.body().getAddress());


                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteContactPersonResponse> call, Throwable t) {

            }
        });


        //GetString
        tvProduct.setText(Product);

        //Contact Person

        String[] contact = {"Dr", "Mr", "Ms", "Mrs"};
        personContact = new ArrayAdapter(this, R.layout.spinner_item, contact);
        personContact.setDropDownViewResource(R.layout.spinner_dropdown);
        spPerson.setAdapter(personContact);
        spPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PersonId = spPerson.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

        ivFileVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(QuotePDF);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
                        tvDepositedDate.setText(strDate);

                    }

                }, year, month, dayOfMonth);

//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
                } catch (Exception e) {

                }
            }
        });
        cbSameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(FinalizeNow.this, "Same Address", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog dialog = new Dialog(FinalizeNow.this);
                    dialog.setContentView(R.layout.another_address_dialog);
                    dialog.show();

                }
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filePdf == null) {
                    Toast.makeText(FinalizeNow.this, "Please Upload File", Toast.LENGTH_SHORT).show();
                } else {
                    int sameAddr = cbSameAddress.isChecked() ? 1 : 0;
                    int Depos = cbDeposited.isChecked() ? 1 : 0;


                    RequestBody Action = create(MediaType.parse("text/plain"), "finalSubmit");
                    RequestBody EmpId = create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(FinalizeNow.this));
                    RequestBody QuoteId = create(MediaType.parse("text/plain"), quoteID);
                    RequestBody PoRef = create(MediaType.parse("text/plain"), edPOR.getText().toString());
                    RequestBody PoDate = create(MediaType.parse("text/plain"), tvPODate.getText().toString());
                    RequestBody Billing_opt = create(MediaType.parse("text/plain"), String.valueOf(sameAddr));
                    RequestBody BillingAddress = create(MediaType.parse("text/plain"), tvAddress.getText().toString());
                    RequestBody BillingAnother = create(MediaType.parse("text/plain"), tvDeliveryAddress.getText().toString());
                    RequestBody Con_pre = create(MediaType.parse("text/plain"), PersonId);
                    RequestBody Con_name = create(MediaType.parse("text/plain"), edName.getText().toString());
                    RequestBody Con_no = create(MediaType.parse("text/plain"), edMobile.getText().toString());
                    RequestBody Pro_spec = create(MediaType.parse("text/plain"), edQus.getText().toString());
                    RequestBody Order_value = create(MediaType.parse("text/plain"), edOrderValue.getText().toString());
                    RequestBody Advance_value = create(MediaType.parse("text/plain"), edAdvanceValue.getText().toString());
                    RequestBody Deposit = create(MediaType.parse("text/plain"), String.valueOf(Depos));
                    RequestBody Deposit_date = create(MediaType.parse("text/plain"), tvDepositedDate.getText().toString());
                    RequestBody Pay_bef_dispatch = create(MediaType.parse("text/plain"), edpaymentBforeDispatch.getText().toString());
                    RequestBody Pay_aft_dispatch = create(MediaType.parse("text/plain"), edpaymentaterDispatch.getText().toString());
                    RequestBody Pay_on_install = create(MediaType.parse("text/plain"), edpaymentOnInstalltion.getText().toString());
                    RequestBody Installments = create(MediaType.parse("text/plain"), edAmount.getText().toString());
                    RequestBody Install_terms = create(MediaType.parse("text/plain"), edRemarkPayment.getText().toString());
                    RequestBody Spl_remarks = create(MediaType.parse("text/plain"), edSpecialRemark.getText().toString());
                    RequestBody Warranty = create(MediaType.parse("text/plain"), edWarrenty.getText().toString());
                    RequestBody Delivery_date = create(MediaType.parse("text/plain"), tvDeliveySchedule.getText().toString());
                    RequestBody requestFile = create(MediaType.parse("multipart/form-data"), filePdf);
                    MultipartBody.Part POPDF = MultipartBody.Part.createFormData("poPdf", filePdf.getName(), requestFile);

                    SalesQuotesUpdateFinalizeInteface salesQuotesUpdateFinalizeInteface = APIClient.getClient().create(SalesQuotesUpdateFinalizeInteface.class);
                    salesQuotesUpdateFinalizeInteface.callFinalSubmit(Action, EmpId, QuoteId, PoRef, PoDate, Billing_opt, BillingAddress, BillingAnother, Con_pre, Con_name, Con_no, Pro_spec, Order_value, Advance_value, Deposit, Deposit_date, Pay_bef_dispatch, Pay_aft_dispatch, Pay_on_install, Installments, Install_terms, Spl_remarks, Warranty, Delivery_date, POPDF).enqueue(new Callback<SalesQuotesUpdateFinalizeResponse>() {
                        @Override
                        public void onResponse(Call<SalesQuotesUpdateFinalizeResponse> call, Response<SalesQuotesUpdateFinalizeResponse> response) {
                            try {
                                if (response.isSuccessful()) {
                                    Toast.makeText(FinalizeNow.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onFailure(Call<SalesQuotesUpdateFinalizeResponse> call, Throwable t) {

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

                    try {
                        filePdf = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
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