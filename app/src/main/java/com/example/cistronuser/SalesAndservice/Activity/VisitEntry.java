package com.example.cistronuser.SalesAndservice.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.VisitEntryAddInterface;
import com.example.cistronuser.API.Interface.VisitEntryDoctorInterface;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Interface.VisitEntryHospitalInterface;
import com.example.cistronuser.API.Interface.VisitEntryListInterface;
import com.example.cistronuser.API.Interface.VisitEntryListSubmitInterFace;
import com.example.cistronuser.API.Interface.VisitEntryProductInterface;
import com.example.cistronuser.API.Interface.VisitEntryStateInterface;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Model.VisitEntryDoctorModel;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.example.cistronuser.API.Model.VisitEntryListModel;
import com.example.cistronuser.API.Model.VisitEntryProductModel;
import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.example.cistronuser.API.Response.VisitEntryAddResponse;
import com.example.cistronuser.API.Response.VisitEntryDoctorResponse;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryListResponse;
import com.example.cistronuser.API.Response.VisitEntryListSubmitResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisitEntryProductResponse;
import com.example.cistronuser.API.Response.VisityEntryStateResponse;
import com.example.cistronuser.Activity.DashboardActivity;
import com.example.cistronuser.Common.LocationBackgroundService;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Adapter.VisitEntryAdapter;
import com.example.cistronuser.ServiceEngineer.Activity.UpcomingCallReport;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitEntry extends AppCompatActivity {

    TextView tvDate, tvAdd;
    ImageView ivBack, ivList;
    EditText EdComment;
    Spinner spDistrict, spState, spDoctor, spProduct, sphospital;
    Context context;
    String ip;

    //District

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
    String Lat, Lng;


    //ChefDoctor
    ArrayList<VisitEntryDoctorModel> visitEntryDoctorModels = new ArrayList<>();
    ArrayList<String> strDoctor = new ArrayList<>();
    ArrayAdapter chefDocAdapter;
    String ChefDocID;

    //Products
    ArrayList<VisitEntryProductModel> visitEntryProductModels = new ArrayList<>();
    ArrayList<String> strProduct = new ArrayList<>();
    ArrayAdapter productAdapter;
    String ProductId;

    //VisitEntryBottomList
    TextView tvSubmit, tvNodata;
    RecyclerView rvVisitEntryList;
    VisitEntryAdapter visitEntryAdapter;
    ArrayList<VisitEntryListModel> visitEntryListModels = new ArrayList<>();

    //Background Location
    LocationBroadcastReceiver receiver;
    Double lat, longg, str;
    String pincode, city, state, countrycode, Address;

    String TestEmp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_entry);

        spDistrict = findViewById(R.id.spDistrict);
        tvDate = findViewById(R.id.tvDate);
        ivBack = findViewById(R.id.ivBack);
        spProduct = findViewById(R.id.spProduct);
        spState = findViewById(R.id.spState);
        spDoctor = findViewById(R.id.spDoctor);
        sphospital = findViewById(R.id.sphospital);
        tvAdd = findViewById(R.id.tvAdd);
        ivList = findViewById(R.id.ivList);
        EdComment = findViewById(R.id.EdComment);
        rvVisitEntryList = findViewById(R.id.rvVisitEntryList);
        tvSubmit = findViewById(R.id.tvSubmit);
        tvNodata = findViewById(R.id.tvNodata);


        context = getApplicationContext();
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));

        // ******** Background Location ******* //
        receiver = new LocationBroadcastReceiver();
        startLocation();
        TestEmp=PreferenceManager.getEmpID(this);
        // ******** Background Location  End******* //
        //View List

        CallVisitEntryList();
        visitEntryAdapter = new VisitEntryAdapter(this, visitEntryListModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvVisitEntryList.setAdapter(visitEntryAdapter);
        rvVisitEntryList.setLayoutManager(linearLayoutManager);


        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSubmit();
            }
        });


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
                Lat = visitEntryHospitalModels.get(position).getLat();
                Lng = visitEntryHospitalModels.get(position).getLng();

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Product
        callProduct();
        productAdapter = new ArrayAdapter(this, R.layout.spinner_item, strProduct);
        productAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spProduct.setAdapter(productAdapter);
        spProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProductId = visitEntryProductModels.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setError(null);

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DATE, -1);

                DatePickerDialog datePickerDialog = new DatePickerDialog(VisitEntry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDate.setText(strDate);


                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                datePickerDialog.show();


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
                    } else if (spProduct.getSelectedItemPosition() == -1) {
                        setSpinnerError(spProduct, "Please Select the Product");
                        isfilled = false;
                    }
                    if (isfilled) {
                        CallAdd(HospitalID, ChefDocID, ProductId);
                    }
                } catch (Exception e) {

                }
            }
        });


        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CallViewList();
            }
        });


    }

    private void CallAdd(String hospitalID, String chefDocID, String productId) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Adding...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryAddInterface visitEntryAddInterface = APIClient.getClient().create(VisitEntryAddInterface.class);
        visitEntryAddInterface.CallAddVisitEntry("addVisitEntry", hospitalID, chefDocID, productId, EdComment.getText().toString(), ip, tvDate.getText().toString(), PreferenceManager.getEmpID(this), lat, longg, Address, state, city, countrycode, pincode).enqueue(new Callback<VisitEntryAddResponse>() {
            @Override
            public void onResponse(Call<VisitEntryAddResponse> call, Response<VisitEntryAddResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(VisitEntry.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(VisitEntry.this, DashboardActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisitEntryAddResponse> call, Throwable t) {

            }
        });
    }

    private void CallViewList() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.visit_entry_recycleview);
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();
        rvVisitEntryList = bottomSheetDialog.findViewById(R.id.rvVisitEntryList);
        ImageView ivBack = bottomSheetDialog.findViewById(R.id.ivBack);
        tvSubmit = bottomSheetDialog.findViewById(R.id.tvSubmit);
        tvNodata = bottomSheetDialog.findViewById(R.id.tvNodata);


//        CallVisitEntryList();
//        visitEntryAdapter=new VisitEntryAdapter(this,visitEntryListModels);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        rvVisitEntryList.setAdapter(visitEntryAdapter);
//        rvVisitEntryList.setLayoutManager(linearLayoutManager);
//
//
//        tvSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CallSubmit();
//            }
//        });


    }

    private void CallSubmit() {

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Visit Entry Submitted...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        VisitEntryListSubmitInterFace visitEntryListSubmitInterFace = APIClient.getClient().create(VisitEntryListSubmitInterFace.class);
        visitEntryListSubmitInterFace.CallVisitEntrySubmit("submitVisitEntries", PreferenceManager.getEmpID(this)).enqueue(new Callback<VisitEntryListSubmitResponse>() {
            @Override
            public void onResponse(Call<VisitEntryListSubmitResponse> call, Response<VisitEntryListSubmitResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getSuccess().trim().equals("1")) {
                            Toast.makeText(VisitEntry.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(VisitEntry.this, DashboardActivity.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent1);

                        } else {

                            AlertDialog.Builder msg = new AlertDialog.Builder(VisitEntry.this, R.style.AlertDialogCustom);
                            msg.setMessage(response.body().getMessage());
                            msg.setTitle(" Failed !");
                            msg.setIcon(R.drawable.oops);
                            AlertDialog alertDialog = msg.create();
                            alertDialog.show();

                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisitEntryListSubmitResponse> call, Throwable t) {

            }
        });

    }

    private void CallVisitEntryList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryListInterface visitEntryListInterface = APIClient.getClient().create(VisitEntryListInterface.class);
        visitEntryListInterface.CallVisitEntryList("viewVisitEntries", PreferenceManager.getEmpID(this)).enqueue(new Callback<VisitEntryListResponse>() {
            @Override
            public void onResponse(Call<VisitEntryListResponse> call, Response<VisitEntryListResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getVisitEntryListModels().size() > 0) {
                            visitEntryAdapter.visitEntryListModels = response.body().getVisitEntryListModels();
                            visitEntryAdapter.notifyDataSetChanged();
                            tvNodata.setVisibility(View.GONE);
                            rvVisitEntryList.setVisibility(View.VISIBLE);
                            tvSubmit.setVisibility(View.VISIBLE);

                        } else {
                            tvNodata.setVisibility(View.VISIBLE);
                            rvVisitEntryList.setVisibility(View.GONE);
                            tvSubmit.setVisibility(View.GONE);
                        }


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<VisitEntryListResponse> call, Throwable t) {

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

    private void callProduct() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        VisitEntryProductInterface visitEntryProductInterface = APIClient.getClient().create(VisitEntryProductInterface.class);
        visitEntryProductInterface.CallProduct("getAvailProd").enqueue(new Callback<VisitEntryProductResponse>() {
            @Override
            public void onResponse(Call<VisitEntryProductResponse> call, Response<VisitEntryProductResponse> response) {
                try {
                    if (response.body().getVisitEntryProductModels().size() > 0) {
                        visitEntryProductModels = response.body().getVisitEntryProductModels();
                        strProduct.clear();
                        for (int i = 0; i < visitEntryProductModels.size(); i++) {
                            strProduct.add(visitEntryProductModels.get(i).getProduct());
                        }
                        productAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<VisitEntryProductResponse> call, Throwable t) {

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
                        visitEntryDoctorModels = response.body().getVisitEntryDoctorModels();
                        strDoctor.clear();
                        for (int i = 0; i < visitEntryDoctorModels.size(); i++) {
                            strDoctor.add(visitEntryDoctorModels.get(i).getChiefDr());
                        }
                        chefDocAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    } else {
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

    public void startLocation() {
        IntentFilter intentFilter = new IntentFilter("Location");
        registerReceiver(receiver, intentFilter);
        Intent intent = new Intent(this, LocationBackgroundService.class);
        startService(intent);
    }

    private void CallCheckLocation() {

        try {

            //  RespLatitude = "10.70427880";

            Location locationA = new Location("Location A");
            Location locationB = new Location("Location B");
            locationA.setLatitude(Double.parseDouble(String.valueOf(lat)));
            locationA.setLongitude(Double.parseDouble(String.valueOf(longg)));
            locationB.setLatitude(Double.parseDouble(String.valueOf(Lat)));
            locationB.setLongitude(Double.parseDouble(String.valueOf(Lng)));

            str = Double.valueOf(locationA.distanceTo(locationB) / 1000);
            Log.e(TAG, "onResponse: " + str);


            if (str <= 1.0) {
                tvAdd.setVisibility(View.VISIBLE);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(VisitEntry.this, R.style.AlertDialogCustom);
                builder.setTitle("Your location has not been reached.");
                builder.setMessage("Are you at the right place ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvAdd.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Intent intent = new Intent(VisitEntry.this, LocationBackgroundService.class);
                stopService(intent);
                tvAdd.setVisibility(View.GONE);

            }


        } catch (Exception e) {
            //  Log.d(TAG, "CallCheckLocation: " + e.getMessage());
        }


    }

    public class LocationBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Location")) {

                lat = intent.getDoubleExtra("latitude", 0f);
                longg = intent.getDoubleExtra("longitude", 0f);
                Address = intent.getStringExtra("Address");
                pincode = intent.getStringExtra("pincode");
                city = intent.getStringExtra("city");
                state = intent.getStringExtra("state");
                countrycode = intent.getStringExtra("countrycode");

                //Log.e(TAG, "onReceive: " + lat + "\n" + longg+"\n" + state + "\n" + city+"\n" + countrycode + "\n" + pincode+"\n" + url  );

            }

            if (TestEmp.trim().equals("e411") || TestEmp.trim().equals("E411") || TestEmp.trim().equals("E410") || TestEmp.trim().equals("e410")){
                CallCheckLocation();
            }else {
                tvAdd.setVisibility(View.VISIBLE);
            }



        }
    }


}