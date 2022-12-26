package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.AdjustmentExpensesInterface;
import com.example.cistronuser.API.Interface.HardcopyExpensesReportInterface;
import com.example.cistronuser.API.Interface.PaidReportExpensesInterface;
import com.example.cistronuser.API.Interface.PaymentProcessedExpensesInterface;
import com.example.cistronuser.API.Interface.ReportExpensesViewInterface;
import com.example.cistronuser.API.Interface.ResetExpensesReportInterface;
import com.example.cistronuser.API.Model.ReportExpensesModel;
import com.example.cistronuser.API.Model.ReportExpensesViewModel;
import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;
import com.example.cistronuser.API.Response.OperatorModel;
import com.example.cistronuser.API.Response.ReportExpensesViewResponses;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportExpenseAdapter extends RecyclerView.Adapter<ReportExpenseAdapter.ViewHolder> {

    public ArrayList<ReportExpensesModel> reportExpensesModels;
    Activity activity;


    //BottomView
    RecyclerView rvExpenseweeklyView;
    TextView tvGrandsumDoc;
    String weekpreview;
    String BaseUrl;
    ReportViewWeeklyAdapter reportViewWeeklyAdapter;
    ArrayList<ReportExpensesViewModel> reportExpensesViewModels = new ArrayList<>();

    ImageView ivBack, ivWeekPreview;
    RelativeLayout rlAdjustmentLayout;
    TextView tvOperterTag, tvAmtTag, tvReasoTag, tvUpdate;
    ImageView ivDown, ivUp;
    Spinner spOperator;
    EditText edAmt, edReason;


    //Reset
    TextView tvResetTag;
    TextView tvWeeklyPreviewTag;
    SwitchCompat stReset;

    //Hardcopy
    SwitchCompat sthardcopy;
    TextView tvDateTag, tvDate, tvRpTag, tvhcSubmit;
    EditText tvRp;

    //Payment
    SwitchCompat stPay;
    TextView tvPayDateTag, tvPayDate, tvPaySubmit;

    //Paid

    SwitchCompat stPaid;
    TextView tvPaidDateTag, tvPaidDate, tvPaidSubmit;

    //Spinner
    ArrayAdapter operator;
    ArrayList<String> strOperator = new ArrayList<>();
    ArrayList<OperatorModel> operatorModels = new ArrayList<>();
    String OperatorID;


    public ReportExpenseAdapter(ArrayList<ReportExpensesModel> reportExpensesModels, Activity activity) {
        this.reportExpensesModels = reportExpensesModels;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ReportExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_submitted_report, parent, false);
        return new ReportExpenseAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportExpenseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvEmpId.setText(reportExpensesModels.get(position).getEmpid());
        holder.tvName.setText(reportExpensesModels.get(position).getName());
        holder.tvStartDate.setText(reportExpensesModels.get(position).getStartdate());
        holder.tvEndDate.setText(reportExpensesModels.get(position).getEnddate());
        holder.tvSubmitDate.setText(reportExpensesModels.get(position).getTimestamp());

        holder.tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity,R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.expenses_viewweekly_report);
                bottomSheetDialog.show();
                bottomSheetDialog.setCancelable(false);
                rvExpenseweeklyView = bottomSheetDialog.findViewById(R.id.rvExpenseweeklyView);
                tvGrandsumDoc = bottomSheetDialog.findViewById(R.id.tvGrandsumDoc);
                ivBack = bottomSheetDialog.findViewById(R.id.ivBack);
                ivWeekPreview = bottomSheetDialog.findViewById(R.id.ivWeekPreview);
                tvWeeklyPreviewTag = bottomSheetDialog.findViewById(R.id.tvWeeklyPreviewTag);


                strOperator.clear();
                operatorModels.clear();


                //Adjustment
                rlAdjustmentLayout = bottomSheetDialog.findViewById(R.id.rlAdjustmentLayout);
                ivDown = bottomSheetDialog.findViewById(R.id.ivDown);
                ivUp = bottomSheetDialog.findViewById(R.id.ivUp);
                tvOperterTag = bottomSheetDialog.findViewById(R.id.tvOperterTag);
                tvAmtTag = bottomSheetDialog.findViewById(R.id.tvAmtTag);
                tvReasoTag = bottomSheetDialog.findViewById(R.id.tvReasoTag);
                tvUpdate = bottomSheetDialog.findViewById(R.id.tvUpdate);
                spOperator = bottomSheetDialog.findViewById(R.id.spOperator);
                edAmt = bottomSheetDialog.findViewById(R.id.edAmt);
                edReason = bottomSheetDialog.findViewById(R.id.edReason);


                //Reset
                stReset = bottomSheetDialog.findViewById(R.id.stReset);

                //Hardcopy
                sthardcopy = bottomSheetDialog.findViewById(R.id.sthardcopy);
                tvDateTag = bottomSheetDialog.findViewById(R.id.tvDateTag);
                tvDate = bottomSheetDialog.findViewById(R.id.tvDate);
                tvRpTag = bottomSheetDialog.findViewById(R.id.tvRpTag);
                tvRp = bottomSheetDialog.findViewById(R.id.tvRp);
                tvhcSubmit = bottomSheetDialog.findViewById(R.id.tvhcSubmit);

                //Payment
                stPay = bottomSheetDialog.findViewById(R.id.stPay);
                tvPayDateTag = bottomSheetDialog.findViewById(R.id.tvPayDateTag);
                tvPayDate = bottomSheetDialog.findViewById(R.id.tvPayDate);
                tvPaySubmit = bottomSheetDialog.findViewById(R.id.tvPaySubmit);

                //Paid
                stPaid = bottomSheetDialog.findViewById(R.id.stPaid);
                tvPaidDateTag = bottomSheetDialog.findViewById(R.id.tvPaidDateTag);
                tvPaidDate = bottomSheetDialog.findViewById(R.id.tvPaidDate);
                tvPaidSubmit = bottomSheetDialog.findViewById(R.id.tvPaidSubmit);


                OperatorModel select = new OperatorModel();
                select.setId("0");
                select.setOperator("----Select----");
                operatorModels.add(select);

                OperatorModel plus = new OperatorModel();
                plus.setId("1");
                plus.setOperator("+");
                operatorModels.add(plus);

                OperatorModel minus = new OperatorModel();
                minus.setId("2");
                minus.setOperator("-");
                operatorModels.add(minus);


                for (int i = 0; i < operatorModels.size(); i++) {
                    strOperator.add(operatorModels.get(i).getOperator());

                }

                operator = new ArrayAdapter(activity, R.layout.spinner_item, strOperator);
                operator.setDropDownViewResource(R.layout.spinner_dropdown);
                spOperator.setAdapter(operator);

                spOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        OperatorID = operatorModels.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


//                Date d = new Date();
//                CharSequence s = DateFormat.format("yyyy -MM-dd ", d.getTime());
//                tvDate.setText(s);
//
//                Date pay = new Date();
//                CharSequence pays = DateFormat.format("yyyy -MM-dd ", pay.getTime());
//                tvPayDate.setText(pays);
//
//                Date paid = new Date();
//                CharSequence paids = DateFormat.format("yyyy -MM-dd ", paid.getTime());
//                tvPaidDate.setText(paids);


                callExpenseView(reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate());
                reportViewWeeklyAdapter = new ReportViewWeeklyAdapter(activity, BaseUrl, reportExpensesViewModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvExpenseweeklyView.setAdapter(reportViewWeeklyAdapter);
                rvExpenseweeklyView.setLayoutManager(linearLayoutManager);

                tvDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callDatePicker();
                    }
                });
                tvPaidDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callpaidDate();
                    }
                });
                tvPayDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callPaydate();
                    }
                });

                ivBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                ivWeekPreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        weekpreview(BaseUrl + weekpreview);

                    }
                });


                ivDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivUp.setVisibility(View.VISIBLE);
                        tvOperterTag.setVisibility(View.VISIBLE);
                        tvAmtTag.setVisibility(View.VISIBLE);
                        tvReasoTag.setVisibility(View.VISIBLE);
                        tvUpdate.setVisibility(View.VISIBLE);
                        spOperator.setVisibility(View.VISIBLE);
                        edAmt.setVisibility(View.VISIBLE);
                        edReason.setVisibility(View.VISIBLE);
                        ivDown.setVisibility(View.GONE);


                    }
                });
                ivUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ivUp.setVisibility(View.GONE);
                        tvOperterTag.setVisibility(View.GONE);
                        tvAmtTag.setVisibility(View.GONE);
                        tvReasoTag.setVisibility(View.GONE);
                        tvUpdate.setVisibility(View.GONE);
                        spOperator.setVisibility(View.GONE);
                        edAmt.setVisibility(View.GONE);
                        edReason.setVisibility(View.GONE);
                        ivDown.setVisibility(View.VISIBLE);
                    }
                });
                stReset.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {

                            final ProgressDialog progressDialog = new ProgressDialog(activity);
                            progressDialog.setMessage("Reset...");
                            progressDialog.setCancelable(false);
                            progressDialog.show();

                            ResetExpensesReportInterface resetExpensesReportInterface = APIClient.getClient().create(ResetExpensesReportInterface.class);
                            resetExpensesReportInterface.callReset("resetSubmittedExpenses", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate()).enqueue(new Callback<AdjustmentExpensesResponse>() {
                                @Override
                                public void onResponse(Call<AdjustmentExpensesResponse> call, Response<AdjustmentExpensesResponse> response) {
                                    try {
                                        if (response.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            //bottomSheetDialog.dismiss();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        }

                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void onFailure(Call<AdjustmentExpensesResponse> call, Throwable t) {

                                }
                            });

                        } else {

                        }


                    }
                });

                stPaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            tvPaidDateTag.setVisibility(View.VISIBLE);
                            tvPaidDate.setVisibility(View.VISIBLE);
                            tvPaidSubmit.setVisibility(View.VISIBLE);
                        } else {

                            tvPaidDateTag.setVisibility(View.GONE);
                            tvPaidDate.setVisibility(View.GONE);
                            tvPaidSubmit.setVisibility(View.GONE);

                        }


                    }
                });

                stPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            tvPayDateTag.setVisibility(View.VISIBLE);
                            tvPayDate.setVisibility(View.VISIBLE);
                            tvPaySubmit.setVisibility(View.VISIBLE);
                        } else {

                            tvPayDateTag.setVisibility(View.GONE);
                            tvPayDate.setVisibility(View.GONE);
                            tvPaySubmit.setVisibility(View.GONE);

                        }


                    }
                });

                sthardcopy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            tvhcSubmit.setVisibility(View.VISIBLE);
                            tvDateTag.setVisibility(View.VISIBLE);
                            tvDate.setVisibility(View.VISIBLE);
                            tvRpTag.setVisibility(View.VISIBLE);
                            tvRp.setVisibility(View.VISIBLE);
                        } else {

                            tvhcSubmit.setVisibility(View.GONE);
                            tvDateTag.setVisibility(View.GONE);
                            tvDate.setVisibility(View.GONE);
                            tvRpTag.setVisibility(View.GONE);
                            tvRp.setVisibility(View.GONE);

                        }


                    }
                });

                tvhcSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        HardcopyExpensesReportInterface hardcopyExpensesReportInterface = APIClient.getClient().create(HardcopyExpensesReportInterface.class);
                        hardcopyExpensesReportInterface.callhardcopy("expHardcopyReceived", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate(), tvDate.getText().toString(), tvRp.getText().toString()).enqueue(new Callback<AdjustmentExpensesResponse>() {
                            @Override
                            public void onResponse(Call<AdjustmentExpensesResponse> call, Response<AdjustmentExpensesResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {

                                }

                            }

                            @Override
                            public void onFailure(Call<AdjustmentExpensesResponse> call, Throwable t) {

                            }
                        });


                    }
                });

                tvUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        AdjustmentExpensesInterface adjustmentExpensesInterface = APIClient.getClient().create(AdjustmentExpensesInterface.class);
                        adjustmentExpensesInterface.callAdjustment("expSaveAdjustment", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate(), OperatorID, edAmt.getText().toString(), edReason.getText().toString()).enqueue(new Callback<AdjustmentExpensesResponse>() {
                            @Override
                            public void onResponse(Call<AdjustmentExpensesResponse> call, Response<AdjustmentExpensesResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<AdjustmentExpensesResponse> call, Throwable t) {

                            }
                        });


                    }
                });
                tvPaidSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        PaidReportExpensesInterface paidReportExpensesInterface = APIClient.getClient().create(PaidReportExpensesInterface.class);
                        paidReportExpensesInterface.callPaid("expPaymentPaid", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate(), tvPaidDate.getText().toString()).enqueue(new Callback<AdjustmentExpensesResponse>() {
                            @Override
                            public void onResponse(Call<AdjustmentExpensesResponse> call, Response<AdjustmentExpensesResponse> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<AdjustmentExpensesResponse> call, Throwable t) {

                            }
                        });


                    }
                });
                tvPaySubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        PaymentProcessedExpensesInterface paymentProcessedExpensesInterface = APIClient.getClient().create(PaymentProcessedExpensesInterface.class);
                        paymentProcessedExpensesInterface.callPay("expPaymentProcessed", reportExpensesModels.get(position).getEmpid(), reportExpensesModels.get(position).getStartdate(), reportExpensesModels.get(position).getEnddate(), tvPayDate.getText().toString()).enqueue(new Callback<AdjustmentExpensesResponse>() {
                            @Override
                            public void onResponse(Call<AdjustmentExpensesResponse> call, Response<AdjustmentExpensesResponse> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onFailure(Call<AdjustmentExpensesResponse> call, Throwable t) {

                            }
                        });

                    }
                });


            }
        });

    }

    private void callExpenseView(String empid, String startdate, String enddate) {


        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ReportExpensesViewInterface reportExpensesViewInterface = APIClient.getClient().create(ReportExpensesViewInterface.class);
        reportExpensesViewInterface.callreportviewexpenses("viewsubmittedExpenses", empid, startdate, enddate).enqueue(new Callback<ReportExpensesViewResponses>() {
            @Override
            public void onResponse(Call<ReportExpensesViewResponses> call, Response<ReportExpensesViewResponses> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();

                        tvGrandsumDoc.setText(response.body().getGrandSum());
                        BaseUrl = response.body().getAttachBaseUrl();
                        weekpreview = response.body().getFilename_r();
                        reportViewWeeklyAdapter.weeklyExpensesModels = response.body().getReportExpensesViewModels();
                        reportViewWeeklyAdapter.notifyDataSetChanged();

                        if (response.body().getFilename_r().trim().equals("")) {
                            ivWeekPreview.setVisibility(View.GONE);
                            tvWeeklyPreviewTag.setVisibility(View.GONE);
                        } else {
                            ivWeekPreview.setVisibility(View.VISIBLE);
                            tvWeeklyPreviewTag.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getAdj_op().trim().equals("0")) {


                            tvOperterTag.setVisibility(View.GONE);
                            tvAmtTag.setVisibility(View.GONE);
                            tvReasoTag.setVisibility(View.GONE);
                            tvUpdate.setVisibility(View.GONE);
                            spOperator.setVisibility(View.GONE);
                            edAmt.setVisibility(View.GONE);
                            edReason.setVisibility(View.GONE);
                            ivDown.setVisibility(View.VISIBLE);


                        } else {

                            String oper=response.body().getAdj_op();
                            spOperator.setSelection(Integer.parseInt(oper));
                            edAmt.setText(response.body().getAdj_amt());
                            edReason.setText(response.body().getAdj_reason());
                            ivUp.setVisibility(View.VISIBLE);
                            tvOperterTag.setVisibility(View.VISIBLE);
                            tvAmtTag.setVisibility(View.VISIBLE);
                            tvReasoTag.setVisibility(View.VISIBLE);
                            tvUpdate.setVisibility(View.VISIBLE);
                            spOperator.setVisibility(View.VISIBLE);
                            edAmt.setVisibility(View.VISIBLE);
                            edReason.setVisibility(View.VISIBLE);
                            ivDown.setVisibility(View.GONE);

                        }

                        if (response.body().getChk_paid().trim().equals("0")) {
                            stPaid.setChecked(false);
                            tvPaidDateTag.setVisibility(View.GONE);
                            tvPaidDate.setVisibility(View.GONE);
                            tvPaidSubmit.setVisibility(View.GONE);
                        } else {
                            stPaid.setChecked(true);
                            tvPaidDate.setText(response.body().getDate_paid());
                            tvPaidDateTag.setVisibility(View.VISIBLE);
                            tvPaidDate.setVisibility(View.VISIBLE);
                            tvPaidSubmit.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getChk_hardcopy().trim().equals("0")) {

                            sthardcopy.setChecked(false);
                            tvhcSubmit.setVisibility(View.GONE);
                            tvDateTag.setVisibility(View.GONE);
                            tvDate.setVisibility(View.GONE);
                            tvRpTag.setVisibility(View.GONE);
                            tvRp.setVisibility(View.GONE);

                        } else {
                            sthardcopy.setChecked(true);
                            tvDate.setText(response.body().getDate_hardcopy());
                            tvRp.setText(response.body().getHardcopy_person());
                            tvhcSubmit.setVisibility(View.VISIBLE);
                            tvDateTag.setVisibility(View.VISIBLE);
                            tvDate.setVisibility(View.VISIBLE);
                            tvRpTag.setVisibility(View.VISIBLE);
                            tvRp.setVisibility(View.VISIBLE);

                        }
                        if (response.body().getChk_pay().trim().equals("0")) {

                            stPay.setChecked(false);
                            tvPayDateTag.setVisibility(View.GONE);
                            tvPayDate.setVisibility(View.GONE);
                            tvPaySubmit.setVisibility(View.GONE);

                        } else {
                            stPay.setChecked(true);
                            tvPayDate.setText(response.body().getDate_pay());
                            tvPayDateTag.setVisibility(View.VISIBLE);
                            tvPayDate.setVisibility(View.VISIBLE);
                            tvPaySubmit.setVisibility(View.VISIBLE);

                        }

//                                if (response.body().getActive().trim().equals("0")){
//
//                                    stReset.setChecked(true);
//
//                                }else {
//                                    stReset.setChecked(false);
//                                }
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<ReportExpensesViewResponses> call, Throwable t) {

                progressDialog.dismiss();

            }
        });
    }

    private void callPaydate() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String moth, dt;

                moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                dt = (day > 9) ? "" + day : ("0" + day);


                String strDate = year + "-" + moth + "-" + dt;
                tvPayDate.setText(strDate);
            }

        }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


        datePickerDialog.show();
    }

    private void callpaidDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String moth, dt;

                moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                dt = (day > 9) ? "" + day : ("0" + day);


                String strDate = year + "-" + moth + "-" + dt;

                tvPaidDate.setText(strDate);

            }

        }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


        datePickerDialog.show();
    }

    private void callDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String moth, dt;

                moth = ((month + 1) > 9) ? "" + (month + 1) : ("0" + (month + 1));

                dt = (day > 9) ? "" + day : ("0" + day);


                String strDate = year + "-" + moth + "-" + dt;
                tvDate.setText(strDate);

            }

        }, year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


        datePickerDialog.show();
    }

    void weekpreview(String url) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return reportExpensesModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvName, tvEmpId, tvStartDate, tvEndDate, tvSubmitDate, tvView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvView = itemView.findViewById(R.id.tvView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);
            tvSubmitDate = itemView.findViewById(R.id.tvSubmitDate);


        }
    }
}
