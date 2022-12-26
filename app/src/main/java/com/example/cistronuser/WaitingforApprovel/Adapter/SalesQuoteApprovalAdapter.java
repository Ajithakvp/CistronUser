package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CompOffDeletedReqinterface;
import com.example.cistronuser.API.Interface.SaleQuoteApprovalDeleteInterface;
import com.example.cistronuser.API.Model.SalesQuoteApprovalListModel;
import com.example.cistronuser.API.Response.CompOffDeleteReqResponse;
import com.example.cistronuser.API.Response.SaleQuoteApprovalDeleteResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Activity.SalesQuoteApproval;
import com.example.cistronuser.WaitingforApprovel.Activity.ViewSalesQuoteApprovalList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesQuoteApprovalAdapter extends RecyclerView.Adapter<SalesQuoteApprovalAdapter.ViewHolder> {

    public ArrayList<SalesQuoteApprovalListModel> salesQuoteApprovalListModels;
    Activity activity;

    public SalesQuoteApprovalAdapter(Activity activity, ArrayList<SalesQuoteApprovalListModel> salesQuoteApprovalListModels) {
        this.activity = activity;
        this.salesQuoteApprovalListModels = salesQuoteApprovalListModels;
    }

    @NonNull
    @Override
    public SalesQuoteApprovalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_quote_approval_list, parent, false);
        return new SalesQuoteApprovalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesQuoteApprovalAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(salesQuoteApprovalListModels.get(position).getUser());
        holder.tvDistrict.setText(salesQuoteApprovalListModels.get(position).getDistrict());
        holder.tvProduct.setText(salesQuoteApprovalListModels.get(position).getProduct());
        holder.tvHospital.setText(salesQuoteApprovalListModels.get(position).getHospital());
        String QuotePdf = salesQuoteApprovalListModels.get(position).getQuotePdf();

        holder.ivPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(QuotePdf);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
            }
        });

        holder.rlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewSalesQuoteApprovalList.class);
                intent.putExtra("OAReqID",salesQuoteApprovalListModels.get(position).getOa_id());
                activity.startActivity(intent);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Deleted this  Sales Quote Approval?");
                builder.setTitle("Deleted!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        SaleQuoteApprovalDeleteInterface saleQuoteApprovalDeleteInterface=APIClient.getClient().create(SaleQuoteApprovalDeleteInterface.class);
                        saleQuoteApprovalDeleteInterface.CallDelete("deleteOrderCall",salesQuoteApprovalListModels.get(position).getOa_id()).enqueue(new Callback<SaleQuoteApprovalDeleteResponse>() {
                            @Override
                            public void onResponse(Call<SaleQuoteApprovalDeleteResponse> call, Response<SaleQuoteApprovalDeleteResponse> response) {
                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        if (response.body().getError().trim().equals("1")){
                                            AlertDialog.Builder msg=new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                                            msg.setMessage(response.body().getMessage());
                                            msg.setTitle(" Failed !");
                                            msg.setIcon(R.drawable.oops);
                                            AlertDialog alertDialog = msg.create();
                                            alertDialog.show();
                                        }else {

                                            Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);


                                        }


                                    }

                                }catch (Exception e){

                                }
                            }

                            @Override
                            public void onFailure(Call<SaleQuoteApprovalDeleteResponse> call, Throwable t) {

                            }
                        });



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


    }

    @Override
    public int getItemCount() {
        return salesQuoteApprovalListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvHospital, tvDistrict, tvProduct;
        ImageView ivPdf,ivDelete;
        RelativeLayout rlView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvHospital = itemView.findViewById(R.id.tvHospital);
            tvDistrict = itemView.findViewById(R.id.tvDistrict);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            ivPdf = itemView.findViewById(R.id.ivPdf);
            rlView = itemView.findViewById(R.id.rlView);
            ivDelete=itemView.findViewById(R.id.ivDelete);

        }
    }
}
