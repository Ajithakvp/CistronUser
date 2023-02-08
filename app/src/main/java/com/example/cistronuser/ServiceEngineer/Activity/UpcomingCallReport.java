package com.example.cistronuser.ServiceEngineer.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static okhttp3.RequestBody.create;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.cistronuser.API.Interface.CallReportSubmitInterface;
import com.example.cistronuser.API.Interface.CallReportingUpComingSubmitInterface;
import com.example.cistronuser.API.Interface.ConsumeCusSpareSubmitInterface;
import com.example.cistronuser.API.Interface.ConsumeSpareSubmitInterface;
import com.example.cistronuser.API.Interface.EscalatePaymentSubmitInterface;
import com.example.cistronuser.API.Interface.InstallamentEscalatedSubmitInterface;
import com.example.cistronuser.API.Interface.ServiceSpareRequestInterface;
import com.example.cistronuser.API.Interface.SupplyEscalatedSubmitedInterface;
import com.example.cistronuser.API.Interface.UpcomingCallReportInterface;
import com.example.cistronuser.API.Interface.YesDoYouConsumeSpareInterface;
import com.example.cistronuser.API.Model.CallReportComplaintSubCategoryModel;
import com.example.cistronuser.API.Model.CallStatusModel;
import com.example.cistronuser.API.Model.CallTypeModel;
import com.example.cistronuser.API.Model.ComplaintCategoryModel;
import com.example.cistronuser.API.Model.ConsumeSpareRecordModel;
import com.example.cistronuser.API.Model.ConsumerCustSpareRecordModel;
import com.example.cistronuser.API.Model.CustomerPoResponseModel;
import com.example.cistronuser.API.Model.CustomerPoSpareRecordModel;
import com.example.cistronuser.API.Model.InstallamentModel;
import com.example.cistronuser.API.Model.ServiceSpareRequestModel;
import com.example.cistronuser.API.Model.SpareInwardRecordModel;
import com.example.cistronuser.API.Model.SpareRequestsRecordModel;
import com.example.cistronuser.API.Model.SparesConsumedRecordModel;
import com.example.cistronuser.API.Response.CallReportComplaintSubCategoryResponse;
import com.example.cistronuser.API.Response.CallReportServiceSubmitResponse;
import com.example.cistronuser.API.Response.CallReportSpareConsumedSubmitResponse;
import com.example.cistronuser.API.Response.CallReportSubmitCusPoRespones;
import com.example.cistronuser.API.Response.CallReportSubmitResponse;
import com.example.cistronuser.API.Response.CallReportingUpComingSubmitResponse;
import com.example.cistronuser.API.Response.CallReportsubmitCusInvoiceResponse;
import com.example.cistronuser.API.Response.ConsumeSpareSubmitResponse;
import com.example.cistronuser.API.Response.ConsumerCusSpareSubmitResponse;
import com.example.cistronuser.API.Response.EscalatePaymentSubmitResponse;
import com.example.cistronuser.API.Response.InstallamentEscalatedSubmitResponse;
import com.example.cistronuser.API.Response.ServiceSpareRequestResponse;
import com.example.cistronuser.API.Response.SupplyEscalatedSubmitedResponse;
import com.example.cistronuser.API.Response.UpcomingCallReportResponse;
import com.example.cistronuser.API.Response.YesDoYouConsumeSpareResponse;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.ConsumeCusSpareYesAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.ConsumePoSpareYesAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.ConsumeSpareYesAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.CustomerPoAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.InstallmentAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.PendingRequestupSpareAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.SpareInwardListAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.SpareReqAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.SparesConsumedAdapter;
import com.google.android.material.textfield.TextInputLayout;

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


    //Supply
    CardView cvSupply;
    int aftPayment, RevAfrDsptch;
    TextView tvPaymentafterDispatch, tvRecvPaymentDispatch, tvSupplyTotalamt, tvSupplyEscalate, tvSupplyEscalateDetails;
    //Supplyupload
    TextView tvWayBill, tvLR;
    String strWayBill, strLR;
    File fileWaybill, fileLR;

    //Customer Details
    TextView tvCusDetails, tvProdDetails, tvProdSerial, tvCreated, tvReportby;
    String strTotalPayment;
    Integer strPayment, strReceviedPayment;

    //Complaint & subComplaint
    RelativeLayout rlComplaint;
    Spinner spComplaint, spSubComplaint;
    EditText edTypeComplaintCat, edTypeSubComplaintCat;
    TextView tvTypeComplaintCat, tvTypeSubComplaintCat, tvSubComplaint;
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


    //SpareInWard
    RelativeLayout rlSpareInward;
    TextView tvSpareInwardCount;
    RecyclerView rvSpareInward;
    SpareInwardListAdapter spareInwardListAdapter;
    ArrayList<SpareInwardRecordModel> spareInwardRecordModels = new ArrayList<>();

    //Installament
    CardView cvInstallament;
    RecyclerView rvInstallment;
    int installmentVisble;
    TextView tvTotalPOI, tvTotalRPOI, tvInstallmentTotalamt, tvInstallmentEscalate;
    InstallmentAdapter installmentAdapter;
    ArrayList<InstallamentModel> installamentModels = new ArrayList<>();
    ArrayList<String>strInstallament=new ArrayList<>();
    String tm,bp_installmentTot,bp_installmentrTot;


    //Yes DoYouConsumeSpares
    RelativeLayout rlCustomerPOSpares, rlConsumeSpares, rlConsumecusSpares;
    RecyclerView rvCustPoSpareYes, rvConsumespareYes, rvConsumeCusSpareYes;
    TextView tvCusPoSpareCount, tvConsumeSpareCount, tvConsumecusSpareCount;
    ConsumeCusSpareYesAdapter consumeCusSpareYesAdapter;
    ArrayList<ConsumerCustSpareRecordModel> consumerCustSpareRecordModels = new ArrayList<>();
    ConsumePoSpareYesAdapter consumePoSpareYesAdapter; // *** Customer PO Spare *** //
    ArrayList<CustomerPoSpareRecordModel> customerPoSpareRecordModels = new ArrayList<>();
    ConsumeSpareYesAdapter consumeSpareYesAdapter;
    ArrayList<ConsumeSpareRecordModel> consumeSpareRecordModels = new ArrayList<>();

    String DoYouConsumerID, PartID, ComplaintID, SubComplaintCatID, CallRegID, hpidd, pdtidd, chk1, bp_install, bp_installr;
    String CallAssignId, CallStatusID, CallTypePayoptionsID, Spc, CusPoradiobID;
    //Validation
    String ComplaintValidRequired;
    int SpareDocVaild, TypeValid;


    //rbGrup1Id
    int rbconsumerSpareID;
    int rbClosePayID;
    int rbRecPendingID;
    int cbSetID;


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
        edTypeComplaintCat = findViewById(R.id.edTypeComplaintCat);
        edTypeSubComplaintCat = findViewById(R.id.edTypeSubComplaintCat);
        tvTypeComplaintCat = findViewById(R.id.tvTypeComplaintCat);
        tvTypeSubComplaintCat = findViewById(R.id.tvTypeSubComplaintCat);
        tvSubComplaint = findViewById(R.id.tvSubComplaint);


        // *********** Supply *****************//
        cvSupply = findViewById(R.id.cvSupply);
        tvPaymentafterDispatch = findViewById(R.id.tvPaymentafterDispatch);
        tvRecvPaymentDispatch = findViewById(R.id.tvRecvPaymentDispatch);
        tvSupplyTotalamt = findViewById(R.id.tvSupplyTotalamt);
        tvSupplyEscalateDetails = findViewById(R.id.tvSupplyEscalateDetails);
        tvSupplyEscalate = findViewById(R.id.tvSupplyEscalate);
        tvLR = findViewById(R.id.tvLR);
        tvWayBill = findViewById(R.id.tvWayBill);
        // *********** Supply End *****************//


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


        // *********** Installament *********** //
        cvInstallament = findViewById(R.id.cvInstallament);
        rvInstallment = findViewById(R.id.rvInstallment);
        tvTotalPOI = findViewById(R.id.tvTotalPOI);
        tvTotalRPOI = findViewById(R.id.tvTotalRPOI);
        tvInstallmentTotalamt = findViewById(R.id.tvInstallmentTotalamt);
        tvInstallmentEscalate = findViewById(R.id.tvInstallmentEscalate);
        // *********** Installament end *********** //


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

        // ***********  Spare Inward *********** //
        rlSpareInward = findViewById(R.id.rlSpareInward);
        tvSpareInwardCount = findViewById(R.id.tvSpareInwardCount);
        rvSpareInward = findViewById(R.id.rvSpareInward);
        // ***********  Spare Inward End *********** //

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
        CharSequence s = DateFormat.format("yyyy-MM-d", d.getTime());
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
                        tvPaymentafterDispatch.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_ba_dispatch());
                        tvRecvPaymentDispatch.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_ba_dispatchr());
                        SerialID1 = response.body().getUpcomingCallReportModel().getSeriesid1();
                        SerialID2 = response.body().getUpcomingCallReportModel().getSeriesid2();
                        CallAssignId = response.body().getUpcomingCallReportModel().getCallInfoModel().getCallAssignId();
                        CallRegID = response.body().getUpcomingCallReportModel().getCallInfoModel().getCallId();
                        Spc = response.body().getUpcomingCallReportModel().getHiddenValuesModel().getSpc();
                        hpidd = response.body().getUpcomingCallReportModel().getHiddenValuesModel().getHpidd();
                        pdtidd = response.body().getUpcomingCallReportModel().getHiddenValuesModel().getPdtidd();
                        chk1 = response.body().getUpcomingCallReportModel().getHiddenValuesModel().getChk1();
                        DoYouConsumerID = response.body().getUpcomingCallReportModel().getId();
                        PartID = response.body().getUpcomingCallReportModel().getHiddenValuesModel().getJsonPartIds();
                        bp_installr = response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_install();
                        bp_install = response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_installr();


                        // ***********  Customer PO  *********** //
                        tvCustomerPOCount.setText(response.body().getUpcomingCallReportModel().getCustomerPoModel().getCount());
                        customerPoAdapter.customerPoResponseModels = response.body().getUpcomingCallReportModel().getCustomerPoModel().getCustomerPoResponseModels();
                        customerPoAdapter.notifyDataSetChanged();
                        // ***********  Customer PO End *********** //

                        // ********** Spare Consumed  ********** //

                        SpareDocVaild = Integer.parseInt(response.body().getUpcomingCallReportModel().getSparesConsumedModel().getCount());
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

                        // ********** Spare Inward  ********** //
                        if (response.body().getUpcomingCallReportModel().getSpareInwardModel().getCount().trim().equals("0")) {
                            rlSpareInward.setVisibility(View.GONE);
                        } else {
                            rlSpareInward.setVisibility(View.VISIBLE);
                        }
                        tvSpareInwardCount.setText(response.body().getUpcomingCallReportModel().getSpareInwardModel().getCount());
                        spareInwardListAdapter.spareInwardRecordModels = response.body().getUpcomingCallReportModel().getSpareInwardModel().getSpareInwardRecordModels();
                        spareInwardListAdapter.notifyDataSetChanged();
                        // ********** Spare Inward End  ********** //

                        if (response.body().getUpcomingCallReportModel().getCustomerPoModel().getCount().trim().equals("0")) {
                            rlCustomerPO.setVisibility(View.GONE);
                        } else {
                            rlCustomerPO.setVisibility(View.VISIBLE);
                        }


                        // ***********  Call Type ******** //
                        callTypeModels = response.body().getUpcomingCallReportModel().getCallTypeModels();
                        for (int i = 0; i < callTypeModels.size(); i++) {
                            strType.add(callTypeModels.get(i).getText());
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

                        ComplaintValidRequired = response.body().getUpcomingCallReportModel().getCompliantRequired();
                        if (response.body().getUpcomingCallReportModel().getCompliantRequired().trim().equals("1")) {
                            rlComplaint.setVisibility(View.VISIBLE);
                        } else {
                            rlComplaint.setVisibility(View.GONE);
                        }

                        complaintCategoryModels = response.body().getUpcomingCallReportModel().getComplaintCategoryModels();
                        for (int i = 0; i < complaintCategoryModels.size(); i++) {
                            strComplaint.add(complaintCategoryModels.get(i).getText());
                        }
                        complaintAdapter.notifyDataSetChanged();
                        // ***********  Complaint Category End  ******** //


                        // ********** InstallpaymentCalc ********** //

                        strPayment = Integer.valueOf(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_install());
                        strReceviedPayment = Integer.valueOf(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_bp_installr());
                        Integer Total = Integer.valueOf(strPayment - strReceviedPayment);
                        strTotalPayment = Total.toString();
                        tvTotalamt.setText(strTotalPayment + "/- escalate payment on installation.");

                        // ********** InstallpaymentCalc End ********** //


                        // ********** Escalate ********** //

                        if (response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_esc_ins().trim().equals("0")) {
                            tvEscalate.setVisibility(View.VISIBLE);
                        } else {
                            tvEscalate.setVisibility(View.GONE);
                        }

                        // ********** Escalate End ********** //

                        // ********** SupplyCalc ********** //
                        aftPayment = Integer.parseInt(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_ba_dispatch());
                        RevAfrDsptch = Integer.parseInt(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_ba_dispatchr());
                        String supplyTotal = String.valueOf(aftPayment - RevAfrDsptch);
                        tvSupplyTotalamt.setText(supplyTotal + "/- escalate payment after dispatch.");
                        // ********** SupplyCalc End ********** //

                        // ********** Escalate ********** //

                        if (response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_esc_ad().trim().equals("0")) {
                            tvSupplyEscalate.setVisibility(View.VISIBLE);
                            tvSupplyEscalateDetails.setVisibility(View.GONE);
                        } else {
                            tvSupplyEscalate.setVisibility(View.GONE);
                            tvSupplyEscalateDetails.setVisibility(View.VISIBLE);
                        }

                        // ********** Escalate End ********** //


                        // ********** Installament  ********** //

                        installmentVisble = Integer.parseInt(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_noofins());
                        tm=response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getTm();
                        bp_installmentrTot=response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getBp_installmentrTot();
                        bp_installmentTot=response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getBp_installmentTot();
                        tvTotalPOI.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getBp_installmentTot());
                        tvTotalRPOI.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getBp_installmentrTot());
                        tvInstallmentTotalamt.setText(response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getBp_installmentTot() + "/- escalate payment on Installment.");
                        installmentAdapter.installamentModels = response.body().getUpcomingCallReportModel().getCallInfoModel().getLogisticsInstallmentsModel().getInstallamentModels();
                        installmentAdapter.notifyDataSetChanged();

                        // ********** Installament End ********** //

                        // ********** Escalate ********** //

                        if (response.body().getUpcomingCallReportModel().getCallInfoModel().getLogistics_esc_installment().trim().equals("0")) {
                            tvInstallmentEscalate.setVisibility(View.VISIBLE);
                            cvInstallament.setVisibility(View.VISIBLE);
                        } else {
                            tvInstallmentEscalate.setVisibility(View.GONE);
                            cvInstallament.setVisibility(View.GONE);

                        }

                        // ********** Escalate End ********** //





                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<UpcomingCallReportResponse> call, Throwable t) {

            }
        });
        //********Customer Detalils End ******************//

        edTypeComplaintCat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edTypeComplaintCat.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edTypeSubComplaintCat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edTypeSubComplaintCat.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //******** Installament Recycleview ******************//
        installmentAdapter = new InstallmentAdapter(this, installamentModels, new InstallmentAdapter.GetInstallament() {
            @Override
            public void getData(InstallamentModel installamentModel) {

                strInstallament.add(installamentModel.getAmtIns());

            }
        });
        LinearLayoutManager installmentlayout = new LinearLayoutManager(this);
        installmentlayout.setOrientation(RecyclerView.VERTICAL);
        rvInstallment.setLayoutManager(installmentlayout);
        rvInstallment.setAdapter(installmentAdapter);
        //******** Installament Recycleview End ******************//

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

        //******** Spare Inward Recycleview ******************//
        spareInwardListAdapter = new SpareInwardListAdapter(this, spareInwardRecordModels);
        LinearLayoutManager spareInward = new LinearLayoutManager(this);
        spareInward.setOrientation(RecyclerView.VERTICAL);
        rvSpareInward.setLayoutManager(spareInward);
        rvSpareInward.setAdapter(spareInwardListAdapter);
        //******** Spare Inward Recycleview End ******************//


        // ***********  Customer PO Recycleview *********** //
        customerPoAdapter = new CustomerPoAdapter(this, customerPoResponseModels, new CustomerPoAdapter.ItemClickListener() {
            @Override
            public void onClick(String s) {

                CusPoradiobID = s.toString();
                rvCustomerPO.post(new Runnable() {
                    @Override
                    public void run() {
                        customerPoAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
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
                ComplaintID = complaintCategoryModels.get(position).getId();
                CallSubComplaint(ComplaintID);

                if (complaintCategoryModels.get(position).getText().trim().equals("Others")) {
                    tvSubComplaint.setVisibility(View.GONE);
                    spSubComplaint.setVisibility(View.GONE);
                    tvTypeComplaintCat.setVisibility(View.VISIBLE);
                    tvTypeSubComplaintCat.setVisibility(View.VISIBLE);
                    edTypeComplaintCat.setVisibility(View.VISIBLE);
                    edTypeSubComplaintCat.setVisibility(View.VISIBLE);
                } else {
                    tvSubComplaint.setVisibility(View.VISIBLE);
                    spSubComplaint.setVisibility(View.VISIBLE);
                    tvTypeComplaintCat.setVisibility(View.GONE);
                    tvTypeSubComplaintCat.setVisibility(View.GONE);
                    edTypeComplaintCat.setVisibility(View.GONE);
                    edTypeSubComplaintCat.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // CallTypeText(id);
        //CallType
        callTypeAdapter = new ArrayAdapter(this, R.layout.spinner_item, strType);
        callTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spCallType.setAdapter(callTypeAdapter);
        spCallType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CallTypePayoptionsID = callTypeModels.get(position).getId();

                TypeValid = Integer.parseInt(callTypeModels.get(position).getId());


                if (callTypeModels.get(position).getText().trim().equals("Paid")) {
                    tvCusInvoice.setVisibility(View.VISIBLE);
                    cvInstallation.setVisibility(View.GONE);
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
                    cvInstallament.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);
                }


                if (callTypeModels.get(position).getText().trim().equals("Supply")) {
                    tvWayBill.setVisibility(View.VISIBLE);
                    tvLR.setVisibility(View.VISIBLE);
                    cvSupply.setVisibility(View.VISIBLE);

                } else {
                    cvSupply.setVisibility(View.GONE);
                    tvLR.setVisibility(View.GONE);
                    tvWayBill.setVisibility(View.GONE);
                }

                if (callTypeModels.get(position).getText().trim().equals("Installation") && installmentVisble > 0) {
                    cvInstallament.setVisibility(View.VISIBLE);
                }else {
                    cvInstallament.setVisibility(View.GONE);
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

                CallStatusID = callStatusModels.get(position).getId();

                if (callStatusModels.get(position).getText().trim().equals("Pending")) {
                    tvPendingReason.setVisibility(View.VISIBLE);
                    tvFollowUpDate.setVisibility(View.VISIBLE);
                    tvserviceReportAttach.setVisibility(View.VISIBLE);
                    tvReason.setVisibility(View.GONE);
                    cvInstallation.setVisibility(View.GONE);
                    cvInstallament.setVisibility(View.GONE);
                    tvInstallationImage1.setVisibility(View.GONE);
                    tvInstallationImage2.setVisibility(View.GONE);
                    tvInstallationImage3.setVisibility(View.GONE);
                    tvWarrentycard.setVisibility(View.GONE);
                    tvInstallReportAttach.setVisibility(View.GONE);

                    cvSupply.setVisibility(View.GONE);
                    tvLR.setVisibility(View.GONE);
                    tvWayBill.setVisibility(View.GONE);

                } else {
                    tvPendingReason.setVisibility(View.GONE);
                    tvFollowUpDate.setVisibility(View.GONE);

                }


                if (callStatusModels.get(position).getText().trim().equals("Require Spare's")) {
                    tvserviceReportAttach.setVisibility(View.VISIBLE);

                    cvInstallation.setVisibility(View.GONE);
                    cvInstallament.setVisibility(View.GONE);
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

                }  else if (callStatusModels.get(position).getText().trim().equals("Closed")) {
                    rlRevPaymentPending.setVisibility(View.VISIBLE);
                    rlDCConsumerSpareFile.setVisibility(View.VISIBLE);

                } else {
                    cvInstallation.setVisibility(View.GONE);
                    cvInstallament.setVisibility(View.GONE);
                    rlRevPaymentPending.setVisibility(View.GONE);
                    rlDCConsumerSpareFile.setVisibility(View.GONE);
                }


                if (spCallType.getSelectedItem().equals("Supply") && spCallStatus.getSelectedItem().equals("Closed")) {
                    tvWayBill.setVisibility(View.VISIBLE);
                    tvLR.setVisibility(View.VISIBLE);
                    cvSupply.setVisibility(View.VISIBLE);
                } else {
                    cvSupply.setVisibility(View.GONE);
                    tvLR.setVisibility(View.GONE);
                    tvWayBill.setVisibility(View.GONE);
                }

                if (tvSpareConsumedCount.getText().toString().trim().equals("0") && callStatusModels.get(position).getText().trim().equals("Closed")) {
                    rlRevPaymentPending.setVisibility(View.VISIBLE);
                    rlDCConsumerSpareFile.setVisibility(View.GONE);

                }

                 if (spCallType.getSelectedItem().equals("Installation") &&  callStatusModels.get(position).getText().trim().equals("Closed") && installmentVisble > 0) {
                    cvInstallament.setVisibility(View.VISIBLE);
                }else {
                     cvInstallament.setVisibility(View.GONE);
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

                SubComplaintCatID = callReportComplaintSubCategoryModels.get(position).getId();

                if (callReportComplaintSubCategoryModels.get(position).getText().trim().equals("Others")) {
                    tvTypeComplaintCat.setVisibility(View.VISIBLE);
                    edTypeComplaintCat.setVisibility(View.VISIBLE);
                    tvTypeSubComplaintCat.setVisibility(View.GONE);
                    edTypeSubComplaintCat.setVisibility(View.GONE);
                } else {
                    tvTypeComplaintCat.setVisibility(View.GONE);
                    edTypeComplaintCat.setVisibility(View.GONE);
                    tvTypeSubComplaintCat.setVisibility(View.GONE);
                    edTypeSubComplaintCat.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cbAttach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbSetID = cbAttach.isChecked() ? 1 : 0;
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

        tvWayBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 12);
                } catch (Exception e) {

                }
            }
        });

        tvLR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 13);
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
                tvDepositedDate.setError(null);
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
                tvFollowUpDate.setError(null);

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
                tvStartingTime.setError(null);

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
                tvEndTime.setError(null);
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


        tvSupplyEscalate.setOnClickListener(new View.OnClickListener() {
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
                        SupplyEscalatedSubmitedInterface supplyEscalatedSubmitedInterface = APIClient.getClient().create(SupplyEscalatedSubmitedInterface.class);
                        supplyEscalatedSubmitedInterface.CallSubmit("onSupplyCallEscalate", PreferenceManager.getEmpID(UpcomingCallReport.this), LogsitId, tvPaymentafterDispatch.getText().toString(), tvRecvPaymentDispatch.getText().toString()).enqueue(new Callback<SupplyEscalatedSubmitedResponse>() {
                            @Override
                            public void onResponse(Call<SupplyEscalatedSubmitedResponse> call, Response<SupplyEscalatedSubmitedResponse> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        dialogInterface.dismiss();
                                        progressDialog.dismiss();
                                        Toast.makeText(UpcomingCallReport.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                        tvSupplyEscalate.setVisibility(View.GONE);
                                        tvSupplyEscalateDetails.setVisibility(View.VISIBLE);
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<SupplyEscalatedSubmitedResponse> call, Throwable t) {
                                progressDialog.dismiss();

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

        tvInstallmentEscalate.setOnClickListener(new View.OnClickListener() {
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
                        InstallamentEscalatedSubmitInterface installamentEscalatedSubmitInterface=APIClient.getClient().create(InstallamentEscalatedSubmitInterface.class);
                        installamentEscalatedSubmitInterface.CallSubmit("onInstallmentEscalate",PreferenceManager.getEmpID(UpcomingCallReport.this),LogsitId,bp_installmentTot,bp_installmentrTot,tm,strInstallament).enqueue(new Callback<InstallamentEscalatedSubmitResponse>() {
                            @Override
                            public void onResponse(Call<InstallamentEscalatedSubmitResponse> call, Response<InstallamentEscalatedSubmitResponse> response) {
                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();
                                        dialogInterface.dismiss();
                                        Toast.makeText(UpcomingCallReport.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                        tvInstallmentEscalate.setVisibility(View.GONE);
                                        cvInstallament.setVisibility(View.GONE);
                                    }
                                }catch (Exception e){

                                }
                            }

                            @Override
                            public void onFailure(Call<InstallamentEscalatedSubmitResponse> call, Throwable t) {
                                progressDialog.dismiss();

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


        //  ************** Do you really want to close the call without getting payment ************** //

        rbGrp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = rbGrp2.findViewById(checkedId);
                int rb = rbGrp2.indexOfChild(view);
                switch ((rb)) {
                    case 0:
                        rbClosePayID = 1;
                        break;

                    case 1:
                        rbClosePayID = 0;
                        break;
                }
            }
        });


        //  ************** Do you really want to close the call without getting payment End ************** //


        //  ************** Do you want to consume spares Check ************** //

        rbGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = rbGrp.findViewById(checkedId);
                int rb = rbGrp.indexOfChild(view);
                switch (rb) {
                    case 0:
                        rbconsumerSpareID = 1;
                        Dialog dialog = new Dialog(UpcomingCallReport.this);
                        dialog.setContentView(R.layout.consumer_spare_yes_dialog_recycleview);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        ImageView ivClose = dialog.findViewById(R.id.ivClose);
                        TextView tvYesConsume = dialog.findViewById(R.id.tvYesConsume);
                        TextView tvYesConsumeCusSpare = dialog.findViewById(R.id.tvYesConsumeCusSpare);
                        tvConsumecusSpareCount = dialog.findViewById(R.id.tvConsumecusSpareCount);
                        tvConsumeSpareCount = dialog.findViewById(R.id.tvConsumeSpareCount);
                        tvCusPoSpareCount = dialog.findViewById(R.id.tvCusPoSpareCount);
                        rvConsumeCusSpareYes = dialog.findViewById(R.id.rvConsumeCusSpareYes);
                        rvConsumespareYes = dialog.findViewById(R.id.rvConsumespareYes);
                        rvCustPoSpareYes = dialog.findViewById(R.id.rvCustPoSpareYes);
                        rlCustomerPOSpares = dialog.findViewById(R.id.rlCustomerPOSpares);
                        rlConsumeSpares = dialog.findViewById(R.id.rlConsumeSpares);
                        rlConsumecusSpares = dialog.findViewById(R.id.rlConsumecusSpares);

                        dialog.show();


                        //***** Consume Spare *****//
                        ArrayList<String> strConQty = new ArrayList<>();
                        ArrayList<String> strSpareId = new ArrayList<>();
                        ArrayList<String> strUnitPrice = new ArrayList<>();
                        ArrayList<String> strOpt = new ArrayList<>();
                        ArrayList<String> strMyQty = new ArrayList<>();
                        //***** Consume Spare End *****//

                        // **** Consume Customer Spare ***//
                        ArrayList<String> strConCusMyQty = new ArrayList<>();
                        ArrayList<String> strConCusQtyConsume = new ArrayList<>();
                        ArrayList<String> strConCusPartId = new ArrayList<>();
                        ArrayList<String> strConCusOpt = new ArrayList<>();
                        ArrayList<String> strConCusCustomerStockID = new ArrayList<>();

                        // **** Consume Customer Spare ***//


                        CallYesDoYouConsumerSpare();
                        //***** Consume Spare *****//
                        consumeSpareYesAdapter = new ConsumeSpareYesAdapter(UpcomingCallReport.this, consumeSpareRecordModels, new ConsumeSpareYesAdapter.Reference() {
                            @Override
                            public void reference(ConsumeSpareRecordModel consumeSpareRecordModel) {

                                strSpareId.add(consumeSpareRecordModel.getSparePartId());
                                strUnitPrice.add(consumeSpareRecordModel.getUnitPrice());
                                strOpt.add(consumeSpareRecordModel.getOpt());
                                strMyQty.add(consumeSpareRecordModel.getMyQty());


                            }
                        });
                        LinearLayoutManager consumeSpare = new LinearLayoutManager(UpcomingCallReport.this);
                        consumeSpare.setOrientation(RecyclerView.VERTICAL);
                        rvConsumespareYes.setAdapter(consumeSpareYesAdapter);
                        rvConsumespareYes.setLayoutManager(consumeSpare);
                        //***** Consume Spare End *****//

                        //***** Consume Customer Spare *****//
                        consumeCusSpareYesAdapter = new ConsumeCusSpareYesAdapter(UpcomingCallReport.this, consumerCustSpareRecordModels, new ConsumeCusSpareYesAdapter.GetConsumerCustSpare() {
                            @Override
                            public void getData(ConsumerCustSpareRecordModel consumerCustSpareRecordModel) {
                                strConCusCustomerStockID.add(consumerCustSpareRecordModel.getCustomerStockId());
                                strConCusMyQty.add(consumerCustSpareRecordModel.getAvlQty());
                                strConCusPartId.add(consumerCustSpareRecordModel.getId());
                                strConCusOpt.add(consumerCustSpareRecordModel.getOpt());


                            }
                        });
                        LinearLayoutManager consumeCusSpare = new LinearLayoutManager(UpcomingCallReport.this);
                        consumeCusSpare.setOrientation(RecyclerView.VERTICAL);
                        rvConsumeCusSpareYes.setLayoutManager(consumeCusSpare);
                        rvConsumeCusSpareYes.setAdapter(consumeCusSpareYesAdapter);
                        //***** Consume Customer Spare End *****//


                        //*****  Customer Po Spare  *****//
                        consumePoSpareYesAdapter = new ConsumePoSpareYesAdapter(UpcomingCallReport.this, customerPoSpareRecordModels);
                        LinearLayoutManager cusPoSpare = new LinearLayoutManager(UpcomingCallReport.this);
                        cusPoSpare.setOrientation(RecyclerView.VERTICAL);
                        rvCustPoSpareYes.setAdapter(consumePoSpareYesAdapter);
                        rvCustPoSpareYes.setLayoutManager(cusPoSpare);
                        //*****  Customer Po Spare End *****//

                        ivClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        tvYesConsume.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                strConQty.clear();
                                for (int i = 0; i < rvConsumespareYes.getAdapter().getItemCount(); i++) {
                                    v = rvConsumespareYes.getChildAt(i);
                                    EditText consumeQty = (EditText) v.findViewById(R.id.edQtyReq);
                                    strConQty.add(i, consumeQty.getText().toString());
                                }

                                final ProgressDialog progressDialog = new ProgressDialog(UpcomingCallReport.this, R.style.ProgressBarDialog);
                                progressDialog.setMessage("Please Wait...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                ConsumeSpareSubmitInterface consumeSpareSubmitInterface = APIClient.getClient().create(ConsumeSpareSubmitInterface.class);
                                consumeSpareSubmitInterface.CallSubmit("onConsumeSpares", CusPoradiobID, DoYouConsumerID, CallTypePayoptionsID, strSpareId, strMyQty, strConQty, strUnitPrice, strOpt, PreferenceManager.getEmpID(UpcomingCallReport.this)).enqueue(new Callback<ConsumeSpareSubmitResponse>() {
                                    @Override
                                    public void onResponse(Call<ConsumeSpareSubmitResponse> call, Response<ConsumeSpareSubmitResponse> response) {

                                        try {
                                            if (response.isSuccessful()) {
                                                progressDialog.dismiss();
                                                dialog.dismiss();
                                                Toast.makeText(UpcomingCallReport.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                                            }

                                        } catch (Exception e) {

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ConsumeSpareSubmitResponse> call, Throwable t) {
                                        progressDialog.dismiss();

                                    }
                                });
                            }
                        });
                        tvYesConsumeCusSpare.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                strConCusQtyConsume.clear();

                                for (int i = 0; i < rvConsumeCusSpareYes.getAdapter().getItemCount(); i++) {
                                    v = rvConsumeCusSpareYes.getChildAt(i);
                                    EditText QtyConsume = (EditText) v.findViewById(R.id.edQtyReq);
                                    strConCusQtyConsume.add(i, QtyConsume.getText().toString());
                                }

                                final ProgressDialog progressDialog = new ProgressDialog(UpcomingCallReport.this, R.style.ProgressBarDialog);
                                progressDialog.setMessage("Please Wait...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                ConsumeCusSpareSubmitInterface consumeCusSpareSubmitInterface = APIClient.getClient().create(ConsumeCusSpareSubmitInterface.class);
                                consumeCusSpareSubmitInterface.CallSubmit("onConsumeCustSpares", DoYouConsumerID, CusPoradiobID, strConCusPartId, strConCusMyQty, strConCusQtyConsume, strConCusOpt, strConCusCustomerStockID, PreferenceManager.getEmpID(UpcomingCallReport.this)).enqueue(new Callback<ConsumerCusSpareSubmitResponse>() {
                                    @Override
                                    public void onResponse(Call<ConsumerCusSpareSubmitResponse> call, Response<ConsumerCusSpareSubmitResponse> response) {
                                        try {
                                            if (response.isSuccessful()) {
                                                progressDialog.dismiss();
                                                dialog.dismiss();
                                                Toast.makeText(UpcomingCallReport.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                                            }

                                        } catch (Exception e) {

                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ConsumerCusSpareSubmitResponse> call, Throwable t) {

                                        progressDialog.dismiss();
                                    }
                                });

                            }
                        });

                        break;
                    case 1:
                        rbconsumerSpareID = 0;
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
                        rbRecPendingID = 1;
                        rlYesRevPayment.setVisibility(View.VISIBLE);
                        rlNoRevPayment.setVisibility(View.GONE);
                        break;
                    case 1:
                        rbRecPendingID = 0;
                        rlYesRevPayment.setVisibility(View.GONE);
                        rlNoRevPayment.setVisibility(View.VISIBLE);
                        break;

                }

            }
        });
        //  ************** Received pending sales payment Check End ************** //


        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {


                try {
                    boolean isfilled = true;

                    if (spCallType.getSelectedItem().equals("Paid") && tvCusInvoice.getText().length() == 0) {
                        tvCusInvoice.setError("Please attach the customer invoice. ");
                        tvCusInvoice.requestFocus();
                        isfilled = false;
                    } else if (ComplaintValidRequired.trim().equals("1") && spComplaint.getSelectedItemPosition() == -1) {
                        setSpinnerError(spComplaint, "Please Select a Complaint");
                        Toast.makeText(UpcomingCallReport.this, "Please Select a Complaint", Toast.LENGTH_SHORT).show();
                        isfilled = false;
                    } else if (spComplaint.getSelectedItem().equals("Others") && edTypeComplaintCat.getText().toString().trim().length() == 0 && edTypeSubComplaintCat.getText().toString().trim().length() == 0) {
                        edTypeSubComplaintCat.setError("please Enter Type Complaint Category");
                        edTypeComplaintCat.setError("Please Enter Type Complaint SubCategory");
                        edTypeSubComplaintCat.requestFocus();
                        edTypeComplaintCat.requestFocus();
                        isfilled = false;
                    } else if (spSubComplaint.getSelectedItemPosition() == -1) {
                        setSpinnerError(spSubComplaint, "Please Select a Sub Complaint");
                        Toast.makeText(UpcomingCallReport.this, "Please Select a Sub Complaint", Toast.LENGTH_SHORT).show();
                        isfilled = false;
                    } else if (spSubComplaint.getSelectedItem().equals("Others") && edTypeComplaintCat.getText().toString().trim().length() == 0) {
                        edTypeComplaintCat.setError("Please enter the Type Complaint Category");
                        edTypeComplaintCat.requestFocus();
                        isfilled = false;
                    } else if (rbGrp.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(UpcomingCallReport.this, "Select a consume spares", Toast.LENGTH_SHORT).show();
                        isfilled = false;

                    } else if (SpareDocVaild > 0 && spCallStatus.getSelectedItem().equals("Closed") && tvSpareFile1.getText().toString().trim().length() == 0 && tvSpareFile2.getText().toString().trim().length() == 0 && tvSpareFile3.getText().toString().trim().length() == 0) {
                        tvSpareFile1.setError("Please Attach DC/Invoice for Consumed spares");
                        tvSpareFile2.setError("Please Attach DC/Invoice for Consumed spares");
                        tvSpareFile3.setError("Please Attach DC/Invoice for Consumed spares");
                        tvSpareFile1.requestFocus();
                        tvSpareFile2.requestFocus();
                        tvSpareFile3.requestFocus();
                        isfilled = false;

                    } else if (spCallStatus.getSelectedItem().equals("Pending") && edPendingReason.getText().toString().trim().length() == 0) {
                        edPendingReason.setError("Please enter a pending reason.");
                        edPendingReason.requestFocus();
                        isfilled = false;

                    } else if (spCallStatus.getSelectedItem().equals("Pending") && tvFollowUpDate.getText().toString().trim().length() == 0) {
                        tvFollowUpDate.setError("Please provide a follow-up date.");
                        tvFollowUpDate.requestFocus();
                        isfilled = false;
                    } else if (tvStartingTime.getText().toString().trim().length() == 0) {
                        tvStartingTime.setError("Please select a start time.");
                        tvStartingTime.requestFocus();
                        isfilled = false;
                    } else if (tvEndTime.getText().toString().trim().length() == 0) {
                        tvEndTime.setError("Please select an end time.");
                        tvEndTime.requestFocus();
                        isfilled = false;
                    } else if (edName.getText().toString().trim().length() == 0) {
                        edName.setError("Please enter the contact person's name.");
                        edName.requestFocus();
                        isfilled = false;
                    } else if (edMobile.getText().toString().trim().length() == 0) {
                        edMobile.setError("Please enter the mobile phone number of the contact person");
                        edMobile.requestFocus();
                        isfilled = false;
                    } else if (edWorkdone.getText().toString().trim().length() == 0) {
                        edWorkdone.setError("Please enter your work.");
                        edWorkdone.requestFocus();
                        isfilled = false;
                    } else if (edEngineerAdvice.getText().toString().trim().length() == 0) {
                        edEngineerAdvice.setError("Please enter the  Engineer Advice");
                        edEngineerAdvice.requestFocus();
                        isfilled = false;
                    } else if (spCallStatus.getSelectedItem().equals("Closed") && rbGrp1.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(UpcomingCallReport.this, "Please select a Received pending service payment", Toast.LENGTH_SHORT).show();
                        isfilled = false;
                    } else if (edSaleORserviceAmt.getText().toString().trim().length() == 0) {
                        edSaleORserviceAmt.setError("Please Enter the Amount");
                        edSaleORserviceAmt.requestFocus();
                        isfilled = false;
                    } else if (edSpareAmt.getText().toString().trim().length() == 0) {
                        edSpareAmt.setError("Please Enter the Spare Amount");
                        edSpareAmt.requestFocus();
                        isfilled = false;
                    } else if (rbPOaymentNo.isChecked() && rbGrp2.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(UpcomingCallReport.this, "Please select close the call without getting payment", Toast.LENGTH_SHORT).show();
                        rbGrp2.requestFocus();
                        isfilled = false;
                    } else if (rbPaymentYes.isChecked() && edCheckUTRno.getText().toString().trim().length() == 0 && tvDepositedDate.getText().toString().trim().length() == 0) {
                        edCheckUTRno.setError("Please Enter the Cheque/UTR no");
                        edCheckUTRno.requestFocus();
                        tvDepositedDate.setError("Please Select a Deposite Date");
                        tvDepositedDate.requestFocus();
                        isfilled = false;
                    } else if (TypeValid != 6 && cbAttach.isChecked() == true && tvserviceReportAttach.getText().toString().trim().length() == 0) {
                        tvserviceReportAttach.setError("Please Attach the Report");
                        tvserviceReportAttach.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Pending") && cbAttach.isChecked() == true && tvserviceReportAttach.getText().toString().trim().length() == 0) {
                        tvserviceReportAttach.setError("Please Attach the Report");
                        tvserviceReportAttach.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed") && cbAttach.isChecked() == true && tvInstallationImage1.getText().toString().trim().length() == 0) {
                        tvInstallationImage1.setError("Please Attach the Installation Image 1");
                        tvInstallationImage1.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed") && cbAttach.isChecked() == true && tvInstallationImage2.getText().toString().trim().length() == 0) {
                        tvInstallationImage2.setError("Please Attach the  Installation Image 2");
                        tvInstallationImage2.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed") && cbAttach.isChecked() == true && tvInstallationImage3.getText().toString().trim().length() == 0) {
                        tvInstallationImage3.setError("Please Attach the Installation Image 2");
                        tvInstallationImage3.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed") && cbAttach.isChecked() == true && tvWarrentycard.getText().toString().trim().length() == 0) {
                        tvWarrentycard.setError("Please Attach the Warranty Card ");
                        tvWarrentycard.requestFocus();
                        isfilled = false;
                    } else if (spCallType.getSelectedItem().equals("Installation") && spCallStatus.getSelectedItem().equals("Closed") && cbAttach.isChecked() == true && tvInstallReportAttach.getText().toString().trim().length() == 0) {
                        tvInstallationImage1.setError("Please Attach the Install Report");
                        tvInstallationImage1.requestFocus();
                        isfilled = false;
                    } else if (cbAttach.isChecked() == false && edReason.getText().toString().trim().length() == 0) {
                        edReason.setError("Please Enter the Reason");
                        edReason.requestFocus();
                        isfilled = false;
                    } else if (ratingBar.getRating() == 0) {
                        Toast.makeText(UpcomingCallReport.this, "Please Select a Customer Review", Toast.LENGTH_SHORT).show();
                        isfilled = false;
                    } else {

                        Toast.makeText(UpcomingCallReport.this, "submit", Toast.LENGTH_SHORT).show();
                        CallReportSubmit();
                    }

                } catch (Exception e) {

                }


//                if (fileCustomerPO != null) {
//                    callCusPoFile();
//                } else if (fileinstallImg1 != null && fileinstallImg2 != null && fileinstallImg3 != null && fileWarrentyCard != null && fileinstallReport != null) {
//                    CallInstallImg();
//
//                } else if (spareFile1 != null && spareFile2 != null && spareFile3 != null) {
//                    callspareConsumedFile();
//
//                } else if (fileinvoice != null) {
//                    callcusInvoice();
//                } else if (fileLR != null) {
//                    callFileLR();
//                }
//                else if (fileWaybill != null) {
//                   CallFileWayBill();
//                }else {
//                    Toast.makeText(UpcomingCallReport.this, "Plesae Select image", Toast.LENGTH_SHORT).show();
//                }

            }
        });

    }

    private void CallFileWayBill() {
        //#API: callClose.php?action=uploadWayBill&call_status=1&pay_option=8&callassignid=&empid=&fileName=file_inwb


        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadWayBill");
        RequestBody call_status = RequestBody.create(MediaType.parse("text/plain"), CallStatusID);
        RequestBody pay_option = RequestBody.create(MediaType.parse("text/plain"), CallTypePayoptionsID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody empid = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody wb = RequestBody.create(MediaType.parse("multipart/form-data"), fileWaybill);
        MultipartBody.Part filewb = MultipartBody.Part.createFormData("file_inwb", fileWaybill.getName(), wb);
        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallWaybill(action, call_status, pay_option, callassignid, empid, filewb).enqueue(new Callback<CallReportSubmitResponse>() {
            @Override
            public void onResponse(Call<CallReportSubmitResponse> call, Response<CallReportSubmitResponse> response) {
                try {
                    if (response.isSuccessful()) {

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CallReportSubmitResponse> call, Throwable t) {

            }
        });
    }

    private void CallReportSubmit() {
        CallReportingUpComingSubmitInterface callReportingUpComingSubmitInterface = APIClient.getClient().create(CallReportingUpComingSubmitInterface.class);
        callReportingUpComingSubmitInterface.callReportSubmit("callClose", LogsitId, CallTypePayoptionsID, ComplaintID, SubComplaintCatID, edTypeComplaintCat.getText().toString(), edTypeSubComplaintCat.getText().toString(), "", "", "Not Detect", CallAssignId, CallRegID, CallAssignId, pdtidd, hpidd, chk1,
                CallStatusID, "", "", tvInstallDate.getText().toString(), bp_install, bp_installr, "", "", "", String.valueOf(rbconsumerSpareID), Spc, tvFollowUpDate.getText().toString(),
                tvStartingTime.getText().toString(), tvEndTime.getText().toString(), edPendingReason.getText().toString(), edName.getText().toString(), edMobile.getText().toString(), String.valueOf(rbRecPendingID), tvDepositedDate.getText().toString(), edCheckUTRno.getText().toString(), String.valueOf(rbClosePayID), edSaleORserviceAmt.getText().toString(), edSpareAmt.getText().toString(), edWorkdone.getText().toString(), edEngineerAdvice.getText().toString(), String.valueOf(ratingBar.getRating()), String.valueOf(cbSetID), edReason.getText().toString(), "",
                "", "", "").enqueue(new Callback<CallReportingUpComingSubmitResponse>() {
            @Override
            public void onResponse(Call<CallReportingUpComingSubmitResponse> call, Response<CallReportingUpComingSubmitResponse> response) {

            }

            @Override
            public void onFailure(Call<CallReportingUpComingSubmitResponse> call, Throwable t) {

            }
        });

    }


    private void CallYesDoYouConsumerSpare() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        YesDoYouConsumeSpareInterface yesDoYouConsumeSpareInterface = APIClient.getClient().create(YesDoYouConsumeSpareInterface.class);
        yesDoYouConsumeSpareInterface.CallSpare("doYouConsumeSpares", PartID, CallTypePayoptionsID, DoYouConsumerID, CusPoradiobID, PreferenceManager.getEmpID(this)).enqueue(new Callback<YesDoYouConsumeSpareResponse>() {
            @Override
            public void onResponse(Call<YesDoYouConsumeSpareResponse> call, Response<YesDoYouConsumeSpareResponse> response) {

                try {
                    if (response.isSuccessful()) {


                        //**** Consume Spare ****//
                        if (response.body().getConsumeSparesModel().getCount().trim().equals("0")) {
                            rlConsumeSpares.setVisibility(View.GONE);
                        } else {
                            rlConsumeSpares.setVisibility(View.VISIBLE);
                            tvConsumeSpareCount.setText(response.body().getConsumeSparesModel().getCount());
                            consumeSpareYesAdapter.consumeSpareRecordModels = response.body().getConsumeSparesModel().getConsumeSpareRecordModels();
                            consumeSpareYesAdapter.notifyDataSetChanged();
                        }

                        //**** Consume Spare End ****//

                        //**** Consume Cus Spare  ****//
                        if (response.body().getConsumeCustSparesModel().getCount().trim().equals("0")) {
                            rlConsumecusSpares.setVisibility(View.GONE);
                        } else {
                            rlConsumecusSpares.setVisibility(View.VISIBLE);
                            tvConsumecusSpareCount.setText(response.body().getConsumeCustSparesModel().getCount());
                            consumeCusSpareYesAdapter.consumerCustSpareRecordModels = response.body().getConsumeCustSparesModel().getConsumerCustSpareRecordModels();
                            consumeCusSpareYesAdapter.notifyDataSetChanged();
                        }

                        //**** Consume Cus Spare End ****//

                        //****  Cus Po Spare  ****//
                        if (response.body().getCustomerPospareModel().getCount().trim().equals("0")) {
                            rlCustomerPOSpares.setVisibility(View.GONE);
                        } else {
                            rlCustomerPOSpares.setVisibility(View.VISIBLE);
                            tvCusPoSpareCount.setText(response.body().getCustomerPospareModel().getCount());
                            consumePoSpareYesAdapter.customerPoSpareRecordModels = response.body().getCustomerPospareModel().getCustomerPoSpareRecordModels();
                            consumePoSpareYesAdapter.notifyDataSetChanged();
                        }
                        //****  Cus Po Spare End ****//

                        progressDialog.dismiss();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<YesDoYouConsumeSpareResponse> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }

    private void callFileLR() {
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadLR");
        RequestBody call_status = RequestBody.create(MediaType.parse("text/plain"), CallStatusID);
        RequestBody pay_option = RequestBody.create(MediaType.parse("text/plain"), CallTypePayoptionsID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody empid = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody lr = RequestBody.create(MediaType.parse("multipart/form-data"), fileLR);
        MultipartBody.Part fileLr = MultipartBody.Part.createFormData("file_inlr", fileLR.getName(), lr);
        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallReportFile(action, call_status, pay_option, callassignid, empid, fileLr).enqueue(new Callback<CallReportServiceSubmitResponse>() {
            @Override
            public void onResponse(Call<CallReportServiceSubmitResponse> call, Response<CallReportServiceSubmitResponse> response) {

            }

            @Override
            public void onFailure(Call<CallReportServiceSubmitResponse> call, Throwable t) {

            }
        });
    }

    private void callcusInvoice() {

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadCustomerInvoice");
        RequestBody pay_option = RequestBody.create(MediaType.parse("text/plain"), CallTypePayoptionsID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody cuspofile = RequestBody.create(MediaType.parse("multipart/form-data"), fileinvoice);
        MultipartBody.Part fileCusPO = MultipartBody.Part.createFormData("file_in1", fileinvoice.getName(), cuspofile);
        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallUploadCusInvoice(action, pay_option, callassignid, fileCusPO).enqueue(new Callback<CallReportsubmitCusInvoiceResponse>() {
            @Override
            public void onResponse(Call<CallReportsubmitCusInvoiceResponse> call, Response<CallReportsubmitCusInvoiceResponse> response) {

            }

            @Override
            public void onFailure(Call<CallReportsubmitCusInvoiceResponse> call, Throwable t) {

            }
        });

    }

    private void callspareConsumedFile() {
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadDCForSparesConsumed");
        RequestBody spc = RequestBody.create(MediaType.parse("text/plain"), Spc);
        RequestBody call_status = RequestBody.create(MediaType.parse("text/plain"), CallStatusID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody file1 = RequestBody.create(MediaType.parse("multipart/form-data"), spareFile1);
        MultipartBody.Part filespc1 = MultipartBody.Part.createFormData("file_in", spareFile1.getName(), file1);
        RequestBody file2 = RequestBody.create(MediaType.parse("multipart/form-data"), spareFile2);
        MultipartBody.Part filespc2 = MultipartBody.Part.createFormData("file_in2sc", spareFile2.getName(), file2);
        RequestBody file3 = RequestBody.create(MediaType.parse("multipart/form-data"), spareFile3);
        MultipartBody.Part filespc3 = MultipartBody.Part.createFormData("file_in3sc", spareFile3.getName(), file3);
        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallSpareConsumedFile(action, spc, call_status, callassignid, filespc1, filespc2, filespc3).enqueue(new Callback<CallReportSpareConsumedSubmitResponse>() {
            @Override
            public void onResponse(Call<CallReportSpareConsumedSubmitResponse> call, Response<CallReportSpareConsumedSubmitResponse> response) {

            }

            @Override
            public void onFailure(Call<CallReportSpareConsumedSubmitResponse> call, Throwable t) {

            }
        });
    }

    private void callCusPoFile() {
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadInvoice");
        RequestBody sqid = RequestBody.create(MediaType.parse("text/plain"), CusPoradiobID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody cusinvoice = RequestBody.create(MediaType.parse("multipart/form-data"), fileCustomerPO);
        MultipartBody.Part filecusInvoice = MultipartBody.Part.createFormData("file_in4sc", fileCustomerPO.getName(), cusinvoice);
        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallUploadInvoice(action, sqid, callassignid, filecusInvoice).enqueue(new Callback<CallReportSubmitCusPoRespones>() {
            @Override
            public void onResponse(Call<CallReportSubmitCusPoRespones> call, Response<CallReportSubmitCusPoRespones> response) {

            }

            @Override
            public void onFailure(Call<CallReportSubmitCusPoRespones> call, Throwable t) {

            }
        });
    }

    private void CallInstallImg() {

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "uploadInstallationImages");
        RequestBody call_status = RequestBody.create(MediaType.parse("text/plain"), CallStatusID);
        RequestBody callassignid = RequestBody.create(MediaType.parse("text/plain"), CallAssignId);
        RequestBody empid = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody installimg1 = create(MediaType.parse("multipart/form-data"), fileinstallImg1);
        MultipartBody.Part fileInstallimg1 = MultipartBody.Part.createFormData("installImg1", fileinstallImg1.getName(), installimg1);
        RequestBody installimg2 = RequestBody.create(MediaType.parse("multipart/form-data"), fileinstallImg2);
        MultipartBody.Part fileInstallimg2 = MultipartBody.Part.createFormData("installImg2", fileinstallImg1.getName(), installimg2);
        RequestBody installimg3 = RequestBody.create(MediaType.parse("multipart/form-data"), fileinstallImg3);
        MultipartBody.Part fileInstallimg3 = MultipartBody.Part.createFormData("installImg3", fileinstallImg3.getName(), installimg3);
        RequestBody installimg4 = RequestBody.create(MediaType.parse("multipart/form-data"), fileWarrentyCard);
        MultipartBody.Part fileInstallimg4 = MultipartBody.Part.createFormData("installImg4", fileWarrentyCard.getName(), installimg4);
        RequestBody installimg5 = RequestBody.create(MediaType.parse("multipart/form-data"), fileinstallReport);
        MultipartBody.Part fileInstallimg5 = MultipartBody.Part.createFormData("installImg5", fileinstallReport.getName(), installimg5);


        CallReportSubmitInterface callReportSubmitInterface = APIClient.getClient().create(CallReportSubmitInterface.class);
        callReportSubmitInterface.CallInstallationFile(action, call_status, callassignid, empid, fileInstallimg1, fileInstallimg2, fileInstallimg3, fileInstallimg4, fileInstallimg5).enqueue(new Callback<CallReportSubmitResponse>() {
            @Override
            public void onResponse(Call<CallReportSubmitResponse> call, Response<CallReportSubmitResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpcomingCallReport.this, "install", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CallReportSubmitResponse> call, Throwable t) {

            }
        });

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
                        fileinvoice = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileinvoice.length();
                    mb = mb / 1024;

                    try {
                        if (mb < 5120) {
                            String myStr = strCusInvoiceAttach;
                            tvCusInvoice.setError(null);
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            System.out.println(extension);
                            if (extension.equals(".pdf")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else if (extension.equals(".jpg")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else if (extension.equals(".png")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else if (extension.equals(".jpeg")) {
                                tvCusInvoice.setText(strCusInvoiceAttach);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        } else {
                            tvCusInvoice.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvCusInvoice.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileservice = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileservice.length();
                    mb = mb / 1024;


                    try {
                        if (5120 > mb) {
                            tvserviceReportAttach.setError(null);
                            String myStr = strSerAttach;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else if (extension.equals(".jpg")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else if (extension.equals(".png")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else if (extension.equals(".jpeg")) {
                                tvserviceReportAttach.setText(strSerAttach);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvserviceReportAttach.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvserviceReportAttach.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileinstallImg1 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    long mb = fileinstallImg1.length();
                    mb = mb / 1024;


                    try {
//                        String mb= Formatter.formatShortFileSize(this,fileinstallImg1.length());
//                        Log.e(TAG, "onActivityResult: "+mb.replace("MB"," ") );
                        if (mb < 5120) {
                            tvInstallationImage1.setError(null);
                            String myStr = strInstallImg1;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallationImage1.setText(strInstallImg1);
                            } else if (extension.equals(".jpg")) {
                                tvInstallationImage1.setText(strInstallImg1);
                            } else if (extension.equals(".png")) {
                                tvInstallationImage1.setText(strInstallImg1);
                            } else if (extension.equals(".jpeg")) {
                                tvInstallationImage1.setText(strInstallImg1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }

                        } else {
                            tvInstallationImage1.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvInstallationImage1.requestFocus();
                        }


                    } catch (Exception e) {

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
                        fileinstallImg2 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileinstallImg2.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvInstallationImage2.setError(null);
                            String myStr = strInstallImg2;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else if (extension.equals(".jpg")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else if (extension.equals(".png")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else if (extension.equals(".jpeg")) {
                                tvInstallationImage2.setText(strInstallImg2);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvInstallationImage2.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvInstallationImage2.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileinstallImg3 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileinstallImg3.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvInstallationImage3.setError(null);
                            String myStr = strInstallImg3;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else if (extension.equals(".jpg")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else if (extension.equals(".png")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else if (extension.equals(".jpeg")) {
                                tvInstallationImage3.setText(strInstallImg3);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvInstallationImage3.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvInstallationImage3.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileWarrentyCard = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileWarrentyCard.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvWarrentycard.setError(null);
                            String myStr = strWarrenty;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else if (extension.equals(".jpg")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else if (extension.equals(".png")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else if (extension.equals(".jpeg")) {
                                tvWarrentycard.setText(strWarrenty);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvWarrentycard.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvWarrentycard.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileinstallReport = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileinstallReport.length();
                    mb = mb / 1024;


                    try {
                        if (5120 > mb) {
                            tvInstallReportAttach.setError(null);
                            String myStr = strInstallReport;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else if (extension.equals(".jpg")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else if (extension.equals(".png")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else if (extension.equals(".jpeg")) {
                                tvInstallReportAttach.setText(strInstallReport);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvInstallReportAttach.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvInstallReportAttach.requestFocus();
                        }

                    } catch (Exception e) {

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
                        spareFile1 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    long mb = spareFile1.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvSpareFile1.setError(null);
                            String myStr = strsparefile1;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else if (extension.equals(".png")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else if (extension.equals(".jpeg")) {
                                tvSpareFile1.setText(strsparefile1);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvSpareFile1.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvSpareFile1.requestFocus();
                        }

                    } catch (Exception e) {

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
                        spareFile2 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = spareFile2.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvSpareFile2.setError(null);
                            String myStr = strsparefile2;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else if (extension.equals(".png")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else if (extension.equals(".jpeg")) {
                                tvSpareFile2.setText(strsparefile2);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvSpareFile2.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvSpareFile2.requestFocus();
                        }

                    } catch (Exception e) {

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
                        spareFile3 = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = spareFile3.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvSpareFile3.setError(null);
                            String myStr = strsparefile3;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else if (extension.equals(".jpg")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else if (extension.equals(".png")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else if (extension.equals(".jpeg")) {
                                tvSpareFile3.setText(strsparefile3);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvSpareFile3.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvSpareFile3.requestFocus();
                        }

                    } catch (Exception e) {

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
                        fileCustomerPO = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileCustomerPO.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvCusPOFileInvoiceAttch.setError(null);
                            String myStr = strCustomerPo;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else if (extension.equals(".jpg")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else if (extension.equals(".png")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else if (extension.equals(".jpeg")) {
                                tvCusPOFileInvoiceAttch.setText(strCustomerPo);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvCusPOFileInvoiceAttch.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvCusPOFileInvoiceAttch.requestFocus();
                        }

                    } catch (Exception e) {

                    }


                }
                break;

            case 12:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strWayBill = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strWayBill, Toast.LENGTH_SHORT).show();

                    try {
                        fileWaybill = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileWaybill.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvWayBill.setError(null);
                            String myStr = strWayBill;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvWayBill.setText(strWayBill);
                            } else if (extension.equals(".jpg")) {
                                tvWayBill.setText(strWayBill);
                            } else if (extension.equals(".png")) {
                                tvWayBill.setText(strWayBill);
                            } else if (extension.equals(".jpeg")) {
                                tvWayBill.setText(strWayBill);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvWayBill.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvWayBill.requestFocus();
                        }

                    } catch (Exception e) {

                    }


                }
                break;

            case 13:
                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strLR = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strLR, Toast.LENGTH_SHORT).show();

                    try {
                        fileLR = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    long mb = fileLR.length();
                    mb = mb / 1024;

                    try {
                        if (5120 > mb) {
                            tvLR.setError(null);
                            String myStr = strLR;
                            int index = myStr.lastIndexOf(".");
                            String extension = myStr.substring(index);
                            if (extension.equals(".pdf")) {
                                tvLR.setText(strLR);
                            } else if (extension.equals(".jpg")) {
                                tvLR.setText(strLR);
                            } else if (extension.equals(".png")) {
                                tvLR.setText(strLR);
                            } else if (extension.equals(".jpeg")) {
                                tvLR.setText(strLR);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                                builder.setMessage("Please Select Pdf and Image File Only ..");
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        } else {
                            tvLR.setError("Sorry, your file is too large. Upload files up to 5 MB in size below.");
                            tvLR.requestFocus();
                        }

                    } catch (Exception e) {

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