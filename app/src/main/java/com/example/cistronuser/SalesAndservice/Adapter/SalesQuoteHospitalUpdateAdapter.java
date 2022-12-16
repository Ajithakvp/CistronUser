package com.example.cistronuser.SalesAndservice.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SaleQuoteExistingUpdateInterface;
import com.example.cistronuser.API.Interface.SalesQuoteUpdateStatusInterface;
import com.example.cistronuser.API.Model.SaleQuoteExistingUpdateModel;
import com.example.cistronuser.API.Model.SalesQuoteHospitalUpdateModel;
import com.example.cistronuser.API.Model.SalesQuoteStausModel;
import com.example.cistronuser.API.Response.SaleQuoteExistingUpdateResponse;
import com.example.cistronuser.API.Response.SalesQuoteUpdateStatusResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.SalesAndservice.Activity.FinalizeNow;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesQuoteHospitalUpdateAdapter extends RecyclerView.Adapter<SalesQuoteHospitalUpdateAdapter.ViewHolder> {
    
    
    //Dialog
    RecyclerView rvExistingStatus;
    Spinner spStatus;
    TextView tvDate,tvSubmit;
    ImageView ivClose;
    EditText edRemark;
    SalesQuoteExistingAdapter salesQuoteExistingAdapter;
    ArrayList<SaleQuoteExistingUpdateModel>saleQuoteExistingUpdateModels=new ArrayList<>();

    //Status
    ArrayList<SalesQuoteStausModel>salesQuoteStausModels=new ArrayList<>();
    ArrayList<String>strStatus=new ArrayList<>();
    ArrayAdapter statusAdapter;
    String StatusID;
    
    //Dialog End
    

    
    Activity activity;
    public ArrayList<SalesQuoteHospitalUpdateModel>salesQuoteHospitalUpdateModels;

    public SalesQuoteHospitalUpdateAdapter(Activity activity, ArrayList<SalesQuoteHospitalUpdateModel> salesQuoteHospitalUpdateModels) {
        this.activity = activity;
        this.salesQuoteHospitalUpdateModels = salesQuoteHospitalUpdateModels;
    }

    @NonNull
    @Override
    public SalesQuoteHospitalUpdateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_hospital_update_list, parent, false);
        return new SalesQuoteHospitalUpdateAdapter.ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SalesQuoteHospitalUpdateAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(salesQuoteHospitalUpdateModels.get(position).getUser());
        holder.tvDate.setText(salesQuoteHospitalUpdateModels.get(position).getDate());
        holder.tvQuote.setText(salesQuoteHospitalUpdateModels.get(position).getRefNo());
        holder.tvProduct.setText(salesQuoteHospitalUpdateModels.get(position).getProduct());
        holder.tvStatus.setText(salesQuoteHospitalUpdateModels.get(position).getStatus());
        String QuotePdf=salesQuoteHospitalUpdateModels.get(position).getQuote();


        holder.rlQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(QuotePdf);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                activity.startActivity(intent);

            }
        });

        if (salesQuoteHospitalUpdateModels.get(position).getStatus().trim().equals("Update")){
            holder.tvStatus.setText(salesQuoteHospitalUpdateModels.get(position).getStatus());
            holder.tvStatus.setBackgroundColor(R.color.purple_700);
            holder.tvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.sales_quote_update_dialog_recycleview);
                    dialog.show();
                    rvExistingStatus=dialog.findViewById(R.id.rvExistingStatus);
                    edRemark=dialog.findViewById(R.id.edRemark);
                    spStatus=dialog.findViewById(R.id.spStatus);
                    tvDate=dialog.findViewById(R.id.tvDate);
                    tvSubmit=dialog.findViewById(R.id.tvSubmit);
                    ivClose=dialog.findViewById(R.id.ivClose);
                    
                    
                    CallExistingList(salesQuoteHospitalUpdateModels.get(position).getQuoteId());
                    salesQuoteExistingAdapter=new SalesQuoteExistingAdapter(activity,saleQuoteExistingUpdateModels);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    rvExistingStatus.setAdapter(salesQuoteExistingAdapter);
                    rvExistingStatus.setLayoutManager(linearLayoutManager);



                    //Status
                    strStatus.clear();
                    salesQuoteStausModels.clear();
                    SalesQuoteStausModel Requirement=new SalesQuoteStausModel();
                    Requirement.setStatus("No Requirement");
                    salesQuoteStausModels.add(Requirement);

                    SalesQuoteStausModel Recev=new SalesQuoteStausModel();
                    Recev.setStatus("Received PO");
                    salesQuoteStausModels.add(Recev);


                    SalesQuoteStausModel Final=new SalesQuoteStausModel();
                    Final.setStatus("Finalize now");
                    salesQuoteStausModels.add(Final);


                    for (int i=0;i<salesQuoteStausModels.size();i++){
                        strStatus.add(salesQuoteStausModels.get(i).getStatus());
                    }




                    statusAdapter=new ArrayAdapter<>(activity,R.layout.spinner_item,strStatus);
                    statusAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
                    spStatus.setAdapter(statusAdapter);
                    spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (salesQuoteStausModels.get(position).getStatus().trim().equals("Finalize now")){
                                Toast.makeText(activity,salesQuoteStausModels.get(position).getStatus(), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(activity, FinalizeNow.class);
                                activity.startActivity(intent);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });



                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
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

                            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);


                            datePickerDialog.show();

                        }
                    });

                    tvSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CallUpdateStatus(salesQuoteHospitalUpdateModels.get(position).getQuoteId(),dialog);
                        }
                    });

                }
            });
        }else {
            holder.tvStatus.setText(salesQuoteHospitalUpdateModels.get(position).getStatus());
            holder.tvStatus.setBackgroundColor(R.color.Radio);
        }

    }

    private void CallUpdateStatus(String quoteId, Dialog dialog) {
        StatusID=spStatus.getSelectedItem().toString();
        SalesQuoteUpdateStatusInterface salesQuoteUpdateStatusInterface=APIClient.getClient().create(SalesQuoteUpdateStatusInterface.class);
        salesQuoteUpdateStatusInterface.callUpdatestatus("updateStatus",quoteId,tvDate.getText().toString(),StatusID,edRemark.getText().toString(), PreferenceManager.getEmpID(activity)).enqueue(new Callback<SalesQuoteUpdateStatusResponse>() {
            @Override
            public void onResponse(Call<SalesQuoteUpdateStatusResponse> call, Response<SalesQuoteUpdateStatusResponse> response) {

                try {
                    if (response.isSuccessful()){
                        Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<SalesQuoteUpdateStatusResponse> call, Throwable t) {

            }
        });
    }

    private void CallExistingList(String quoteId) {
        SaleQuoteExistingUpdateInterface saleQuoteExistingUpdateInterface= APIClient.getClient().create(SaleQuoteExistingUpdateInterface.class);
        saleQuoteExistingUpdateInterface.CallExistingUpdateList("getQuoteUpdates",quoteId).enqueue(new Callback<SaleQuoteExistingUpdateResponse>() {
            @Override
            public void onResponse(Call<SaleQuoteExistingUpdateResponse> call, Response<SaleQuoteExistingUpdateResponse> response) {
                try {
                    if (response.isSuccessful()){
                        salesQuoteExistingAdapter.saleQuoteExistingUpdateModels=response.body().getSaleQuoteExistingUpdateModels();
                        salesQuoteExistingAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<SaleQuoteExistingUpdateResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return salesQuoteHospitalUpdateModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDate,tvQuote,tvProduct,tvStatus;
        RelativeLayout rlQuote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvQuote=itemView.findViewById(R.id.tvQuote);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            rlQuote=itemView.findViewById(R.id.rlQuote);

        }
    }
}
