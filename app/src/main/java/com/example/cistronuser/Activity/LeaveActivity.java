package com.example.cistronuser.Activity;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LeaveDetailsInterface;
import com.example.cistronuser.API.Interface.LeaveFormDetails;
import com.example.cistronuser.API.Interface.LeavePolicyInterface;
import com.example.cistronuser.API.Model.CompOffModel;
import com.example.cistronuser.API.Model.LeaveFormAllocatedleave;
import com.example.cistronuser.API.Model.LeaveResons;
import com.example.cistronuser.API.Model.LeavedetailsModel;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;
import com.example.cistronuser.API.Response.LeavePolicyResponse;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveActivity extends Activity {


    //Internet
    BroadcastReceiver broadcastReceiver;

    //leaveform
    TextView tvDate;
    Spinner spReson, tvLeaveType, tvDayType;
    RelativeLayout rlUpload;
    File file;
    TextView tvDoc;
    String DocName;

    final static String TAG = "Name";
    ImageView ivBack, ivdetails, ivBackbottom;
   // LottieAnimationView ivAdd;
    //Date
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    TextView tvmsg, tvHeader;


    //LeaveDetails
    TextView tvClallocatted, tvMlallocatted, tvPLallocatted, tvProlallocatted, tvClavailable,
            tvMlavailable, tvPLavailable, tvProlavailable, tvCompoffallocatted, tvCompOffavailable1;
    ImageView ivDownbottom,ivAdd;

    TextView tvClallocattedTag, tvMlallocattedTag, tvPLallocattedTag, tvProlallocattedTag, tvCompoffallocattedTag;


    ArrayList<String> leave = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    LeaveResons leaveResons;

    ArrayList<String> LeaveType = new ArrayList<>();
    ArrayAdapter typeAdapter;
    int avCl,avPL,avMl,avProbl,avCompOff;


    RadioGroup rbGroup;
    RadioButton rbLcl;
    RadioButton rbMl;
    RadioButton rbPl;



    //Leave Details
    CardView cvApporved,cvPending,cvRejected,cvDeleted,cvCancel,cvCompOff;


    //Approved
    RecyclerView rvApproved;
    ApprovedAdapter approvedAdapter;
    ArrayList<LeavedetailsModel>leavedetailsModels=new ArrayList<>();

    //Pending
    RecyclerView rvPending;
    PendingAdapter pendingAdapter;

    //Rejected , delected and Cancel
    RecyclerView rvRejected,rvDeleted,rvCancel;
    RejectedAdapter rejectedAdapter;

    //CompOff
    RecyclerView rvCompOff;
    CompoffAdapter compoffAdapter;
    ArrayList<CompOffModel>compOffModels=new ArrayList<>();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);


        PreferenceManager.setLoggedStatus(this, true);

        ivAdd = findViewById(R.id.ivMore);
        ivBack = findViewById(R.id.ivBack);
        ivdetails = findViewById(R.id.ivdetails);

        cvApporved = findViewById(R.id.cvApporved);
        cvPending = findViewById(R.id.cvPending);
        cvRejected = findViewById(R.id.cvRejected);
        cvDeleted = findViewById(R.id.cvDeleted);
        cvCancel = findViewById(R.id.cvCancel);
        cvCompOff = findViewById(R.id.cvCompOff);


        //internet

        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //externel file
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        CalPolicy();

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
    }

    private void callCompOff() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.comoffdesign);
        bottomSheetDialog1.show();

        rvCompOff=bottomSheetDialog1.findViewById(R.id.rvCompOff);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);

        callCompoffReport();

        compoffAdapter =new CompoffAdapter(this,compOffModels);
        LinearLayoutManager compoff=new LinearLayoutManager(this);
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
        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("compoffReport",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                        compoffAdapter.compOffModels=response.body().getCompOffModels();
                        compoffAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void callDeleted() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.deleted);
        bottomSheetDialog1.show();

        rvDeleted=bottomSheetDialog1.findViewById(R.id.rvDeleted);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);
        calldeletedReport();

        rejectedAdapter=new RejectedAdapter(this,leavedetailsModels);
        LinearLayoutManager deleted=new LinearLayoutManager(this);
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
        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("deletedLeaveReqs",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                        rejectedAdapter.leavedetailsModels=response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void callCancel() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.cancel);
        bottomSheetDialog1.show();

        rvCancel=bottomSheetDialog1.findViewById(R.id.rvCancel);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);
        callcancelReport();

        rejectedAdapter=new RejectedAdapter(this,leavedetailsModels);
        LinearLayoutManager cancel=new LinearLayoutManager(this);
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
        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("cancelledLeaveReqs",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                        rejectedAdapter.leavedetailsModels=response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void callRejected() {

        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.rejected);
        bottomSheetDialog1.show();

        rvRejected=bottomSheetDialog1.findViewById(R.id.rvRejected);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);
        CallRejected();

        rejectedAdapter=new RejectedAdapter(this,leavedetailsModels);
        LinearLayoutManager rejected=new LinearLayoutManager(this);
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

        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("rejectedLeaveReqs",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                        rejectedAdapter.leavedetailsModels=response.body().getData();
                        rejectedAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });

    }

    private void callPending() {
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.pending);
        bottomSheetDialog1.show();

        rvPending=bottomSheetDialog1.findViewById(R.id.rvPending);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);

        Callpending();

        pendingAdapter=new PendingAdapter(this,leavedetailsModels);
        LinearLayoutManager pending=new LinearLayoutManager(this);
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
        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("pendingLeaveReqs",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                        pendingAdapter.leavedetailsModels=response.body().getData();
                        pendingAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });


    }

    private void callApproved() {

        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
        bottomSheetDialog1.setContentView(R.layout.approved);
        bottomSheetDialog1.show();

        rvApproved=bottomSheetDialog1.findViewById(R.id.rvApproved);
        ImageView ivBack=bottomSheetDialog1.findViewById(R.id.ivBack);

        CallApprovedList();

        approvedAdapter=new ApprovedAdapter(LeaveActivity.this,leavedetailsModels);
        LinearLayoutManager approved=new LinearLayoutManager(LeaveActivity.this);
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
        LeaveDetailsInterface leaveDetailsInterface=APIClient.getClient().create(LeaveDetailsInterface.class);
        leaveDetailsInterface.CallDetails("approvedLeaveReqs",PreferenceManager.getEmpID(this)).enqueue(new Callback<LeaveDetailsResponse>() {
            @Override
            public void onResponse(Call<LeaveDetailsResponse> call, Response<LeaveDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){

                       // Toast.makeText(this,+approvedAdapter.leavedetailsModels=response.body().getData(), Toast.LENGTH_SHORT).show();

                        approvedAdapter.leavedetailsModels=response.body().getData();
                        approvedAdapter.notifyDataSetChanged();

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<LeaveDetailsResponse> call, Throwable t) {

            }
        });
    }

    private void CalPolicy() {
        LeavePolicyInterface leavePolicyInterface = APIClient.getClient().create(LeavePolicyInterface.class);
        leavePolicyInterface.callleavepolicy(PreferenceManager.getEmpID(this), "available_leave").enqueue(new Callback<LeavePolicyResponse>() {
            @Override
            public void onResponse(Call<LeavePolicyResponse> call, Response<LeavePolicyResponse> response) {

                try {
                    if (response.isSuccessful()) {

                        // tvmsg.setText(response.body().getMessage());
                        // tvHeader.setText(response.body().getCategory());
                        if (response.body().getCategory().trim().equals("leave policy")) {
                            final Dialog dialog = new Dialog(LeaveActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.profile);
                            tvmsg = dialog.findViewById(R.id.tvmsg);
                            tvHeader = dialog.findViewById(R.id.tvHeader);
                            CheckBox checkBox = dialog.findViewById(R.id.cbname);
                            Button button = dialog.findViewById(R.id.btSubmit);
                            Button btClose = dialog.findViewById(R.id.btClose);


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
                                                leavePolicyInterface.callleavepolicy(PreferenceManager.getEmpID(LeaveActivity.this), "confirmLeavePolicy").enqueue(new Callback<LeavePolicyResponse>() {
                                                    @Override
                                                    public void onResponse(Call<LeavePolicyResponse> call, Response<LeavePolicyResponse> response) {
                                                        if (response.isSuccessful()) {


                                                            //  Toast.makeText(LeaveActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(LeaveActivity.this, response.body().getCategory(), Toast.LENGTH_SHORT).show();
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
        BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(this);
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
        LeaveFormDetails leaveFormDetails = APIClient.getClient().create(LeaveFormDetails.class);
        leaveFormDetails.callleavedetails(PreferenceManager.getEmpID(this), "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getEmptype().trim().equals("1") || response.body().getEmptype().trim().equals("2") ) {

                            Log.e(TAG, "onResponse:1 " + response.body().getEmptype().trim().equals("1"));

                            avCl= Integer.parseInt(response.body().getAllocatedleaveModel().getCl());
                            avCompOff= Integer.parseInt(response.body().getAllocatedleaveModel().getCompoff());
                            avMl= Integer.parseInt(response.body().getAllocatedleaveModel().getMl());
                            avPL= Integer.parseInt(response.body().getAllocatedleaveModel().getPl());
                            avProbl= 0;



                            tvClallocatted.setText(response.body().getAllocatedleaveModel().getCl());
                            tvMlallocatted.setText(response.body().getAllocatedleaveModel().getMl());
                            tvPLallocatted.setText(response.body().getAllocatedleaveModel().getPl());
                            tvProlallocatted.setText(response.body().getAllocatedleaveModel().getProbl());
                            tvClavailable.setText(response.body().getAvailableLeaveModel().getCl());
                            tvMlavailable.setText(response.body().getAvailableLeaveModel().getMl());
                            tvPLavailable.setText(response.body().getAvailableLeaveModel().getPl());
                            tvProlavailable.setText(response.body().getAvailableLeaveModel().getProbl());
                            tvCompoffallocatted.setVisibility(View.GONE);
                            tvCompoffallocattedTag.setVisibility(View.GONE);
                            tvCompOffavailable1.setVisibility(View.GONE);

//                        } else if (response.body().getEmptype().trim().equals("2")) {
//
//                            Log.e(TAG, "onResponse:2 " + response.body().getEmptype().trim().equals("2"));
//                            tvClallocatted.setText(response.body().getAllocatedleaveModel().getCl());
//                            tvMlallocatted.setText(response.body().getAllocatedleaveModel().getMl());
//                            tvPLallocatted.setText(response.body().getAllocatedleaveModel().getPl());
//                            tvProlallocatted.setText(response.body().getAllocatedleaveModel().getProbl());
//                            tvClavailable.setText(response.body().getAvailableLeaveModel().getCl());
//                            tvMlavailable.setText(response.body().getAvailableLeaveModel().getMl());
//                            tvPLavailable.setText(response.body().getAvailableLeaveModel().getPl());
//                            tvProlavailable.setText(response.body().getAvailableLeaveModel().getProbl());
//                            tvCompoffallocatted.setVisibility(View.GONE);
//                            tvCompoffallocattedTag.setVisibility(View.GONE);
//                            tvCompOffavailable1.setVisibility(View.GONE);
                        } else if (response.body().getEmptype().trim().equals("3")) {

                            Log.e(TAG, "onResponse:3 " + response.body().getEmptype().trim().equals("3"));
                            tvClallocatted.setText(response.body().getAllocatedleaveModel().getCl());
                            tvClavailable.setText(response.body().getAvailableLeaveModel().getCl());
                            tvCompOffavailable1.setText(response.body().getAvailableLeaveModel().getCompoff());
                            tvCompoffallocatted.setText(response.body().getAllocatedleaveModel().getCompoff());


                            avCl= 0;
                            avCompOff= Integer.parseInt(response.body().getAllocatedleaveModel().getCompoff());
                            avMl= 0;
                            avPL= 0;
                            avProbl= Integer.parseInt(response.body().getAllocatedleaveModel().getProbl());

                            tvMlallocatted.setVisibility(View.GONE);
                            tvPLallocatted.setVisibility(View.GONE);
                            tvProlallocatted.setVisibility(View.GONE);
                            tvMlallocattedTag.setVisibility(View.GONE);
                            tvMlavailable.setVisibility(View.GONE);
                            tvPLavailable.setVisibility(View.GONE);
                            tvProlavailable.setVisibility(View.GONE);
                            tvProlallocattedTag.setVisibility(View.GONE);
                            tvPLallocattedTag.setVisibility(View.GONE);


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

    private void CallLeave() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
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

        Date d = new Date();
        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
        tvDate.setText(s);




        callRes();
        Calltype();


        arrayAdapter = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, leave);
        arrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spReson.setAdapter(arrayAdapter);

        typeAdapter = new ArrayAdapter(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item, LeaveType);
        typeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        tvLeaveType.setAdapter(typeAdapter);

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View view=rbGroup.findViewById(checkedId);
                int rb=rbGroup.indexOfChild(view);
               // Toast.makeText(LeaveActivity.this, , Toast.LENGTH_SHORT).show();
                switch (rb){
                    case 0:
                        Toast.makeText(LeaveActivity.this, "Cl", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(LeaveActivity.this, "ML", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(LeaveActivity.this, "PL", Toast.LENGTH_SHORT).show();
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
                callDate();
                //  date();


            }
        });

    }

    private void date() {



    }

    private void Calltype() {
        LeaveFormDetails formDetails = APIClient.getClient().create(LeaveFormDetails.class);
        formDetails.callleavedetails("e367", "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {
                try {
                    if (response.isSuccessful()) {
                        String type = response.body().getLeaveResons().getLeavetype();
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
        LeaveFormDetails leaveFormDetails = APIClient.getClient().create(LeaveFormDetails.class);
        leaveFormDetails.callleavedetails("e367", "available_leave").enqueue(new Callback<LeaveFormAllocatedleave>() {
            @Override
            public void onResponse(Call<LeaveFormAllocatedleave> call, Response<LeaveFormAllocatedleave> response) {


                try {
                    if (response.isSuccessful()) {


                        Log.e(TAG, "onResponse: " + response.body().getLeaveResons().getReasons());
                        String name = response.body().getLeaveResons().getReasons();

                        leave.add("----Select----");
                        String[] strings = name.split(";");
                        for (int i = 0; i < strings.length; i++) {
                            Log.e(TAG, "onResponse: " + strings[i]);
                            leave.add(strings[i]);
                        }

                        arrayAdapter.notifyDataSetChanged();



                        spReson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(LeaveActivity.this, leave.get(position), Toast.LENGTH_SHORT).show();


                                if (leave.get(position).trim().equals("Dependent Sick / Surgery/ Hospitalized")) {
                                    rlUpload.setVisibility(View.VISIBLE);
                                    rbPl.setClickable(false);
                                    rbLcl.setClickable(false);
                                    rbMl.setClickable(true);

                                    rbPl.setVisibility(View.GONE);
                                    rbLcl.setVisibility(View.GONE);
                                    rbMl.setVisibility(View.VISIBLE);

                                } else if (leave.get(position).trim().equals("Long travel or trip ( Visiting temple,Visiting relative house )")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);

                                } else if (leave.get(position).trim().equals("Not Feasible to travel")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("Shifting to new house")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("Sick and not Hospitalized")) {
                                    rlUpload.setVisibility(View.VISIBLE);
                                    rbPl.setClickable(false);
                                    rbLcl.setClickable(false);
                                    rbMl.setClickable(true);

                                    rbPl.setVisibility(View.GONE);
                                    rbLcl.setVisibility(View.GONE);
                                    rbMl.setVisibility(View.VISIBLE);

                                } else if (leave.get(position).trim().equals("Surgery /Hospitalization")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(false);
                                    rbLcl.setClickable(false);
                                    rbMl.setClickable(true);

                                    rbPl.setVisibility(View.GONE);
                                    rbLcl.setVisibility(View.GONE);
                                    rbMl.setVisibility(View.VISIBLE);

                                } else if (leave.get(position).trim().equals("To attend Self / Friends/ Relative marriage")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("To get essential needs")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("Personal Emergency")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);


                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("Sick and Hospitalized")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(false);
                                    rbLcl.setClickable(false);
                                    rbMl.setClickable(true);


                                    rbPl.setVisibility(View.GONE);
                                    rbLcl.setVisibility(View.GONE);
                                    rbMl.setVisibility(View.VISIBLE);

                                } else if (leave.get(position).trim().equals("Family Occasions")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);


                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


                                } else if (leave.get(position).trim().equals("Health Discomfort")) {
                                    rlUpload.setVisibility(View.GONE);
                                    rbPl.setClickable(true);
                                    rbLcl.setClickable(true);
                                    rbMl.setClickable(false);

                                    rbPl.setVisibility(View.VISIBLE);
                                    rbLcl.setVisibility(View.VISIBLE);
                                    rbMl.setVisibility(View.GONE);


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

            }
        });
    }


    private void callDate() {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH, 3);
        datePickerDialog = new DatePickerDialog(LeaveActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());


        datePickerDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
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
                        file = FileUtli.from(this, contentUri);
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