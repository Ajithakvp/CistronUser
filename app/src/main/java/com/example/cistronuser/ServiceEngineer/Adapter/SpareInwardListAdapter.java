package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.AcceptSpareInwardInterFace;
import com.example.cistronuser.API.Interface.ViewSparesInwardDetailsInterface;
import com.example.cistronuser.API.Model.SpareInwardRecordModel;
import com.example.cistronuser.API.Model.TransportModel;
import com.example.cistronuser.API.Response.AcceptSpareInwardResponse;
import com.example.cistronuser.API.Response.ViewSparesInwardDetailsResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Activity.UpcomingCallReport;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareInwardListAdapter extends RecyclerView.Adapter<SpareInwardListAdapter.ViewHolder> {


    public ArrayList<SpareInwardRecordModel> spareInwardRecordModels;
    // Spare Inward Req Info
    SpareInwardReqInfoAdapter spareInwardReqInfoAdapter;
    RecyclerView rvSpareInwardReqInfo;
    ImageView ivClose;
    Activity activity;


    //View
    RelativeLayout rlInvoice,rlCusPo;
    CheckBox cbDcView,cbInVoiceView,cbCusPOeView,cbModeOfTransport,cbDate;
    TextView tvDcView,tvInVoiceView,tvCusPOView,tvDate,tvAccept,tvTime;
    EditText edDcView,edInVoiceView,edICusPOView,edRefNo;
    Spinner spModeOfTransport;
    ImageView ivViewClose;
    String dcView,inVoiceView,cusPoView;
    ArrayAdapter transportAdapter;
    ArrayList<TransportModel>transportModels=new ArrayList<>();
    ArrayList<String>strTransport=new ArrayList<>();
    String TransportID;




    public SpareInwardListAdapter(Activity activity, ArrayList<SpareInwardRecordModel> spareInwardRecordModels) {
        this.activity = activity;
        this.spareInwardRecordModels = spareInwardRecordModels;
    }

    @NonNull
    @Override
    public SpareInwardListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_inward_list_adapter, parent, false);
        return new SpareInwardListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareInwardListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvReqNO.setText(spareInwardRecordModels.get(position).getReqNo());
        holder.tvDate.setText(spareInwardRecordModels.get(position).getReqDate());
        holder.tvPurpose.setText(spareInwardRecordModels.get(position).getReqPurpose());
        holder.tvallocattedDate.setText(spareInwardRecordModels.get(position).getAllocateDt());

        holder.tvReqNO.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.spare_inward_req_info_dialog_recycleview);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                rvSpareInwardReqInfo = dialog.findViewById(R.id.rvSpareInwardReqInfo);
                ivClose = dialog.findViewById(R.id.ivClose);

                spareInwardReqInfoAdapter = new SpareInwardReqInfoAdapter(activity, spareInwardRecordModels.get(position).getSpareInwardReqInfoModels());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvSpareInwardReqInfo.setAdapter(spareInwardReqInfoAdapter);
                rvSpareInwardReqInfo.setLayoutManager(linearLayoutManager);

                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }
        });

        holder.ivView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(activity,R.style.AppBottomSheetDialogTheme);
                bottomSheetDialog.setContentView(R.layout.spare_inward_dialog_view);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.show();

                rlInvoice=bottomSheetDialog.findViewById(R.id.rlInvoice);
                rlCusPo=bottomSheetDialog.findViewById(R.id.rlCusPo);
                cbDate=bottomSheetDialog.findViewById(R.id.cbDate);
                cbModeOfTransport=bottomSheetDialog.findViewById(R.id.cbModeOfTransport);
                cbDcView=bottomSheetDialog.findViewById(R.id.cbDcView);
                cbInVoiceView=bottomSheetDialog.findViewById(R.id.cbInVoiceView);
                cbCusPOeView=bottomSheetDialog.findViewById(R.id.cbCusPOeView);
                tvDcView=bottomSheetDialog.findViewById(R.id.tvDcView);
                tvInVoiceView=bottomSheetDialog.findViewById(R.id.tvInVoiceView);
                tvCusPOView=bottomSheetDialog.findViewById(R.id.tvCusPOView);
                tvDate=bottomSheetDialog.findViewById(R.id.tvDate);
                edDcView=bottomSheetDialog.findViewById(R.id.edDcView);
                edInVoiceView=bottomSheetDialog.findViewById(R.id.edInVoiceView);
                edICusPOView=bottomSheetDialog.findViewById(R.id.edICusPOView);
                edRefNo=bottomSheetDialog.findViewById(R.id.edRefNo);
                spModeOfTransport=bottomSheetDialog.findViewById(R.id.spModeOfTransport);
                ivViewClose=bottomSheetDialog.findViewById(R.id.ivViewClose);
                tvAccept=bottomSheetDialog.findViewById(R.id.tvAccept);
                tvTime=bottomSheetDialog.findViewById(R.id.tvTime);


                CallViewList(spareInwardRecordModels.get(position).getReqId(),spareInwardRecordModels.get(position).getOpt());


                // ************** Transport SPinner ************* //
                transportAdapter=new ArrayAdapter(activity,R.layout.spinner_item,strTransport);
                transportAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                spModeOfTransport.setAdapter(transportAdapter);
                spModeOfTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TransportID=transportModels.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // ************** Transport SPinner End ************* //

                tvDcView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri=Uri.parse(dcView);
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        activity.startActivity(intent);
                    }
                });

                tvCusPOView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CallViewFile(cusPoView);
                    }
                });

                tvInVoiceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CallViewFile(inVoiceView);
                    }
                });

                ivViewClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                tvAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CallAccept(bottomSheetDialog,spareInwardRecordModels.get(position).getReqId(),spareInwardRecordModels.get(position).getOpt());
                    }
                });


                tvDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

                        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                        datePickerDialog.show();



                    }
                });

                tvTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        int mHour = c.get(Calendar.HOUR_OF_DAY);
                        int mMinute = c.get(Calendar.MINUTE);
                        int msec=c.get(Calendar.SECOND);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                tvTime.setText(hourOfDay + ":" + minute+ ":" +msec);
                            }
                        }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                });

            }
        });


    }

    private void CallAccept(BottomSheetDialog bottomSheetDialog, String reqId, String opt) {

        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        int cbc_no=cbDcView.isChecked() ? 1 : 0;
        int cinv_no=cbInVoiceView.isChecked() ? 1 : 0;
        int cpo_no=cbCusPOeView.isChecked() ? 1 : 0;
        int ctransporttype=cbModeOfTransport.isChecked() ? 1 : 0;
        int cdated=cbDate.isChecked() ? 1 : 0;

        AcceptSpareInwardInterFace acceptSpareInwardInterFace=APIClient.getClient().create(AcceptSpareInwardInterFace.class);
        acceptSpareInwardInterFace.CallAccept("acceptSparesInward", String.valueOf(cbc_no),edDcView.getText().toString(), String.valueOf(cinv_no),edInVoiceView.getText().toString(), String.valueOf(cpo_no),edICusPOView.getText().toString(), String.valueOf(ctransporttype),TransportID, edRefNo.getText().toString(), String.valueOf(cdated),tvDate.getText().toString(),tvTime.getText().toString(),reqId, PreferenceManager.getEmpID(activity),opt).enqueue(new Callback<AcceptSpareInwardResponse>() {
            @Override
            public void onResponse(Call<AcceptSpareInwardResponse> call, Response<AcceptSpareInwardResponse> response) {
                try{
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        activity.finish();
                        activity.overridePendingTransition(0, 0);
                        activity.startActivity(activity.getIntent());
                        activity.overridePendingTransition(0, 0);
                        Toast.makeText(activity, "Accepted", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();

                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<AcceptSpareInwardResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }


    private void CallViewList(String reqId, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewSparesInwardDetailsInterface viewSparesInwardDetailsInterface= APIClient.getClient().create(ViewSparesInwardDetailsInterface.class);
        viewSparesInwardDetailsInterface.CallviewSparesInwardDetail("viewSparesInwardDetail",opt,reqId).enqueue(new Callback<ViewSparesInwardDetailsResponse>() {
            @Override
            public void onResponse(Call<ViewSparesInwardDetailsResponse> call, Response<ViewSparesInwardDetailsResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();

                        if (response.body().getChkDc().trim().equals("1")){
                            cbDcView.setChecked(true);
                        }else {
                            cbDcView.setChecked(false);
                        }

                        if (response.body().getChkInv().trim().equals("1")){
                            cbInVoiceView.setChecked(true);
                            rlInvoice.setVisibility(View.VISIBLE);
                        }else {
                            cbInVoiceView.setChecked(false);
                            rlInvoice.setVisibility(View.GONE);
                        }

                        if (response.body().getChkPo().trim().equals("1")){
                            cbCusPOeView.setChecked(true);
                            rlCusPo.setVisibility(View.VISIBLE);
                        }else {
                            cbCusPOeView.setChecked(false);
                            rlCusPo.setVisibility(View.GONE);

                        }

                        if (response.body().getChkTransport().trim().equals("1")){
                            cbModeOfTransport.setChecked(true);
                        }else {
                            cbModeOfTransport.setChecked(false);
                        }

                        if (response.body().getAllocateDt().trim().equals("0000-00-00")){
                            cbDate.setChecked(false);
                        }else {
                            cbDate.setChecked(true);
                        }

                        if (response.body().getDcAttach().trim().equals("")){
                            tvDcView.setVisibility(View.GONE);
                        }else {
                            tvDcView.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getPoAttch().trim().equals("")){
                            tvCusPOView.setVisibility(View.GONE);
                        }else {
                            tvCusPOView.setVisibility(View.VISIBLE);
                        }

                        if (response.body().getInvAttch().trim().equals("")){
                            tvInVoiceView.setVisibility(View.GONE);
                        }else {
                            tvInVoiceView.setVisibility(View.VISIBLE);
                        }


                        dcView=response.body().getDcAttach();
                        cusPoView=response.body().getPoAttch();
                        inVoiceView=response.body().getInvAttch();
                        edDcView.setText(response.body().getDcNo());
                        edICusPOView.setText(response.body().getPoNo());
                        edInVoiceView.setText(response.body().getInvNo());
                        edRefNo.setText(response.body().getRefNo());


                        // ************** Transport SPinner ************* //
                        strTransport.clear();

                        transportModels=response.body().getTransportModels();
                        for (int i=0;i<transportModels.size();i++){
                            strTransport.add(transportModels.get(i).getText());
                           if (transportModels.get(i).getSelected().trim().equals("1")){
                               spModeOfTransport.setSelection(i);
                           }
                        }
                        transportAdapter.notifyDataSetChanged();

                        // ************** Transport SPinner End ************* //


                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onFailure(Call<ViewSparesInwardDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }
    private void CallViewFile(String view) {

        Uri uri=Uri.parse(view);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        activity.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return spareInwardRecordModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReqNO, tvDate, tvPurpose, tvallocattedDate;
        ImageView ivView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReqNO = itemView.findViewById(R.id.tvReqNO);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
            tvallocattedDate = itemView.findViewById(R.id.tvallocattedDate);
            ivView = itemView.findViewById(R.id.ivView);

        }

    }
}
