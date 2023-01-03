package com.example.cistronuser.SalesAndservice.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CompOffDeletedReqinterface;
import com.example.cistronuser.API.Interface.VisitEntryDeleteInterface;
import com.example.cistronuser.API.Model.VisitEntryListModel;
import com.example.cistronuser.API.Response.CompOffDeleteReqResponse;
import com.example.cistronuser.API.Response.VisitEntryDeleteRessponse;
import com.example.cistronuser.R;
import com.example.cistronuser.WaitingforApprovel.Adapter.CompOffReqAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitEntryAdapter extends RecyclerView.Adapter<VisitEntryAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<VisitEntryListModel>visitEntryListModels;

    public VisitEntryAdapter(Activity activity, ArrayList<VisitEntryListModel> visitEntryListModels) {
        this.activity = activity;
        this.visitEntryListModels = visitEntryListModels;
    }

    @NonNull
    @Override
    public VisitEntryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visit_entry_list, parent, false);
        return new VisitEntryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitEntryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvChefDoctor.setText(visitEntryListModels.get(position).getChiefDr());
        holder.tvHospital.setText(visitEntryListModels.get(position).getHospital());
        holder.tvDate.setText(visitEntryListModels.get(position).getEntry());
        holder.tvproduct.setText(visitEntryListModels.get(position).getProduct());
        if (visitEntryListModels.get(position).getComment().trim().equals("")){
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCommentTag.setVisibility(View.GONE);
        }else {
            holder.tvComment.setVisibility(View.VISIBLE);
            holder.tvCommentTag.setVisibility(View.VISIBLE);
            holder.tvComment.setText(visitEntryListModels.get(position).getComment());

        }

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Deleted this Visit Entry?");
                builder.setTitle("Deleted!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity,R.style.ProgressBarDialog);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        VisitEntryDeleteInterface visitEntryDeleteInterface=APIClient.getClient().create(VisitEntryDeleteInterface.class);
                        visitEntryDeleteInterface.CallVisitEntryDelete("deleteVisitEntry",visitEntryListModels.get(position).getId()).enqueue(new Callback<VisitEntryDeleteRessponse>() {
                            @Override
                            public void onResponse(Call<VisitEntryDeleteRessponse> call, Response<VisitEntryDeleteRessponse> response) {
                                try {
                                    if (response.isSuccessful()) {
                                        progressDialog.dismiss();

                                        if (response.body().getStatus().trim().equals("1")) {
                                            Toast.makeText(activity, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        } else {

                                            AlertDialog.Builder msg = new AlertDialog.Builder(activity);
                                            msg.setMessage(response.body().getMessage());
                                            msg.setTitle(" Failed !");
                                            msg.setIcon(R.drawable.oops);
                                            AlertDialog alertDialog = msg.create();
                                            alertDialog.show();

                                        }
                                    }


                                }catch (Exception e){

                                }


                            }

                            @Override
                            public void onFailure(Call<VisitEntryDeleteRessponse> call, Throwable t) {

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
        return visitEntryListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChefDoctor,tvHospital,tvDate,tvproduct,tvCommentTag,tvComment;
        ImageView ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvComment=itemView.findViewById(R.id.tvComment);
            tvCommentTag=itemView.findViewById(R.id.tvCommentTag);
            tvproduct=itemView.findViewById(R.id.tvproduct);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvHospital=itemView.findViewById(R.id.tvHospital);
            tvChefDoctor=itemView.findViewById(R.id.tvChefDoctor);
            ivDelete=itemView.findViewById(R.id.ivDelete);


        }
    }
}
