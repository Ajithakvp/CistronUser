package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cistronuser.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

public class PendingCallReport extends AppCompatActivity {



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


    // Spare Request
    RelativeLayout rlSpareRequest;
    RecyclerView rvSpareRequest;
    TextView tvSpareRequestCount;


    //Customer PO
    RelativeLayout rlCustomerPO;
    TextView tvCustomerPOCount, tvCusPOFileInvoiceAttch;
    RecyclerView rvCustomerPO;
    File fileCustomerPO;
    String strCustomerPo;


    //Installation
    String LogsitId, StringCallNo;
    CardView cvInstallation;
    TextView tvInstallReportAttach, tvWarrentycard, tvInstallationImage3, tvInstallationImage2, tvInstallationImage1, tvInstallDate,
            tvPaymentInstallation, tvRecvPaymentInstallation, tvTotalamt, tvEscalate;

    //Customer Details
    TextView tvCusDetails, tvProdDetails, tvProdSerial, tvCreated, tvReportby;
    String strTotalPayment;
    Integer strPayment, strReceviedPayment;

    //Complaint & subComplaint
    RelativeLayout rlComplaint;
    Spinner spComplaint, spSubComplaint;
    EditText edTypeComplaintCat, edTypeSubComplaintCat;
    TextView tvTypeComplaintCat, tvTypeSubComplaintCat, tvSubComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_call_report);
    }
}