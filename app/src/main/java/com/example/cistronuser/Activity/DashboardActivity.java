package com.example.cistronuser.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ChangePasswordInterface;
import com.example.cistronuser.API.Interface.CompOffApprovalCountInterface;
import com.example.cistronuser.API.Interface.ExpenseCountInterface;
import com.example.cistronuser.API.Interface.LeaveApprovelCountInterface;
import com.example.cistronuser.API.Interface.LogoutInterFace;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Response.ChangePasswordResponse;
import com.example.cistronuser.API.Response.CompOffCountResponse;
import com.example.cistronuser.API.Response.LeaveApprovelCountResponse;
import com.example.cistronuser.API.Response.LogoutResponse;
import com.example.cistronuser.API.Response.WaitingExpenseCountInterface;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.LoginActivity;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Activity.AttendanceReports;
import com.example.cistronuser.Report.Activity.ExpenseReportWM;
import com.example.cistronuser.Report.Activity.LeaveReport;
import com.example.cistronuser.WaitingforApprovel.Activity.CompOffRequest;
import com.example.cistronuser.WaitingforApprovel.Activity.ExpensesReport;
import com.example.cistronuser.WaitingforApprovel.Activity.LeaveRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity {


    private static final String TAG = "DashBoard";
    //Internet

    BroadcastReceiver broadcastReceiver;

    ImageView ivBack;

    CircleImageView ivProfilePhoto;
    RelativeLayout rlProfile;
    CardView cvAttendance, cvExpense, cvLeave;
    LoginuserModel loginuserModel;


    //Bottom
    TextView tvName, tvEmpId, tvDesignation, tvBranch, tvTeamLeader, tvMobile, tvEmail, tvDob, tvDoj, lWebview, tvProfilename, btnpasssubmit;
    EditText edNewPass, edRetypePass;


    LottieAnimationView lottieAnimationView, ivprofile;
    //Gps

    //photo
    String strPhoto;


    //Admin Dashboard

    RelativeLayout rlAdmin,rlWaitingApproval;

    RelativeLayout rlExpenseReport, rlrlAttendaceReport, rlrlLeaveReport, rlWaitingLeaveRequest, rlWaitingCompOFfRequest;
    TextView tvwaitingCountExpense, tvCountLeaveReq, tvCountCompOffReq;

    Context context;


    @Override
    protected void onRestart() {
        super.onRestart();

        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();

    }


    @Override
    protected void onResume() {
        super.onResume();

        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();


    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        PreferenceManager.setLoggedStatus(this, true);
        cvLeave = findViewById(R.id.cvLeave);
        cvExpense = findViewById(R.id.cvExpense);
        cvAttendance = findViewById(R.id.cvAttendance);
        rlProfile = findViewById(R.id.rlProfile);
        lottieAnimationView = findViewById(R.id.ivLogout);
        ivprofile = findViewById(R.id.ivprofile);
        lWebview = findViewById(R.id.lWebview);
        tvProfilename = findViewById(R.id.tvProfilename);
        rlExpenseReport = findViewById(R.id.rlExpenseReport);
        RelativeLayout rlWaitingExpense = findViewById(R.id.rlWaitingExpense);
        tvwaitingCountExpense = findViewById(R.id.tvwaitingCountExpense);
        rlrlAttendaceReport = findViewById(R.id.rlrlAttendaceReport);
        rlrlLeaveReport = findViewById(R.id.rlrlLeaveReport);
        rlWaitingLeaveRequest = findViewById(R.id.rlWaitingLeaveRequest);
        tvCountLeaveReq = findViewById(R.id.tvCountLeaveReq);
        rlWaitingCompOFfRequest = findViewById(R.id.rlWaitingCompOFfRequest);
        tvCountCompOffReq = findViewById(R.id.tvCountCompOffReq);
        rlAdmin = findViewById(R.id.rlAdmin);
        rlWaitingApproval=findViewById(R.id.rlWaitingApproval);


        tvProfilename.setText(PreferenceManager.getEmpName(this));

        lWebview.setMovementMethod(LinkMovementMethod.getInstance());
        lWebview.setLinkTextColor(getResources().getColor(R.color.white));

       // Log.e(TAG, "onCreate: user :" + PreferenceManager.getEmpuser(this));

        String user=PreferenceManager.getEmpuser(this);

        switch (user){
            case "user":
                rlWaitingApproval.setVisibility(View.GONE);
                break;
            case "admin":
                rlWaitingApproval.setVisibility(View.VISIBLE);
                break;

        }


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


       //IP Address
        context = getApplicationContext();
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ipAddress = wifiInf.getIpAddress();
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
        // Log.e(TAG, "onCreate: "+ip );

        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
                progressDialog.setMessage("Log out...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
                builder.setMessage("Do you want to LogOut");
                builder.setTitle("Log Out!");
                builder.setIcon(R.drawable.logout);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        LogoutInterFace logoutInterFace = APIClient.getClient().create(LogoutInterFace.class);
                        logoutInterFace.Calllogout("logout", PreferenceManager.getEmpID(DashboardActivity.this), ip).enqueue(new Callback<LogoutResponse>() {
                            @Override
                            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {

                                try {

                                    if (response.isSuccessful()) {


                                        PreferenceManager.setLoggedStatus(DashboardActivity.this, false);

                                        PreferenceManager.setUserModelData(DashboardActivity.this, loginuserModel);
                                        finish();
                                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<LogoutResponse> call, Throwable t) {

                            }
                        });


                    }
                }));
                builder.setNegativeButton("No", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        dialogInterface.cancel();
                    }
                }));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        cvAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent attend = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(attend);
            }
        });
        cvExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expense = new Intent(DashboardActivity.this, ExpensesActivity.class);
                startActivity(expense);
            }
        });

        cvLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LeaveActivity.class);
                startActivity(intent);
            }
        });


        ivprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileView();
            }
        });

        rlWaitingExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Waiting approval
                Intent intent = new Intent(DashboardActivity.this, ExpensesReport.class);
                startActivity(intent);

            }
        });
        rlWaitingLeaveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaveReq = new Intent(DashboardActivity.this, LeaveRequest.class);
                startActivity(leaveReq);
            }
        });

        rlExpenseReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent expenseReport = new Intent(DashboardActivity.this, ExpenseReportWM.class);
                startActivity(expenseReport);
            }
        });

        rlrlAttendaceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AttendReport = new Intent(DashboardActivity.this, AttendanceReports.class);
                startActivity(AttendReport);
            }
        });
        rlrlLeaveReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent leaveReport = new Intent(DashboardActivity.this, LeaveReport.class);
                startActivity(leaveReport);
            }
        });


        rlWaitingCompOFfRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, CompOffRequest.class);
                startActivity(intent);
            }
        });


        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();


    }

    private void CallLeaveApprovalCount() {

        LeaveApprovelCountInterface leaveApprovelCountInterface = APIClient.getClient().create(LeaveApprovelCountInterface.class);
        leaveApprovelCountInterface.Callcount("getLeaveForApprovalCount").enqueue(new Callback<LeaveApprovelCountResponse>() {
            @Override
            public void onResponse(Call<LeaveApprovelCountResponse> call, Response<LeaveApprovelCountResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvCountLeaveReq.setText(response.body().getCount());
                    }

                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<LeaveApprovelCountResponse> call, Throwable t) {

            }
        });
    }

    private void CallExpenseCount() {
        ExpenseCountInterface expenseCountInterface = APIClient.getClient().create(ExpenseCountInterface.class);
        expenseCountInterface.Callcount("expensesApprovalCount").enqueue(new Callback<WaitingExpenseCountInterface>() {
            @Override
            public void onResponse(Call<WaitingExpenseCountInterface> call, Response<WaitingExpenseCountInterface> response) {
                try {
                    if (response.isSuccessful()) {
                        tvwaitingCountExpense.setText(response.body().getCount());
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<WaitingExpenseCountInterface> call, Throwable t) {

            }
        });
    }

    private void CallCompoffCount() {

        CompOffApprovalCountInterface compOffApprovalCountInterface = APIClient.getClient().create(CompOffApprovalCountInterface.class);
        compOffApprovalCountInterface.CallCompoffcount("getCompoffForApprovalCount").enqueue(new Callback<CompOffCountResponse>() {
            @Override
            public void onResponse(Call<CompOffCountResponse> call, Response<CompOffCountResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvCountCompOffReq.setText(response.body().getCount());
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CompOffCountResponse> call, Throwable t) {

            }
        });
    }


    private void ProfileView() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.profileview);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();

        tvDoj = bottomSheetDialog.findViewById(R.id.tvDoj);
        tvName = bottomSheetDialog.findViewById(R.id.tvName);
        tvEmpId = bottomSheetDialog.findViewById(R.id.tvEmpId);
        tvDesignation = bottomSheetDialog.findViewById(R.id.tvDesignation);
        tvBranch = bottomSheetDialog.findViewById(R.id.tvBranch);
        tvTeamLeader = bottomSheetDialog.findViewById(R.id.tvTeamLeader);
        tvMobile = bottomSheetDialog.findViewById(R.id.tvMobile);
        tvEmail = bottomSheetDialog.findViewById(R.id.tvEmail);
        tvDob = bottomSheetDialog.findViewById(R.id.tvDob);
        ivBack = bottomSheetDialog.findViewById(R.id.ivBack);
        ivProfilePhoto = bottomSheetDialog.findViewById(R.id.ivProfilePhoto);
        TextView tvpassword = bottomSheetDialog.findViewById(R.id.tvpassword);
        edNewPass = bottomSheetDialog.findViewById(R.id.edNewPass);
        edRetypePass = bottomSheetDialog.findViewById(R.id.edRetypePass);
        btnpasssubmit = bottomSheetDialog.findViewById(R.id.btnpasssubmit);
        TextView tvClose = bottomSheetDialog.findViewById(R.id.tvClose);
        TextView tvpasswordnotmatch = bottomSheetDialog.findViewById(R.id.tvpasswordnotmatch);
        TextView tvpasswordlisten1 = bottomSheetDialog.findViewById(R.id.tvpasswordlisten1);
        TextView tvcheck = bottomSheetDialog.findViewById(R.id.tvcheck);


        edNewPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (TextUtils.isEmpty(edNewPass.getText().toString()) || edNewPass.getText().length() < 7) {
                    tvcheck.setVisibility(View.VISIBLE);
                } else {
                    edRetypePass.setVisibility(View.GONE);
                    tvcheck.setVisibility(View.GONE);
                    tvpasswordlisten1.setVisibility(View.GONE);
                    tvpasswordnotmatch.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edNewPass.getText().toString()) || edNewPass.getText().length() < 7) {
                    tvcheck.setVisibility(View.VISIBLE);
                } else {
                    edRetypePass.setVisibility(View.GONE);
                    tvcheck.setVisibility(View.GONE);
                    tvpasswordlisten1.setVisibility(View.GONE);
                    tvpasswordnotmatch.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(edNewPass.getText().toString()) || edNewPass.getText().length() < 7) {
                    tvcheck.setVisibility(View.VISIBLE);
                } else {
                    edRetypePass.setVisibility(View.VISIBLE);
                    tvcheck.setVisibility(View.GONE);
                }

            }
        });


        edRetypePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edNewPass.getText().toString().trim().equals(edRetypePass.getText().toString())) {
                    tvpasswordlisten1.setVisibility(View.VISIBLE);
                    tvpasswordnotmatch.setVisibility(View.GONE);
                    btnpasssubmit.setVisibility(View.VISIBLE);
                    tvcheck.setVisibility(View.GONE);

                } else {

                    tvpasswordlisten1.setVisibility(View.GONE);
                    tvpasswordnotmatch.setVisibility(View.VISIBLE);
                    btnpasssubmit.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edNewPass.getText().toString().trim().equals(edRetypePass.getText().toString())) {
                    tvpasswordlisten1.setVisibility(View.VISIBLE);
                    tvpasswordnotmatch.setVisibility(View.GONE);
                    btnpasssubmit.setVisibility(View.VISIBLE);
                    tvcheck.setVisibility(View.GONE);

                } else {

                    tvpasswordlisten1.setVisibility(View.GONE);
                    tvpasswordnotmatch.setVisibility(View.VISIBLE);
                    btnpasssubmit.setVisibility(View.GONE);

                }

            }
        });


//        Name=getIntent().getStringExtra("Name");
//        EmpiD=getIntent().getStringExtra("EmpID");
//        Des=getIntent().getStringExtra("Des");
//        DOB=getIntent().getStringExtra("DOB");
//        DOJ=getIntent().getStringExtra("DOJ");
//        Branch=getIntent().getStringExtra("Branch");
//        Email=getIntent().getStringExtra("Email");
//        Mob=getIntent().getStringExtra("Mob");
//        TL=getIntent().getStringExtra("TL");
//        strPhoto=getIntent().getStringExtra("Photo");

        strPhoto = PreferenceManager.getEmpphoto(this);

        tvName.setText(PreferenceManager.getEmpName(this));
        tvEmpId.setText(PreferenceManager.getEmpID(this));
        tvBranch.setText(PreferenceManager.getEmpbranch(this));
        tvDesignation.setText(PreferenceManager.getEmpdesignation(this));
        tvDob.setText(PreferenceManager.getEmpdob(this));
        tvDoj.setText(PreferenceManager.getEmpdoj(this));
        tvEmail.setText(PreferenceManager.getEmpemail(this));
        tvMobile.setText(PreferenceManager.getEmpMobile(this));
        tvTeamLeader.setText(PreferenceManager.getEmpteamleader(this));


        try {
            byte[] decodedString = Base64.decode(strPhoto, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivProfilePhoto.setImageBitmap(decodedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        tvpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvpassword.setVisibility(View.GONE);
                tvClose.setVisibility(View.VISIBLE);
                edNewPass.setVisibility(View.VISIBLE);
                tvpasswordlisten1.setVisibility(View.GONE);
                tvpasswordnotmatch.setVisibility(View.GONE);
                tvcheck.setVisibility(View.GONE);


            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tvpassword.setVisibility(View.VISIBLE);
                tvClose.setVisibility(View.GONE);
                edNewPass.setVisibility(View.GONE);
                edRetypePass.setVisibility(View.GONE);
                btnpasssubmit.setVisibility(View.GONE);
                tvpasswordlisten1.setVisibility(View.GONE);
                tvpasswordnotmatch.setVisibility(View.GONE);
                tvcheck.setVisibility(View.GONE);


            }
        });

        btnpasssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
                progressDialog.setMessage("Password changing...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                ChangePasswordInterface changePasswordInterface = APIClient.getClient().create(ChangePasswordInterface.class);
                changePasswordInterface.callchangepasswor("changePassword", PreferenceManager.getEmpID(DashboardActivity.this), edRetypePass.getText().toString()).enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {

                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(DashboardActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                bottomSheetDialog.dismiss();

                            }

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

                    }
                });
            }
        });
    }


    protected void unregBroadcast() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
        unregBroadcast();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==100 &&requestCode==RESULT_OK && data!=null){
//            Uri uri=data.getData();
//            try {
//                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//                byte[] bytes=byteArrayOutputStream.toByteArray();
//                strPhoto= Base64.encodeToString(bytes,Base64.DEFAULT);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setMessage("Are you sure want to Quit App?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();

    }

}