package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.ServiceSpareReqDeleteListInterface;
import com.example.cistronuser.API.Model.SpareSendReqListModel;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.ServiceSpareReqDeleteListResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareReqListAdapter extends RecyclerView.Adapter<SpareReqListAdapter.ViewHolder> {
    
    Activity activity;
    
    public ArrayList<SpareSendReqListModel>spareSendReqListModels;

    public SpareReqListAdapter(Activity activity, ArrayList<SpareSendReqListModel> spareSendReqListModels) {
        this.activity = activity;
        this.spareSendReqListModels = spareSendReqListModels;
    }

    @NonNull
    @Override
    public SpareReqListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_send_request_list_dialog, parent, false);
        return new SpareReqListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareReqListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        
        holder.tvPartName.setText(spareSendReqListModels.get(position).getPartName());
        holder.tvCoQty.setText(spareSendReqListModels.get(position).getCoordQty());
        holder.tvPartNo.setText(spareSendReqListModels.get(position).getPartNo());
        holder.tvStoreQty.setText(spareSendReqListModels.get(position).getStoreQty());
        holder.tvUnitPrice.setText(spareSendReqListModels.get(position).getUnitPrice());
        
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSpareDelete(spareSendReqListModels.get(position).getId());
            }
        });
       


    }

    private void CallSpareDelete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
        builder.setMessage("Are you sure you want to delete Spare Request List?");
        builder.setTitle("Deleted!");
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setCancelable(false);
        builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final ProgressDialog progressDialog = new ProgressDialog(activity,R.style.ProgressBarDialog);
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                ServiceSpareReqDeleteListInterface serviceSpareReqDeleteListInterface=APIClient.getClient().create(ServiceSpareReqDeleteListInterface.class);
                serviceSpareReqDeleteListInterface.CallDelete("deleteSpareRequestTmp",id).enqueue(new Callback<ServiceSpareReqDeleteListResponse>() {
                    @Override
                    public void onResponse(Call<ServiceSpareReqDeleteListResponse> call, Response<ServiceSpareReqDeleteListResponse> response) {
                        try{
                            if (response.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(activity, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                activity.finish();
                                activity.overridePendingTransition(0, 0);
                                activity.startActivity(activity.getIntent());
                                activity.overridePendingTransition(0, 0);
                            }

                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceSpareReqDeleteListResponse> call, Throwable t) {

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


    @Override
    public int getItemCount() {
        return spareSendReqListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartName,tvPartNo,tvUnitPrice,tvStoreQty,tvCoQty;
        EditText edMyQty;
        ImageView ivDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName=itemView.findViewById(R.id.tvPartName);
            tvPartNo=itemView.findViewById(R.id.tvPartNo);
            tvUnitPrice=itemView.findViewById(R.id.tvUnitPrice);
            tvStoreQty=itemView.findViewById(R.id.tvStoreQty);
            tvCoQty=itemView.findViewById(R.id.tvCoQty);
            edMyQty=itemView.findViewById(R.id.edMyQty);
            ivDelete=itemView.findViewById(R.id.ivDelete);
        }
    }
}
