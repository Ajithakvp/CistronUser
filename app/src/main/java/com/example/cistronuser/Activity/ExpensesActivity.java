package com.example.cistronuser.Activity;

import static com.example.cistronuser.Activity.LeaveActivity.TAG;

import static okhttp3.RequestBody.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.AttachRemoveInterface;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.ExpenseInterface;
import com.example.cistronuser.API.Interface.ExpensePolicyInterface;
import com.example.cistronuser.API.Interface.LeavePolicyInterface;
import com.example.cistronuser.API.Interface.SelectWeekDateInterface;
import com.example.cistronuser.API.Interface.ViewExpenseListInterface;
import com.example.cistronuser.API.Interface.WeeklySubmitExpensesInterface;
import com.example.cistronuser.API.Model.SelecteddtExpenses;
import com.example.cistronuser.API.Model.WeeklyExpensesModel;
import com.example.cistronuser.API.Response.AttachRemoveResponse;
import com.example.cistronuser.API.Response.ExpensePolicyResponse;
import com.example.cistronuser.API.Response.ExpenseResponse;
import com.example.cistronuser.API.Response.LeaveDetailsResponse;
import com.example.cistronuser.API.Response.LeavePolicyResponse;
import com.example.cistronuser.API.Response.SelectWeekResponse;
import com.example.cistronuser.API.Response.ViewExpenseResponse;
import com.example.cistronuser.API.Response.WeeklySubmitExpensesResponse;
import com.example.cistronuser.API.Response.response;
import com.example.cistronuser.Adapter.ExpensesViewWeeklyAdapter;
import com.example.cistronuser.Common.ConnectionRecevier;
import com.example.cistronuser.Common.FileUtli;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.Common.WebviewPage;
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

public class ExpensesActivity extends Activity {


    final static String TAG = "Expense";
    private static final int PERMISSION_REQUEST_CODE = 1;
    //Internet
    BroadcastReceiver broadcastReceiver;
    File fileconvency = null;
    File fileticket = null;
    File filelodging = null;
    File fileOther = null;

    ImageView ivBack, ivMore;

    RelativeLayout rlUpload, rlUploadTicket, rlUploadother, rlUploadLodging;
    TextView tvDate, tvConveyanceDoc, tvTicketDoc, tvLodgingDoc, tvOtherDoc, tvStartdate, tvto, tvEnddate, tvSubmit;
    EditText edWorkReport, edConveyance, tvTicket, tvLodging, edOther;


    String strConvenyance, strtickDoc, strLodgingDoc, strotherDoc;


    //AddExpenses
    ImageView ivConvencypreview, ivConvencyDelete, ivpreviewtTicket, ivDeleteTicket, ivpreviewtlodging, ivDeletelodging, ivpreviewOther, ivDeleteOther;
    ImageView ivConvencydoc, ivTicketDoc, ivLodgingDoc, ivotherDoc;
    TextView tvConveyanceEmptyDoc, tvTicketEmptyDoc, tvLodgingEmptyDoc, tvOtherEmptyDoc;


    //ExpenseList
    RecyclerView rvExpense;
    ImageView ivBottomBack;

    //ViewExpenses
    String BaseExpenseUrl, WebviewConvency, WebViewOther, WebViewLodging, WebViewTicket;
    String fillconv, fileot, filelod, filetic;
    TextView tvViewWeeklyreport, tvViewWeeklyreportSubmit;

    //Policy
    RelativeLayout rlmsg, rlExpenseLayout;
    TextView tvMsg, checkboxtextview, tvHeader;

    //WeeklyExpense
    ExpensesViewWeeklyAdapter weeklyAdapter;
    RelativeLayout rlUploadWeekAll;
    TextView tvWeekDoc, tvGrandsumDoc;
    File fileWeek = null;
    String strWeekall;
    ArrayList<WeeklyExpensesModel> weeklyExpensesModels = new ArrayList<>();

    //CheckWeekExpensesmsg
    RelativeLayout rlErrormsg;
    TextView tvExtraMsg, tvCheckWeekReportMsg;
    ImageView ivWeekPreview;
    TextView tvGrandsumDocTag;
    String WeekBaseurl, Filename_r;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        ivBack = findViewById(R.id.ivBack);
        rlUploadother = findViewById(R.id.rlUploadother);
        rlUpload = findViewById(R.id.rlUpload);
        rlUploadTicket = findViewById(R.id.rlUploadTicket);
        rlUploadLodging = findViewById(R.id.rlUploadLodging);
        tvDate = findViewById(R.id.tvDate);
        edWorkReport = findViewById(R.id.edWorkReport);
        edConveyance = findViewById(R.id.edConveyance);
        tvTicket = findViewById(R.id.tvTicket);
        tvLodging = findViewById(R.id.tvLodging);
        edOther = findViewById(R.id.edOther);
        tvConveyanceDoc = findViewById(R.id.tvConveyanceDoc);
        tvTicketDoc = findViewById(R.id.tvTicketDoc);
        tvLodgingDoc = findViewById(R.id.tvLodgingDoc);
        tvOtherDoc = findViewById(R.id.tvOtherDoc);
        tvStartdate = findViewById(R.id.tvselectDate);
        tvto = findViewById(R.id.tvto);
        tvEnddate = findViewById(R.id.tvEnddate);
        tvSubmit = findViewById(R.id.tvSubmit);
        ivMore = findViewById(R.id.ivMore);
        tvViewWeeklyreport = findViewById(R.id.tvViewWeeklyreport);


        rlErrormsg = findViewById(R.id.rlErrormsg);
        tvExtraMsg = findViewById(R.id.tvExtraMsg);
        tvCheckWeekReportMsg = findViewById(R.id.tvCheckWeekReportMsg);

        rlExpenseLayout = findViewById(R.id.rlExpenseLayout);
        rlmsg = findViewById(R.id.rlmsg);
        tvMsg = findViewById(R.id.tvMsg);


        ivConvencypreview = findViewById(R.id.ivConvencypreview);
        ivConvencyDelete = findViewById(R.id.ivConvencyDelete);
        ivpreviewtTicket = findViewById(R.id.ivpreviewtTicket);
        ivDeleteTicket = findViewById(R.id.ivDeleteTicket);
        ivpreviewtlodging = findViewById(R.id.ivpreviewtlodging);
        ivDeletelodging = findViewById(R.id.ivDeletelodging);
        ivpreviewOther = findViewById(R.id.ivpreviewOther);
        ivDeleteOther = findViewById(R.id.ivDeleteOther);


        ivConvencydoc = findViewById(R.id.ivConvencydoc);
        ivTicketDoc = findViewById(R.id.ivTicketDoc);
        ivLodgingDoc = findViewById(R.id.ivLodgingDoc);
        ivotherDoc = findViewById(R.id.ivotherDoc);

        tvConveyanceEmptyDoc = findViewById(R.id.tvConveyanceEmptyDoc);
        tvTicketEmptyDoc = findViewById(R.id.tvTicketEmptyDoc);
        tvLodgingEmptyDoc = findViewById(R.id.tvLodgingEmptyDoc);
        tvOtherEmptyDoc = findViewById(R.id.tvOtherEmptyDoc);


        edConveyance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edConveyance.getText().toString().trim().length() == 0 || Integer.parseInt(edConveyance.getText().toString().trim()) == 0) {
                    rlUpload.setVisibility(View.GONE);

                } else {
                    //rlUpload.setVisibility(View.VISIBLE);
                }

            }
        });


        edOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edOther.getText().toString().trim().length() == 0 || Integer.parseInt(edOther.getText().toString().trim()) == 0) {
                    rlUploadother.setVisibility(View.GONE);

                } else {
                    rlUploadother.setVisibility(View.VISIBLE);
                }

            }
        });


        tvTicket.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvTicket.getText().toString().trim().length() == 0 || Integer.parseInt(tvTicket.getText().toString().trim()) == 0) {
                    rlUploadTicket.setVisibility(View.GONE);

                } else {
                    rlUploadTicket.setVisibility(View.VISIBLE);
                }

            }
        });


        tvLodging.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvLodging.getText().toString().trim().length() == 0 || Integer.parseInt(tvLodging.getText().toString().trim()) == 0) {
                    rlUploadLodging.setVisibility(View.GONE);

                } else {
                    rlUploadLodging.setVisibility(View.VISIBLE);
                }

            }
        });


        //internet
        broadcastReceiver = new ConnectionRecevier();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

//
//        Date d = new Date();
//        CharSequence s = DateFormat.format("d /MM/yyyy ", d.getTime());
//        tvDate.setText(s);


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallDate();
            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rlUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 1);
                    tvConveyanceEmptyDoc.setVisibility(View.GONE);
                    tvConveyanceDoc.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }
        });


        rlUploadTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 2);
                    tvTicketEmptyDoc.setVisibility(View.GONE);
                    tvTicketDoc.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }
        });


        rlUploadother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 3);
                    tvOtherEmptyDoc.setVisibility(View.GONE);
                    tvOtherDoc.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
            }
        });


        rlUploadLodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 4);
                    tvLodgingEmptyDoc.setVisibility(View.GONE);
                    ivLodgingDoc.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }


            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isfilled = true, focus = false;

                if (tvDate.getText().toString().isEmpty()) {
                    tvDate.setError("Please Select Date");
                    tvDate.requestFocus();
                    focus = true;
                    isfilled = false;
                }
                if (edWorkReport.getText().toString().trim().length() == 0) {

                    if (edConveyance.getText().toString().trim().length() > 0) {
                        edWorkReport.setError(" WorkReport for Conveyance is Required");
                        if (!focus)
                            edWorkReport.requestFocus();
                        isfilled = false;
                    }


                }
                if (edConveyance.getText().toString().isEmpty() && edOther.getText().toString().isEmpty() && tvTicket.getText().toString().isEmpty() && tvLodging.getText().toString().isEmpty()) {
                    Toast.makeText(ExpensesActivity.this, "Please enter any one of the expenses", Toast.LENGTH_SHORT).show();
                    isfilled = false;

                }
                if (isfilled) {

                    // Log.e(TAG, "onClick: 5");
                    Callsubmitexpense();
                    if (fileconvency != null) {
                        Callsaveconvency();
                        // Log.e(TAG, "onClick: 1");
                    }
                    if (filelodging != null) {
                        // Log.e(TAG, "onClick: 2");

                        Callsavelodging();
                    }
                    if (fileticket != null) {
                        // Log.e(TAG, "onClick: 3");


                        Callsaveticket();
                    }
                    if (fileOther != null) {
                        // Log.e(TAG, "onClick: 4");

                        Callsaveother();
                    }


                }


            }
        });


        ivConvencyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ExpensesActivity.this, R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Delete ?");
                builder.setTitle("Delete!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        callconvencyDelete();
                        dialogInterface.dismiss();
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


        ivDeleteOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ExpensesActivity.this, R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Delete ?");
                builder.setTitle("Delete!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        calldeleteOther();
                        dialogInterface.dismiss();
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

        ivDeleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ExpensesActivity.this, R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Delete ?");
                builder.setTitle("Delete!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        callTicketDelete();
                        dialogInterface.dismiss();
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


        ivDeletelodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ExpensesActivity.this, R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Delete ?");
                builder.setTitle("Delete!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        calldeleteLodging();
                        dialogInterface.dismiss();
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


        ivConvencypreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e(TAG, "onClick:1 " + BaseExpenseUrl);
                //Log.e(TAG, "onClick:2 " + WebviewConvency);
                previewattache(BaseExpenseUrl + WebviewConvency);
            }
        });

        ivpreviewOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e(TAG, "onClick:1 " + BaseExpenseUrl);
               // Log.e(TAG, "onClick:2 " + WebViewOther);
                previewattache(BaseExpenseUrl + WebViewOther);

            }
        });

        ivpreviewtTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e(TAG, "onClick:1 " + BaseExpenseUrl);
               // Log.e(TAG, "onClick:2 " + WebViewTicket);
                previewattache(BaseExpenseUrl + WebViewTicket);

            }
        });

        ivpreviewtlodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e(TAG, "onClick:1 " + BaseExpenseUrl);
              //  Log.e(TAG, "onClick:2 " + WebViewLodging);
                previewattache(BaseExpenseUrl + WebViewLodging);

            }
        });


        tvViewWeeklyreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvDate.getText().toString().isEmpty()) {
                    Toast.makeText(ExpensesActivity.this, "Please select Date ", Toast.LENGTH_SHORT).show();
                } else {

                    callExpenseList();
                }
            }
        });

        tvCheckWeekReportMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // callExpenseList();

                callcheckWeekList();

            }
        });


        //Policy check
        ExpensePolicyInterface expensePolicyInterface = APIClient.getClient().create(ExpensePolicyInterface.class);
        expensePolicyInterface.CallPolicyExpenses("checkExpPolicy", PreferenceManager.getEmpID(this)).enqueue(new Callback<ExpensePolicyResponse>() {
            @Override
            public void onResponse(Call<ExpensePolicyResponse> call, Response<ExpensePolicyResponse> response) {

               //Log.e(TAG, "onResponse: " + response.body().getMessage());
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getMessage().trim().equals("error")) {
                            rlmsg.setVisibility(View.VISIBLE);
                            tvMsg.setText(response.body().getPolicy());
                            rlExpenseLayout.setVisibility(View.GONE);
                        } else if (response.body().getMessage().trim().equals("not accepted")) {


                            final Dialog dialog = new Dialog(ExpensesActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.profile);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            checkboxtextview = dialog.findViewById(R.id.tvmsg);
                            tvHeader = dialog.findViewById(R.id.tvHeader);
                            CheckBox checkBox = dialog.findViewById(R.id.cbname);
                            Button button = dialog.findViewById(R.id.btSubmit);
                            Button btClose = dialog.findViewById(R.id.btClose);


                            rlmsg.setVisibility(View.GONE);


                            checkboxtextview.setText(response.body().getPolicy());
                            //tvHeader.setText(response.body().getCategory());


                            checkBox.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    boolean checked = ((CheckBox) v).isChecked();

                                    if (checked) {

                                        button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ExpensePolicyInterface expensePolicyaccpted = APIClient.getClient().create(ExpensePolicyInterface.class);
                                                expensePolicyaccpted.CallPolicyExpenses("acceptExpPolicy", PreferenceManager.getEmpID(ExpensesActivity.this)).enqueue(new Callback<ExpensePolicyResponse>() {
                                                    @Override
                                                    public void onResponse(Call<ExpensePolicyResponse> call, Response<ExpensePolicyResponse> response) {
                                                        if (response.isSuccessful()) {
                                                            dialog.dismiss();
                                                            rlExpenseLayout.setVisibility(View.VISIBLE);
                                                            Toast.makeText(ExpensesActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ExpensePolicyResponse> call, Throwable t) {

                                                    }
                                                });

                                            }
                                        });

                                    } else {
                                        Toast.makeText(ExpensesActivity.this, "Policy Check", Toast.LENGTH_SHORT).show();

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


                        } else if (response.body().getMessage().trim().equals("accepted")) {
                            rlExpenseLayout.setVisibility(View.VISIBLE);
                            rlmsg.setVisibility(View.GONE);

                        }


                    }
                } catch (Exception e) {

                }
            }


            @Override
            public void onFailure(Call<ExpensePolicyResponse> call, Throwable t) {

            }
        });

    }

    private void callcheckWeekList() {

        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.checkweeklyexpense);
        bottomSheetDialog.show();
        ivBottomBack = bottomSheetDialog.findViewById(R.id.ivBottomBack);
        rvExpense = bottomSheetDialog.findViewById(R.id.rvExpense);
        tvViewWeeklyreportSubmit = bottomSheetDialog.findViewById(R.id.tvViewWeeklyreportSubmit);
        rlUploadWeekAll = bottomSheetDialog.findViewById(R.id.rlUploadWeekAll);
        tvWeekDoc = bottomSheetDialog.findViewById(R.id.tvWeekDoc);
        tvGrandsumDoc = bottomSheetDialog.findViewById(R.id.tvGrandsumDoc);
        ivWeekPreview = bottomSheetDialog.findViewById(R.id.ivWeekPreview);
        TextView tvGrandsumDocTag = bottomSheetDialog.findViewById(R.id.tvGrandsumDocTag);
        TextView tvWeeklyPreviewTag = bottomSheetDialog.findViewById(R.id.tvWeeklyPreviewTag);


        weeklyAdapter = new ExpensesViewWeeklyAdapter(ExpensesActivity.this, weeklyExpensesModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExpensesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvExpense.setLayoutManager(linearLayoutManager);
        rvExpense.setAdapter(weeklyAdapter);


        ViewExpenseListInterface viewExpenseListInterface = APIClient.getClient().create(ViewExpenseListInterface.class);
        viewExpenseListInterface.Callweek("viewWeeklyExpenses", tvDate.getText().toString(), PreferenceManager.getEmpID(this)).enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                Filename_r = response.body().getFilename_r();
                WeekBaseurl = response.body().getAttachBaseUrl();

                weeklyAdapter.weeklyExpensesModels = response.body().getWeeklyExpensesModels();
                weeklyAdapter.notifyDataSetChanged();

                tvGrandsumDocTag.setVisibility(View.VISIBLE);
                tvGrandsumDoc.setText(response.body().getGrandSum());

                if (response.body().getFilename_r().trim().equals("")) {
                    ivWeekPreview.setVisibility(View.GONE);
                    tvWeeklyPreviewTag.setVisibility(View.GONE);
                } else {
                    ivWeekPreview.setVisibility(View.VISIBLE);
                    tvWeeklyPreviewTag.setVisibility(View.VISIBLE);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

            }
        });

        ivWeekPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewattache(WeekBaseurl + Filename_r);
               // Log.e(TAG, "onClick: " + WeekBaseurl + Filename_r);
            }
        });

        ivBottomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }


    void previewattache(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void calldeleteLodging() {
        AttachRemoveInterface attachRemoveInterface = APIClient.getClient().create(AttachRemoveInterface.class);
        attachRemoveInterface.CallAttachremove("removeAttachment", tvDate.getText().toString(), PreferenceManager.getEmpID(this), "filename_l").enqueue(new Callback<AttachRemoveResponse>() {
            @Override
            public void onResponse(Call<AttachRemoveResponse> call, Response<AttachRemoveResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        Toast.makeText(ExpensesActivity.this, "Remove", Toast.LENGTH_SHORT).show();

                        ivDeletelodging.setVisibility(View.GONE);
                        tvLodgingEmptyDoc.setVisibility(View.VISIBLE);
                        ivLodgingDoc.setVisibility(View.VISIBLE);
                        tvLodgingDoc.setVisibility(View.GONE);
                        ivpreviewtlodging.setVisibility(View.GONE);

                        if (response.body().getSelecteddtExpenses().getFilename_l().trim().equals("")) {

                            ivDeletelodging.setVisibility(View.GONE);
                            ivLodgingDoc.setVisibility(View.VISIBLE);
                            ivpreviewtlodging.setVisibility(View.GONE);

                        } else {

                            ivDeletelodging.setVisibility(View.VISIBLE);
                            ivLodgingDoc.setVisibility(View.GONE);
                            ivpreviewtlodging.setVisibility(View.VISIBLE);
                        }


                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<AttachRemoveResponse> call, Throwable t) {

            }
        });

    }

    private void callTicketDelete() {
        AttachRemoveInterface attachRemoveInterface = APIClient.getClient().create(AttachRemoveInterface.class);
        attachRemoveInterface.CallAttachremove("removeAttachment", tvDate.getText().toString(), PreferenceManager.getEmpID(this), "filename_t").enqueue(new Callback<AttachRemoveResponse>() {
            @Override
            public void onResponse(Call<AttachRemoveResponse> call, Response<AttachRemoveResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(ExpensesActivity.this, "Remove", Toast.LENGTH_SHORT).show();

                        ivpreviewtTicket.setVisibility(View.GONE);
                        tvTicketDoc.setVisibility(View.GONE);
                        tvTicketEmptyDoc.setVisibility(View.VISIBLE);
                        ivTicketDoc.setVisibility(View.VISIBLE);
                        ivDeleteTicket.setVisibility(View.GONE);
                        if (response.body().getSelecteddtExpenses().getFilename_t().trim().equals("")) {
                            ivpreviewtTicket.setVisibility(View.GONE);
                            ivTicketDoc.setVisibility(View.VISIBLE);
                            ivDeleteTicket.setVisibility(View.GONE);
                        } else {
                            ivpreviewtTicket.setVisibility(View.VISIBLE);
                            ivTicketDoc.setVisibility(View.GONE);
                            ivDeleteTicket.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<AttachRemoveResponse> call, Throwable t) {

            }
        });

    }

    private void calldeleteOther() {
        AttachRemoveInterface attachRemoveInterface = APIClient.getClient().create(AttachRemoveInterface.class);
        attachRemoveInterface.CallAttachremove("removeAttachment", tvDate.getText().toString(), PreferenceManager.getEmpID(this), "filename_o").enqueue(new Callback<AttachRemoveResponse>() {
            @Override
            public void onResponse(Call<AttachRemoveResponse> call, Response<AttachRemoveResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(ExpensesActivity.this, "Remove", Toast.LENGTH_SHORT).show();
                        ivpreviewOther.setVisibility(View.GONE);
                        tvOtherDoc.setVisibility(View.VISIBLE);
                        tvOtherEmptyDoc.setVisibility(View.VISIBLE);
                        ivotherDoc.setVisibility(View.VISIBLE);
                        ivDeleteOther.setVisibility(View.GONE);

                        if (response.body().getSelecteddtExpenses().getFilename_o().trim().equals("")) {

                            ivpreviewOther.setVisibility(View.GONE);
                            ivotherDoc.setVisibility(View.VISIBLE);
                            ivDeleteOther.setVisibility(View.GONE);
                        } else {
                            ivpreviewOther.setVisibility(View.VISIBLE);
                            ivotherDoc.setVisibility(View.GONE);
                            ivDeleteOther.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<AttachRemoveResponse> call, Throwable t) {

            }
        });

    }

    private void Callsaveother() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ExpenseInterface convency = APIClient.getClient().create(ExpenseInterface.class);
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "saveExpensesFile");
        RequestBody active = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody EmpId = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody WorkReport = RequestBody.create(MediaType.parse("text/plain"), edWorkReport.getText().toString());
        RequestBody Convency = RequestBody.create(MediaType.parse("text/plain"), edConveyance.getText().toString());
        RequestBody Ticket = RequestBody.create(MediaType.parse("text/plain"), tvTicket.getText().toString());
        RequestBody Lodging = RequestBody.create(MediaType.parse("text/plain"), tvLodging.getText().toString());
        RequestBody Other = RequestBody.create(MediaType.parse("text/plain"), edOther.getText().toString());
        RequestBody Date = RequestBody.create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());


        RequestBody otherrequest = create(MediaType.parse("multipart/form-data"), fileOther);
        MultipartBody.Part OtherFile = MultipartBody.Part.createFormData("filename_o", fileOther.getName(), otherrequest);

        convency.CallOther(action, EmpId, Date, OtherFile).enqueue(new Callback<ExpenseResponse>() {
            @Override
            public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {

                progressDialog.dismiss();
                AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                alert.setMessage("Expense Saved Successfully");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearoldForm();
                        tvDate.setText("");
                        tvStartdate.setText("");
                        tvto.setText("");
                        tvEnddate.setText("");

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<ExpenseResponse> call, Throwable t) {

            }
        });

    }

    private void Callsaveticket() {

        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ExpenseInterface convency = APIClient.getClient().create(ExpenseInterface.class);
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "saveExpensesFile");
        RequestBody active = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody EmpId = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody WorkReport = RequestBody.create(MediaType.parse("text/plain"), edWorkReport.getText().toString());
        RequestBody Convency = RequestBody.create(MediaType.parse("text/plain"), edConveyance.getText().toString());
        RequestBody Ticket = RequestBody.create(MediaType.parse("text/plain"), tvTicket.getText().toString());
        RequestBody Lodging = RequestBody.create(MediaType.parse("text/plain"), tvLodging.getText().toString());
        RequestBody Other = RequestBody.create(MediaType.parse("text/plain"), edOther.getText().toString());
        RequestBody Date = RequestBody.create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());


        RequestBody ticketrequest = create(MediaType.parse("multipart/form-data"), fileticket);
        MultipartBody.Part TicketFile = MultipartBody.Part.createFormData("filename_t", fileticket.getName(), ticketrequest);

        convency.CallTicket(action, EmpId, Date, TicketFile).enqueue(new Callback<ExpenseResponse>() {
            @Override
            public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {
                progressDialog.dismiss();
               // Log.e(TAG, "onResponse: 3");
                progressDialog.dismiss();
                AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                alert.setMessage("Expense Saved Successfully");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearoldForm();
                        tvDate.setText("");
                        tvStartdate.setText("");
                        tvto.setText("");
                        tvEnddate.setText("");

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();


            }

            @Override
            public void onFailure(Call<ExpenseResponse> call, Throwable t) {

            }
        });

    }

    private void Callsavelodging() {
        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ExpenseInterface convency = APIClient.getClient().create(ExpenseInterface.class);
        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "saveExpensesFile");
        RequestBody active = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody EmpId = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody WorkReport = RequestBody.create(MediaType.parse("text/plain"), edWorkReport.getText().toString());
        RequestBody Convency = RequestBody.create(MediaType.parse("text/plain"), edConveyance.getText().toString());
        RequestBody Ticket = RequestBody.create(MediaType.parse("text/plain"), tvTicket.getText().toString());
        RequestBody Lodging = RequestBody.create(MediaType.parse("text/plain"), tvLodging.getText().toString());
        RequestBody Other = RequestBody.create(MediaType.parse("text/plain"), edOther.getText().toString());
        RequestBody Date = RequestBody.create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());

        RequestBody lodgingrequest = create(MediaType.parse("multipart/form-data"), filelodging);
        MultipartBody.Part LodgingFile = MultipartBody.Part.createFormData("filename_l", filelodging.getName(), lodgingrequest);


        convency.CallLodging(action, EmpId, Date, LodgingFile).enqueue(new Callback<ExpenseResponse>() {
            @Override
            public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {
               // Log.e(TAG, "onResponse: 2");

                progressDialog.dismiss();

                AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                alert.setMessage("Expense Saved Successfully");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearoldForm();
                        tvDate.setText("");
                        tvStartdate.setText("");
                        tvto.setText("");
                        tvEnddate.setText("");

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }

            @Override
            public void onFailure(Call<ExpenseResponse> call, Throwable t) {

            }
        });


    }

    private void Callsaveconvency() {

        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ExpenseInterface convency = APIClient.getClient().create(ExpenseInterface.class);

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "saveExpensesFile");
        RequestBody active = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody EmpId = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody WorkReport = RequestBody.create(MediaType.parse("text/plain"), edWorkReport.getText().toString());
        RequestBody Convency = RequestBody.create(MediaType.parse("text/plain"), edConveyance.getText().toString());
        RequestBody Ticket = RequestBody.create(MediaType.parse("text/plain"), tvTicket.getText().toString());
        RequestBody Lodging = RequestBody.create(MediaType.parse("text/plain"), tvLodging.getText().toString());
        RequestBody Other = RequestBody.create(MediaType.parse("text/plain"), edOther.getText().toString());
        RequestBody Date = RequestBody.create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());

        RequestBody convencyrequest = create(MediaType.parse("multipart/form-data"), fileconvency);
        MultipartBody.Part ConvencyFile = MultipartBody.Part.createFormData("filename_all", fileconvency.getName(), convencyrequest);

        convency.CallConvency(action, EmpId, Date, ConvencyFile).enqueue(new Callback<ExpenseResponse>() {
            @Override
            public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {
               // Log.e(TAG, "onResponse: 1");
                progressDialog.dismiss();
                AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                alert.setMessage("Expense Saved Successfully");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearoldForm();
                        tvDate.setText("");
                        tvStartdate.setText("");
                        tvto.setText("");
                        tvEnddate.setText("");

                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }

            @Override
            public void onFailure(Call<ExpenseResponse> call, Throwable t) {

            }
        });

    }

    private void callconvencyDelete() {
        AttachRemoveInterface attachRemoveInterface = APIClient.getClient().create(AttachRemoveInterface.class);
        attachRemoveInterface.CallAttachremove("removeAttachment", tvDate.getText().toString(), PreferenceManager.getEmpID(this), "filename_all").enqueue(new Callback<AttachRemoveResponse>() {
            @Override
            public void onResponse(Call<AttachRemoveResponse> call, Response<AttachRemoveResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        ivConvencyDelete.setVisibility(View.GONE);
                        tvConveyanceDoc.setVisibility(View.GONE);
                        tvConveyanceEmptyDoc.setVisibility(View.VISIBLE);
                        ivConvencydoc.setVisibility(View.VISIBLE);
                        ivConvencypreview.setVisibility(View.GONE);

                        if (response.body().getSelecteddtExpenses().getFilename_all().trim().equals("")) {

                            ivConvencyDelete.setVisibility(View.GONE);
                            ivConvencydoc.setVisibility(View.VISIBLE);
                            ivConvencypreview.setVisibility(View.GONE);
                        } else {
                            ivConvencyDelete.setVisibility(View.VISIBLE);
                            ivConvencydoc.setVisibility(View.GONE);
                            ivConvencypreview.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<AttachRemoveResponse> call, Throwable t) {

            }
        });
    }

    private void callExpenseList() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.expense_list_recycleview);
        bottomSheetDialog.show();

        ivBottomBack = bottomSheetDialog.findViewById(R.id.ivBottomBack);
        rvExpense = bottomSheetDialog.findViewById(R.id.rvExpense);
        tvViewWeeklyreportSubmit = bottomSheetDialog.findViewById(R.id.tvViewWeeklyreportSubmit);
        rlUploadWeekAll = bottomSheetDialog.findViewById(R.id.rlUploadWeekAll);
        tvWeekDoc = bottomSheetDialog.findViewById(R.id.tvWeekDoc);
        tvGrandsumDoc = bottomSheetDialog.findViewById(R.id.tvGrandsumDoc);
        tvGrandsumDocTag = bottomSheetDialog.findViewById(R.id.tvGrandsumDocTag);


        callExp();
        weeklyAdapter = new ExpensesViewWeeklyAdapter(ExpensesActivity.this, weeklyExpensesModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExpensesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvExpense.setLayoutManager(linearLayoutManager);
        rvExpense.setAdapter(weeklyAdapter);


        rlUploadWeekAll.setOnClickListener(new View.OnClickListener() {
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


        ivBottomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        tvViewWeeklyreportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (fileWeek != null) {
                    CallWeeklyFileSubmit();
                } else {
                    callWeeklySubmit();
                }


            }
        });

    }

    private void callExp() {
        final ProgressDialog progressDialog = new ProgressDialog(ExpensesActivity.this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewExpenseListInterface viewExpenseListInterface = APIClient.getClient().create(ViewExpenseListInterface.class);
        viewExpenseListInterface.Callweek("viewWeeklyExpenses", tvDate.getText().toString(), PreferenceManager.getEmpID(this)).enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {

                weeklyAdapter.weeklyExpensesModels = response.body().getWeeklyExpensesModels();
                weeklyAdapter.notifyDataSetChanged();

                tvGrandsumDocTag.setVisibility(View.VISIBLE);
                tvGrandsumDoc.setText(response.body().getGrandSum());

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {

            }
        });
    }

    private void callWeeklySubmit() {
        final ProgressDialog progressDialog = new ProgressDialog(ExpensesActivity.this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        WeeklySubmitExpensesInterface weeklySubmitExpensesInterface = APIClient.getClient().create(WeeklySubmitExpensesInterface.class);
        RequestBody Action = RequestBody.create(MediaType.parse("text/plain"), "submitExpenses");
        RequestBody EmpID = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(ExpensesActivity.this));
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());
        weeklySubmitExpensesInterface.CallConvencynofile(Action, EmpID, StartDate, EndDate).enqueue(new Callback<WeeklySubmitExpensesResponse>() {
            @Override
            public void onResponse(Call<WeeklySubmitExpensesResponse> call, Response<WeeklySubmitExpensesResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                        alert.setMessage("Expense submitted to the accounts department");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<WeeklySubmitExpensesResponse> call, Throwable t) {

            }
        });

    }

    private void CallWeeklyFileSubmit() {

        final ProgressDialog progressDialog = new ProgressDialog(ExpensesActivity.this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        WeeklySubmitExpensesInterface weeklySubmitExpensesInterface = APIClient.getClient().create(WeeklySubmitExpensesInterface.class);
        RequestBody Action = RequestBody.create(MediaType.parse("text/plain"), "submitExpenses");
        RequestBody EmpID = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(ExpensesActivity.this));
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());
        RequestBody WeekAllrequest = create(MediaType.parse("multipart/form-data"), fileWeek);
        MultipartBody.Part WeekFile = MultipartBody.Part.createFormData("filename_r", fileWeek.getName(), WeekAllrequest);
        weeklySubmitExpensesInterface.CallConvency(Action, EmpID, StartDate, EndDate, WeekFile).enqueue(new Callback<WeeklySubmitExpensesResponse>() {
            @Override
            public void onResponse(Call<WeeklySubmitExpensesResponse> call, Response<WeeklySubmitExpensesResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                        alert.setMessage("Expense submitted to the accounts department ");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        });

                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();

                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<WeeklySubmitExpensesResponse> call, Throwable t) {

            }
        });


    }


    private void Callsubmitexpense() {

        final ProgressDialog progressDialog = new ProgressDialog(this,R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ExpenseInterface expenseInterface = APIClient.getClient().create(ExpenseInterface.class);

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), "saveExpensesData");
        RequestBody active = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody EmpId = RequestBody.create(MediaType.parse("text/plain"), PreferenceManager.getEmpID(this));
        RequestBody WorkReport = RequestBody.create(MediaType.parse("text/plain"), edWorkReport.getText().toString());
        RequestBody Convency = RequestBody.create(MediaType.parse("text/plain"), edConveyance.getText().toString());
        RequestBody Ticket = RequestBody.create(MediaType.parse("text/plain"), tvTicket.getText().toString());
        RequestBody Lodging = RequestBody.create(MediaType.parse("text/plain"), tvLodging.getText().toString());
        RequestBody Other = RequestBody.create(MediaType.parse("text/plain"), edOther.getText().toString());
        RequestBody Date = RequestBody.create(MediaType.parse("text/plain"), tvDate.getText().toString());
        RequestBody StartDate = RequestBody.create(MediaType.parse("text/plain"), tvStartdate.getText().toString());
        RequestBody EndDate = RequestBody.create(MediaType.parse("text/plain"), tvEnddate.getText().toString());


        expenseInterface.CallExpenseSubmit(action, EmpId, Convency, Ticket, Lodging, Other, active, StartDate, EndDate, Date, WorkReport
        ).enqueue(new Callback<ExpenseResponse>() {
            @Override
            public void onResponse(Call<ExpenseResponse> call, Response<ExpenseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                       // Log.e(TAG, "onResponse: All");
                        progressDialog.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(ExpensesActivity.this);
                        alert.setMessage("Expense Saved Successfully");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearoldForm();
                                tvDate.setText("");
                                tvStartdate.setText("");
                                tvto.setText("");
                                tvEnddate.setText("");
                            }
                        });

                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<ExpenseResponse> call, Throwable t) {

            }
        });


    }

    private void CallDate() {
        clearoldForm();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String moth, dt;
                        moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                        dt = (day > 9) ? "" + day : ("0" + day);


                        String strDate = year + "-" + moth + "-" + dt;
                        tvDate.setError(null);
                        tvDate.setText(strDate);

                        callselectdateViewExpenses();
                        final ProgressDialog progressDialog = new ProgressDialog(ExpensesActivity.this,R.style.ProgressBarDialog);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        SelectWeekDateInterface selectWeekDateInterface = APIClient.getClient().create(SelectWeekDateInterface.class);
                        selectWeekDateInterface.CallSelectWeek("viewWeeklyExpenses", tvDate.getText().toString(), PreferenceManager.getEmpID(ExpensesActivity.this)).enqueue(new Callback<SelectWeekResponse>() {
                            @Override
                            public void onResponse(Call<SelectWeekResponse> call, Response<SelectWeekResponse> response) {
                                try {
                                    if (response.isSuccessful()) {

                                        if (Integer.parseInt(response.body().getGrandSum().trim()) == 0) {
                                            tvViewWeeklyreport.setVisibility(View.GONE);
                                        } else {
                                            tvViewWeeklyreport.setVisibility(View.VISIBLE);
                                        }


                                        progressDialog.dismiss();
                                        if (response.body().getError().trim().equals("1")) {
                                            rlExpenseLayout.setVisibility(View.GONE);
                                            rlErrormsg.setVisibility(View.VISIBLE);
                                            tvExtraMsg.setText(response.body().getExtra());

                                        } else {
                                            rlExpenseLayout.setVisibility(View.VISIBLE);
                                            rlErrormsg.setVisibility(View.GONE);
                                            rlmsg.setVisibility(View.GONE);

                                        }
                                       // Log.e(TAG, "onResponse: " + response.body().getError());

                                        tvStartdate.setText(response.body().getStartdate());
                                        tvEnddate.setText(response.body().getEnddate());
                                        tvEnddate.setVisibility(View.VISIBLE);
                                        tvto.setVisibility(View.VISIBLE);
                                        tvStartdate.setVisibility(View.VISIBLE);


                                    }

                                } catch (Exception e) {

                                }

                            }

                            @Override
                            public void onFailure(Call<SelectWeekResponse> call, Throwable t) {

                            }
                        });


                    }

                }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


        datePickerDialog.show();
    }

    private void clearoldForm() {

        edWorkReport.setText("");
        edConveyance.setText("");
        tvTicket.setText("");
        tvLodging.setText("");
        edOther.setText("");
        tvConveyanceDoc.setText("");
        tvLodgingDoc.setText("");
        tvTicketDoc.setText("");
        tvOtherDoc.setText("");

        tvViewWeeklyreport.setVisibility(View.GONE);

        ivConvencyDelete.setVisibility(View.GONE);
        ivConvencydoc.setVisibility(View.VISIBLE);
        tvConveyanceEmptyDoc.setVisibility(View.VISIBLE);
        tvConveyanceDoc.setVisibility(View.GONE);
        ivConvencypreview.setVisibility(View.GONE);

        ivpreviewtTicket.setVisibility(View.GONE);
        ivTicketDoc.setVisibility(View.VISIBLE);
        tvTicketEmptyDoc.setVisibility(View.VISIBLE);
        tvTicketDoc.setVisibility(View.GONE);
        ivDeleteTicket.setVisibility(View.GONE);


        ivpreviewOther.setVisibility(View.GONE);
        ivotherDoc.setVisibility(View.VISIBLE);
        tvOtherEmptyDoc.setVisibility(View.VISIBLE);
        tvOtherDoc.setVisibility(View.GONE);
        ivDeleteOther.setVisibility(View.GONE);


        ivDeletelodging.setVisibility(View.GONE);
        ivLodgingDoc.setVisibility(View.VISIBLE);
        tvLodgingEmptyDoc.setVisibility(View.VISIBLE);
        tvLodgingDoc.setVisibility(View.GONE);
        ivpreviewtlodging.setVisibility(View.GONE);


    }


    private void callselectdateViewExpenses() {
        ViewExpenseListInterface viewExpenseListInterface = APIClient.getClient().create(ViewExpenseListInterface.class);
        viewExpenseListInterface.CallSelectWeek("viewWeeklyExpenses", tvDate.getText().toString(), PreferenceManager.getEmpID(this)).enqueue(new Callback<ViewExpenseResponse>() {
            @Override
            public void onResponse(Call<ViewExpenseResponse> call, Response<ViewExpenseResponse> response) {
                try {
                    if (response.isSuccessful()) {


                        if (!response.body().getSelecteddtExpenses().getWorkreport().trim().equals("")) {
                            edWorkReport.setError(null);
                        }

                        edWorkReport.setText(response.body().getSelecteddtExpenses().getWorkreport());

                        if (response.body().getSelecteddtExpenses().getC_amo().trim().equals("0")) {
                            response.body().getSelecteddtExpenses().setC_amo("");
                            response.body().getSelecteddtExpenses().setFilename_all("");
                        }
                        if (response.body().getSelecteddtExpenses().getT_amo().trim().equals("0")) {
                            response.body().getSelecteddtExpenses().setT_amo("");
                            response.body().getSelecteddtExpenses().setFilename_t("");
                        }
                        if (response.body().getSelecteddtExpenses().getL_amo().trim().equals("0")) {
                            response.body().getSelecteddtExpenses().setL_amo("");
                            response.body().getSelecteddtExpenses().setFilename_l("");
                        }
                        if (response.body().getSelecteddtExpenses().getO_amo().trim().equals("0")) {
                            response.body().getSelecteddtExpenses().setO_amo("");
                            response.body().getSelecteddtExpenses().setFilename_o("");
                        }

                        edConveyance.setText(response.body().getSelecteddtExpenses().getC_amo());
                        tvTicket.setText(response.body().getSelecteddtExpenses().getT_amo());
                        tvLodging.setText(response.body().getSelecteddtExpenses().getL_amo());
                        edOther.setText(response.body().getSelecteddtExpenses().getO_amo());


                        tvConveyanceDoc.setText(response.body().getSelecteddtExpenses().getFilename_all());
                        tvLodgingDoc.setText(response.body().getSelecteddtExpenses().getFilename_l());
                        tvTicketDoc.setText(response.body().getSelecteddtExpenses().getFilename_t());
                        tvOtherDoc.setText(response.body().getSelecteddtExpenses().getFilename_o());


                        BaseExpenseUrl = response.body().getAttachBaseUrl();
                        WebviewConvency = response.body().getSelecteddtExpenses().getFilename_all();
                        WebViewOther = response.body().getSelecteddtExpenses().getFilename_o();
                        WebViewTicket = response.body().getSelecteddtExpenses().getFilename_t();
                        WebViewLodging = response.body().getSelecteddtExpenses().getFilename_l();

                        fillconv = response.body().getSelecteddtExpenses().getFilename_all();
                        filelod = response.body().getSelecteddtExpenses().getFilename_l();
                        fileot = response.body().getSelecteddtExpenses().getFilename_o();
                        filetic = response.body().getSelecteddtExpenses().getFilename_t();

                        if (Integer.parseInt(edConveyance.getText().toString().trim()) == 0) {
                            rlUpload.setVisibility(View.GONE);

                        } else {
                            //rlUpload.setVisibility(View.VISIBLE);
                        }


//                        if (response.body().getSelecteddtExpenses().getFilename_all().trim().equals("")) {
//
//                            ivConvencyDelete.setVisibility(View.GONE);
//                            ivConvencydoc.setVisibility(View.VISIBLE);
//                            tvConveyanceEmptyDoc.setVisibility(View.VISIBLE);
//                            tvConveyanceDoc.setVisibility(View.GONE);
//                            ivConvencypreview.setVisibility(View.GONE);
//                        } else {
//                            ivConvencyDelete.setVisibility(View.VISIBLE);
//                            ivConvencydoc.setVisibility(View.GONE);
//                            tvConveyanceEmptyDoc.setVisibility(View.GONE);
//                            tvConveyanceDoc.setVisibility(View.VISIBLE);
//                            ivConvencypreview.setVisibility(View.VISIBLE);
//
//                        }

                        if (response.body().getSelecteddtExpenses().getFilename_t().trim().equals("")) {

                            ivpreviewtTicket.setVisibility(View.GONE);
                            ivTicketDoc.setVisibility(View.VISIBLE);
                            tvTicketEmptyDoc.setVisibility(View.VISIBLE);
                            tvTicketDoc.setVisibility(View.GONE);
                            ivDeleteTicket.setVisibility(View.GONE);
                        } else {
                            ivpreviewtTicket.setVisibility(View.VISIBLE);
                            ivTicketDoc.setVisibility(View.GONE);
                            tvTicketEmptyDoc.setVisibility(View.GONE);
                            tvTicketDoc.setVisibility(View.VISIBLE);
                            ivDeleteTicket.setVisibility(View.VISIBLE);

                        }

                        if (response.body().getSelecteddtExpenses().getFilename_o().trim().equals("")) {

                            ivpreviewOther.setVisibility(View.GONE);
                            ivotherDoc.setVisibility(View.VISIBLE);
                            tvOtherEmptyDoc.setVisibility(View.VISIBLE);
                            tvOtherDoc.setVisibility(View.GONE);
                            ivDeleteOther.setVisibility(View.GONE);
                        } else {
                            ivpreviewOther.setVisibility(View.VISIBLE);
                            ivotherDoc.setVisibility(View.GONE);
                            tvOtherEmptyDoc.setVisibility(View.GONE);
                            tvOtherDoc.setVisibility(View.VISIBLE);
                            ivDeleteOther.setVisibility(View.VISIBLE);

                        }


                        if (response.body().getSelecteddtExpenses().getFilename_l().trim().equals("")) {

                            ivDeletelodging.setVisibility(View.GONE);
                            ivLodgingDoc.setVisibility(View.VISIBLE);
                            tvLodgingEmptyDoc.setVisibility(View.VISIBLE);
                            tvLodgingDoc.setVisibility(View.GONE);
                            ivpreviewtlodging.setVisibility(View.GONE);
                        } else {
                            ivDeletelodging.setVisibility(View.VISIBLE);
                            ivLodgingDoc.setVisibility(View.GONE);
                            tvLodgingEmptyDoc.setVisibility(View.GONE);
                            tvLodgingDoc.setVisibility(View.VISIBLE);
                            ivpreviewtlodging.setVisibility(View.VISIBLE);


                        }


                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ViewExpenseResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 1:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strConvenyance = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strConvenyance, Toast.LENGTH_SHORT).show();
                    tvConveyanceDoc.setVisibility(View.VISIBLE);

                    try {
                        if (strConvenyance.length() > 0) {
                            tvConveyanceDoc.setText(strConvenyance);
                        }

                    } catch (Exception e) {

                    }


                    try {
                        fileconvency = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;


            case 2:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strtickDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strtickDoc, Toast.LENGTH_SHORT).show();
                    tvTicketDoc.setVisibility(View.VISIBLE);


                    try {
                        if (strtickDoc.length() > 0) {
                            tvTicketDoc.setText(strtickDoc);

                        }

                    } catch (Exception e) {

                    }


                    try {
                        fileticket = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;


            case 4:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strLodgingDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strLodgingDoc, Toast.LENGTH_SHORT).show();
                    tvLodgingDoc.setVisibility(View.VISIBLE);

                    try {
                        if (strLodgingDoc.length() > 0) {
                            tvLodgingDoc.setText(strLodgingDoc);


                        }

                    } catch (Exception e) {

                    }


                    try {
                        filelodging = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;


            case 3:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strotherDoc = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strotherDoc, Toast.LENGTH_SHORT).show();
                    tvOtherDoc.setVisibility(View.VISIBLE);
                    try {
                        if (strotherDoc.length() > 0) {
                            tvOtherDoc.setText(strotherDoc);
                        }

                    } catch (Exception e) {

                    }


                    try {
                        fileOther = FileUtli.from(this, contentUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;


            case 5:

                if (resultCode == RESULT_OK) {
                    Uri contentUri = data.getData();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    strWeekall = timeStamp + "." + getFileExt(contentUri);
                    Toast.makeText(this, "File Name" + strWeekall, Toast.LENGTH_SHORT).show();

                    try {
                        if (strWeekall.length() > 0) {
                            tvWeekDoc.setText(strWeekall);
                        }

                    } catch (Exception e) {

                    }


                    try {
                        fileWeek = FileUtli.from(this, contentUri);
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
}