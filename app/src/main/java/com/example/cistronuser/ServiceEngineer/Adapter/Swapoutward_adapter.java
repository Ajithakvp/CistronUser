package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ViewdialogSwapoutInterface;
import com.example.cistronuser.API.Model.SwapOutwardMainModel;
import com.example.cistronuser.API.Response.ViewdialogSwapoutResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Swapoutward_adapter extends RecyclerView.Adapter<Swapoutward_adapter.ViewHolder> {

    public ArrayList<SwapOutwardMainModel>swapOutwardMainModels;
    private Activity activity;

    TextView tvserial,tvpartno,tvpartName,tvReqQty;
    ImageView tvdialogclose;

    public Swapoutward_adapter(ArrayList<SwapOutwardMainModel> swapOutwardMainModels, Activity activity) {
        this.swapOutwardMainModels = swapOutwardMainModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Swapoutward_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.swap_outward_adapter,parent,false);
        return new Swapoutward_adapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull Swapoutward_adapter.ViewHolder holder, int position) {

        holder.tvserNo.setText(swapOutwardMainModels.get(position).getReq_no());
        holder.tvreturndate.setText(swapOutwardMainModels.get(position).getReturn_date());
        holder.tvreturnby.setText(swapOutwardMainModels.get(position).getSecondeng());

        holder.rlview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.view_swap_outward_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                tvserial=dialog.findViewById(R.id.tvserial);
                tvpartno=dialog.findViewById(R.id.tvpartno);
                tvpartName=dialog.findViewById(R.id.tvpartName);
                tvReqQty=dialog.findViewById(R.id.tvReqQty);
                tvdialogclose=dialog.findViewById(R.id.tvdialogclose);

                callviewswapout(swapOutwardMainModels.get(position).getId(),swapOutwardMainModels.get(position).getOpt());
                tvdialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



            }
        });


    }

    private void callviewswapout(String id, String opt) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ViewdialogSwapoutInterface viewdialogSwapoutInterface= APIClient.getClient().create(ViewdialogSwapoutInterface.class);
        viewdialogSwapoutInterface.view(id,opt).enqueue(new Callback<ViewdialogSwapoutResponse>() {
            @Override
            public void onResponse(Call<ViewdialogSwapoutResponse> call, Response<ViewdialogSwapoutResponse> response) {
                try {
                    if (response.isSuccessful()){
                        tvpartName.setText(response.body().getViewdialogSwapoutModels().get(0).getName());
                        tvserial.setText(response.body().getViewdialogSwapoutModels().get(0).getSno());
                        tvReqQty.setText(response.body().getViewdialogSwapoutModels().get(0).getReq_qty1());
                        tvpartno.setText(response.body().getViewdialogSwapoutModels().get(0).getPart_no());
                        progressDialog.dismiss();

                    }

                }catch (Exception e){
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ViewdialogSwapoutResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public int getItemCount() {
        return swapOutwardMainModels.size();
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
