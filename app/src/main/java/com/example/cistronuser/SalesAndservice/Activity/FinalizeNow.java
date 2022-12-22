package com.example.cistronuser.SalesAndservice.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static okhttp3.RequestBody.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import com.example.cistronuser.API.Interface.SalesQuotegetHospitalAddressInterface;
import com.example.cistronuser.API.Interface.SalesQuotesUpdateFinalizeInteface;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Interface.VisitEntryHospitalInterface;
import com.example.cistronuser.API.Interface.VisitEntryStateInterface;
import com.example.cistronuser.API.Model.ContactPrefixModel;
import com.example.cistronuser.API.Model.EditTextModel;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.example.cistronuser.API.Response.SalesQuoteContactPersonResponse;
import com.example.cistronuser.API.Response.SalesQuotegetHospitalAddressResponse;
import com.example.cistronuser.API.Response.SalesQuotesUpdateFinalizeResponse;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisityEntryStateResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalizeNow extends AppCompatActivity {

    ArrayList<EditText> installEdt;
    File filePdf;
    String filename;
    CheckBox cbDeposited, cbSameAddress;
    TextView tvProduct, tvDepositedDate, tvPODate, tvDeliveryAddress, tvAttach, tvAddress, tvQty, tvDeliveySchedule, tvSubmit;
    EditText edPOR, edName, edMobile, edQus, edOrderValue, edAdvanceValue, edpaymentBforeDispatch, edpaymentaterDispatch, edpaymentOnInstalltion,
            edRemarkPayment, edWarrenty, edSpecialRemark;
    Spinner spPerson;
    LinearLayout layout_list;
    ArrayList<String> strEditText = new ArrayList<>();
    ImageView ivAdd, ivFileVIew;

    //AddEditText
    EditText edAmount;
    ImageView ivRemove;

    String QuotePDF;
    String SubmitquoteID;

    //DialogDelivery
    Spinner spState, spDistrict, sphospital;
    TextView tvDeliveryAddressDialog, tvSubmitDialog;
    ImageView ivClose;
    String GeoData;
    String DeliveryID;


    ArrayList<VisitEntryGetDistrictModel> visitEntryGetDistrictModels = new ArrayList<>();
    ArrayList<String> strDistrict = new ArrayList<>();
    ArrayAdapter districtAdapter;


    //State
    ArrayList<VisitEntryStateModel> visitEntryStateModels = new ArrayList<>();
    ArrayList<String> strState = new ArrayList<>();
    ArrayAdapter stateAdapter;


    ArrayList<VisitEntryHospitalModel> visitEntryHospitalModels = new ArrayList<>();
    ArrayList<String> strHospital = new ArrayList<>();
    ArrayAdapter hospitalAdapter;
    String HospitalID;
    //ContactPrefix
    String sameAddress;
    ArrayAdapter personContact;
    String PersonId;
    String  prefix;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_now);

        installEdt = new ArrayList<>();
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


        //Contact Person Prefix
        String[] contact = {"Title","Dr. ", "Mr. ", "Ms. ", "Mrs. "};
        personContact = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, contact);
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

        //File Access Permission
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);




        //*********Contact person details ********//
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String QuoteID = getIntent().getStringExtra("QuoteID");
        SalesQuoteContactPersonInterface contactPersonInterface = APIClient.getClient().create(SalesQuoteContactPersonInterface.class);
        contactPersonInterface.callContact("viewSalesQuote", QuoteID).enqueue(new Callback<SalesQuoteContactPersonResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteContactPersonResponse> call, Response<SalesQuoteContactPersonResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        tvAddress.setText(response.body().getAddress());
                        sameAddress=response.body().getAddress();
                        tvDeliveryAddress.setText(response.body().getAddress());
                        tvProduct.setText(response.body().getProduct());
                        edName.setText(response.body().getCon_person());
                        edMobile.setText(response.body().getMobile());
                        QuotePDF = response.body().getQuote_pdf();
                        SubmitquoteID = response.body().getQuoteId();
                        edOrderValue.setText(response.body().getAmount());
                        prefix = response.body().getCon_prefix();
                        for (int i=0;i<personContact.getCount();i++){
                            if(personContact.getItem(i).toString().equals(prefix))
                                spPerson.setSelection(i);
                        }


                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteContactPersonResponse> call, Throwable t) {

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
                Log.e(TAG, "onClick: " + QuotePDF);
                Uri uri = Uri.parse(QuotePDF);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tvDepositedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDepositedDate.setError(null);
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
                tvPODate.setError(null);
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
                tvDeliveySchedule.setError(null);
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
                    tvDeliveryAddress.setText(sameAddress);
                    Toast.makeText(FinalizeNow.this, "Same Address", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog dialog = new Dialog(FinalizeNow.this);
                    dialog.setContentView(R.layout.another_address_dialog);
                    dialog.show();
                    spState = dialog.findViewById(R.id.spState);
                    spDistrict = dialog.findViewById(R.id.spDistrict);
                    sphospital = dialog.findViewById(R.id.sphospital);
                    tvDeliveryAddressDialog = dialog.findViewById(R.id.tvDeliveryAddressDialog);
                    ivClose = dialog.findViewById(R.id.ivClose);
                    tvSubmitDialog = dialog.findViewById(R.id.tvSubmitDialog);

                    //State
                    CallState();
                    stateAdapter = new ArrayAdapter(FinalizeNow.this, R.layout.spinner_item, strState);
                    stateAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                    spState.setAdapter(stateAdapter);
                    spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            callDistrict(spState.getSelectedItem().toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    //District
                    districtAdapter = new ArrayAdapter(FinalizeNow.this, R.layout.spinner_item, strDistrict);
                    districtAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                    spDistrict.setAdapter(districtAdapter);
                    spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            callHospital(spState.getSelectedItem().toString(), spDistrict.getSelectedItem().toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    //Hospital
                    hospitalAdapter = new ArrayAdapter(FinalizeNow.this, R.layout.spinner_item, strHospital);
                    hospitalAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                    sphospital.setAdapter(hospitalAdapter);
                    sphospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            HospitalID = visitEntryHospitalModels.get(position).getId();
                            CallHospitalAddress(HospitalID);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    tvSubmitDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });


                }
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFilled=true;

                strEditText.clear();
                for (int i = 0; i < layout_list.getChildCount(); i++) {
                    LinearLayout vi = (LinearLayout) layout_list.getChildAt(i);
                    EditText edt = (EditText) vi.getChildAt(0);
                    if (edt.getText().toString().trim().length() > 0)
                        strEditText.add(edt.getText().toString().trim());
                    else {
                        edt.setError("Enter The Amount / Remove It");
                        edt.requestFocus();
                    }
                }



                if (edPOR.getText().toString().length() == 0) {
                    edPOR.setError("Fill the Value");
                    edPOR.requestFocus();
                }
               else if (tvPODate.getText().toString().trim().length() == 0) {
                    tvPODate.setError("Fill the Value");
                    tvPODate.requestFocus();
                } else if (filePdf == null) {
                    Toast.makeText(FinalizeNow.this, "Please Upload File", Toast.LENGTH_SHORT).show();
                } else if (edName.getText().toString().trim().length() == 0) {
                    edName.setError("Please Enter the Name");
                    edName.requestFocus();
                } else if (edMobile.getText().toString().trim().length() == 0) {
                    edMobile.setError("Please Enter the Mobile");
                    edMobile.requestFocus();
                } else if (edQus.getText().toString().trim().length() == 0) {
                    edQus.setError("Please Enter the As per Quotation");
                    edQus.requestFocus();
//                }else  if((Ordervalue != AdvanceValue + PaymentAfter + PaymentBefor + PayIntallation)) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(FinalizeNow.this);
//                    builder.setMessage("Payment terms not matching with order value. Please check.");
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
                } else if (edOrderValue.getText().toString().trim().length() == 0) {
                    edOrderValue.setError("Please Enter a OrderValue");
                    edOrderValue.requestFocus();
                } else if (edAdvanceValue.getText().toString().trim().length() == 0) {
                    edAdvanceValue.setError("Please Enter a Advance Value");
                    edAdvanceValue.requestFocus();
                } else if (cbDeposited.isChecked()) {
                  if (tvDepositedDate.getText().toString().trim().length()==0){
                      tvDepositedDate.setError("Please Select the Date");
                      tvDepositedDate.requestFocus();
                  }
                } else if (edpaymentBforeDispatch.getText().toString().trim().length() == 0) {
                    edpaymentBforeDispatch.setError("Please Enter a Payment before Dispatch");
                    edpaymentBforeDispatch.requestFocus();
                } else if (edpaymentaterDispatch.getText().toString().trim().length() == 0) {
                    edpaymentaterDispatch.setError("Please Enter a Payment After Dispatch");
                    edpaymentaterDispatch.requestFocus();
                } else if (edpaymentOnInstalltion.getText().toString().trim().length() == 0) {
                    edpaymentOnInstalltion.setError("Please Entera Payment On Installation");
                    edpaymentOnInstalltion.requestFocus();
                } else if (tvDeliveySchedule.getText().toString().trim().length() == 0) {
                    tvDeliveySchedule.setError("Please Enter a Delivery Date");
                    tvDeliveySchedule.requestFocus();
                } else if (spPerson.getSelectedItemPosition() == -1) {
                    setSpinnerError(spPerson, "Please Select a Person perfix");
                    spPerson.requestFocus();
                } else {
                   int Ordervalue = Integer.parseInt(edOrderValue.getText().toString());
                   int AdvanceValue = Integer.parseInt(edAdvanceValue.getText().toString());
                   int PaymentBefor = Integer.parseInt(edpaymentBforeDispatch.getText().toString());
                   int PaymentAfter = Integer.parseInt(edpaymentaterDispatch.getText().toString());
                   int PayIntallation = Integer.parseInt(edpaymentOnInstalltion.getText().toString());

                   if ((Ordervalue != AdvanceValue + PaymentAfter + PaymentBefor + PayIntallation)) {
                       AlertDialog.Builder builder = new AlertDialog.Builder(FinalizeNow.this);
                       builder.setMessage("Payment terms not matching with order value. Please check.");
                       AlertDialog dialog = builder.create();
                       dialog.show();
                   } else {
                       int sameAddr = cbSameAddress.isChecked() ? 1 : 0;
                       int Depos = cbDeposited.isChecked() ? 1 : 0;
                       final ProgressDialog progressDialog = new ProgressDialog(FinalizeNow.this);
                       progressDialog.setMessage("Loading...");
                       progressDialog.setCancelable(false);
                       progressDialog.show();
                       RequestBody requestFile = create(MediaType.parse("multipart/form-data"), filePdf);
                       MultipartBody.Part POPDF = MultipartBody.Part.createFormData("poPdf", filePdf.getName(), requestFile);
                       RequestBody Action = create(MediaType.parse("text/plain"), "finalSubmit");
                       RequestBody EmpId = create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(FinalizeNow.this));
                       RequestBody QuoteId = create(MediaType.parse("text/plain"), SubmitquoteID);
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
                       RequestBody Installments = create(MediaType.parse("text/plain"), strEditText.toString().replace("[", "").replace("]", ""));
                       RequestBody Install_terms = create(MediaType.parse("text/plain"), edRemarkPayment.getText().toString());
                       RequestBody Spl_remarks = create(MediaType.parse("text/plain"), edSpecialRemark.getText().toString());
                       RequestBody Warranty = create(MediaType.parse("text/plain"), edWarrenty.getText().toString());
                       RequestBody Delivery_date = create(MediaType.parse("text/plain"), tvDeliveySchedule.getText().toString());

                       SalesQuotesUpdateFinalizeInteface salesQuotesUpdateFinalizeInteface = APIClient.getClient().create(SalesQuotesUpdateFinalizeInteface.class);
                       salesQuotesUpdateFinalizeInteface.callFinalSubmit(Action, EmpId, QuoteId, PoRef, PoDate, Billing_opt, BillingAddress, BillingAnother, Con_pre, Con_name, Con_no, Pro_spec, Order_value, Advance_value, Deposit, Deposit_date, Pay_bef_dispatch, Pay_aft_dispatch, Pay_on_install, Installments, Install_terms, Spl_remarks, Warranty, Delivery_date, POPDF).enqueue(new Callback<SalesQuotesUpdateFinalizeResponse>() {
                           @Override
                           public void onResponse(Call<SalesQuotesUpdateFinalizeResponse> call, Response<SalesQuotesUpdateFinalizeResponse> response) {
                               try {
                                   if (response.isSuccessful()) {
                                       progressDialog.dismiss();
                                       Toast.makeText(FinalizeNow.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                       onBackPressed();
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

            }
        });

    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.

        }
    }

    private void CallHospitalAddress(String hospitalID) {
        SalesQuotegetHospitalAddressInterface salesQuotegetHospitalAddressInterface = APIClient.getClient().create(SalesQuotegetHospitalAddressInterface.class);
        salesQuotegetHospitalAddressInterface.CallAddrss("getHospitalAddress", hospitalID).enqueue(new Callback<SalesQuotegetHospitalAddressResponse>() {
            @Override
            public void onResponse(Call<SalesQuotegetHospitalAddressResponse> call, Response<SalesQuotegetHospitalAddressResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvDeliveryAddressDialog.setText(response.body().getRsponse());
                        tvDeliveryAddress.setText(response.body().getRsponse());
                        GeoData = response.body().getGeodata();
                        DeliveryID = response.body().getHospitalId();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuotegetHospitalAddressResponse> call, Throwable t) {

            }
        });

    }

    private void callHospital(String toString, String toString1) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryHospitalInterface visitEntryHospitalInterface = APIClient.getClient().create(VisitEntryHospitalInterface.class);
        visitEntryHospitalInterface.CallHospital("getStateHospitals", toString, toString1).enqueue(new Callback<VisitEntryModelResponse>() {
            @Override
            public void onResponse(Call<VisitEntryModelResponse> call, Response<VisitEntryModelResponse> response) {

                try {
                    if (response.body().getVisitEntryHospitalModels().size() > 0) {
                        visitEntryHospitalModels = response.body().getVisitEntryHospitalModels();

                        strHospital.clear();
                        for (int i = 0; i < visitEntryHospitalModels.size(); i++) {
                            strHospital.add(visitEntryHospitalModels.get(i).getHospital());
                        }
                        hospitalAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<VisitEntryModelResponse> call, Throwable t) {

            }
        });
    }


    private void callDistrict(String state) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryGetDistrictInterface visitEntryGetDistrictInterface = APIClient.getClient().create(VisitEntryGetDistrictInterface.class);
        visitEntryGetDistrictInterface.CallDistrict("getDistricts", state).enqueue(new Callback<VisitEntryGetDistrictResponse>() {
            @Override
            public void onResponse(Call<VisitEntryGetDistrictResponse> call, Response<VisitEntryGetDistrictResponse> response) {
                try {
                    if (response.body().getVisitEntryGetDistrictModels().size() > 0) {
                        visitEntryGetDistrictModels = response.body().getVisitEntryGetDistrictModels();
                        strDistrict.clear();
                        for (int i = 0; i < visitEntryGetDistrictModels.size(); i++) {
                            strDistrict.add(visitEntryGetDistrictModels.get(i).getDistrict());
                        }
                        districtAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<VisitEntryGetDistrictResponse> call, Throwable t) {

            }
        });
    }

    private void CallState() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryStateInterface visitEntryStateInterface = APIClient.getClient().create(VisitEntryStateInterface.class);
        visitEntryStateInterface.CallState("getUserStates", PreferenceManager.getEmpID(this)).enqueue(new Callback<VisityEntryStateResponse>() {
            @Override
            public void onResponse(Call<VisityEntryStateResponse> call, Response<VisityEntryStateResponse> response) {
                try {

                    if (response.body().getVisitEntryStateModels().size() > 0) {

                        visitEntryStateModels = response.body().getVisitEntryStateModels();
                        strState.clear();
                        for (int i = 0; i < visitEntryStateModels.size(); i++) {
                            strState.add(visitEntryStateModels.get(i).getState());
                        }
                        stateAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisityEntryStateResponse> call, Throwable t) {

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
                installEdt.remove(edAmount);
            }
        });

        installEdt.add(edAmount);
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
                            String myStr = filename;
                            int index=myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            System.out.println(extension);
                            if(extension.equals(".pdf")){
                                tvAttach.setText(filename);
                            }else{
                               AlertDialog.Builder builder=new AlertDialog.Builder(this);
                               builder.setMessage("Please Select Pdf File Only ..");
                               AlertDialog dialog=builder.create();
                               dialog.show();
                            }


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