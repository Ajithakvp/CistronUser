package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CreateSpareSendReqInterface;
import com.example.cistronuser.API.Model.MyStockListSEModel;
import com.example.cistronuser.API.Response.CreateSpareSendReqResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSpareReqListAdapter extends RecyclerView.Adapter<CreateSpareReqListAdapter.ViewHolder> {
    public ArrayList<MyStockListSEModel> myStockListSEModels;
    Activity activity;
    //Search
    ArrayList<MyStockListSEModel> tempMystockList = new ArrayList<>();
    ArrayList<String> strMyStockList = new ArrayList<>();

    public CreateSpareReqListAdapter(Activity activity, ArrayList<MyStockListSEModel> myStockListSEModels) {
        this.activity = activity;
        this.myStockListSEModels = myStockListSEModels;
    }

    @NonNull
    @Override
    public CreateSpareReqListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_spare_req_list_adapter, parent, false);
        return new CreateSpareReqListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateSpareReqListAdapter.ViewHolder holder, int position) {
        holder.tvpartName.setText(myStockListSEModels.get(position).getName());
        holder.tvMyQty.setText(myStockListSEModels.get(position).getEng_qty());
        holder.tvPartId.setText(myStockListSEModels.get(position).getId());
        holder.tvPartNo.setText(myStockListSEModels.get(position).getPart_no());
        holder.tvUnitPrice.setText(myStockListSEModels.get(position).getPrice());
        holder.tvStoreQty.setText(myStockListSEModels.get(position).getStore_qty());
        holder.tvCoOrdinatqty.setText(myStockListSEModels.get(position).getCoord_qty());
        String Spareid = myStockListSEModels.get(position).getId();
        String opt = myStockListSEModels.get(position).getLabel();
        String series = myStockListSEModels.get(position).getSeries();


        if (myStockListSEModels.get(position).getLabel().trim().equals("cis")) {
            holder.tvPartcisIdName.setVisibility(View.VISIBLE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);

        } else if (myStockListSEModels.get(position).getLabel().trim().equals("cspl")) {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.VISIBLE);
        } else {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.VISIBLE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setText(myStockListSEModels.get(position).getLabel());
        }


        holder.tvSendReqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                CreateSpareSendReqInterface createSpareSendReqInterface = APIClient.getClient().create(CreateSpareSendReqInterface.class);
                createSpareSendReqInterface.CalSend("addToSpareRequestsQueue", PreferenceManager.getEmpID(activity), Spareid, opt, series).enqueue(new Callback<CreateSpareSendReqResponse>() {
                    @Override
                    public void onResponse(Call<CreateSpareSendReqResponse> call, Response<CreateSpareSendReqResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                if (response.body().getStatus().trim().equals("1")) {
                                    Toast.makeText(activity, "Send", Toast.LENGTH_SHORT).show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                                    builder.setMessage(response.body().getMessage());
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            }

                        } catch (Exception e) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateSpareSendReqResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });


    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        myStockListSEModels.clear();
        if (charText.length() == 0) {
            myStockListSEModels.addAll(tempMystockList);
        } else {
            for (String searchedValue : strMyStockList) {

                if (searchedValue.toLowerCase().contains(charText)) {
                    for (int i = 0; i < tempMystockList.size(); i++) {

                        if (tempMystockList.get(i).getName().equalsIgnoreCase(searchedValue)) {
                            myStockListSEModels.add(tempMystockList.get(i));
                            break;
                        }
                    }

                }
            }


        }
        notifyDataSetChanged();
    }

    public void searchAdapter(ArrayList<MyStockListSEModel> data) {
        tempMystockList.addAll(data);
        strMyStockList.clear();
        for (int i = 0; i < tempMystockList.size(); i++) {
            strMyStockList.add(tempMystockList.get(i).getName());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return myStockListSEModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartId, tvPartcisIdName, tvPartotherIdName, tvPartcsplIdName, tvPartNo, tvUnitPrice, tvMyQty, tvpartName, tvStoreQty, tvCoOrdinatqty, tvSendReqbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartId = itemView.findViewById(R.id.tvPartId);
            tvPartcisIdName = itemView.findViewById(R.id.tvPartcisIdName);
            tvPartcsplIdName = itemView.findViewById(R.id.tvPartcsplIdName);
            tvPartotherIdName = itemView.findViewById(R.id.tvPartotherIdName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
            tvMyQty = itemView.findViewById(R.id.tvMyQty);
            tvpartName = itemView.findViewById(R.id.tvpartName);
            tvStoreQty = itemView.findViewById(R.id.tvStoreQty);
            tvCoOrdinatqty = itemView.findViewById(R.id.tvCoOrdinatqty);
            tvSendReqbtn = itemView.findViewById(R.id.tvSendReqbtn);
        }
    }
}
