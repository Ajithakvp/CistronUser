package com.example.cistronuser.Activity;

import static okhttp3.RequestBody.create;


import com.example.cistronuser.API.Interface.DateDisableInterface;
import com.example.cistronuser.API.Model.DateDisableModel;
import com.example.cistronuser.API.Response.DateDisableResponse;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LeaveDetailsInterface;
import com.example.cistronuser.API.Interface.LeaveFormDetails;
import com.example.cistronuser.API.Interface.LeavePolicyInterface;
import com.example.cistronuser.API.Interface.LeaveSubmitForm;
import com.example.cistronuser.API.Model.AvailableLeaveModel;
import com.example.cistronuser.API.Model.CompOffModel;
import com.example.cistronuser.API.Model.LeaveFormAllocatedleave;
import com.example.cistronuser.API.Model.LeaveResons;
import com.example.cistronuser.API.Model.LeavedetailsModel;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;
import com.example.cistronuser.API.Response.LeavePolicyResponse;
import com.example.cistronuser.API.Response.leavesubmitresponse;
import com.example.cistronuser.Adapter.ApprovedAdapter;
import com.example.cistronuser.Adapter.CompoffAdapter;
import com.example.cistronuser.Adapter.PendingAdapter;
import com.example.cistronuser.Adapter.RejectedAdapter;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

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

public class LeaveActivity extends Activity {


    final static String TAG = "Leave";
    //Internet
    BroadcastReceiver broadcastReceiver;
    //leaveform
    TextView tvDate, tvview, tvselectDate;
    Spinner spReson, tvLeaveType, tvDayType;
    RelativeLayout rlUpload;
    File filename;
    TextView tvDoc;
    Uri contentUri;
    String DocName;
    String reasonName;
    int reason;
    ImageView ivBack, ivdetails, ivBackbottom;
    // LottieAnimationView ivAdd;
    //Date
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    TextView tvmsg, tvHeader;


    //LeaveDetails
    TextView tvClallocatted, tvMlallocatted, tvPLallocatted, tvProlallocatted, tvClavailable, tvMlavailable, tvPLavailable, tvProlavailable, tvCompoffallocatted, tvCompOffavailable1;
    ImageView ivDownbottom, ivAdd;

    TextView tvClallocattedTag, tvMlallocattedTag, tvPLallocattedTag, tvProlallocattedTag, tvCompoffallocattedTag;


    ArrayList<String> leave = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    LeaveResons leaveResons;

    ArrayList<String> LeaveType = new ArrayList<>();
    ArrayAdapter typeAdapter;
    double avCl, avPl, avMl, avProbl, avCompOff;


    RadioGroup rbGroup, rbGroup2, rbGroup3;
    int typeid;
    int day;
    int lop;
    int compoff;
    RadioButton rbLcl, rbMl, rbPl, rbCompOff, rblop, rbfullday, rbHalfday;

    //Leave Details
    CardView cvApporved, cvPending, cvRejected, cvDeleted, cvCancel, cvCompOff;


    //Approved
    RecyclerView rvApproved;
    ApprovedAdapter approvedAdapter;
    ArrayList<LeavedetailsModel> leavedetailsModels = new ArrayList<>();

    //Pending
    RecyclerView rvPending;
    TextView tvNodata;
    PendingAdapter pendingAdapter;

    //Rejected , delected and Cancel
    RecyclerView rvRejected, rvDeleted, rvCancel;
    RejectedAdapter rejectedAdapter;

    //CompOff
    RecyclerView rvCompOff;
    CompoffAdapter compoffAdapter;
    ArrayList<CompOffModel> compOffModels = new ArrayList<>();


    //    *******************
    AvailableLeaveModel availableLeaveModel;
    LeaveFormDetails leaveFormDetails;


    String empid;
    String sel;


    //No leave
    RelativeLayout rlmsg, rlcardatted;

    TextView tvNomsg;

    ProgressBar simpleProgressBar;


    //DisableDate
    ArrayList<String> strDisbleDate = new ArrayList<>();
    ArrayList<DateDisableModel> dateDisableModels = new ArrayList<>();
    String DisbleDate;
    int Disableyear, DisableMonth, DisableDay;

    //AM(OR)PM
    LinearLayout rlHalfDay;
    RadioGroup rbGroup4;
    RadioButton rbAM,rbPM;
    String AMorPM;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        empid = PreferenceManager.getEmpID(this);


        PreferenceManager.setLoggedStatus(this, true);

        ivAdd =

                findViewById(R.id.ivMore);

        ivBack =

                findViewById(R.id.ivBack);

        ivdetails =

                findViewById(R.id.ivdetails);

        cvApporved =

                findViewById(R.id.cvApporved);

        cvPending =

                findViewById(R.id.cvPending);

        cvRejected =

                findViewById(R.id.cvRejected);

        cvDeleted =

                findViewById(R.id.cvDeleted);

        cvCancel =

                findViewById(R.id.cvCancel);

        cvCompOff =

                findViewById(R.id.cvCompOff);
        tvNomsg = findViewById(R.id.tvMsg);

        rlcardatted = findViewById(R.id.rlcardatted);
        rlmsg = findViewById(R.id.rlmsg);

        simpleProgressBar = findViewById(R.id.simpleProgressBar);


        //internet

        broadcastReceiver = new

                ConnectionRecevier();

        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //externel file
        ActivityCompat.requestPermissions(this, new String[]

                {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        //*********************No leave ***************//


        try {
            simpleProgressBar.setVisibility(View.VISIBLE);
            LeavePolicyInterface leavePolicyInterface = APIClient.getClient().create(LeavePolicyInterface.class);
            leavePolicyInterface.callleavepolicy(empid, "available_leave").enqueue(new Callback<LeavePolicyResponse>() {
                @Override
                public void onResponse(Call<LeavePolicyResponse> call, Response<LeavePolicyResponse> response) {

                    try {
                        if (response.isSuccessful()) {

                            if (response.body().getCategory().trim().equals("no leave")) {
                                tvNomsg.setText(response.body().getMessage());
                                rlmsg.setVisibility(View.VISIBLE);
                                simpleProgressBar.setVisibility(View.GONE);
                                rlcardatted.setVisibility(View.GONE);
                                ivAdd.setVisibility(View.GONE);
                                ivdetails.setVisibility(View.GONE);

                            } else {
                                CalPolicy();
                            }

                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<LeavePolicyResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }

        cvApporved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApproved();

            }
        });
        cvPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPending();

            }
        });

        cvRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRejected();

            }
        });


        cvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCancel();

            }
        });

        cvDeleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDeleted();

            }
        });

        cvCompOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCompOff();

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallLeave();
            }
        });

        ivdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallleaveDetails();
            }
        });


        //DisableDate
//        DateDisableInterface dateDisableInterface=APIClient.getClient().create(DateDisableInterface.class);
//        dateDisableInterface.calldisble(PreferenceManager.getEmpID(this),"getDisabledDates").enqueue(new Callback<DateDisableResponse>() {
//            @Override
//            public void onResponse(Call<DateDisableResponse> call, Response<DateDisableResponse> response) {
//
//
//                try {
//                    if (response.body().getDateDisableModels().size()>0){
//                        dateDisableModels=response.body().getDateDisableModels();
//                        for (int i=0;i<dateDisableModels.size();i++){
//                            strDisbleDate.add(dateDisableModels.get(i).getDate());
//
//                            Calendar disableDt = Calendar.getInstance();
//                            disableDt.set(year,month,dayOfMonth);
//                            Calendar[] disabledDays = new Calendar[1];
//                            disabledDays[0] = disableDt;
//                            datePickerDialog.setDisabledDays(disabledDays);
//                        }
//                    }
//
//
//                }catch (Exception e){
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DateDisableResponse> call, Throwable t) {
//
//            }
//        });


        //        *********
        leaveFormDetails = APIClient.getClient().create(LeaveFormDetails.class);
        leaveFormDetails.callleavedetails(empid, "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {

            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {
                try {


                    if (response.isSuccessful()) {
                        avCl = Double.parseDouble(response.body().getAvailableLeaveModel().getCl());
                        avMl = Double.parseDouble(response.body().getAvailableLeaveModel().getMl());
                        avPl = Double.parseDouble(response.body().getAvailableLeaveModel().getPl());
                        avProbl = Double.parseDouble(response.body().getAvailableLeaveModel().getProbl());
                        avCompOff = Double.parseDouble(response.body().getAvailableLeaveModel().getCompoff());


                    }
                    //Log.e(TAG, "onResponse: CL" + avCl + ":" + avMl + ":" + avPl + ":" + avProbl + ":" + avCompOff);
                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<LeaveFormAllocatedleave> call, Throwable t) {

            }
        });

    }

    private void callCompOff() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.comoffdesign);
        bottomSheetDialog1.show();

        rvCompOff = bottomSheetDialog1.findViewById(R.id.rvCompOff);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);

        callCompoffReport();

        compoffAdapter = new CompoffAdapter(this, compOffModels);
        LinearLayoutManager compoff = new LinearLayoutManager(this);
        compoff.setOrientation(RecyclerView.VERTICAL);
        rvCompOff.setAdapter(compoffAdapter);
        rvCompOff.setLayoutManager(compoff);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });


    }

    private void callCompoffReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("compoffReport", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        progressDialog.dismiss();
                        simpleProgressBar.setVisibility(View.GONE);
                        compoffAdapter.compOffModels = response.body().getCompOffModels();
                        compoffAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void callDeleted() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.deleted);
        bottomSheetDialog1.show();

        rvDeleted = bottomSheetDialog1.findViewById(R.id.rvDeleted);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);
        calldeletedReport();

        rejectedAdapter = new RejectedAdapter(this, leavedetailsModels);
        LinearLayoutManager deleted = new LinearLayoutManager(this);
        deleted.setOrientation(RecyclerView.VERTICAL);
        rvDeleted.setLayoutManager(deleted);
        rvDeleted.setAdapter(rejectedAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });


    }

    private void calldeletedReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("deletedLeaveReqs", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        rejectedAdapter.leavedetailsModels = response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {
                    progressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void callCancel() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.cancel);
        bottomSheetDialog1.show();

        rvCancel = bottomSheetDialog1.findViewById(R.id.rvCancel);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);
        callcancelReport();

        rejectedAdapter = new RejectedAdapter(this, leavedetailsModels);
        LinearLayoutManager cancel = new LinearLayoutManager(this);
        cancel.setOrientation(RecyclerView.VERTICAL);
        rvCancel.setLayoutManager(cancel);
        rvCancel.setAdapter(rejectedAdapter);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });

    }

    private void callcancelReport() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("cancelledLeaveReqs", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        rejectedAdapter.leavedetailsModels = response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

                progressDialog.dismiss();
            }

        });
    }

    private void callRejected() {

        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.rejected);
        bottomSheetDialog1.show();

        rvRejected = bottomSheetDialog1.findViewById(R.id.rvRejected);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);
        CallRejected();

        rejectedAdapter = new RejectedAdapter(this, leavedetailsModels);
        LinearLayoutManager rejected = new LinearLayoutManager(this);
        rejected.setOrientation(RecyclerView.VERTICAL);
        rvRejected.setLayoutManager(rejected);
        rvRejected.setAdapter(rejectedAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });

    }

    private void CallRejected() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("rejectedLeaveReqs", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        rejectedAdapter.leavedetailsModels = response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });

    }

    private void callPending() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.pending);
        bottomSheetDialog1.show();

        rvPending = bottomSheetDialog1.findViewById(R.id.rvPending);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);

        Callpending();

        pendingAdapter = new PendingAdapter(this, leavedetailsModels);
        LinearLayoutManager pending = new LinearLayoutManager(this);
        pending.setOrientation(RecyclerView.VERTICAL);
        rvPending.setAdapter(pendingAdapter);
        rvPending.setLayoutManager(pending);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });

    }

    private void Callpending() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("pendingLeaveReqs", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        pendingAdapter.leavedetailsModels = response.body().getData();
                        pendingAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });


    }

    private void callApproved() {

        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.approved);
        bottomSheetDialog1.show();

        rvApproved = bottomSheetDialog1.findViewById(R.id.rvApproved);
        ImageView ivBack = bottomSheetDialog1.findViewById(R.id.ivBack);

        CallApprovedList();

        approvedAdapter = new ApprovedAdapter(LeaveActivity.this, leavedetailsModels);
        LinearLayoutManager approved = new LinearLayoutManager(LeaveActivity.this);
        approved.setOrientation(RecyclerView.VERTICAL);
        rvApproved.setLayoutManager(approved);
        rvApproved.setAdapter(approvedAdapter);
        rvApproved.setNestedScrollingEnabled(false);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });


    }

    private void CallApprovedList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveDetailsInterface leaveDetailsInterface = APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("approvedLeaveReqs", PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();

                        // Toast.makeText(this,+approvedAdapter.leavedetailsModels=response.body().getData(), Toast.LENGTH_SHORT).show();
                        simpleProgressBar.setVisibility(View.GONE);
                        approvedAdapter.leavedetailsModels = response.body().getData();
                        approvedAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    private void CalPolicy() {
        simpleProgressBar.setVisibility(View.VISIBLE);
        LeavePolicyInterface leavePolicyInterface = APIClient.getClient().create(LeavePolicyInterface.class);
        leavePolicyInterface.callleavepolicy(empid, "available_leave").enqueue(new Callback<LeavePolicyResponse>() {
            @Override
            public void onResponse(Call<LeavePolicyResponse> call, Response<LeavePolicyResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        simpleProgressBar.setVisibility(View.GONE);
                        // tvmsg.setText(response.body().getMessage());
                        // tvHeader.setText(response.body().getCategory());
                        if (response.body().getCategory().trim().equals("leave policy")) {
                            final Dialog dialog = new Dialog(LeaveActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.profile);
                            tvmsg = dialog.findViewById(R.id.tvmsg);
                            tvHeader = dialog.findViewById(R.id.tvHeader);
                            CheckBox checkBox = dialog.findViewById(R.id.cbname);
                            Button button = dialog.findViewById(R.id.btSubmit);
                            Button btClose = dialog.findViewById(R.id.btClose);


                            rlmsg.setVisibility(View.GONE);
                            rlcardatted.setVisibility(View.VISIBLE);
                            ivAdd.setVisibility(View.VISIBLE);
                            ivdetails.setVisibility(View.VISIBLE);


                            tvmsg.setText(response.body().getMessage());
                            tvHeader.setText(response.body().getCategory());

                            checkBox.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean checked = ((CheckBox) v).isChecked();

                                    if (checked) {

                                        button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                LeavePolicyInterface leavePolicyInterface = APIClient.getClient().create(LeavePolicyInterface.class);
                                                leavePolicyInterface.callleavepolicy(empid, "confirmLeavePolicy").enqueue(new Callback<LeavePolicyResponse>() {
                                                    @Override
                                                    public void onResponse(Call<LeavePolicyResponse> call, Response<LeavePolicyResponse> response) {
                                                        if (response.isSuccessful()) {


                                                            Toast.makeText(LeaveActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                            dialog.dismiss();


                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<LeavePolicyResponse> call, Throwable t) {

                                                    }
                                                });
                                            }
                                        });

                                    } else {
                                        Toast.makeText(LeaveActivity.this, "Policy Check", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                            dialog.show();

                            btClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    onBackPressed();
                                }
                            });
//
                        } else if (response.body().getCategory().trim().equals("leave form")) {
                            //Toast.makeText(LeaveActivity.this, response.body().getCategory(), Toast.LENGTH_SHORT).show();

                            rlmsg.setVisibility(View.GONE);
                            rlcardatted.setVisibility(View.VISIBLE);
                            ivAdd.setVisibility(View.VISIBLE);
                            ivdetails.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<LeavePolicyResponse> call, Throwable t) {

            }
        });
    }


    private void CallleaveDetails() {

        simpleProgressBar.setVisibility(View.VISIBLE);
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog1.setContentView(R.layout.leave_lop);
        bottomSheetDialog1.show();


        tvClallocatted = bottomSheetDialog1.findViewById(R.id.tvClallocatted);
        tvMlallocatted = bottomSheetDialog1.findViewById(R.id.tvMlallocatted);
        tvPLallocatted = bottomSheetDialog1.findViewById(R.id.tvPLallocatted);
        tvProlallocatted = bottomSheetDialog1.findViewById(R.id.tvProlallocatted);
        tvClavailable = bottomSheetDialog1.findViewById(R.id.tvClavailable1);
        tvMlavailable = bottomSheetDialog1.findViewById(R.id.tvMlavailable1);
        tvPLavailable = bottomSheetDialog1.findViewById(R.id.tvPLavailable1);
        tvProlavailable = bottomSheetDialog1.findViewById(R.id.tvProlavailable1);
        ivDownbottom = bottomSheetDialog1.findViewById(R.id.ivDownbottom);
        tvCompoffallocatted = bottomSheetDialog1.findViewById(R.id.tvCompoffallocatted);
        tvCompOffavailable1 = bottomSheetDialog1.findViewById(R.id.tvCompOffavailable1);


        tvClallocattedTag = bottomSheetDialog1.findViewById(R.id.tvClallocattedTag);
        tvMlallocattedTag = bottomSheetDialog1.findViewById(R.id.tvMlallocattedTag);
        tvPLallocattedTag = bottomSheetDialog1.findViewById(R.id.tvPLallocattedTag);
        tvProlallocattedTag = bottomSheetDialog1.findViewById(R.id.tvProlallocattedTag);
        tvCompoffallocattedTag = bottomSheetDialog1.findViewById(R.id.tvCompoffallocattedTag);

        callDetail();


        ivDownbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog1.dismiss();
            }
        });

    }


    private void callDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveFormDetails leaveFormDetails = APIClient.getClient().create(LeaveFormDetails.class);
        leaveFormDetails.callleavedetails(empid, "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {


                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        simpleProgressBar.setVisibility(View.GONE);
                        if (response.body().getEmptype().trim().equals("1") || response.body().getEmptype().trim().equals("2")) {
                            tvClallocatted.setText(response.body().getAllocatedleaveModel().getCl());
                            tvMlallocatted.setText(response.body().getAllocatedleaveModel().getMl());
                            tvPLallocatted.setText(response.body().getAllocatedleaveModel().getPl());
                            tvCompoffallocatted.setText(response.body().getAllocatedleaveModel().getCompoff());

                            tvClavailable.setText(response.body().getAvailableLeaveModel().getCl());
                            tvMlavailable.setText(response.body().getAvailableLeaveModel().getMl());
                            tvPLavailable.setText(response.body().getAvailableLeaveModel().getPl());
                            tvCompOffavailable1.setText(response.body().getAvailableLeaveModel().getCompoff());


                            tvMlallocattedTag.setVisibility(View.VISIBLE);
                            tvPLallocattedTag.setVisibility(View.VISIBLE);
                            tvClallocattedTag.setVisibility(View.VISIBLE);
                            tvCompoffallocattedTag.setVisibility(View.VISIBLE);


                            tvProlallocattedTag.setVisibility(View.GONE);
                            tvProlallocatted.setVisibility(View.GONE);
                            tvProlavailable.setVisibility(View.GONE);


                        } else if (response.body().getEmptype().trim().equals("3")) {


                            progressDialog.dismiss();
                            tvMlallocattedTag.setVisibility(View.GONE);
                            tvMlallocatted.setVisibility(View.GONE);
                            tvMlavailable.setVisibility(View.GONE);
                            tvPLallocattedTag.setVisibility(View.GONE);
                            tvPLallocatted.setVisibility(View.GONE);
                            tvPLavailable.setVisibility(View.GONE);
                            tvClallocattedTag.setVisibility(View.GONE);
                            tvClavailable.setVisibility(View.GONE);
                            tvClallocatted.setVisibility(View.GONE);


                            tvProlallocattedTag.setVisibility(View.VISIBLE);
                            tvCompoffallocattedTag.setVisibility(View.VISIBLE);
                            tvProlallocatted.setText(response.body().getAllocatedleaveModel().getProbl());
                            tvProlavailable.setText(response.body().getAvailableLeaveModel().getProbl());
                            tvCompOffavailable1.setText(response.body().getAvailableLeaveModel().getCompoff());
                            tvCompoffallocatted.setText(response.body().getAllocatedleaveModel().getCompoff());


                        }
                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LeaveFormAllocatedleave> call, Throwable t) {

                progressDialog.dismiss();
            }
        });

    }

    private void CallLeave() {


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.leave_from);
        bottomSheetDialog.show();


        leave.clear();
        tvDate = bottomSheetDialog.findViewById(R.id.tvDate);
        spReson = bottomSheetDialog.findViewById(R.id.spReson);
        tvLeaveType = bottomSheetDialog.findViewById(R.id.tvLeaveType);
        tvDayType = bottomSheetDialog.findViewById(R.id.tvDayType);
        ivBackbottom = bottomSheetDialog.findViewById(R.id.ivBackbottom);
        rlUpload = bottomSheetDialog.findViewById(R.id.rlUpload);
        tvDoc = bottomSheetDialog.findViewById(R.id.tvConveyanceDoc);
        rbGroup = bottomSheetDialog.findViewById(R.id.rbGroup);
        rbLcl = bottomSheetDialog.findViewById(R.id.rbLcl);
        rbMl = bottomSheetDialog.findViewById(R.id.rbMl);
        rbPl = bottomSheetDialog.findViewById(R.id.rbPl);
        rbCompOff = bottomSheetDialog.findViewById(R.id.rbCompOff);
        rbGroup2 = bottomSheetDialog.findViewById(R.id.rbGroup2);
        rblop = bottomSheetDialog.findViewById(R.id.rblop);
        rbGroup3 = bottomSheetDialog.findViewById(R.id.rbGroup3);
        rbfullday = bottomSheetDialog.findViewById(R.id.rbfullday);
        rbHalfday = bottomSheetDialog.findViewById(R.id.rbHalfday);
        tvselectDate = bottomSheetDialog.findViewById(R.id.tvselectDate);
        TextView tvSubmit = bottomSheetDialog.findViewById(R.id.tvSubmit);
        rbGroup4 = bottomSheetDialog.findViewById(R.id.rbGroup4);
        rlHalfDay = bottomSheetDialog.findViewById(R.id.rlHalfDay);
        rbAM = bottomSheetDialog.findViewById(R.id.rbAM);
        rbPM = bottomSheetDialog.findViewById(R.id.rbPM);


        tvview = bottomSheetDialog.findViewById(R.id.tvview);


        if ((avCompOff + avCl + avPl + avProbl) == 0) {

            tvview.setVisibility(View.VISIBLE);
            rblop.setChecked(true);
            rblop.setClickable(false);
            rblop.setVisibility(View.VISIBLE);

        }


//        Date d = new Date();
//        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
//        tvDate.setText(s);

        CallDisableDate();
        callRes();
        Calltype();


        arrayAdapter = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, leave);
        arrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spReson.setAdapter(arrayAdapter);

        typeAdapter = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, LeaveType);
        typeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        tvLeaveType.setAdapter(typeAdapter);

        rbGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbHalfday.setError(null);
                View gb4 = rbGroup4.findViewById(checkedId);
                int rb4 = rbGroup4.indexOfChild(gb4);
                switch (rb4) {
                    case 0:
                        AMorPM="AM";
                        break;

                    case 1:

                        AMorPM="PM";

                        break;
                }
            }
        });

        rbGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbHalfday.setError(null);
                View gb3 = rbGroup3.findViewById(checkedId);
                int rb3 = rbGroup3.indexOfChild(gb3);
                switch (rb3) {
                    case 0:
                        // Toast.makeText(LeaveActivity.this, "full day", Toast.LENGTH_SHORT).show();
                        day = 0;
                        AMorPM="";
                        rlHalfDay.setVisibility(View.GONE);

                        break;

                    case 1:
                        // Toast.makeText(LeaveActivity.this, "Half day", Toast.LENGTH_SHORT).show();
                        day = 1;
                        rlHalfDay.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        rbGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View gb2 = rbGroup2.findViewById(checkedId);
                int rb2 = rbGroup2.indexOfChild(gb2);
                rblop.isChecked();
                switch (rb2) {
                    case 0:

                        // Toast.makeText(LeaveActivity.this, "CompOff", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:

                        // Toast.makeText(LeaveActivity.this, "Lop", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View view = rbGroup.findViewById(checkedId);
                int rb = rbGroup.indexOfChild(view);
                // Toast.makeText(LeaveActivity.this, , Toast.LENGTH_SHORT).show();
                switch (rb) {
                    case 0:
                        typeid = 1;
                        //Toast.makeText(LeaveActivity.this, "Cl", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        typeid = 3;
                        //Toast.makeText(LeaveActivity.this, "ML", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        typeid = 2;
                        // Toast.makeText(LeaveActivity.this, "PL", Toast.LENGTH_SHORT).show();
                        break;
                }

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


        ivBackbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(LeaveActivity.this, R.style.ProgressBarDialog);
                progressDialog.setMessage("Leave Date ...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                DateDisableInterface dateDisableInterface = APIClient.getClient().create(DateDisableInterface.class);
                dateDisableInterface.calldisble(PreferenceManager.getEmpID(LeaveActivity.this), "getDisabledDates").enqueue(new Callback<DateDisableResponse>() {
                    @Override
                    public void onResponse(Call<DateDisableResponse> call, Response<DateDisableResponse> response) {


                        try {

                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                //Log.e(TAG, "onResponse: "+response.body().getDateDisableModels().size() );
                                 callDate();
                                //  date();

                                if (response.body().getDateDisableModels().size() > 0) {
                                    progressDialog.dismiss();
                                    dateDisableModels = response.body().getDateDisableModels();
                                    for (int i = 0; i < dateDisableModels.size(); i++) {
                                        String[] dt = dateDisableModels.get(i).getDate().split("-");
                                        Disableyear = Integer.parseInt(dt[0]);
                                        DisableMonth = Integer.parseInt(dt[1]) - 1;
                                        DisableDay = Integer.parseInt(dt[2]);

                                        Calendar disableDt = Calendar.getInstance();
                                        disableDt.set(Disableyear, DisableMonth, DisableDay);
                                        Calendar[] disabledDays = new Calendar[1];
                                        disabledDays[0] = disableDt;
                                        datePickerDialog.setDisabledDays(disabledDays);
                                        datePickerDialog.setHighlightedDays(disabledDays);
                                    }

                                }
                            }else {
                                progressDialog.dismiss();
                            }


                        } catch (Exception e) {
                            progressDialog.dismiss();


                        }

                    }

                    @Override
                    public void onFailure(Call<DateDisableResponse> call, Throwable t) {

                        progressDialog.dismiss();


                    }
                });




            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    boolean isfilled = true;
                    if (tvDate.getText().toString().trim().equals("")) {
                        tvDate.setError("Select a date");
                        tvDate.requestFocus();
                        isfilled = false;
                    } else if (spReson.getSelectedItemPosition() == 0) {
                        setSpinnerError(spReson, "Select a Reason");
                        tvDate.requestFocus();
                        isfilled = false;
                    } else if (rbGroup.getCheckedRadioButtonId() == -1 && rbGroup2.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(LeaveActivity.this, "Select leave type", Toast.LENGTH_SHORT).show();
                        isfilled = false;
                        rbGroup.requestFocus();
                    } else if (rbGroup3.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(LeaveActivity.this, "Select half day or full day", Toast.LENGTH_SHORT).show();
                        rbHalfday.requestFocus();
                        isfilled = false;
                    }else if (day==1) {
                        if (rbGroup4.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(LeaveActivity.this, "Select AM or PM", Toast.LENGTH_SHORT).show();
                            rbPM.requestFocus();
                            isfilled = false;
                        }
                    }
                    if (isfilled) {

                        if (filename == null) {
                            Callsubmit(bottomSheetDialog);
                            //bottomSheetDialog.dismiss();
                        } else {
                            calluploadsubmit(bottomSheetDialog);
                            bottomSheetDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    //Log.e(TAG, "onClick: " + e.getMessage());
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

    private void calluploadsubmit(BottomSheetDialog bottomSheetDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Leave Request is processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveSubmitForm leaveSubmitForm = APIClient.getClient().create(LeaveSubmitForm.class);

        int lop = rblop.isChecked() ? 1 : 0;
        int compoff = rbCompOff.isChecked() ? 1 : 0;
        int clmlpl;
        if (lop == 1) {
            clmlpl = 2;
        } else {
            clmlpl = rbLcl.isChecked() ? 1 : (rbMl.isChecked() ? 3 : (rbPl.isChecked() ? 2 : 0));
        }


        RequestBody requestFile = create(MediaType.parse("multipart/form-data"), filename);
        MultipartBody.Part file = MultipartBody.Part.createFormData("file_in", filename.getName(), requestFile);
        RequestBody action = create(MediaType.parse("text/plain"), "applyLeave");
        RequestBody empid = create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody AMPM = create(MediaType.parse("text/plain"),AMorPM);
        RequestBody code = create(MediaType.parse("text/plain"), String.valueOf(clmlpl));
        RequestBody reasonid = create(MediaType.parse("text/plain"), String.valueOf(reason));
        RequestBody date = create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody fhhd = create(MediaType.parse("text/plain"), String.valueOf(day));
        RequestBody LOP = create(MediaType.parse("text/plain"), String.valueOf(lop));
        RequestBody COMPOFF = create(MediaType.parse("text/plain"), String.valueOf(compoff));


        leaveSubmitForm.callLeaveformsubmitWithDocumentAPI(action, empid,AMPM,code, reasonid, date, fhhd, LOP, COMPOFF, file).enqueue(new Callback<leavesubmitresponse>() {
            @Override
            public void onResponse(Call<leavesubmitresponse> call, Response<leavesubmitresponse> response) {

                try {
                    if (response.isSuccessful()) {

                        Toast.makeText(LeaveActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        bottomSheetDialog.dismiss();

                    }
                } catch (Exception e) {
                    // Log.e(TAG, "onResponse: " + e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<leavesubmitresponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LeaveActivity.this, "Not Submited", Toast.LENGTH_SHORT).show();
                // Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });


    }

    private void Callsubmit(BottomSheetDialog bottomSheetDialog) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Leave Request is processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        LeaveSubmitForm leaveSubmitForm = APIClient.getClient().create(LeaveSubmitForm.class);
        int lop = rblop.isChecked() ? 1 : 0;
        int compoff = rbCompOff.isChecked() ? 1 : 0;
        int clmlpl;
        if (lop == 1)
            clmlpl = 2;
        else {
            clmlpl = rbLcl.isChecked() ? 1 : (rbMl.isChecked() ? 3 : (rbPl.isChecked() ? 2 : 0));
        }
        leaveSubmitForm.callLeaveformsubmit("applyLeave", empid,AMorPM,clmlpl, reason, tvDate.getText().toString(), day, lop, compoff, String.valueOf(filename)).enqueue(new Callback<leavesubmitresponse>() {
            @Override
            public void onResponse(Call<leavesubmitresponse> call, Response<leavesubmitresponse> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(LeaveActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        bottomSheetDialog.dismiss();

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<leavesubmitresponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    private void Calltype() {
        LeaveFormDetails formDetails = APIClient.getClient().create(LeaveFormDetails.class);
        formDetails.callleavedetails(empid, "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {
                try {
                    if (response.isSuccessful()) {
                        String type = response.body().getLeaveResons().getLeavetype();
                        LeaveType.add("");
                        String[] typearray = type.split(";");
                        for (int i = 0; i < typearray.length; i++) {
                            LeaveType.add(typearray[i]);
                        }

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LeaveFormAllocatedleave> call, Throwable t) {

            }
        });
    }

    private void callRes() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        LeaveFormDetails leaveFormDetails = APIClient.getClient().create(LeaveFormDetails.class);
        leaveFormDetails.callleavedetails(empid, "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {


                try {
                    if (response.isSuccessful()) {

                        progressDialog.dismiss();


                        // Log.e(TAG, "onResponse: " + response.body().getLeaveResons().getReasons());
                        String name = response.body().getLeaveResons().getReasons();

                        leave.add("----Select----");
                        String[] strings = name.split(";");
                        for (int i = 0; i < strings.length; i++) {
                            //Log.e(TAG, "onResponse: " + strings[i]);
                            leave.add(strings[i]);
                        }

                        arrayAdapter.notifyDataSetChanged();


                        spReson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @SuppressLint("SuspiciousIndentation")
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                try {


                                    if (!leave.get(position).trim().equals("----Select----"))

                                        // Toast.makeText(LeaveActivity.this, leave.get(position), Toast.LENGTH_SHORT).show();
                                        // reason = Integer.parseInt(leave.get(position));
                                        reason = position;
                                    //reasonName=leave.get(position);

                                    // Toast.makeText(LeaveActivity.this, LeaveType.get(position), Toast.LENGTH_SHORT).show();

                                    String cl = LeaveType.get(position);
                                    String[] clpl = cl.split(",");
                                    rbPl.setEnabled(false);
                                    rbLcl.setEnabled(false);
                                    rbMl.setEnabled(false);

                                    if (avCompOff == 0) {

                                        for (int i = 0; i < clpl.length; i++) {

                                            if (clpl[i].trim().equals("CL")) {
                                                if (avCl > 0 || avProbl > 0) {
                                                    rbLcl.setEnabled(true);
                                                    rlUpload.setVisibility(View.GONE);
                                                } else {
                                                    rblop.setEnabled(true);
                                                }

                                            } else if (clpl[i].trim().equals("PL")) {
                                                if (avPl > 0 || avProbl > 0) {
                                                    rbPl.setEnabled(true);
                                                    rlUpload.setVisibility(View.GONE);
                                                } else {
                                                    rblop.setEnabled(true);
                                                }
                                            } else if (clpl[i].trim().equals("ML")) {
                                                if (avMl > 0 || avProbl > 0) {
                                                    rblop.setEnabled(false);
                                                    rbMl.setEnabled(true);
                                                    rlUpload.setVisibility(View.VISIBLE);
                                                } else {
                                                    rblop.setEnabled(true);
                                                }
                                            }

                                        }
                                    } else {
                                        rbCompOff.setChecked(true);
                                        rbCompOff.setClickable(false);
                                        rbCompOff.setVisibility(View.VISIBLE);

                                    }
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                                Toast.makeText(LeaveActivity.this, "Name", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<LeaveFormAllocatedleave> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }


    private void callDate() {

        datePickerDialog = new DatePickerDialog();
        datePickerDialog.setTitle("Choose a Leave Date");
        datePickerDialog.setAccentColor("#6495ED");
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //calendar.add(Calendar.MONTH, 3);
        Calendar min_date_c = Calendar.getInstance();
        datePickerDialog.setMinDate(min_date_c);

        Calendar max_date_c = Calendar.getInstance();
        max_date_c.set(Calendar.YEAR, year + 2);
        datePickerDialog.setMaxDate(max_date_c);

        for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
            int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY) {
                Calendar[] disabledDays = new Calendar[1];
                disabledDays[0] = loopdate;
                datePickerDialog.setDisabledDays(disabledDays);
            }
        }

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                String monthStr;
                String DayStr;
                boolean isDisble = false;
                String date = tvDate.getText().toString();
                monthOfYear++;
                monthStr = (monthOfYear < 10) ? "0" + monthOfYear : "" + monthOfYear;
                DayStr = (dayOfMonth < 10) ? "0" + dayOfMonth : "" + dayOfMonth;

                String selectedDt = year + "-" + monthStr + "-" + DayStr;

//                if (!date.isEmpty()) {
//                    if (date.contains(", " + selectedDt)) {
//                        date = date.replace(", " + selectedDt, "");
//                    } else if (date.contains(selectedDt)) {
//                        if (date.equals(selectedDt) || isDisble==true  ) {
//                            date ="";
//                            tvselectDate.setText("");
//                        }
//                        else {
//                            date = date.replace(selectedDt + ", ", "");
//                        }
//                    } else {
//                        System.out.println("3\n");
//                        date += ", " + selectedDt;
//                    }
//                } else {
//                    date = selectedDt;
//                }


                date = selectedDt;
                for (int i = 0; i < dateDisableModels.size(); i++) {
                    String dt = dateDisableModels.get(i).getDate().toString();

                    if (dt.trim().equals(date)) {
                        tvselectDate.setText("");
                        tvDate.setText("");
                        isDisble = true;
                    }

                }
                tvDate.setError(null);


                if (isDisble == false) {
                    tvDate.setText(date);
                } else {
                    tvselectDate.setText("");
                    tvDate.setText("");

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LeaveActivity.this, R.style.AlertDialogCustom);
                    alertDialog.setMessage("You Cannot Select this Date !");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = alertDialog.create();
                    dialog.show();

                }

//                    if (tvDate.getText().toString().length() > 0) {
//                        Log.e(TAG, "onDateSet: "+ tvDate.getText().toString().length());
//                        int count = date.split(",", -1).length;
//                        Log.e(TAG, "onDateSet: c " + count);
//                        if (count == 0)
//                            tvselectDate.setText("");
//                        else if (count == 1)
//                            tvselectDate.setText("One day is selected");
//                        else
//
//                            tvselectDate.setText(count + " days are selected");
//                    }


            }

        });




        datePickerDialog.show(getFragmentManager(), "Leave Date");


    }

    private void CallDisableDate() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    DocName = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + DocName, Toast.LENGTH_SHORT).show();

                    try {
                        if (DocName.length() > 0) {
                            tvDoc.setText(DocName);
                        }

                    } catch (Exception e) {

                    }


                    try {
                        filename = FileUtli.from(this, contentUri);
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