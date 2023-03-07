package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SpareInwardViewCoInteface;
import com.example.cistronuser.API.Model.SpareInwardCoModel;
import com.example.cistronuser.API.Model.SpareInwardViewCoModel;
import com.example.cistronuser.API.Model.TransportModel;
import com.example.cistronuser.API.Response.SpareInwardViewCoResponse;
import com.example.cistronuser.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareInwardCoAdapter extends RecyclerView.Adapter<SpareInwardCoAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<SpareInwardCoModel>spareInwardCoModels;




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

    //SpareinwardViewRecycleView
    TextView tvCr, tvHospital, tvProduct, tvCallType;
    RecyclerView rvReqDetails;
    ImageView ivBack;
    RelativeLayout rlCr, rlDetails;
    SpareInwardViewCoAdapter spareInwardViewCoAdapter;
    ArrayList<SpareInwardViewCoModel>spareInwardViewCoModels=new ArrayList<>();

    public SpareInwardCoAdapter(Activity activity, ArrayList<SpareInwardCoModel> spareInwardCoModels) {
        this.activity = activity;
        this.spareInwardCoModels = spareInwardCoModels;
    }

    @NonNull
    @Override
    public SpareInwardCoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_inward_co_adapter, parent, false);
        return new SpareInwardCoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareInwardCoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvSerialNo.setText(spareInwardCoModels.get(position).getReqNo());
        holder.tvPurpose.setText(spareInwardCoModels.get(position).getPurpose());
        holder.tvallocatted.setText(spareInwardCoModels.get(position).getAllocatedDt());
        holder.tvReqDate.setText(spareInwardCoModels.get(position).getReqDt());

        if (spareInwardCoModels.get(position).getUser().trim().equals("admin")){
            holder.tvEmpidTag.setVisibility(View.VISIBLE);
            holder.tvEmpid.setVisibility(View.VISIBLE);
            holder.tvEmpid.setText(spareInwardCoModels.get(position).getReqBy());
        }else {
            holder.tvEmpidTag.setVisibility(View.GONE);
            holder.tvEmpid.setVisibility(View.GONE);
        }
        
        holder.rlSerNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.spare_inward_co_view_dialolg_recycleview);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();
                tvHospital = dialog.findViewById(R.id.tvHospital);
                tvCallType = dialog.findViewById(R.id.tvCallType);
                tvProduct = dialog.findViewById(R.id.tvProduct);
                tvCr = dialog.findViewById(R.id.tvCr);
                ivBack = dialog.findViewById(R.id.ivBack);
                rvReqDetails = dialog.findViewById(R.id.rvReqDetails);
                rlDetails = dialog.findViewById(R.id.rlDetails);
                rlCr = dialog.findViewById(R.id.rlCr);
                
                CallViewDetails(spareInwardCoModels.get(position).getReqid(),spareInwardCoModels.get(position).getOpt());
                spareInwardViewCoAdapter=new SpareInwardViewCoAdapter(activity,spareInwardViewCoModels);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                rvReqDetails.setLayoutManager(linearLayoutManager);
                rvReqDetails.setAdapter(spareInwardViewCoAdapter);

                ivBack.setOnClickListener(new View.OnClickListener() {
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
                bottomSheetDialog.setContentView(R.layout.spare_inward_view_dash);
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



                ivViewClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
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

    private void CallViewDetails(String reqid, String opt) {

        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        SpareInwardViewCoInteface spareInwardViewCoInteface= APIClient.getClient().create(SpareInwardViewCoInteface.class);
        spareInwardViewCoInteface.CallReport("viewSpareInwardDetail",reqid,opt).enqueue(new Callback<SpareInwardViewCoResponse>() {
            @Override
            public void onResponse(Call<SpareInwardViewCoResponse> call, Response<SpareInwardViewCoResponse> response) {
                try {
                    progressDialog.dismiss();

                    if (response.body().getCallType().trim().equals("Stock")) {
                        rlCr.setVisibility(View.GONE);
                        rlDetails.setVisibility(View.GONE);
                    } else {
                        rlCr.setVisibility(View.GONE);
                        rlDetails.setVisibility(View.VISIBLE);
                    }

                    tvCallType.setText(response.body().getCallType());
                    tvHospital.setText(response.body().getHospital());
                    tvProduct.setText(response.body().getProduct());

                    spareInwardViewCoAdapter.spareInwardViewCoModels=response.body().getSpareInwardViewCoModels();
                    spareInwardViewCoAdapter.notifyDataSetChanged();

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<SpareInwardViewCoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return spareInwardCoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerialNo, tvReqDate, tvPurpose,tvEmpidTag,tvEmpid,tvallocatted;
        ImageView ivView;
        RelativeLayout rlSerNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivView = itemView.findViewById(R.id.ivView);
            tvSerialNo = itemView.findViewById(R.id.tvSerialNo);
            tvReqDate = itemView.findViewById(R.id.tvReqDate);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
            tvEmpid=itemView.findViewById(R.id.tvEmpid);
            tvEmpidTag=itemView.findViewById(R.id.tvEmpidTag);
            tvallocatted=itemView.findViewById(R.id.tvallocatted);
            rlSerNo=itemView.findViewById(R.id.rlSerNo);
        }
    }
}
