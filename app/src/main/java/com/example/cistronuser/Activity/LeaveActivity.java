package com.example.cistronuser.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.LeaveFormDetails;
import com.example.cistronuser.API.Interface.LeavePolicyInterface;
import com.example.cistronuser.API.Model.LeaveFormAllocatedleave;
import com.example.cistronuser.API.Response.LeavePolicyResponse;
import com.example.cistronuser.Adapter.LeaveDetailsAdapter;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveActivity extends Activity {


    //Internet
    BroadcastReceiver broadcastReceiver;

    //Bottom
    TextView tvDate;
    Spinner spReson, tvLeaveType, tvDayType;

    final static String TAG = "Name";
    ImageView ivBack, ivdetails, ivBackbottom;
    LottieAnimationView ivAdd;
    //Date
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    RecyclerView rcLeave;
    LeaveDetailsAdapter leaveDetailsAdapter;
    TextView tvmsg, tvHeader;


    //LeaveDetails
    TextView tvClallocatted, tvMlallocatted, tvPLallocatted, tvProlallocatted, tvClavailable,
            tvMlavailable, tvPLavailable, tvProlavailable,tvCompoffallocatted,tvCompOffavailable1;
    ImageView ivDownbottom;

    TextView tvClallocattedTag,tvMlallocattedTag,tvPLallocattedTag,tvProlallocattedTag,tvCompoffallocattedTag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);


        ivAdd = findViewById(R.id.ivMore);
        ivBack = findViewById(R.id.ivBack);
        rcLeave = findViewById(R.id.rcLeave);
        ivdetails = findViewById(R.id.ivdetails);


        //internet

        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        CalPolicy();


        //RecycleView

        leaveDetailsAdapter = new LeaveDetailsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rcLeave.setAdapter(leaveDetailsAdapter);
        rcLeave.setLayoutManager(linearLayoutManager);


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
                        if (response.body().getEmptype().trim().equals("1")) {

                            Log.e(TAG, "onResponse:1 " + response.body().getEmptype().trim().equals("1"));

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

                        } else if (response.body().getEmptype().trim().equals("2")) {

                            Log.e(TAG, "onResponse:2 " + response.body().getEmptype().trim().equals("2"));
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
                        } else if (response.body().getEmptype().trim().equals("3")) {

                            Log.e(TAG, "onResponse:3 " + response.body().getEmptype().trim().equals("3"));
                            tvClallocatted.setText(response.body().getAllocatedleaveModel().getCl());
                            tvClavailable.setText(response.body().getAvailableLeaveModel().getCl());


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

        tvDate = bottomSheetDialog.findViewById(R.id.tvDate);
        spReson = bottomSheetDialog.findViewById(R.id.spReson);
        tvLeaveType = bottomSheetDialog.findViewById(R.id.tvLeaveType);
        tvDayType = bottomSheetDialog.findViewById(R.id.tvDayType);
        ivBackbottom = bottomSheetDialog.findViewById(R.id.ivBackbottom);


        Date d = new Date();
        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
        tvDate.setText(s);


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
            }
        });

    }


    private void callDate() {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
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


}