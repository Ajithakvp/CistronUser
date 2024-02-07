package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.FeedbackFiledownloadInterface;
import com.example.cistronuser.API.Model.FeedbackassignModel;
import com.example.cistronuser.API.Response.FeedbackFiledownloadResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Activity.Fileupload_fb;
import com.example.cistronuser.ServiceEngineer.Activity.Pendingcall_Feedback;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingcallFb_adapter extends RecyclerView.Adapter<PendingcallFb_adapter.ViewHolder> {

    public  Activity activity;
    public ArrayList<FeedbackassignModel>feedbackassignModels;
    String filedownload;

    //FileUpload
    ImageView ivBack;
    RelativeLayout rlUpload;
    TextView tvfilename,tvfileSubmit;

    public PendingcallFb_adapter(Activity activity, ArrayList<FeedbackassignModel> feedbackassignModels) {
        this.activity = activity;
        this.feedbackassignModels = feedbackassignModels;
    }


    @NonNull
    @Override
    public PendingcallFb_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pms_adapter_list, parent, false);
        return new PendingcallFb_adapter.ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull PendingcallFb_adapter.ViewHolder holder, int position) {
        String hos=feedbackassignModels.get(position).getHospitalDataModel().getHospital()+"\n"+feedbackassignModels.get(position).getHospitalDataModel().getAddress();
        holder.tvHospital.setText(hos);
        holder.tvdate.setText(feedbackassignModels.get(position).getAssign_date());
        holder.tvempid.setText(feedbackassignModels.get(position).getAssign_emp());
        holder.tvProduct.setText(feedbackassignModels.get(position).getPro_name());

        if(feedbackassignModels.get(position).getPserial_no().trim().equals("") || feedbackassignModels.get(position).getPserial_no().trim().equals(null))
            holder.tvserial.setText("No Serial Number");
        else
            holder.tvserial.setText(feedbackassignModels.get(position).getPserial_no());




        holder.rldownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                FeedbackFiledownloadInterface feedbackFiledownloadInterface= APIClient.getClient().create(FeedbackFiledownloadInterface.class);
                feedbackFiledownloadInterface.Callres("filedownload", PreferenceManager.getEmpID(activity),feedbackassignModels.get(position).getCid(),feedbackassignModels.get(position).getProduct_id()).enqueue(new Callback<FeedbackFiledownloadResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackFiledownloadResponse> call, Response<FeedbackFiledownloadResponse> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            filedownload=response.body().getResponse();

                            if(response.body().getResponse().trim().equals("") ||  response.body().getResponse().trim().equals("0")){
                                Toast.makeText(activity, "Please Contact Your Admin", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else if (response.body().getResponse().trim().equals("2")) {
                                Toast.makeText(activity, "Pdf Not Generate Please Contact Your Admin", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            } else {
                                progressDialog.dismiss();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(filedownload));
                                activity.startActivity(intent);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<FeedbackFiledownloadResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });


            }
        });

        holder.rluploadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



           Intent intent=new Intent(activity, Fileupload_fb.class);
           intent.putExtra("hid",feedbackassignModels.get(position).getHid());
                intent.putExtra("pid",feedbackassignModels.get(position).getProduct_id());
                intent.putExtra("cid",feedbackassignModels.get(position).getCid());
                intent.putExtra("serlno",feedbackassignModels.get(position).getPserial_no());
                intent.putExtra("sid",feedbackassignModels.get(position).getSid());
           activity.startActivity(intent);
            }


        });


    }


    @Override
    public int getItemCount() {
        return feedbackassignModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospital,tvdate,tvserial,tvProduct,tvempid;
        RelativeLayout rldownload,rluploadpdf;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospital=itemView.findViewById(R.id.tvHospital);
            tvdate=itemView.findViewById(R.id.tvdate);
            tvserial=itemView.findViewById(R.id.tvserial);
            tvProduct=itemView.findViewById(R.id.tvProduct);
            tvempid=itemView.findViewById(R.id.tvempid);
            rldownload=itemView.findViewById(R.id.rldownload);
            rluploadpdf=itemView.findViewById(R.id.rluploadpdf);


        }



    }




}
