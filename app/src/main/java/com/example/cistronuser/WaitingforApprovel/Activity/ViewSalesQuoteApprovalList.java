package com.example.cistronuser.WaitingforApprovel.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SalesQuoteApprovalViewInterface;
import com.example.cistronuser.API.Interface.SalesQuotegetHospitalAddressInterface;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Interface.VisitEntryHospitalInterface;
import com.example.cistronuser.API.Interface.VisitEntryStateInterface;
import com.example.cistronuser.API.Model.SalesQuoteApprovalViewInstallmentModel;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.example.cistronuser.API.Response.SalesQuoteApprovalViewResponse;
import com.example.cistronuser.API.Response.SalesQuotegetHospitalAddressResponse;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.API.Response.VisitEntryModelResponse;
import com.example.cistronuser.API.Response.VisityEntryStateResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Activity.FinalizeNow;
import com.example.cistronuser.WaitingforApprovel.Adapter.InstallmentAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSalesQuoteApprovalList extends AppCompatActivity {




    File attachFile;
    String filename;

    EditText edPOR,edName,edMobile,edQus,edOrderValue,edAdvanceValue,edpaymentBforeDispatch,
            edpaymentaterDispatch,edpaymentOnInstalltion,edRemarkPayment,edWarrenty,edSpecialRemark;
    TextView tvPODate,tvAttach,tvAddress,tvDeliveryAddress,tvSubmit,tvProduct,tvDeliveySchedule,tvQty,tvDepositedDate;
    CheckBox cbAttach,cbSameAddress,cbDeposited;
    Spinner spPerson;
    ImageView ivFileVIew,ivAdd,ivBack;
    RecyclerView rvInstallmentAmt;
    String QuotePdf;
    ArrayAdapter personContact;
    String PersonId;

    //AddEditText
    EditText edAmount;
    ArrayList<String> strEditText = new ArrayList<>();
    ImageView ivRemove;
    ArrayList<EditText>installEdt=new ArrayList<>();
    LinearLayout layout_list;

    //InstallmentList
    InstallmentAdapter installmentAdapter;
    ArrayList<SalesQuoteApprovalViewInstallmentModel>salesQuoteApprovalViewInstallmentModels=new ArrayList<>();


    //Dialog Address
    Spinner spState, spDistrict, sphospital;
    TextView tvDeliveryAddressDialog, tvSubmitDialog;
    ImageView ivClose;
    String DailveryAdrs;
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



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales_quote_approval_list);

        ivFileVIew=findViewById(R.id.ivFileVIew);
        spPerson=findViewById(R.id.spPerson);
        cbAttach=findViewById(R.id.cbAttach);
        cbSameAddress=findViewById(R.id.cbSameAddress);
        cbDeposited=findViewById(R.id.cbDeposited);
        tvPODate=findViewById(R.id.tvPODate);
        tvAttach=findViewById(R.id.tvAttach);
        tvAddress=findViewById(R.id.tvAddress);
        tvDeliveryAddress=findViewById(R.id.tvDeliveryAddress);
        tvProduct=findViewById(R.id.tvProduct);
        tvDeliveySchedule=findViewById(R.id.tvDeliveySchedule);
        tvQty=findViewById(R.id.tvQty);
        edPOR=findViewById(R.id.edPOR);
        edName=findViewById(R.id.edName);
        edMobile=findViewById(R.id.edMobile);
        edQus=findViewById(R.id.edQus);
        edOrderValue=findViewById(R.id.edOrderValue);
        edAdvanceValue=findViewById(R.id.edAdvanceValue);
        edpaymentBforeDispatch=findViewById(R.id.edpaymentBforeDispatch);
        edpaymentaterDispatch=findViewById(R.id.edpaymentaterDispatch);
        edpaymentOnInstalltion=findViewById(R.id.edpaymentOnInstalltion);
        edRemarkPayment=findViewById(R.id.edRemarkPayment);
        edWarrenty=findViewById(R.id.edWarrenty);
        edSpecialRemark=findViewById(R.id.edSpecialRemark);
        tvDepositedDate=findViewById(R.id.tvDepositedDate);
        rvInstallmentAmt=findViewById(R.id.rvInstallmentAmt);
        ivAdd=findViewById(R.id.ivAdd);
        layout_list=findViewById(R.id.layout_list);
        tvSubmit = findViewById(R.id.tvSubmit);
        ivBack=findViewById(R.id.ivBack);



        String[] contact = {"Contact Prefix","Dr. ", "Mr. ", "Ms. ", "Mrs. "};
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


        //***GetString***
        String OAreqID=getIntent().getStringExtra("OAReqID");


        //*****View Approval*****
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SalesQuoteApprovalViewInterface salesQuoteApprovalViewInterface= APIClient.getClient().create(SalesQuoteApprovalViewInterface.class);
        salesQuoteApprovalViewInterface.CallViewApproval("viewOrderCall",OAreqID).enqueue(new Callback<SalesQuoteApprovalViewResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteApprovalViewResponse> call, Response<SalesQuoteApprovalViewResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        edPOR.setText(response.body().getSalesQuoteApprovalViewModel().getPo_ref());
                        tvPODate.setText(response.body().getSalesQuoteApprovalViewModel().getPo_date());
                        tvAddress.setText(response.body().getSalesQuoteApprovalViewModel().getBilling());
                        tvDeliveryAddress.setText(response.body().getSalesQuoteApprovalViewModel().getDelivery());
                        DailveryAdrs=response.body().getSalesQuoteApprovalViewModel().getDelivery();
                        edName.setText(response.body().getSalesQuoteApprovalViewModel().getCon_name());
                        edMobile.setText(response.body().getSalesQuoteApprovalViewModel().getCon_no());
                        tvProduct.setText(response.body().getSalesQuoteApprovalViewModel().getProduct());
                        edQus.setText(response.body().getSalesQuoteApprovalViewModel().getPro_spec());
                        tvQty.setText(response.body().getSalesQuoteApprovalViewModel().getQty());
                        edOrderValue.setText(response.body().getSalesQuoteApprovalViewModel().getOrder_value());
                        edAdvanceValue.setText(response.body().getSalesQuoteApprovalViewModel().getAdvance_value());
                        edpaymentBforeDispatch.setText(response.body().getSalesQuoteApprovalViewModel().getBp_dispatch());
                        edpaymentaterDispatch.setText(response.body().getSalesQuoteApprovalViewModel().getBa_dispatch());
                        edpaymentOnInstalltion.setText(response.body().getSalesQuoteApprovalViewModel().getBp_install());
                        edRemarkPayment.setText(response.body().getSalesQuoteApprovalViewModel().getBp_remarks());
                        edSpecialRemark.setText(response.body().getSalesQuoteApprovalViewModel().getSpl_remarks());
                        edWarrenty.setText(response.body().getSalesQuoteApprovalViewModel().getWarranty());
                        tvDeliveySchedule.setText(response.body().getSalesQuoteApprovalViewModel().getDelivery_date());
                        QuotePdf=response.body().getSalesQuoteApprovalViewModel().getQuote_pdf();
                        String prefix = response.body().getSalesQuoteApprovalViewModel().getCon_pre();

                        for (int i=0;i<personContact.getCount();i++){
                            Log.e(TAG, "onResponse: "+personContact.getItem(i).toString().equals(prefix) );
                            if(personContact.getItem(i).toString().equals(prefix))
                                spPerson.setSelection(i);
                        }


                        if (response.body().getSalesQuoteApprovalViewModel().getDeposit().trim().equals("0")){
                            tvDepositedDate.setVisibility(View.GONE);
                            cbDeposited.setChecked(false);
                        }else {
                            tvDepositedDate.setVisibility(View.VISIBLE);
                            tvDepositedDate.setText(response.body().getSalesQuoteApprovalViewModel().getDeposit_date());
                            cbDeposited.setChecked(true);
                        }

                        installmentAdapter =new InstallmentAdapter(ViewSalesQuoteApprovalList.this,salesQuoteApprovalViewInstallmentModels);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewSalesQuoteApprovalList.this);
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        rvInstallmentAmt.setAdapter(installmentAdapter);
                        rvInstallmentAmt.setLayoutManager(linearLayoutManager);

                        installmentAdapter.viewInstallmentModels=response.body().getSalesQuoteApprovalViewModel().getSalesQuoteApprovalViewInstallmentModels();
                        installmentAdapter.notifyDataSetChanged();


                    }


                }catch (Exception e){

                }


            }

            @Override
            public void onFailure(Call<SalesQuoteApprovalViewResponse> call, Throwable t) {

            }
        });

        ivFileVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(QuotePdf);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvAttach.setVisibility(View.VISIBLE);
                }else {
                    tvAttach.setVisibility(View.GONE);
                }
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


        cbSameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvDeliveryAddress.setText(DailveryAdrs);
                    Toast.makeText(ViewSalesQuoteApprovalList.this, "Same Address", Toast.LENGTH_SHORT).show();
                } else {
                    Dialog dialog = new Dialog(ViewSalesQuoteApprovalList.this);
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
                    stateAdapter = new ArrayAdapter(ViewSalesQuoteApprovalList.this, R.layout.spinner_item, strState);
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
                    districtAdapter = new ArrayAdapter(ViewSalesQuoteApprovalList.this, R.layout.spinner_item, strDistrict);
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
                    hospitalAdapter = new ArrayAdapter(ViewSalesQuoteApprovalList.this, R.layout.spinner_item, strHospital);
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

        tvDepositedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDepositedDate.setError(null);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewSalesQuoteApprovalList.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewSalesQuoteApprovalList.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ViewSalesQuoteApprovalList.this, new DatePickerDialog.OnDateSetListener() {
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


        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEditText();
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Log.e(TAG, "onClick: "+strEditText.toString() );
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

        installEdt.add(edAmount);
        ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_list.removeView(addEditText);
                installEdt.remove(edAmount);
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
                        attachFile = FileUtli.from(this, contentUri);
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