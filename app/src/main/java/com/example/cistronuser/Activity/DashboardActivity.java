package com.example.cistronuser.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ChangePasswordInterface;
import com.example.cistronuser.API.Interface.CompOffApprovalCountInterface;
import com.example.cistronuser.API.Interface.DashboardCallCountInterface;
import com.example.cistronuser.API.Interface.ExpenseCountInterface;
import com.example.cistronuser.API.Interface.LeaveApprovelCountInterface;
import com.example.cistronuser.API.Interface.LogoutInterFace;
import com.example.cistronuser.API.Interface.SalesQuoteApprovalCountInterFace;
import com.example.cistronuser.API.Interface.UpcomingCallListInterface;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.example.cistronuser.API.Response.ChangePasswordResponse;
import com.example.cistronuser.API.Response.CompOffCountResponse;
import com.example.cistronuser.API.Response.DashboardCallCountResponse;
import com.example.cistronuser.API.Response.LeaveApprovelCountResponse;
import com.example.cistronuser.API.Response.LogoutResponse;
import com.example.cistronuser.API.Response.SalesQuoteApprovalCountResponse;
import com.example.cistronuser.API.Response.UpcomingCallListResponse;
import com.example.cistronuser.API.Response.WaitingExpenseCountInterface;
import com.example.cistronuser.Common.BroadCastReceiver;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.LocationBackgroundService;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.LoginActivity;
import com.example.cistronuser.R;
import com.example.cistronuser.Report.Activity.AttendanceReports;
import com.example.cistronuser.Report.Activity.ExpenseReportWM;
import com.example.cistronuser.Report.Activity.LeaveReport;
import com.example.cistronuser.Report.Activity.SalesQuoteReport;
import com.example.cistronuser.Report.Activity.VisitEntryReport;
import com.example.cistronuser.SalesAndservice.Activity.SalesQuote;
import com.example.cistronuser.SalesAndservice.Activity.VisitEntry;
import com.example.cistronuser.ServiceEngineer.Activity.CurrentCallActivity;
import com.example.cistronuser.ServiceEngineer.Activity.PendicallActivity;
import com.example.cistronuser.ServiceEngineer.Activity.ReturnReqPendingCoActivity;
import com.example.cistronuser.ServiceEngineer.Activity.SpareInwardActivity;
import com.example.cistronuser.ServiceEngineer.Activity.SpareReqPendingCOActivity;
import com.example.cistronuser.ServiceEngineer.Activity.UpComingCallActivity;
import com.example.cistronuser.ServiceEngineer.Activity.UpcomingCallReport;
import com.example.cistronuser.WaitingforApprovel.Activity.CompOffRequest;
import com.example.cistronuser.WaitingforApprovel.Activity.ExpensesReport;
import com.example.cistronuser.WaitingforApprovel.Activity.LeaveRequest;
import com.example.cistronuser.WaitingforApprovel.Activity.SalesQuoteApproval;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity {

    public static final String NOTIFICATION_CHANNEL_ID = "1";
    private final static String default_notification_channel_id = "cistron";
    private static final String TAG = "DashBoard";
    //Internet

    BroadcastReceiver broadcastReceiver;

    ImageView ivBack;

    LinearLayout llview3;
    CircleImageView ivProfilePhoto;
    RelativeLayout rlProfile;
    CardView cvAttendance, cvExpense, cvLeave, cvVisitEntry, cvQuote;
    LoginuserModel loginuserModel;


    //Bottom
    TextView tvName, tvEmpId, tvDesignation, tvBranch, tvTeamLeader, tvMobile, tvEmail, tvDob, tvDoj, lWebview, tvProfilename, btnpasssubmit;
    EditText edNewPass, edRetypePass;


    LottieAnimationView lottieAnimationView, ivprofile;
    //Gps

    //photo
    String strPhoto;


    RelativeLayout rlWaitingExpense, rlSalesService;
    //Admin Dashboard
    RelativeLayout rlAdmin, rlWaitingApproval, rlVisitEntryReportLayout, rlWaitingSalesQuoteApprovalRequest;

    //Service
    RelativeLayout rlUpcomingCallLayout, rlPendingingCallLayout, rlCurrentCallLayout, rlSpareRequestPendingCoLayout, rlSpareInwardLayout, rlReturnReqPendingCoLayout;
    TextView tvSpareInwardCount, tvSpareRequestPendingCoCount, tvReturnReqPendingCoCount, tvCurrentCallCount;
    //Report
    RelativeLayout rlExpenseReport, rlrlAttendaceReport, rlrlLeaveReport, rlService, rlWaitingLeaveRequest, rlWaitingCompOFfRequest, rlQuoteReport;
    TextView tvwaitingCountExpense, tvCountLeaveReq, tvCountCompOffReq, tvWaitingCountSalesQuote, tvUpcomingCallCount, tvPendingCallCount;

    Context context;


    // Background Location

    LocationBroadcastReceiver receiver;
    ArrayList<UpcomingCallListModel> locationTrackerCallReportModels = new ArrayList<>();
    Double lat, longg, str;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //******************* get Location background ******************//
        receiver = new LocationBroadcastReceiver();
        startBackgroundService();
        //E378 CallCheckLocation();

        //******************* get Location background end ******************//


        cvLeave = findViewById(R.id.cvLeave);
        cvExpense = findViewById(R.id.cvExpense);
        cvAttendance = findViewById(R.id.cvAttendance);
        rlProfile = findViewById(R.id.rlProfile);
        lottieAnimationView = findViewById(R.id.ivLogout);
        ivprofile = findViewById(R.id.ivprofile);
        lWebview = findViewById(R.id.lWebview);
        tvProfilename = findViewById(R.id.tvProfilename);
        rlExpenseReport = findViewById(R.id.rlExpenseReport);
        rlWaitingExpense = findViewById(R.id.rlWaitingExpense);
        tvwaitingCountExpense = findViewById(R.id.tvwaitingCountExpense);
        rlrlAttendaceReport = findViewById(R.id.rlrlAttendaceReport);
        rlrlLeaveReport = findViewById(R.id.rlrlLeaveReport);
        rlWaitingLeaveRequest = findViewById(R.id.rlWaitingLeaveRequest);
        tvCountLeaveReq = findViewById(R.id.tvCountLeaveReq);
        rlWaitingCompOFfRequest = findViewById(R.id.rlWaitingCompOFfRequest);
        tvCountCompOffReq = findViewById(R.id.tvCountCompOffReq);
        rlAdmin = findViewById(R.id.rlAdmin);
        rlWaitingApproval = findViewById(R.id.rlWaitingApproval);
        rlWaitingSalesQuoteApprovalRequest = findViewById(R.id.rlWaitingSalesQuoteApprovalRequest);
        tvWaitingCountSalesQuote = findViewById(R.id.tvWaitingSalesQuote);
        rlQuoteReport = findViewById(R.id.rlQuoteReport);
        tvUpcomingCallCount = findViewById(R.id.tvUpcomingCallCount);
        tvPendingCallCount = findViewById(R.id.tvPendingCallCount);
        rlService = findViewById(R.id.rlService);


        rlUpcomingCallLayout = findViewById(R.id.rlUpcomingCallLayout);
        rlPendingingCallLayout = findViewById(R.id.rlPendingingCallLayout);
        rlSpareRequestPendingCoLayout = findViewById(R.id.rlSpareRequestPendingCoLayout);
        rlSpareInwardLayout = findViewById(R.id.rlSpareInwardLayout);
        tvSpareRequestPendingCoCount = findViewById(R.id.tvSpareRequestPendingCoCount);
        tvSpareInwardCount = findViewById(R.id.tvSpareInwardCount);
        tvReturnReqPendingCoCount = findViewById(R.id.tvReturnReqPendingCoCount);
        rlReturnReqPendingCoLayout = findViewById(R.id.rlReturnReqPendingCoLayout);
        rlCurrentCallLayout = findViewById(R.id.rlCurrentCallLayout);
        tvCurrentCallCount = findViewById(R.id.tvCurrentCallCount);


        rlVisitEntryReportLayout = findViewById(R.id.rlVisitEntryReportLayout);
        cvVisitEntry = findViewById(R.id.cvVisitEntry);
        cvQuote = findViewById(R.id.cvQuote);
        llview3 = findViewById(R.id.llview3);


        tvProfilename.setText(PreferenceManager.getEmpName(this));

        lWebview.setMovementMethod(LinkMovementMethod.getInstance());
        lWebview.setLinkTextColor(getResources().getColor(R.color.white));

        String user = PreferenceManager.getEmpuser(this);

        switch (user) {
            case "user":
                rlService.setVisibility(View.GONE);
                rlWaitingApproval.setVisibility(View.GONE);
                rlVisitEntryReportLayout.setVisibility(View.VISIBLE);
                break;
            case "admin":
                rlService.setVisibility(View.VISIBLE);
                rlWaitingApproval.setVisibility(View.VISIBLE);
                rlVisitEntryReportLayout.setVisibility(View.VISIBLE);
                break;
            case "service engineer":
                rlService.setVisibility(View.VISIBLE);
                rlWaitingApproval.setVisibility(View.GONE);
                rlVisitEntryReportLayout.setVisibility(View.VISIBLE);
                break;

        }


        String company = PreferenceManager.getEmpCompany(this).toLowerCase();
        switch (company) {
            case "sukimos":
                cvVisitEntry.setVisibility(View.GONE);
                rlExpenseReport.setVisibility(View.GONE);
                rlVisitEntryReportLayout.setVisibility(View.GONE);
                rlQuoteReport.setVisibility(View.GONE);
                llview3.setVisibility(View.GONE);
                rlService.setVisibility(View.GONE);
                break;
        }

//        CallReportIngCheckInterface callReportIngCheckInterface = APIClient.getClient().create(CallReportIngCheckInterface.class);
//        callReportIngCheckInterface.CalLCheck("checkEmployee", PreferenceManager.getEmpID(this)).enqueue(new Callback<CallReportIngCheckResponse>() {
//            @Override
//            public void onResponse(Call<CallReportIngCheckResponse> call, Response<CallReportIngCheckResponse> response) {
//                try {
//                    if (response.body().getResponse().trim().equals("0")) {
//                        rlService.setVisibility(View.GONE);
//                    } else {
//                        rlService.setVisibility(View.VISIBLE);
//                    }
//
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CallReportIngCheckResponse> call, Throwable t) {
//
//            }
//        });


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
                final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this, R.style.ProgressBarDialog);
                progressDialog.setMessage("Log out...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this, R.style.AlertDialogCustom);
                builder.setMessage("Do you want to LogOut ?");
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


                                        // PreferenceManager.setLoggedStatus(DashboardActivity.this, false);

                                        PreferenceManager.setUserModelData(DashboardActivity.this, loginuserModel);
                                        finish();
                                        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Intent intent1 = new Intent(DashboardActivity.this, LocationBackgroundService.class);
                                        stopService(intent1);
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

        cvVisitEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, VisitEntry.class);
                startActivity(intent);
            }
        });

        rlVisitEntryReportLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, VisitEntryReport.class);
                startActivity(intent);
            }
        });

        cvQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SalesQuote.class);
                startActivity(intent);
            }
        });

        rlWaitingSalesQuoteApprovalRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardActivity.this, SalesQuoteApproval.class);
                startActivity(intent);

            }
        });

        rlQuoteReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SalesQuoteReport.class);
                startActivity(intent);
            }
        });


        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();

        //SalesQuoteCount
        CallSalesQuoteCount();

        //UpcomingCall and Pendingcall Count
        CallUpComPendingCount();


    }


    private void CallUpComPendingCount() {
        DashboardCallCountInterface dashboardCallCountInterface = APIClient.getClient().create(DashboardCallCountInterface.class);
        dashboardCallCountInterface.CallCount("getDashboardCounts", PreferenceManager.getEmpID(this)).enqueue(new Callback<DashboardCallCountResponse>() {
            @Override
            public void onResponse(Call<DashboardCallCountResponse> call, Response<DashboardCallCountResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvUpcomingCallCount.setText(response.body().getUpcomingCalls());
                        tvPendingCallCount.setText(response.body().getPendingCalls());
                        tvSpareInwardCount.setText(response.body().getSparesInward());
                        tvSpareRequestPendingCoCount.setText(response.body().getSpareReqPending());
                        tvReturnReqPendingCoCount.setText(response.body().getReturnReqPending());
                        tvCurrentCallCount.setText(response.body().getTodayCalls());

                        rlCurrentCallLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (response.body().getTodayCalls().trim().equals("0")) {
                                    Toast.makeText(context, "No Today Calls ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, CurrentCallActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });


                        rlUpcomingCallLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (response.body().getUpcomingCalls().trim().equals("0")) {
                                    Toast.makeText(context, "No  Upcoming Calls ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, UpComingCallActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

                        rlPendingingCallLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (response.body().getPendingCalls().trim().equals("0")) {
                                    Toast.makeText(context, "No Pending Calls ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, PendicallActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });


                        rlSpareRequestPendingCoLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (response.body().getSpareReqPending().trim().equals("0")) {
                                    Toast.makeText(context, "No Spare Request ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, SpareReqPendingCOActivity.class);
                                    startActivity(intent);
                                }

                            }
                        });

                        rlSpareInwardLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (response.body().getSparesInward().trim().equals("0")) {
                                    Toast.makeText(context, "No Spare Inward ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, SpareInwardActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

                        rlReturnReqPendingCoLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (response.body().getReturnReqPending().trim().equals("0")) {
                                    Toast.makeText(context, "No Return Request ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(DashboardActivity.this, ReturnReqPendingCoActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<DashboardCallCountResponse> call, Throwable t) {

            }
        });
    }

    private void CallSalesQuoteCount() {
        SalesQuoteApprovalCountInterFace salesQuoteApprovalCountInterFace = APIClient.getClient().create(SalesQuoteApprovalCountInterFace.class);
        salesQuoteApprovalCountInterFace.callCount("getApprovalCounts").enqueue(new Callback<SalesQuoteApprovalCountResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteApprovalCountResponse> call, Response<SalesQuoteApprovalCountResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvWaitingCountSalesQuote.setText(response.body().getSalesQuoteApprovalCountModel().getOaApproval());

                        if (response.body().getSalesQuoteApprovalCountModel().getOaApproval().trim().equals("0")) {
                            rlWaitingSalesQuoteApprovalRequest.setVisibility(View.GONE);

                        } else {
                            rlWaitingSalesQuoteApprovalRequest.setVisibility(View.VISIBLE);
                        }


                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteApprovalCountResponse> call, Throwable t) {

            }
        });
    }

    private void CallLeaveApprovalCount() {

        LeaveApprovelCountInterface leaveApprovelCountInterface = APIClient.getClient().create(LeaveApprovelCountInterface.class);
        leaveApprovelCountInterface.Callcount("getLeaveForApprovalCount").enqueue(new Callback<LeaveApprovelCountResponse>() {
            @Override
            public void onResponse(Call<LeaveApprovelCountResponse> call, Response<LeaveApprovelCountResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        tvCountLeaveReq.setText(response.body().getCount());

                        if (response.body().getCount().trim().equals("0")) {
                            rlWaitingLeaveRequest.setVisibility(View.GONE);

                        } else {
                            rlWaitingLeaveRequest.setVisibility(View.VISIBLE);
                        }

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
                        if (response.body().getCount().trim().equals("0")) {

                            rlWaitingExpense.setVisibility(View.GONE);

                        } else {
                            rlWaitingExpense.setVisibility(View.VISIBLE);
                        }
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

                        if (response.body().getCount().trim().equals("0")) {
                            rlWaitingCompOFfRequest.setVisibility(View.GONE);

                        } else {
                            rlWaitingCompOFfRequest.setVisibility(View.VISIBLE);
                        }
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

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme);
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
                final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this, R.style.ProgressBarDialog);
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

    private void CallCheckLocation() {
        UpcomingCallListInterface upcomingCallListInterface = APIClient.getClient().create(UpcomingCallListInterface.class);
        upcomingCallListInterface.CallUpcomingCallReport("getCallsRecords", PreferenceManager.getEmpID(this), "today").enqueue(new Callback<UpcomingCallListResponse>() {
            @Override
            public void onResponse(Call<UpcomingCallListResponse> call, Response<UpcomingCallListResponse> response) {
                try {
                    locationTrackerCallReportModels = response.body().getUpcomingCallListModels();


                    for (int i = 0; i < locationTrackerCallReportModels.size(); i++) {


                        String[] Hosp = locationTrackerCallReportModels.get(i).getHospital().split("\\\\n");


                        Location locationA = new Location("Location A");
                        Location locationB = new Location("Location B");
                        locationA.setLatitude(Double.parseDouble(String.valueOf(lat)));
                        locationA.setLongitude(Double.parseDouble(String.valueOf(longg)));
                        locationB.setLatitude(Double.parseDouble(String.valueOf(locationTrackerCallReportModels.get(i).getLat())));
                        locationB.setLongitude(Double.parseDouble(String.valueOf(locationTrackerCallReportModels.get(i).getLng())));

                        str = Double.valueOf(locationA.distanceTo(locationB) / 1000);
                        // Log.e(TAG, "onResponse: " + str);
                        if (str <= 1.0) {
                            // ************** Location check  ************** //
                            String id = locationTrackerCallReportModels.get(i).getButton();
                            String PM = locationTrackerCallReportModels.get(i).getIssue();
                            //Log.e(TAG, "onResponse: " + Hosp[0]);
                            //Toast.makeText(DashboardActivity.this, "Location Reached", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this, R.style.AlertDialogCustom);
                            builder.setCancelable(false);
                            builder.setMessage(Hosp[0] + "\n" + locationTrackerCallReportModels.get(i).getAddress());
                            builder.setTitle("Your location has been reached.");
                            builder.setPositiveButton("Call Report", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Intent intent = new Intent(DashboardActivity.this, UpcomingCallReport.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("PM", PM);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onClick: " + e.getMessage());
                                    }


                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            // ************** Time Check ************** //

                             String TimeCheck = response.body().getUpcomingCallListModels().get(i).getDate();
                            // TimeCheck = TimeCheck.substring(11); 28-02-2023 10:00:00
                            // Log.e(TAG, "onResponse: " + TimeCheck);
                           // String TimeCheck = "03-03-2023 11:00:00";

                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            //Log.e(TAG, "onResponse: "+currentDateandTime );


                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

                            Date TimeDate = null;
                            Date CurrentTimeDate = null;
                            try {
                                TimeDate = format.parse(TimeCheck);
                                CurrentTimeDate = format.parse(currentDateandTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }



                            long diff = TimeDate.getTime() - CurrentTimeDate.getTime();
                            long diffSeconds = diff / 1000;
                            long diffMinutes = diff / (60 * 1000);
                            long diffHours = diff / (60 * 60 * 1000);
                            Log.e(TAG, "onResponse: check " + diffHours + "---" + diffMinutes);

                            if (diffHours <= 1) {
                                Log.e(TAG, "TimeCheck: in " + diffMinutes);

                                scheduleNotification(getNotification("You have a scheduled appointment at " + Hosp[0] + "\n" + locationTrackerCallReportModels.get(i).getAddress() + "\n"
                                        + "for " + locationTrackerCallReportModels.get(i).getDate(), "Call Close Notification !"));

                                break;
                            } else {
                                Log.e(TAG, "TimeCheck: out " + diffMinutes);
                            }
                            // ************** Time Check End ************** //



                            break;
                        } else {

                            Log.e(TAG, "onResponse: not reached");


                            // ************** Time Check ************** //

                             String TimeCheck = response.body().getUpcomingCallListModels().get(i).getDate();
                            // TimeCheck = TimeCheck.substring(11); 28-02-2023 10:00:00
                            // Log.e(TAG, "onResponse: " + TimeCheck);
                            //String TimeCheck = "03-03-2023 11:00:00";

                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                            String currentDateandTime = sdf.format(new Date());
                            //Log.e(TAG, "onResponse: "+currentDateandTime );


                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

                            Date TimeDate = null;
                            Date CurrentTimeDate = null;
                            try {
                                TimeDate = format.parse(TimeCheck);
                                CurrentTimeDate = format.parse(currentDateandTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }



                            long diff = TimeDate.getTime() - CurrentTimeDate.getTime();
                            long diffSeconds = diff / 1000;
                            long diffMinutes = diff / (60 * 1000);
                            long diffHours = diff / (60 * 60 * 1000);
                            Log.e(TAG, "onResponse: check " + diffHours + "---" + diffMinutes);

                            if (diffHours <= 1) {
                                Log.e(TAG, "TimeCheck: in " + diffMinutes);

                                scheduleNotification(getNotification("You have a scheduled appointment at " + Hosp[0] + "\n" + locationTrackerCallReportModels.get(i).getAddress() + "\n"
                                        + "for " + locationTrackerCallReportModels.get(i).getDate(), "Call Close Notification !"));

                                break;
                            } else {
                                Log.e(TAG, "TimeCheck: out " + diffMinutes);
                            }
                            // ************** Time Check End ************** //
                        }


                        // ************** Location check End ************** //










                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<UpcomingCallListResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        alertDialog.setMessage("Are you sure want to Quit App?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(DashboardActivity.this, LocationBackgroundService.class);
                stopService(intent);
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


    private void startBackgroundService() {

        IntentFilter filter = new IntentFilter("Location");
        registerReceiver(receiver, filter);
        Intent intent = new Intent(DashboardActivity.this, LocationBackgroundService.class);
        startService(intent);
        //E378   CallCheckLocation();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        CallCheckLocation();
//
//    }

    @Override
    protected void onResume() {
        super.onResume();


        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();

        //SalesQuoteCount
        CallSalesQuoteCount();

        //UpcomingCall and Pendingcall Count
        CallUpComPendingCount();
    }

    @Override
    protected void onRestart() {
        super.onRestart();


        //LeaveApprovalCount
        CallLeaveApprovalCount();

        //ExpenseCount
        CallExpenseCount();

        //CompoffCount
        CallCompoffCount();

        //SalesQuoteCount
        CallSalesQuoteCount();

        //UpcomingCall and Pendingcall Count
        CallUpComPendingCount();

    }

    private void scheduleNotification(Notification notification) {

        Intent notificationIntent = new Intent(this, BroadCastReceiver.class);
        notificationIntent.putExtra(BroadCastReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(BroadCastReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
    }

    private Notification getNotification(String content, String Title) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        Intent resultIntent = new Intent(DashboardActivity.this, CurrentCallActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(DashboardActivity.this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentTitle(Title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.cis_icon);
        builder.setAutoCancel(true);
        builder.setContentIntent(resultPendingIntent);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    public class LocationBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Location")) {

                lat = intent.getDoubleExtra("latitude", 0f);
                longg = intent.getDoubleExtra("longitude", 0f);
                // String Address = intent.getStringExtra("Address");


            }
        }
    }

}