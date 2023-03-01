package com.example.cistronuser.SalesAndservice.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SalesQuoteAddInterface;
import com.example.cistronuser.API.Interface.SalesQuoteCategoryInterface;
import com.example.cistronuser.API.Interface.SalesQuoteHospitalUpdateInterface;
import com.example.cistronuser.API.Interface.SalesQuoteMailtoSendInterface;
import com.example.cistronuser.API.Interface.SalesQuoteProductsInterFace;
import com.example.cistronuser.API.Interface.SalesQuoteSubmitedInterface;
import com.example.cistronuser.API.Interface.VisitEntryDoctorInterface;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Interface.VisitEntryHospitalInterface;
import com.example.cistronuser.API.Interface.VisitEntryStateInterface;
import com.example.cistronuser.API.Model.SalesQuoteCategoryModel;
import com.example.cistronuser.API.Model.SalesQuoteHospitalUpdateModel;
import com.example.cistronuser.API.Model.SalesQuoteProductsAddonModel;
import com.example.cistronuser.API.Model.SalesQuoteProductsModel;
import com.example.cistronuser.API.Model.VisitEntryDoctorModel;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.example.cistronuser.API.Response.SalesQuoteAddResponse;
import com.example.cistronuser.API.Response.SalesQuoteCategoryResponse;
import com.example.cistronuser.API.Response.SalesQuoteHospitalUpdateResponse;
import com.example.cistronuser.API.Response.SalesQuoteMailtoSendResponse;
import com.example.cistronuser.API.Response.SalesQuoteProductsResponse;
import com.example.cistronuser.API.Response.SalesQuoteSubmitedResponse;
import com.example.cistronuser.API.Response.VisitEntryDoctorResponse;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisityEntryStateResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Adapter.SalesQuoteAddonAdapter;
import com.example.cistronuser.SalesAndservice.Adapter.SalesQuoteHospitalUpdateAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SalesQuote extends AppCompatActivity implements LocationListener {


    TextView tvDate, tvAdd, tvSubmit;
    ImageView ivBack, ivList;
    CheckBox cbSms;
    Spinner spDistrict, spState, spDoctor, spProduct, sphospital, spCategory;


    ArrayList<VisitEntryGetDistrictModel> visitEntryGetDistrictModels = new ArrayList<>();
    ArrayList<String> strDistrict = new ArrayList<>();
    ArrayAdapter districtAdapter;
    String District;

    //State
    ArrayList<VisitEntryStateModel> visitEntryStateModels = new ArrayList<>();
    ArrayList<String> strState = new ArrayList<>();
    ArrayAdapter stateAdapter;
    String StateId;

    //Hospital
    ArrayList<VisitEntryHospitalModel> visitEntryHospitalModels = new ArrayList<>();
    ArrayList<String> strHospital = new ArrayList<>();
    ArrayAdapter hospitalAdapter;
    String HospitalID;


    //ChefDoctor
    ArrayList<VisitEntryDoctorModel> visitEntryDoctorModels = new ArrayList<>();
    ArrayList<String> strDoctor = new ArrayList<>();
    ArrayAdapter chefDocAdapter;
    EditText edMail, edAnotherMail;
    String ChefDocID;


    //Category
    ArrayList<SalesQuoteCategoryModel> salesQuoteCategoryModels = new ArrayList<>();
    ArrayList<String> strCategory = new ArrayList<>();
    ArrayAdapter categoryAdapter;
    String CategoryID;

    //Product
    ArrayList<SalesQuoteProductsModel> salesQuoteProductsModels = new ArrayList<>();
    ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels = new ArrayList<>();
    ArrayList<String> strAddon = new ArrayList<>();
    ArrayList<Integer> AddOnprice = new ArrayList<>();
    SalesQuoteAddonAdapter salesQuoteAddonAdapter;
    TextView tvPrice;
    ArrayList<String> strProduct = new ArrayList<>();
    ArrayAdapter ProductAdapter;
    RecyclerView rvProducts;
    String ProductID;
    String MobileNo;
    int Product;

    //Address
    double Latitude;
    double Longtitude;
    String AddressLine;
    LocationManager locationManager;

    //IpAddress
    Context context;
    String ip;

    //SalesHospitalUpdateBottom
    RecyclerView rvSalesQuoteHospitalUpdate;
    TextView tvNodata;
    ImageView ivBottomBack;
    SalesQuoteHospitalUpdateAdapter salesQuoteHospitalUpdateAdapter;
    ArrayList<SalesQuoteHospitalUpdateModel> salesQuoteHospitalUpdateModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_quote);

        spDistrict = findViewById(R.id.spDistrict);
        tvDate = findViewById(R.id.tvDate);
        ivBack = findViewById(R.id.ivBack);
        spProduct = findViewById(R.id.spProduct);
        spState = findViewById(R.id.spState);
        spDoctor = findViewById(R.id.spDoctor);
        sphospital = findViewById(R.id.sphospital);
        tvAdd = findViewById(R.id.tvAdd);
        ivList = findViewById(R.id.ivList);
        spCategory = findViewById(R.id.spCategory);
        rvProducts = findViewById(R.id.rvProducts);
        tvPrice = findViewById(R.id.tvPrice);
        edAnotherMail = findViewById(R.id.edAnotherMail);
        edMail = findViewById(R.id.edMail);
        tvSubmit = findViewById(R.id.tvSubmit);
        cbSms = findViewById(R.id.cbSms);


        //LocationPermission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        //Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();

        //Ip Address
        context = getApplicationContext();
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));

        Date Start = new Date();
        CharSequence SDate = DateFormat.format("yyyy-MM-dd ", Start.getTime());
        tvDate.setText(SDate);


        //*********MailValidation**********//
        String emailPattern = "(([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)(\\s*(;|,)\\s*|\\s*$))*";
        edMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edMail.getText().toString().trim().matches(emailPattern)) {
                    edMail.setError(null);
                } else {
                    edMail.setError("Invalid Mail");
                    edMail.requestFocus();
                }

            }
        });

        edAnotherMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edAnotherMail.getText().toString().trim().matches(emailPattern)) {
                    edAnotherMail.setError(null);
                } else {
                    edAnotherMail.setError("Invalid Mail");
                    edAnotherMail.requestFocus();
                }

            }
        });

        //******************MailValidation end****************//
        //State
        CallState();
        stateAdapter = new ArrayAdapter(this, R.layout.spinner_item, strState);
        stateAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spState.setAdapter(stateAdapter);
        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                callDistrict(spState.getSelectedItem().toString());
                callHospital(spState.getSelectedItem().toString(), "District");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //District

        districtAdapter = new ArrayAdapter(this, R.layout.spinner_item, strDistrict);
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

        hospitalAdapter = new ArrayAdapter(this, R.layout.spinner_item, strHospital);
        hospitalAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sphospital.setAdapter(hospitalAdapter);
        sphospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                HospitalID = visitEntryHospitalModels.get(position).getId();
                CallChefDoc(HospitalID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //ChefDoctor
        chefDocAdapter = new ArrayAdapter(this, R.layout.spinner_item, strDoctor);
        chefDocAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spDoctor.setAdapter(chefDocAdapter);
        spDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChefDocID = visitEntryDoctorModels.get(position).getId();
                MobileNo = visitEntryDoctorModels.get(position).getMobile();
                edMail.setText(visitEntryDoctorModels.get(position).getMail());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Category
        CallCategory();
        categoryAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strCategory);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCategory.setAdapter(categoryAdapter);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryID = salesQuoteCategoryModels.get(position).getSeriesId();
                rvProducts.setVisibility(View.GONE);
                CallProduct(CategoryID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Product'

        ProductAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, strProduct);
        ProductAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spProduct.setAdapter(ProductAdapter);
        spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tvPrice.setText(salesQuoteProductsModels.get(position).getPrice());


                salesQuoteAddonAdapter = new SalesQuoteAddonAdapter(new SalesQuoteAddonAdapter.OnItemCheckListener() {
                    @Override
                    public void onItemCheck(SalesQuoteProductsAddonModel item) {
                        strAddon.add(item.getAddonId());
                        android.text.TextUtils.join(",", strAddon);
                        AddOnprice.add(Integer.valueOf(item.getPrice()));
                        Product = Integer.parseInt(salesQuoteProductsModels.get(position).getPrice());

                        calcQuotePrice(AddOnprice, Product);

                    }

                    @Override
                    public void onItemUncheck(SalesQuoteProductsAddonModel item) {

                        strAddon.remove(item.getAddonId());
                        android.text.TextUtils.join(",", strAddon);
                        AddOnprice.remove(Integer.valueOf(item.getPrice()));
                        Product = Integer.parseInt(salesQuoteProductsModels.get(position).getPrice());
                        calcQuotePrice(AddOnprice, Product);


                    }
                }, SalesQuote.this, salesQuoteProductsAddonModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SalesQuote.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvProducts.setLayoutManager(linearLayoutManager);
                rvProducts.setAdapter(salesQuoteAddonAdapter);


                if (salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels().size() > 0) {
                    rvProducts.setVisibility(View.VISIBLE);
                    salesQuoteAddonAdapter.salesQuoteProductsAddonModels = salesQuoteProductsModels.get(position).getSalesQuoteProductsAddonModels();


                    salesQuoteAddonAdapter.notifyDataSetChanged();

                } else {
                    rvProducts.setVisibility(View.GONE);
                }

                ProductID = salesQuoteProductsModels.get(position).getProdId();


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//
//        tvDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvDate.setError(null);
//
//                Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                calendar.add(Calendar.DATE, -1);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(SalesQuote.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        String moth, dt;
//
//                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));
//
//                        dt = (day > 9) ? "" + day : ("0" + day);
//
//
//                        String strDate = year + "-" + moth + "-" + dt;
//                        tvDate.setText(strDate);
//
//
//                    }
//
//                }, year, month, dayOfMonth);
//
//                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
//
//
//                datePickerDialog.show();
//
//
//            }
//        });

        cbSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context, MobileNo, Toast.LENGTH_SHORT).show();


                } else {

                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    boolean isfilled = true;

                    if (tvDate.getText().toString().trim().length() == 0) {
                        tvDate.setError("Please Select the Date");
                        tvDate.requestFocus();
                        isfilled = false;
                    } else if (spState.getSelectedItemPosition() == -1) {
                        setSpinnerError(spState, "Please Select the State");
                        Log.e(TAG, "onClick: state");
                        isfilled = false;
                    } else if (sphospital.getSelectedItemPosition() == -1) {
                        setSpinnerError(sphospital, "Please Select the Hospital");
                        isfilled = false;
                    } else if (spDoctor.getSelectedItemPosition() == -1) {
                        setSpinnerError(spDoctor, "Please Select the Chef.Doctor");
                        isfilled = false;
                    } else if (spCategory.getSelectedItemPosition() == -1) {
                        setSpinnerError(spCategory, "Please Select the Category");
                        isfilled = false;
                    } else if (spProduct.getSelectedItemPosition() == -1) {
                        setSpinnerError(spProduct, "Please Select the Product");
                        isfilled = false;
                    }
                    if (isfilled) {

                        CallPreview(android.text.TextUtils.join(",", strAddon));
                    }
                } catch (Exception e) {

                }
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {


                    boolean isfilled = true;

                    if (tvDate.getText().toString().trim().length() == 0) {
                        tvDate.setError("Please Select the Date");
                        tvDate.requestFocus();
                        isfilled = false;
                    } else if (spState.getSelectedItemPosition() == -1) {
                        setSpinnerError(spState, "Please Select the State");
                        Log.e(TAG, "onClick: state");
                        isfilled = false;
                    } else if (sphospital.getSelectedItemPosition() == -1) {
                        setSpinnerError(sphospital, "Please Select the Hospital");
                        isfilled = false;
                    } else if (spDoctor.getSelectedItemPosition() == -1) {
                        setSpinnerError(spDoctor, "Please Select the Chef.Doctor");
                        isfilled = false;
                    } else if (spCategory.getSelectedItemPosition() == -1) {
                        setSpinnerError(spCategory, "Please Select the Category");
                        isfilled = false;
                    } else if (spProduct.getSelectedItemPosition() == -1) {
                        setSpinnerError(spProduct, "Please Select the Product");
                        isfilled = false;
                    }
                    if (isfilled) {
                        CallSalesQuoteSubmitted(android.text.TextUtils.join(",", strAddon), Latitude, Longtitude);
                    }
                } catch (Exception e) {

                }

            }
        });

        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHospitalUpdateList();
            }
        });

    }

    private void CallHospitalUpdateList() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.sales_quote_hospital_recycleview);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();

        rvSalesQuoteHospitalUpdate = bottomSheetDialog.findViewById(R.id.rvSalesQuoteHospitalUpdate);
        ivBottomBack = bottomSheetDialog.findViewById(R.id.ivBack);
        tvNodata = bottomSheetDialog.findViewById(R.id.tvNodata);


        CallHospitalUpdateView();
        salesQuoteHospitalUpdateAdapter = new SalesQuoteHospitalUpdateAdapter(this, salesQuoteHospitalUpdateModels);
        LinearLayoutManager SaleQuoteUpdate = new LinearLayoutManager(this);
        SaleQuoteUpdate.setOrientation(RecyclerView.VERTICAL);
        rvSalesQuoteHospitalUpdate.setAdapter(salesQuoteHospitalUpdateAdapter);
        rvSalesQuoteHospitalUpdate.setLayoutManager(SaleQuoteUpdate);

        ivBottomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });


    }

    private void CallHospitalUpdateView() {

        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Sales Quote...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteHospitalUpdateInterface salesQuoteHospitalUpdateInterface = APIClient.getClient().create(SalesQuoteHospitalUpdateInterface.class);
        salesQuoteHospitalUpdateInterface.CallHospitalUpdateList("viewAvailableSalesQuote", HospitalID).enqueue(new Callback<SalesQuoteHospitalUpdateResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteHospitalUpdateResponse> call, Response<SalesQuoteHospitalUpdateResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getSalesQuoteHospitalUpdateModels() == null) {
                            progressDialog.dismiss();
                            tvNodata.setVisibility(View.VISIBLE);
                            rvSalesQuoteHospitalUpdate.setVisibility(View.GONE);
                        } else {
                            rvSalesQuoteHospitalUpdate.setVisibility(View.VISIBLE);
                            tvNodata.setVisibility(View.GONE);
                            salesQuoteHospitalUpdateAdapter.salesQuoteHospitalUpdateModels = response.body().getSalesQuoteHospitalUpdateModels();
                            salesQuoteHospitalUpdateAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteHospitalUpdateResponse> call, Throwable t) {

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

    private void CallSalesQuoteSubmitted(String join, double latitude, double longtitude) {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Sales Quote...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        int sms = cbSms.isChecked() ? 1 : 0;
        SalesQuoteSubmitedInterface salesQuoteSubmitedInterface = APIClient.getClient().create(SalesQuoteSubmitedInterface.class);
        salesQuoteSubmitedInterface.salesQuoteSubmit("generateSalesQuote", PreferenceManager.getEmpID(this), HospitalID, ChefDocID, CategoryID, ProductID, tvPrice.getText().toString(), edMail.getText().toString(),
                edAnotherMail.getText().toString(), sms, MobileNo, join, latitude, longtitude, ip, AddressLine).enqueue(new Callback<SalesQuoteSubmitedResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteSubmitedResponse> call, Response<SalesQuoteSubmitedResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(SalesQuote.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        String Quote = response.body().getQuote();
                        Uri uri = Uri.parse(Quote);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

//                        "empid": "E367",
//                                "id": 53177,
//                                "quote_id": "8910",
//                                "doctor": "Mr.  TEST DOCTOR TEST DOCTOR MD",
//                                "doc_email": "",
//                                "product": "Instrument Washer Cum Disinfector",
//                                "m_status": 1,
//                                "mailto": "",
//                                "mailcc": "",
//                                "bro_name": "",
//                                "a": 1

                        String empid = response.body().getSalesQuoteMailSendModel().getEmpid();
                        String id = response.body().getSalesQuoteMailSendModel().getId();
                        String quote_id = response.body().getSalesQuoteMailSendModel().getQuote_id();
                        String doctor = response.body().getSalesQuoteMailSendModel().getDoctor();
                        String doc_email = response.body().getSalesQuoteMailSendModel().getDoc_email();
                        String product = response.body().getSalesQuoteMailSendModel().getProduct();
                        String m_status = response.body().getSalesQuoteMailSendModel().getM_status();
                        String mailto = response.body().getSalesQuoteMailSendModel().getMailto();
                        String mailcc = response.body().getSalesQuoteMailSendModel().getMailcc();
                        String bro_name = response.body().getSalesQuoteMailSendModel().getBro_name();
                        String a = response.body().getSalesQuoteMailSendModel().getA();


                        CallMailToSend(empid, id, quote_id, doctor, doc_email, product, m_status, mailto, mailcc, bro_name, a);

                        onBackPressed();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<SalesQuoteSubmitedResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }

    private void CallMailToSend(String empid, String id, String quote_id, String doctor, String doc_email, String product, String m_status, String mailto, String mailcc, String bro_name, String a) {
        SalesQuoteMailtoSendInterface salesQuoteMailtoSendInterface = APIClient.getClient().create(SalesQuoteMailtoSendInterface.class);
        salesQuoteMailtoSendInterface.callSendMail(empid, id, quote_id, doctor, doc_email, product, m_status, mailto, mailcc, bro_name, a).enqueue(new Callback<SalesQuoteMailtoSendResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteMailtoSendResponse> call, Response<SalesQuoteMailtoSendResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    //Log.e(TAG, "mail: " + response.body().getMail_error());
                    //Log.e(TAG, "sms: " + response.body().getSms_error());
                }
            }

            @Override
            public void onFailure(Call<SalesQuoteMailtoSendResponse> call, Throwable t) {

            }
        });

    }


    private void calcQuotePrice(ArrayList<Integer> addOnprice, int product) {
        int sum = 0;
        for (int i = 0; i < addOnprice.size(); i++) {
            sum += addOnprice.get(i);
        }
        sum += product;
        String Price = String.valueOf(sum);
        tvPrice.setText(Price);
    }


    private void CallPreview(String join) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Preview...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        SalesQuoteAddInterface salesQuoteAddInterface = APIClient.getClient().create(SalesQuoteAddInterface.class);
        salesQuoteAddInterface.CallPreview("salesQuotePreview", CategoryID, ProductID, HospitalID, join).enqueue(new Callback<SalesQuoteAddResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteAddResponse> call, Response<SalesQuoteAddResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        String Preview = response.body().getPreviewUrl();
                        Uri uri = Uri.parse(Preview);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<SalesQuoteAddResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void CallProduct(String categoryID) {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteProductsInterFace salesQuoteProductsInterFace = APIClient.getClient().create(SalesQuoteProductsInterFace.class);
        salesQuoteProductsInterFace.CallProduct("getProductOfTheSeries", categoryID).enqueue(new Callback<SalesQuoteProductsResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteProductsResponse> call, Response<SalesQuoteProductsResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();

                        if (response.body().getSalesQuoteProductsModels().size() > 0) {
                            salesQuoteProductsModels = response.body().getSalesQuoteProductsModels();
                            strProduct.clear();
                            for (int i = 0; i < salesQuoteProductsModels.size(); i++) {
                                strProduct.add(salesQuoteProductsModels.get(i).getSeriesName());
                            }
                            ProductAdapter.notifyDataSetChanged();
                        }

                    }

                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<SalesQuoteProductsResponse> call, Throwable t) {

            }
        });

    }

    private void CallCategory() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteCategoryInterface salesQuoteCategoryInterface = APIClient.getClient().create(SalesQuoteCategoryInterface.class);
        salesQuoteCategoryInterface.CallCategory("getAvailableCategory").enqueue(new Callback<SalesQuoteCategoryResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteCategoryResponse> call, Response<SalesQuoteCategoryResponse> response) {
                try {
                    if (response.body().getSalesQuoteCategoryModels().size() > 0) {
                        progressDialog.dismiss();
                        salesQuoteCategoryModels = response.body().getSalesQuoteCategoryModels();
                        strCategory.clear();
                        for (int i = 0; i < salesQuoteCategoryModels.size(); i++) {
                            strCategory.add(salesQuoteCategoryModels.get(i).getSeriesName());
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(Call<SalesQuoteCategoryResponse> call, Throwable t) {

            }
        });
    }


    private void CallChefDoc(String hospitalID) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryDoctorInterface visitEntryDoctorInterface = APIClient.getClient().create(VisitEntryDoctorInterface.class);
        visitEntryDoctorInterface.callChefDoctor("getChiefDr", hospitalID).enqueue(new Callback<VisitEntryDoctorResponse>() {
            @Override
            public void onResponse(Call<VisitEntryDoctorResponse> call, Response<VisitEntryDoctorResponse> response) {
                try {
                    if (response.body().getVisitEntryDoctorModels().size() > 0) {
                        progressDialog.dismiss();
                        visitEntryDoctorModels = response.body().getVisitEntryDoctorModels();
                        strDoctor.clear();
                        for (int i = 0; i < visitEntryDoctorModels.size(); i++) {
                            strDoctor.add(visitEntryDoctorModels.get(i).getChiefDr());
                        }
                        chefDocAdapter.notifyDataSetChanged();
                    } else {
                        chefDocAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisitEntryDoctorResponse> call, Throwable t) {

            }
        });
    }

    private void callHospital(String state, String district) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryHospitalInterface visitEntryHospitalInterface = APIClient.getClient().create(VisitEntryHospitalInterface.class);
        visitEntryHospitalInterface.CallHospital("getStateHospitals", state, district).enqueue(new Callback<VisitEntryModelResponse>() {
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
                        hospitalAdapter.notifyDataSetChanged();
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

    private void CallState() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
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

    private void callDistrict(String state) {

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
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


    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(SalesQuote.this, R.style.AlertDialogCustom)
                    .setTitle("Enable GPS Service")
                    .setIcon(R.drawable.ic_baseline_location_on_24)
                    .setMessage("Allow Cistron App to Access this device's location?")
                    .setCancelable(false)
                    .setPositiveButton("ALLOW", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("DENY", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Latitude = addresses.get(0).getLatitude();
            Longtitude = addresses.get(0).getLongitude();
            AddressLine = addresses.get(0).getAddressLine(0);
            //  Log.e(TAG, "Sales Quote onLocationChanged: "+Latitude+"--"+Longtitude+"--"+AddressLine );

        } catch (Exception e) {
        }
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}