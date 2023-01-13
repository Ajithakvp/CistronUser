package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ServiceSpareSendReqbtnInterface;
import com.example.cistronuser.API.Model.ServiceSpareListModel;
import com.example.cistronuser.API.Response.ServiceSpareSendReqbtnResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareSendReqListAdapter extends RecyclerView.Adapter<SpareSendReqListAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<ServiceSpareListModel>serviceSpareListModels;

    //Search
    ArrayList<ServiceSpareListModel> tempserviceList = new ArrayList<>();
    ArrayList<String> strSpareList = new ArrayList<>();

    public SpareSendReqListAdapter(Activity activity, ArrayList<ServiceSpareListModel> serviceSpareListModels) {
        this.activity = activity;
        this.serviceSpareListModels = serviceSpareListModels;
    }

    @NonNull
    @Override
    public SpareSendReqListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_send_request_dialog, parent, false);
        return new SpareSendReqListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareSendReqListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvPartName.setText(serviceSpareListModels.get(position).getPartName());
        holder.tvPartNo.setText(serviceSpareListModels.get(position).getPartNo());
        holder.tvUnitPrice.setText(serviceSpareListModels.get(position).getUnitPrice());
        holder.tvStoreQty.setText(serviceSpareListModels.get(position).getStoreQty());
        holder.tvCoQty.setText(serviceSpareListModels.get(position).getCoordQty());
        holder.tvMyQty.setText(serviceSpareListModels.get(position).getEngQty());

        holder.tvSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSendReq(serviceSpareListModels.get(position).getP(),serviceSpareListModels.get(position).getPcat(),serviceSpareListModels.get(position).getS());
            }
        });



    }

    private void CallSendReq(String p, String pcat, String s) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Sending Request...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceSpareSendReqbtnInterface serviceSpareSendReqbtnInterface= APIClient.getClient().create(ServiceSpareSendReqbtnInterface.class);
        serviceSpareSendReqbtnInterface.callSendReqSubmit("addSpareRequestTmp", PreferenceManager.getEmpID(activity),p,pcat,s).enqueue(new Callback<ServiceSpareSendReqbtnResponse>() {
            @Override
            public void onResponse(Call<ServiceSpareSendReqbtnResponse> call, Response<ServiceSpareSendReqbtnResponse> response) {
                try{
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(activity, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<ServiceSpareSendReqbtnResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceSpareListModels.size();
    }
    public void filter(String charText) {
        charText = charText.toLowerCase();
        serviceSpareListModels.clear();
        if (charText.length() == 0) {
            serviceSpareListModels.addAll(tempserviceList);
        } else {
            for (String searchedValue : strSpareList) {

                if (searchedValue.toLowerCase().contains(charText)) {
                    for (int i = 0; i < tempserviceList.size(); i++) {

                        if (tempserviceList.get(i).getPartName().equalsIgnoreCase(searchedValue)) {
                            serviceSpareListModels.add(tempserviceList.get(i));
                            break;
                        }
                    }

                }
            }


        }
        notifyDataSetChanged();
    }

    public void searchAdapter(ArrayList<ServiceSpareListModel> data) {
        tempserviceList.addAll(data);
        strSpareList.clear();
        for (int i = 0; i < tempserviceList.size(); i++) {
            strSpareList.add(tempserviceList.get(i).getPartName());
        }
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartName,tvPartNo,tvUnitPrice,tvStoreQty,tvCoQty,tvMyQty,tvSendReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice=itemView.findViewById(R.id.tvUnitPrice);
            tvStoreQty=itemView.findViewById(R.id.tvStoreQty);
            tvCoQty=itemView.findViewById(R.id.tvCoQty);
            tvMyQty=itemView.findViewById(R.id.tvMyQty);
            tvSendReq=itemView.findViewById(R.id.tvSendReq);

        }
    }
}
