package com.example.cistronuser.SalesAndservice.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.SaleQuoteExistingUpdateInterface;
import com.example.cistronuser.API.Model.SaleQuoteExistingUpdateModel;
import com.example.cistronuser.API.Model.SalesQuoteHospitalUpdateModel;
import com.example.cistronuser.API.Response.SaleQuoteExistingUpdateResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

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
                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    
                    
                    
                }
            });
        }else {
            holder.tvStatus.setText(salesQuoteHospitalUpdateModels.get(position).getStatus());
            holder.tvStatus.setBackgroundColor(R.color.Radio);
        }

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
