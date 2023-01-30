package com.example.cistronuser.ServiceEngineer.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Formatter;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CallReportComplaintSubCategoryInterface;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.EscalatePaymentSubmitInterface;
import com.example.cistronuser.API.Interface.ServiceSpareRequestInterface;
import com.example.cistronuser.API.Interface.UpcomingCallReportInterface;
import com.example.cistronuser.API.Model.CallReportComplaintSubCategoryModel;
import com.example.cistronuser.API.Model.CallStatusModel;
import com.example.cistronuser.API.Model.CallTypeModel;
import com.example.cistronuser.API.Model.ComplaintCategoryModel;
import com.example.cistronuser.API.Model.CustomerPoResponseModel;
import com.example.cistronuser.API.Model.ServiceSpareRequestModel;
import com.example.cistronuser.API.Model.SpareRequestsRecordModel;
import com.example.cistronuser.API.Model.SparesConsumedRecordModel;
import com.example.cistronuser.API.Response.CallReportComplaintSubCategoryResponse;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.EscalatePaymentSubmitResponse;
import com.example.cistronuser.API.Response.ServiceSpareRequestResponse;
import com.example.cistronuser.API.Response.UpcomingCallReportResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.CustomerPoAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.PendingRequestupSpareAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.SpareReqAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.SparesConsumedAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingCallReport extends AppCompatActivity {

    ImageView ivBack;
    Spinner spCallType, spCallStatus;
    TextView tvCusInvoice, tvFollowUpDate, tvStartingTime, tvEndTime, tvserviceReportAttach, tvSubmit;
    EditText edName, edMobile, edWorkdone, edEngineerAdvice, edReason, edPendingReason;
    RadioGroup rbGrp;
    TextInputLayout tvReason, tvPendingReason;
    RadioButton rbYes, rbNo;
    CheckBox cbAttach;
    RatingBar ratingBar;

    //Received pending sales payment
    RelativeLayout rlRevPaymentPending;
    RadioGroup rbGrp1;
    RadioButton rbPaymentYes, rbPOaymentNo;
    TextView tvSaleORserviceAmtTag, tvRevPendingPaymnet;
    EditText edSaleORserviceAmt, edSpareAmt;

    // Yes Received Payment
    RelativeLayout rlYesRevPayment;
    TextView tvDepositedDate;
    EditText edCheckUTRno;

    // No ed Payment
    RelativeLayout rlNoRevPayment;
    RadioGroup rbGrp2;
    RadioButton rbPaymentCloseYes, rbPaymentCloseNo;

    // Spare Consumer
    RelativeLayout rlDCConsumerSpareFile, rlSpareConsumed;
    File spareFile1, spareFile2, spareFile3;
    RecyclerView rvSpareConsumer;
    TextView tvSpareFile1, tvSpareFile2, tvSpareFile3, tvSpareConsumedCount;
    String strsparefile1, strsparefile2, strsparefile3;
    SparesConsumedAdapter sparesConsumedAdapter;
    ArrayList<SparesConsumedRecordModel> sparesConsumedRecordModels = new ArrayList<>();

    // Spare Request
    RelativeLayout rlSpareRequest;
    RecyclerView rvSpareRequest;
    TextView tvSpareRequestCount;
    PendingRequestupSpareAdapter pendingRequestupSpareAdapter;
    ArrayList<SpareRequestsRecordModel> spareRequestsRecordModels = new ArrayList<>();

    //Customer PO

    RelativeLayout rlCustomerPO;
    TextView tvCustomerPOCount, tvCusPOFileInvoiceAttch;
    RecyclerView rvCustomerPO;
    File fileCustomerPO;
    String strCustomerPo;
    CustomerPoAdapter customerPoAdapter;
    ArrayList<CustomerPoResponseModel> customerPoResponseModels = new ArrayList<>();


    //Installation
    String LogsitId, StringCallNo;
    CardView cvInstallation;
    TextView tvInstallReportAttach, tvWarrentycard, tvInstallationImage3, tvInstallationImage2, tvInstallationImage1, tvInstallDate, tvPaymentInstallation, tvRecvPaymentInstallation, tvTotalamt, tvEscalate;

    String strRating, strSerAttach, strCusInvoiceAttach, strInstallImg1, strInstallImg2, strInstallImg3, strWarrenty, strInstallReport;
    File fileservice, fileinvoice, fileinstallImg1, fileinstallImg2, fileinstallImg3, fileWarrentyCard, fileinstallReport;

    //Customer Details
    TextView tvCusDetails, tvProdDetails, tvProdSerial, tvCreated, tvReportby;
    String strTotalPayment;
    Integer strPayment, strReceviedPayment;

    //Complaint & subComplaint
    RelativeLayout rlComplaint;
    Spinner spComplaint, spSubComplaint;
    ArrayList<ComplaintCategoryModel> complaintCategoryModels = new ArrayList<>();
    ArrayList<CallReportComplaintSubCategoryModel> callReportComplaintSubCategoryModels = new ArrayList<>();
    ArrayList<String> strSubCom = new ArrayList<>();
    ArrayList<String> strComplaint = new ArrayList<>();
    ArrayAdapter complaintAdapter, subComplaintAdapter;


    //CallType
    ArrayList<CallTypeModel> callTypeModels = new ArrayList<>();
    ArrayList<String> strType = new ArrayList<>();
    ArrayAdapter callTypeAdapter;
    String selected;

    //Call Status
    ArrayList<CallStatusModel> callStatusModels = new ArrayList<>();
    ArrayList<String> strStatus = new ArrayList<>();
    ArrayAdapter callStatusAdapter;

    //SpareReq
    SpareReqAdapter spareReqAdapter;
    ArrayList<ServiceSpareRequestModel> serviceSpareRequestModels = new ArrayList<>();
    RecyclerView rvReq;
    ImageView ivClose;
    String SerialID1, SerialID2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_call_report);

        ivBack = findViewById(R.id.ivBack);
        spCallType = findViewById(R.id.spCallType);
        spCallStatus = findViewById(R.id.spCallStatus);
        tvCusInvoice = findViewById(R.id.tvCusInvoice);
        tvFollowUpDate = findViewById(R.id.tvFollowUpDate);
        tvStartingTime = findViewById(R.id.tvStartingTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvserviceReportAttach = findViewById(R.id.tvserviceReportAttach);
        tvReason = findViewById(R.id.tvReason);
        edName = findViewById(R.id.edName);
        edMobile = findViewById(R.id.edMobile);
        edWorkdone = findViewById(R.id.edWorkdone);
        edEngineerAdvice = findViewById(R.id.edEngineerAdvice);
        edReason = findViewById(R.id.edReason);
        rbGrp = findViewById(R.id.rbGrp);
        rbYes = findViewById(R.id.rbYes);
        tvPendingReason = findViewById(R.id.tvPendingReason);
        edPendingReason = findViewById(R.id.edPendingReason);
        rbNo = findViewById(R.id.rbNo);
        cbAttach = findViewById(R.id.cbAttach);
        ratingBar = findViewById(R.id.ratingBar);
        tvSubmit = findViewById(R.id.tvSubmit);
        tvCusDetails = findViewById(R.id.tvCusDetails);
        tvProdDetails = findViewById(R.id.tvProdDetails);
        tvProdSerial = findViewById(R.id.tvProdSerial);
        tvCreated = findViewById(R.id.tvCreated);
        tvReportby = findViewById(R.id.tvReportby);
        rlComplaint = findViewById(R.id.rlComplaint);
        spComplaint = findViewById(R.id.spComplaint);
        spSubComplaint = findViewById(R.id.spSubComplaint);


        // *********** Installation *********** //
        tvInstallReportAttach = findViewById(R.id.tvInstallReportAttach);
        tvWarrentycard = findViewById(R.id.tvWarrentycard);
        tvInstallationImage3 = findViewById(R.id.tvInstallationImage3);
        tvInstallationImage2 = findViewById(R.id.tvInstallationImage2);
        tvInstallationImage1 = findViewById(R.id.tvInstallationImage1);
        tvInstallDate = findViewById(R.id.tvInstallDate);
        tvPaymentInstallation = findViewById(R.id.tvPaymentInstallation);
        tvRecvPaymentInstallation = findViewById(R.id.tvRecvPaymentInstallation);
        cvInstallation = findViewById(R.id.cvInstallation);
        tvTotalamt = findViewById(R.id.tvTotalamt);
        tvEscalate = findViewById(R.id.tvEscalate);
        // *********** Installation End *********** //


        // ***********  spare Consumer *********** //
        rlSpareConsumed = findViewById(R.id.rlSpareConsumed);
        rvSpareConsumer = findViewById(R.id.rvSpareConsumer);
        tvSpareConsumedCount = findViewById(R.id.tvSpareConsumedCount);
        rlDCConsumerSpareFile = findViewById(R.id.rlDCConsumerSpareFile);
        tvSpareFile1 = findViewById(R.id.tvSpareFile1);
        tvSpareFile2 = findViewById(R.id.tvSpareFile2);
        tvSpareFile3 = findViewById(R.id.tvSpareFile3);
        // *********** spare Consumer  End *********** //

        // ***********  Spare Request *********** //
        rlSpareRequest = findViewById(R.id.rlSpareRequest);
        rvSpareRequest = findViewById(R.id.rvSpareRequest);
        tvSpareRequestCount = findViewById(R.id.tvSpareRequestCount);

        // ***********  Spare Request End *********** //


        // ***********  Customer PO *********** //
        rlCustomerPO = findViewById(R.id.rlCustomerPO);
        tvCustomerPOCount = findViewById(R.id.tvCustomerPOCount);
        tvCusPOFileInvoiceAttch = findViewById(R.id.tvCusPOFileInvoiceAttch);
        rvCustomerPO = findViewById(R.id.rvCustomerPO);

        // ***********  Customer PO End *********** //

        // *********** Yes OR No Payment Received *********** //
        rlYesRevPayment = findViewById(R.id.rlYesRevPayment);
        tvDepositedDate = findViewById(R.id.tvDepositedDate);
        edCheckUTRno = findViewById(R.id.edCheckUTRno);
        rlNoRevPayment = findViewById(R.id.rlNoRevPayment);
        rbGrp2 = findViewById(R.id.rbGrp2);
        rbPaymentCloseYes = findViewById(R.id.rbPaymentCloseYes);
        rbPaymentCloseNo = findViewById(R.id.rbPaymentCloseNo);
        // *********** Yes OR No Payment Received End *********** //


        // *********** Received pending sales payment *********** //
        rlRevPaymentPending = findViewById(R.id.rlRevPaymentPending);
        rbGrp1 = findViewById(R.id.rbGrp1);
        rbPaymentYes = findViewById(R.id.rbPaymentYes);
        rbPOaymentNo = findViewById(R.id.rbPOaymentNo);
        tvSaleORserviceAmtTag = findViewById(R.id.tvSaleORserviceAmtTag);
        edSaleORserviceAmt = findViewById(R.id.edSaleORserviceAmt);
        tvRevPendingPaymnet = findViewById(R.id.tvRevPendingPaymnet);
        edSpareAmt = findViewById(R.id.edSpareAmt);
        // *********** Received pending sales payment End *********** //


        // ************ File Access Permission ***********//
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        // ************ File Access Permission End ***********//

        // *********** GetString **********//
        String id = getIntent().getStringExtra("id");
        strRating = String.valueOf(ratingBar.getRating());
        // *********** GetString End **********//

        // *********** Current Date **********//
        Date d = new Date();
        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
        tvInstallDate.setText(s);
        // *********** Current Date End **********//

        //********Customer Detalils ******************//
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Call Report...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UpcomingCallReportInterface upcomingCallReportInterface = APIClient.getClient().create(UpcomingCallReportInterface.class);
        upcomingCallReportInterface.CallUpcomingCallReport("callReporting", id, PreferenceManager.getEmpID(this)).enqueue(new Callback<UpcomingCallReportResponse>() {
            @Override
            public void onResponse(Call<UpcomingCallReportResponse> call, Response<UpcomingCallReportResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        PreferenceManager.saveCallNo(UpcomingCallReport.this, response.body().getUpcomingCallReportModel().getCallInfoModel().getCallNo());
                        StringCallNo = response.body().getUpcomingCallReportModel().getCallInfoModel().getCallNo();
                        LogsitId = response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsId();
                        tvCusDetails.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getCustDetail());
                        tvProdDetails.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getProdDetail());
                        tvProdSerial.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getProSerial());
                        tvCreated.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getCreatedBy());
                        tvReportby.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getReportBy());
                        tvPaymentInstallation.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_install());
                        tvRecvPaymentInstallation.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_installr());
                        tvRevPendingPaymnet.setText(response.body().getUpcomingCallReportModel().getLabelModel().getLabel1());
                        tvSaleORserviceAmtTag.setText(response.body().getUpcomingCallReportModel().getLabelModel().getLabel2());
                        SerialID1 = response.body().getUpcomingCallReportModel().getSeriesid1();
                        SerialID2 = response.body().getUpcomingCallReportModel().getSeriesid2();


                        // ********** Escalate ********** //

                        if (response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_esc_ins().trim().equals("0")) {
                            tvEscalate.setVisibility(View.VISIBLE);
                        } else {
                            tvEscalate.setVisibility(View.GONE);
                        }

                        // ********** Escalate End ********** //


                        // ********** Spare Consumed  ********** //

                        if (response.body().getUpcomingCallReportModel().getSparesConsumedModel().getCount().trim().equals("0")) {
                            rlSpareConsumed.setVisibility(View.GONE);
                            rlDCConsumerSpareFile.setVisibility(View.GONE);
                        } else {
                            rlSpareConsumed.setVisibility(View.VISIBLE);
                            rlDCConsumerSpareFile.setVisibility(View.VISIBLE);
                        }
                        tvSpareConsumedCount.setText(response.body().getUpcomingCallReportModel().getSparesConsumedModel().getCount());
                        sparesConsumedAdapter.sparesConsumedRecordModels = response.body().getUpcomingCallReportModel().getSparesConsumedModel().getSparesConsumedRecordModels();
                        sparesConsumedAdapter.notifyDataSetChanged();
                        // ********** Spare Consumed End  ********** //

                        // ********** Spare Request  ********** //
                        if (response.body().getUpcomingCallReportModel().getSpareRequestsModel().getCount().trim().equals("0")) {
                            rlSpareRequest.setVisibility(View.GONE);
                        } else {
                            rlSpareRequest.setVisibility(View.VISIBLE);
                        }
                        tvSpareRequestCount.setText(response.body().getUpcomingCallReportModel().getSpareRequestsModel().getCount());
                        pendingRequestupSpareAdapter.spareRequestsRecordModels = response.body().getUpcomingCallReportModel().getSpareRequestsModel().getSpareRequestsRecordModels();
                        pendingRequestupSpareAdapter.notifyDataSetChanged();
                        // ********** Spare Request End  ********** //

                        // ***********  Customer PO  *********** //
                        if (response.body().getUpcomingCallReportModel().getCustomerPoModel().getCount().trim().equals("0")) {
                            rlCustomerPO.setVisibility(View.GONE);
                        } else {
                            rlCustomerPO.setVisibility(View.VISIBLE);
                        }
                        tvCustomerPOCount.setText(response.body().getUpcomingCallReportModel().getCustomerPoModel().getCount());
                        customerPoAdapter.customerPoResponseModels = response.body().getUpcomingCallReportModel().getCustomerPoModel().getCustomerPoResponseModels();
                        customerPoAdapter.notifyDataSetChanged();
                        // ***********  Customer PO End *********** //

                        // ********** InstallpaymentCalc ********** //

                        strPayment = Integer.valueOf(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_install());
                        strReceviedPayment = Integer.valueOf(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_installr());
                        Integer Total = Integer.valueOf(strPayment - strReceviedPayment);
                        strTotalPayment = Total.toString();
                        tvTotalamt.setText(strTotalPayment + "/- escalate payment on installation.");

                        // ********** InstallpaymentCalc End ********** //


                        if (response.body().getUpcomingCallReportModel().getCompliantRequired().trim().equals("1")) {
                            rlComplaint.setVisibility(View.VISIBLE);
                        } else {
                            rlComplaint.setVisibility(View.GONE);
                        }

                        // ***********  Call Type ******** //


                        callTypeModels = response.body().getUpcomingCallReportModel().getCallTypeModels();
                        for (int i = 0; i < callTypeModels.size(); i++) {
                            strType.add(callTypeModels.get(i).getText());
                            selected = String.valueOf(callTypeModels.get(i).getSelected().trim().equals("1"));
                            if (callTypeModels.get(i).getSelected().trim().equals("1")) {
                                spCallType.setSelection(i);
                            }
                        }
                        callTypeAdapter.notifyDataSetChanged();
                        // ***********  Call Type End ******** //


                        // ***********  Call Status ******** //

                        callStatusModels = response.body().getUpcomingCallReportModel().getCallStatusModels();
                        for (int i = 0; i < callStatusModels.size(); i++) {
                            strStatus.add(callStatusModels.get(i).getText());
                        }
                        callStatusAdapter.notifyDataSetChanged();
                        // ***********  Call Status End ******** //

                        // ***********  Complaint Category  ******** //

                        complaintCategoryModels = response.body().getUpcomingCallReportModel().getComplaintCategoryModels();
                        for (int i = 0; i < complaintCategoryModels.size(); i++) {
                            strComplaint.add(complaintCategoryModels.get(i).getText());
                        }
                        complaintAdapter.notifyDataSetChanged();
                        // ***********  Complaint Category End  ******** //
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<UpcomingCallReportResponse> call, Throwable t) {

            }
        });
        //********Customer Detalils End ******************//


        //******** Spare Counsumed Recycleview ******************//

        sparesConsumedAdapter = new SparesConsumedAdapter(this, sparesConsumedRecordModels);
        LinearLayoutManager spareCounsumed = new LinearLayoutManager(this);
        spareCounsumed.setOrientation(RecyclerView.VERTICAL);
        rvSpareConsumer.setAdapter(sparesConsumedAdapter);
        rvSpareConsumer.setLayoutManager(spareCounsumed);

        //******** Spare Counsumed Recycleview End ******************//

        //******** Spare Request Recycleview ******************//
        pendingRequestupSpareAdapter = new PendingRequestupSpareAdapter(this, spareRequestsRecordModels);
        LinearLayoutManager spareReq = new LinearLayoutManager(this);
        spareReq.setOrientation(RecyclerView.VERTICAL);
        rvSpareRequest.setLayoutManager(spareReq);
        rvSpareRequest.setAdapter(pendingRequestupSpareAdapter);

        //******** Spare Request Recycleview End ******************//


        // ***********  Customer PO Recycleview *********** //
        customerPoAdapter = new CustomerPoAdapter(this, customerPoResponseModels);
        LinearLayoutManager custPO = new LinearLayoutManager(this);
        custPO.setOrientation(RecyclerView.VERTICAL);
        rvCustomerPO.setLayoutManager(custPO);
        rvCustomerPO.setAdapter(customerPoAdapter);

        // ***********  Customer PO Recycleview End *********** //


        //Complaint Details

        complaintAdapter = new ArrayAdapter(this, R.layout.spinner_item, strComplaint);
        complaintAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spComplaint.setAdapter(complaintAdapter);
        spComplaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ComplaintID = complaintCategoryModels.get(position).getId();
                CallSubComplaint(ComplaintID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //CallType
        callTypeAdapter = new ArrayAdapter(this, R.layout.spinner_item, strType);
        callTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCallType.setAdapter(callTypeAdapter);
        spCallType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (callTypeModels.get(position).getText().trim().equals("Paid")) {
                    tvCusInvoice.setVisibility(View.VISIBLE);
                    cvInstallation.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);
                } else if (callTypeModels.get(position).getText().trim().equals("Installation")) {
                    cvInstallation.setVisibility(View.VISIBLE);
                    tvCusInvoice.setVisibility(View.GONE);
                    tvserviceReportAttach.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.VISIBLE);
                    tvInstallationImage2.setVisibility(View.VISIBLE);
                    tvInstallationImage3.setVisibility(View.VISIBLE);
                    tvWarrentycard.setVisibility(View.VISIBLE);
                    tvInstallReportAttach.setVisibility(View.VISIBLE);
                } else {
                    tvCusInvoice.setVisibility(View.GONE);
                    cvInstallation.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Call Status
        callStatusAdapter = new ArrayAdapter(this, R.layout.spinner_item, strStatus);
        callStatusAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCallStatus.setAdapter(callStatusAdapter);
        spCallStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (callStatusModels.get(position).getText().trim().equals("Pending")) {
                    tvPendingReason.setVisibility(View.VISIBLE);
                    tvFollowUpDate.setVisibility(View.VISIBLE);
                    tvserviceReportAttach.setVisibility(View.VISIBLE);


                    tvReason.setVisibility(View.GONE);
                    cvInstallation.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);

                } else {
                    tvPendingReason.setVisibility(View.GONE);
                    tvFollowUpDate.setVisibility(View.GONE);
                }


                if (callStatusModels.get(position).getText().trim().equals("Require Spare's")) {
                    tvserviceReportAttach.setVisibility(View.VISIBLE);

                    cvInstallation.setVisibility(View.GONE);
                    tvReason.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);

                    CallRequiredSpareDialog();
                }


                if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed")) {
                    tvInstallationImage1.setVisibility(View.VISIBLE);
                    tvInstallationImage2.setVisibility(View.VISIBLE);
                    tvInstallationImage3.setVisibility(View.VISIBLE);
                    tvWarrentycard.setVisibility(View.VISIBLE);
                    cvInstallation.setVisibility(View.VISIBLE);
                    tvInstallReportAttach.setVisibility(View.VISIBLE);
                    tvserviceReportAttach.setVisibility(View.GONE);
                    tvReason.setVisibility(View.GONE);
                    rlDCConsumerSpareFile.setVisibility(View.VISIBLE);
                    rlRevPaymentPending.setVisibility(View.VISIBLE);

                } else if (callStatusModels.get(position).getText().trim().equals("Closed")) {
                    rlRevPaymentPending.setVisibility(View.VISIBLE);
                    rlDCConsumerSpareFile.setVisibility(View.VISIBLE);

                } else {

                    cvInstallation.setVisibility(View.GONE);
                    rlRevPaymentPending.setVisibility(View.GONE);
                    rlDCConsumerSpareFile.setVisibility(View.GONE);
                }

                if (tvSpareConsumedCount.getText().toString().trim().equals("0") && callStatusModels.get(position).getText().trim().equals("Closed")) {
                    rlRevPaymentPending.setVisibility(View.VISIBLE);
                    rlDCConsumerSpareFile.setVisibility(View.GONE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SubComplaint
        subComplaintAdapter = new ArrayAdapter(this, R.layout.spinner_item, strSubCom);
        subComplaintAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spSubComplaint.setAdapter(subComplaintAdapter);
        spSubComplaint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed")) {
                        tvInstallationImage1.setVisibility(View.VISIBLE);
                        tvInstallationImage2.setVisibility(View.VISIBLE);
                        tvInstallationImage3.setVisibility(View.VISIBLE);
                        tvWarrentycard.setVisibility(View.VISIBLE);
                        tvInstallReportAttach.setVisibility(View.VISIBLE);
                        tvserviceReportAttach.setVisibility(View.GONE);
                        tvReason.setVisibility(View.GONE);
                    } else {
                        tvserviceReportAttach.setVisibility(View.VISIBLE);
                        tvReason.setVisibility(View.GONE);
                        tvInstallationImage1.setVisibility(View.GONE);
                        tvInstallationImage2.setVisibility(View.GONE);
                        tvInstallationImage3.setVisibility(View.GONE);
                        tvWarrentycard.setVisibility(View.GONE);
                        tvInstallReportAttach.setVisibility(View.GONE);
                    }


                } else {
                    tvReason.setVisibility(View.VISIBLE);
                    tvserviceReportAttach.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);
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

        tvInstallationImage1.setOnClickListener(new View.OnClickListener() {
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

        tvInstallationImage2.setOnClickListener(new View.OnClickListener() {
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

        tvInstallationImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 5);
                } catch (Exception e) {

                }
            }
        });

        tvWarrentycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 6);
                } catch (Exception e) {

                }
            }
        });

        tvInstallReportAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 7);
                } catch (Exception e) {

                }
            }
        });

        tvSpareFile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 8);
                } catch (Exception e) {

                }
            }
        });

        tvSpareFile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 9);
                } catch (Exception e) {

                }
            }
        });

        tvSpareFile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 10);
                } catch (Exception e) {

                }
            }
        });


        tvCusPOFileInvoiceAttch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 11);
                } catch (Exception e) {

                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvDepositedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpcomingCallReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDepositedDate.setText(strDate);

                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                datePickerDialog.show();


            }

        });

        tvFollowUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.add(Calendar.DATE, 1);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpcomingCallReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;

                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvFollowUpDate.setText(strDate);

                    }

                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                datePickerDialog.show();


            }
        });

        tvStartingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(UpcomingCallReport.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        tvStartingTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpcomingCallReport.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        tvEndTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        tvEscalate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpcomingCallReport.this, R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to escalate?");
                builder.setTitle("Escalate ");
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(UpcomingCallReport.this, R.style.ProgressBarDialog);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        EscalatePaymentSubmitInterface escalatePaymentSubmitInterface = APIClient.getClient().create(EscalatePaymentSubmitInterface.class);
                        escalatePaymentSubmitInterface.submitEscalate("escalatePayment", LogsitId, String.valueOf(strPayment), String.valueOf(strReceviedPayment), StringCallNo, PreferenceManager.getEmpID(UpcomingCallReport.this)).enqueue(new Callback<EscalatePaymentSubmitResponse>() {
                            @Override
                            public void onResponse(Call<EscalatePaymentSubmitResponse> call, Response<EscalatePaymentSubmitResponse> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        dialogInterface.dismiss();
                                        Toast.makeText(UpcomingCallReport.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                        tvEscalate.setVisibility(View.GONE);
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<EscalatePaymentSubmitResponse> call, Throwable t) {

                            }
                        });


                    }
                }));
                builder.setNegativeButton("No", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        //  ************** Do you want to consume spares Check ************** //

        rbGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = rbGrp.findViewById(checkedId);
                int rb = rbGrp.indexOfChild(view);
                switch (rb) {
                    case 0:
                        Dialog dialog = new Dialog(UpcomingCallReport.this);
                        dialog.setContentView(R.layout.consumer_spare_dialog_recycleview);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                        break;
                    case 1:
                        break;
                }
            }
        });


        //  ************** Do you want to consume spares Check End ************** //


        //  ************** Received pending sales payment Check ************** //
        rbGrp1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = rbGrp1.findViewById(checkedId);

                int rb1 = rbGrp1.indexOfChild(view);
                switch (rb1) {
                    case 0:
                        rlYesRevPayment.setVisibility(View.VISIBLE);
                        rlNoRevPayment.setVisibility(View.GONE);
                        break;
                    case 1:
                        rlYesRevPayment.setVisibility(View.GONE);
                        rlNoRevPayment.setVisibility(View.VISIBLE);
                        break;

                }

            }
        });
        //  ************** Received pending sales payment Check End ************** //

    }


    private void CallRequiredSpareDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.spare_request_dialog_recycleview);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        ivClose = dialog.findViewById(R.id.ivClose);
        rvReq = dialog.findViewById(R.id.rvReq);

        CallSpareReq();
        spareReqAdapter = new SpareReqAdapter(this, serviceSpareRequestModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvReq.setLayoutManager(linearLayoutManager);
        rvReq.setAdapter(spareReqAdapter);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void CallSpareReq() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceSpareRequestInterface spareRequestInterface = APIClient.getClient().create(ServiceSpareRequestInterface.class);
        spareRequestInterface.CallReq("onRequireSparesCallStatus", SerialID1, SerialID2).enqueue(new Callback<ServiceSpareRequestResponse>() {
            @Override
            public void onResponse(Call<ServiceSpareRequestResponse> call, Response<ServiceSpareRequestResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        spareReqAdapter.serviceSpareRequestModels = response.body().getServiceSpareRequestModels();
                        spareReqAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ServiceSpareRequestResponse> call, Throwable t) {

            }
        });
    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error");
            selectedTextView.setTextColor(Color.RED);
            selectedTextView.setText(error);
            spinner.performClick();

        }
    }


    private void CallSubComplaint(String complaintID) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        CallReportComplaintSubCategoryInterface callReportComplaintSubCategoryInterface = APIClient.getClient().create(CallReportComplaintSubCategoryInterface.class);
        callReportComplaintSubCategoryInterface.CallSubCat("getCompliantSubcategory", complaintID).enqueue(new Callback<CallReportComplaintSubCategoryResponse>() {
            @Override
            public void onResponse(Call<CallReportComplaintSubCategoryResponse> call, Response<CallReportComplaintSubCategoryResponse> response) {
                try {
                    if (response.body().getCallReportComplaintSubCategoryModels().size() > 0) {
                        progressDialog.dismiss();
                        callReportComplaintSubCategoryModels = response.body().getCallReportComplaintSubCategoryModels();
                        strSubCom.clear();
                        for (int i = 0; i < callReportComplaintSubCategoryModels.size(); i++) {
                            strSubCom.add(callReportComplaintSubCategoryModels.get(i).getText());
                        }
                        subComplaintAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CallReportComplaintSubCategoryResponse> call, Throwable t) {

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
                            String myStr = strCusInvoiceAttach;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            System.out.println(extension);
                            if (extension.equals(".pdf")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else if (extension.equals(".jpeg")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else if (extension.equals(".png")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        } else {
                            Toast.makeText(this, "Plese 5 mb", Toast.LENGTH_SHORT).show();
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
                            String myStr = strSerAttach;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);

                            if (extension.equals(".pdf")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else if (extension.equals(".jpg")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else if (extension.equals(".png")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


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


            case 3:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strInstallImg1 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strInstallImg1, Toast.LENGTH_SHORT).show();


                    try {
//                        String mb= Formatter.formatShortFileSize(this,fileinstallImg1.length());
//                        Log.e(TAG, "onActivityResult: "+mb.replace("MB"," ") );
//                        int MB= Integer.parseInt(mb.replace("MB"," "));
//                        Log.e(TAG, "onActivityResult: hgh"+MB );

//                        int maxFileSize = 5 * 1024 * 1024;
//                        Long l = fileinstallImg1.length();
//                        String fileSize = l.toString();
//                        int finalFileSize = Integer.parseInt(fileSize);


                        if (strInstallImg1.length() > 0) {
                               String myStr = strInstallImg1;
                               int index = myStr.lastIndexOf(".");
                               String extension = myStr.substring(index);
                               if (extension.equals(".pdf")) {
                                   tvInstallationImage1.setText(strInstallImg1);
                               } else if (extension.equals(".jpg")) {
                                   tvInstallationImage1.setText(strInstallImg1);
                               } else if (extension.equals(".png")) {
                                   tvInstallationImage1.setText(strInstallImg1);
                               } else {
                                   AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                   builder.setMessage("Please Select Pdf and Image File Only ..");
                                   AlertDialog dialog = builder.create();
                                   dialog.show();
                               }

                           }
                          

                    } catch (Exception e) {

                    }

                    try {
                        fileinstallImg1 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strInstallImg2 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strInstallImg2, Toast.LENGTH_SHORT).show();

                    try {
                        if (strInstallImg2.length() > 0) {
                            String myStr = strInstallImg2;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else if (extension.equals(".jpg")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else if (extension.equals(".png")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileinstallImg2 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            case 5:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strInstallImg3 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strInstallImg3, Toast.LENGTH_SHORT).show();

                    try {
                        if (strInstallImg3.length() > 0) {
                            String myStr = strInstallImg3;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else if (extension.equals(".jpg")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else if (extension.equals(".png")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileinstallImg3 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            case 6:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strWarrenty = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strWarrenty, Toast.LENGTH_SHORT).show();

                    try {
                        if (strWarrenty.length() > 0) {
                            String myStr = strWarrenty;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else if (extension.equals(".jpg")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else if (extension.equals(".png")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileWarrentyCard = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            case 7:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strInstallReport = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strInstallReport, Toast.LENGTH_SHORT).show();

                    try {
                        if (strInstallReport.length() > 0) {
                            String myStr = strInstallReport;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else if (extension.equals(".jpg")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else if (extension.equals(".png")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileinstallReport = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;


            case 8:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strsparefile1 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strsparefile1, Toast.LENGTH_SHORT).show();

                    try {
                        if (strsparefile1.length() > 0) {
                            String myStr = strsparefile1;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else if (extension.equals(".png")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        spareFile1 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;


            case 9:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strsparefile2 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strsparefile2, Toast.LENGTH_SHORT).show();

                    try {
                        if (strsparefile2.length() > 0) {
                            String myStr = strsparefile2;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else if (extension.equals(".png")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        spareFile2 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;


            case 10:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strsparefile3 = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strsparefile3, Toast.LENGTH_SHORT).show();

                    try {
                        if (strsparefile3.length() > 0) {
                            String myStr = strsparefile3;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else if (extension.equals(".png")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        spareFile3 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;


            case 11:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strCustomerPo = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strCustomerPo, Toast.LENGTH_SHORT).show();

                    try {
                        if (strCustomerPo.length() > 0) {
                            String myStr = strCustomerPo;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else if (extension.equals(".jpg")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else if (extension.equals(".png")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }

                    } catch (Exception e) {

                    }

                    try {
                        fileCustomerPO = FileUtli.from(this, contentUri);
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