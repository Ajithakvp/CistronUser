package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SwapinwardAcceptInterface;
import com.example.cistronuser.API.Interface.ViewSparesInwardDetailsInterface;
import com.example.cistronuser.API.Interface.ViewSwapindialogInterface;
import com.example.cistronuser.API.Model.SwapInwardmainModel;
import com.example.cistronuser.API.Model.TransportModel;
import com.example.cistronuser.API.Response.SwapinwardAcceptResponse;
import com.example.cistronuser.API.Response.ViewSparesInwardDetailsResponse;
import com.example.cistronuser.API.Response.ViewSwapindialogResponse;
import com.example.cistronuser.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwapInward_adapter extends RecyclerView.Adapter<SwapInward_adapter.ViewHolder> {

    public ArrayList<SwapInwardmainModel>swapInwardmainModels;
    private Activity activity;

    public SwapInward_adapter(ArrayList<SwapInwardmainModel> swapInwardmainModels, Activity activity) {
        this.swapInwardmainModels = swapInwardmainModels;
        this.activity = activity;
    }
    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

    TextView tvserial,tvpartno,tvpartName,tvReqQty,tvDate,tvAccept;
    CheckBox cbModeOfTransport,cbDate;
    Spinner spModeOfTransport;
    EditText edRefNo;

    ImageView ivViewClose;

    ArrayAdapter spmodeadapter;
    ArrayList<String>strModelist=new ArrayList<>();
    String TransportID;
    ArrayList<TransportModel>transportModels=new ArrayList<>();
    String seletTransport;




    @NonNull
    @Override
    public SwapInward_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.swap_inward_adapter,parent,false);
        return new SwapInward_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwapInward_adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvserNo.setText(swapInwardmainModels.get(position).getReq_no());
        String reqby=swapInwardmainModels.get(position).getReq_by_name()+" ( "+swapInwardmainModels.get(position).getReq_by()+" ) ";
        holder.tvreturnby.setText(reqby);
        holder.tvreturndate.setText(swapInwardmainModels.get(position).getReturn_date());
        holder.rlview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.swap_inward_view_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                tvDate=dialog.findViewById(R.id.tvDate);
                edRefNo=dialog.findViewById(R.id.edRefNo);
                tvserial=dialog.findViewById(R.id.tvserial);
                tvpartno=dialog.findViewById(R.id.tvpartno);
                tvpartName=dialog.findViewById(R.id.tvpartName);
                ivViewClose=dialog.findViewById(R.id.ivViewClose);
                tvReqQty=dialog.findViewById(R.id.tvReqQty);
                cbModeOfTransport=dialog.findViewById(R.id.cbModeOfTransport);
                cbDate=dialog.findViewById(R.id.cbDate);
                spModeOfTransport=dialog.findViewById(R.id.spModeOfTransport);
                tvAccept=dialog.findViewById(R.id.tvAccept);


                ivViewClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                tvAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id=swapInwardmainModels.get(position).getId();
                        String opt=swapInwardmainModels.get(position).getOpt();
                        String date=tvDate.getText().toString();
                        String edref=edRefNo.getText().toString();

                        int chktransmode=cbModeOfTransport.isChecked() ? 1 : 0;
                        int chkdate=cbDate.isChecked() ? 1:0;

                        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        SwapinwardAcceptInterface swapinwardAcceptInterface=APIClient.getClient().create(SwapinwardAcceptInterface.class);
                        swapinwardAcceptInterface.accept(swapInwardmainModels.get(position).getId(),swapInwardmainModels.get(position).getOpt(),String.valueOf(chktransmode),TransportID,edref,String.valueOf(chkdate),date).enqueue(new Callback<SwapinwardAcceptResponse>() {
                            @Override
                            public void onResponse(Call<SwapinwardAcceptResponse> call, Response<SwapinwardAcceptResponse> response) {
                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();
                                        activity.finish();
                                        activity.overridePendingTransition(0, 0);
                                        activity.startActivity(activity.getIntent());
                                        activity.overridePendingTransition(0, 0);
                                        Toast.makeText(activity, "Accepted", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }

                                }catch (Exception e){
                                    progressDialog.dismiss();


                                }

                            }

                            @Override
                            public void onFailure(Call<SwapinwardAcceptResponse> call, Throwable t) {
                                progressDialog.dismiss();

                            }
                        });

                    }
                });

                tvDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                                String dateandtime=strDate + " " + currentTime;
                                tvDate.setText(dateandtime);

                            }

                        }, year, month, dayOfMonth);

                        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());


                        datePickerDialog.show();




                    }
                });

                CallViewSwapInward(swapInwardmainModels.get(position).getId(),swapInwardmainModels.get(position).getOpt());
                CallViewList(swapInwardmainModels.get(position).getId(),swapInwardmainModels.get(position).getOpt());
                spmodeadapter=new ArrayAdapter(activity,R.layout.spinner_item,strModelist);
                spmodeadapter.setDropDownViewResource(R.layout.spinner_dropdown);
                spModeOfTransport.setAdapter(spmodeadapter);
                spModeOfTransport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        TransportID=transportModels.get(i).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
        });


    }

    private void CallViewSwapInward(String id, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewSwapindialogInterface swapindialogInterface= APIClient.getClient().create(ViewSwapindialogInterface.class);
        swapindialogInterface.view(id,opt).enqueue(new Callback<ViewSwapindialogResponse>() {
            @Override
            public void onResponse(Call<ViewSwapindialogResponse> call, Response<ViewSwapindialogResponse> response) {
                try {
                    if (response.isSuccessful()){
                        tvserial.setText(response.body().getViewSwapindialogModels().get(0).getSno());
                        tvpartno.setText(response.body().getViewSwapindialogModels().get(0).getPart_no());
                        tvpartName.setText(response.body().getViewSwapindialogModels().get(0).getName());
                        tvReqQty.setText(response.body().getViewSwapindialogModels().get(0).getReturn_qty());
                        tvDate.setText(response.body().getReturn_date());
                        edRefNo.setText(response.body().getTransport_ref());
                        seletTransport=response.body().getTransportmode1();
                        if(response.body().getChk_transport1().trim().equals("1")){
                            cbModeOfTransport.setChecked(true);
                        }
                        if(! response.body().getReturn_date().trim().equals("")){
                            cbDate.setChecked(true);
                        }
                        notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                }catch (Exception e){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ViewSwapindialogResponse> call, Throwable t) {
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




                        // ************** Transport SPinner ************* //
                        strModelist.clear();

                        transportModels=response.body().getTransportModels();
                        for (int i=0; i<transportModels.size();i++){
                            strModelist.add(transportModels.get(i).getText());
                            //Log.e("sle", "in: "+transportModels.get(i).getId());
                            if(transportModels.get(i).getId().trim().equals(seletTransport)){
                               // Log.e("sle", "out: "+transportModels.get(i).getId());
                                spModeOfTransport.setSelection(i);
                            }
                        }
                        spmodeadapter.notifyDataSetChanged();

                        // ************** Transport SPinner End ************* //


                    }

                }catch (Exception e){
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ViewSparesInwardDetailsResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });


    }

    @Override
    public int getItemCount() {
        return swapInwardmainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvserNo,tvreturndate,tvreturnby,tvView;
        RelativeLayout rlview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvView=itemView.findViewById(R.id.tvView);
            tvserNo=itemView.findViewById(R.id.tvserNo);
            tvreturndate=itemView.findViewById(R.id.tvreturndate);
            tvreturnby=itemView.findViewById(R.id.tvreturnby);
            rlview=itemView.findViewById(R.id.rlview);


        }
    }
}
