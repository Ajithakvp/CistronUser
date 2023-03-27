package com.example.cistronuser.ServiceEngineer.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DeleteSendSpareReqInterface;
import com.example.cistronuser.API.Model.CreateSparesendreqProposeViewModel;
import com.example.cistronuser.API.Model.CreateSparesendreqViewModel;
import com.example.cistronuser.API.Response.DeleteSendSpareReqResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSpareSendReqListAdapter extends RecyclerView.Adapter<CreateSpareSendReqListAdapter.ViewHolder> {

    public ArrayList<CreateSparesendreqViewModel> createSparesendreqViewModels;
    Activity activity;
    ArrayList<String> strPurpose = new ArrayList<>();
    ArrayAdapter adapter;

    public CreateSpareSendReqListAdapter(ArrayList<CreateSparesendreqViewModel> createSparesendreqViewModels, Activity activity) {
        this.createSparesendreqViewModels = createSparesendreqViewModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CreateSpareSendReqListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_spare_send_req_list_adapter, parent, false);
        return new CreateSpareSendReqListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateSpareSendReqListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvpartName.setText(createSparesendreqViewModels.get(position).getName());
        holder.tvPartNo.setText(createSparesendreqViewModels.get(position).getPart_no());
        holder.tvCoOrdinatqty.setText(createSparesendreqViewModels.get(position).getCoord_qty());
        holder.tvStoreQty.setText(createSparesendreqViewModels.get(position).getQty());
        holder.tvUnitPrice.setText(createSparesendreqViewModels.get(position).getPrice());

        if (createSparesendreqViewModels.get(position).getOpt().trim().equals("cis")) {
            holder.tvPartcisIdName.setVisibility(View.VISIBLE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);

        } else if (createSparesendreqViewModels.get(position).getOpt().trim().equals("cspl")) {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.GONE);
            holder.tvPartcsplIdName.setVisibility(View.VISIBLE);
        } else {
            holder.tvPartcisIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setVisibility(View.VISIBLE);
            holder.tvPartcsplIdName.setVisibility(View.GONE);
            holder.tvPartotherIdName.setText(createSparesendreqViewModels.get(position).getOpt());

        }


        try {
            strPurpose.clear();
            for (int i = 0; i < createSparesendreqViewModels.get(position).getCreateSparesendreqProposeViewModels().size(); i++) {
                strPurpose.add(createSparesendreqViewModels.get(position).getCreateSparesendreqProposeViewModels().get(i).getText());
            }
        } catch (Exception e) {

        }
        adapter = new ArrayAdapter(activity, R.layout.spinner_white_item, strPurpose);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        holder.spPurpose.setAdapter(adapter);


        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                DeleteSendSpareReqInterface sendSpareReqInterface = APIClient.getClient().create(DeleteSendSpareReqInterface.class);
                sendSpareReqInterface.CallDelete("deleteFromSpareRequestsQueue", createSparesendreqViewModels.get(position).getReqId()).enqueue(new Callback<DeleteSendSpareReqResponse>() {
                    @Override
                    public void onResponse(Call<DeleteSendSpareReqResponse> call, Response<DeleteSendSpareReqResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(activity, "Remove", Toast.LENGTH_SHORT).show();
                                activity.overridePendingTransition(0, 0);
                                activity.startActivity(activity.getIntent());
                                activity.overridePendingTransition(0, 0);
                                activity.finish();
                            }

                        } catch (Exception e) {
                            progressDialog.dismiss();

                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteSendSpareReqResponse> call, Throwable t) {
                        progressDialog.dismiss();

                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return createSparesendreqViewModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvpartName, tvPartNo, tvUnitPrice, tvCoOrdinatqty, tvStoreQty, tvPartcisIdName, tvPartotherIdName, tvPartcsplIdName;
        EditText edQtyReq;
        Spinner spPurpose;
        ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartcisIdName = itemView.findViewById(R.id.tvPartcisIdName);
            tvPartcsplIdName = itemView.findViewById(R.id.tvPartcsplIdName);
            tvPartotherIdName = itemView.findViewById(R.id.tvPartotherIdName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice = itemView.findViewById(R.id.tvUnitPrice);
            edQtyReq = itemView.findViewById(R.id.edQtyReq);
            tvpartName = itemView.findViewById(R.id.tvpartName);
            tvStoreQty = itemView.findViewById(R.id.tvStoreQty);
            tvCoOrdinatqty = itemView.findViewById(R.id.tvCoOrdinatqty);
            spPurpose = itemView.findViewById(R.id.spPurpose);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
