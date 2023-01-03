package com.example.cistronuser.WaitingforApprovel.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
import com.example.cistronuser.API.Interface.ApprovalLeaveReqInterface;
import com.example.cistronuser.API.Interface.DeletedAPIInterface;
import com.example.cistronuser.API.Interface.LeaveApprovalDeleteInterface;
import com.example.cistronuser.API.Interface.LeaveApprovalRejectedInterface;
import com.example.cistronuser.API.Interface.LeaveApprovelInterface;
import com.example.cistronuser.API.Model.LeaveApprovelModel;
import com.example.cistronuser.API.Response.ApprovalleaveRequestResponse;
import com.example.cistronuser.API.Response.DeleteResponse;
import com.example.cistronuser.API.Response.LeaveApprovalDeletedResponse;
import com.example.cistronuser.API.Response.LeaveApprovalRejectedResponse;
import com.example.cistronuser.API.Response.leaveApprovelResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApprovalAdapter extends RecyclerView.Adapter<LeaveApprovalAdapter.ViewHolder> {

    public ArrayList<LeaveApprovelModel> leaveApprovelModels;
    Activity activity;
    String baseUrl;


    public LeaveApprovalAdapter(ArrayList<LeaveApprovelModel> leaveApprovelModels, Activity activity) {
        this.leaveApprovelModels = leaveApprovelModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LeaveApprovalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_reqesut_approvel, parent, false);
        return new LeaveApprovalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveApprovalAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvName.setText(leaveApprovelModels.get(position).getEmpname());
        holder.tvEmpId.setText(leaveApprovelModels.get(position).getEmpid());
        holder.tvLeaveDate.setText(leaveApprovelModels.get(position).getLeavedate());
//        holder.tvCL.setText(leaveApprovelModels.get(position).getCl());
//        holder.tvPL.setText(leaveApprovelModels.get(position).getPl());
//        holder.tvML.setText(leaveApprovelModels.get(position).getMl());
//        holder.tvProb.setText(leaveApprovelModels.get(position).getProbl());
//        holder.tvCompOff.setText(leaveApprovelModels.get(position).getCompoff());
        holder.tvReason.setText(leaveApprovelModels.get(position).getReason());
        holder.tvLeaveType.setText(leaveApprovelModels.get(position).getLeavetype());
        holder.tvAppliedDate.setText(leaveApprovelModels.get(position).getApplied_date());
        holder.tvAppliedTime.setText(leaveApprovelModels.get(position).getApplied_time());
        holder.tvApproved.setText(leaveApprovelModels.get(position).getBtn_txt());
        holder.tvFDHD.setText(leaveApprovelModels.get(position).getFdhd());
        holder.tvcisorsuki.setText(leaveApprovelModels.get(position).getOpt_user());


        String Attachfile = leaveApprovelModels.get(position).getMedattach();





        LeaveApprovelInterface leaveApprovelInterface = APIClient.getClient().create(LeaveApprovelInterface.class);
        leaveApprovelInterface.callLeaveApprovel("leaveForApproval").enqueue(new Callback<leaveApprovelResponse>() {
            @Override
            public void onResponse(Call<leaveApprovelResponse> call, Response<leaveApprovelResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        baseUrl=response.body().getAttchBaseUrl();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<leaveApprovelResponse> call, Throwable t) {

            }
        });


        holder.tvApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                ApprovalLeaveReqInterface approvalLeaveReqInterface=APIClient.getClient().create(ApprovalLeaveReqInterface.class);
                approvalLeaveReqInterface.CallApproval("approveLeaveRequest",leaveApprovelModels.get(position).getLeaveid(),leaveApprovelModels.get(position).getLop(),leaveApprovelModels.get(position).getCompoff(),leaveApprovelModels.get(position).getEmpid()).enqueue(new Callback<ApprovalleaveRequestResponse>() {
                    @Override
                    public void onResponse(Call<ApprovalleaveRequestResponse> call, Response<ApprovalleaveRequestResponse> response) {
                        try {
                            if (response.isSuccessful()){
                                progressDialog.dismiss();
                                if (response.body().getSuccess().trim().equals("1")){
                                    Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                    activity.finish();
                                    activity.overridePendingTransition(0, 0);
                                    activity.startActivity(activity.getIntent());
                                    activity.overridePendingTransition(0, 0);
                                }else {
                                    AlertDialog.Builder msg=new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
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
                    public void onFailure(Call<ApprovalleaveRequestResponse> call, Throwable t) {

                    }
                });
            }
        });

        holder.tvRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to Reject this leave request?");
                builder.setTitle("Rejected!");
                builder.setIcon(R.drawable.ic_baseline_dangerous_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        LeaveApprovalRejectedInterface leaveApprovalRejectedInterface=APIClient.getClient().create(LeaveApprovalRejectedInterface.class);
                        leaveApprovalRejectedInterface.CallRejected("rejectLeaveRequest",leaveApprovelModels.get(position).getLeaveid(),leaveApprovelModels.get(position).getEmpid()).enqueue(new Callback<LeaveApprovalRejectedResponse>() {
                            @SuppressLint("ResourceType")
                            @Override
                            public void onResponse(Call<LeaveApprovalRejectedResponse> call, Response<LeaveApprovalRejectedResponse> response) {

                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        if (response.body().getSuccess().trim().equals("1")){
                                            Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        }else {

                                            AlertDialog.Builder msg=new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
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
                            public void onFailure(Call<LeaveApprovalRejectedResponse> call, Throwable t) {

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


        holder.tvDeleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
                builder.setMessage("Are you sure you want to delete this leave request?");
                builder.setTitle("Deleted!");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setCancelable(false);
                builder.setPositiveButton("yes", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        LeaveApprovalDeleteInterface leaveApprovalDeleteInterface=APIClient.getClient().create(LeaveApprovalDeleteInterface.class);
                        leaveApprovalDeleteInterface.CallDeleted("deleteLeaveRequestByAdmin",leaveApprovelModels.get(position).getLeaveid(),leaveApprovelModels.get(position).getEmpid()).enqueue(new Callback<LeaveApprovalDeletedResponse>() {
                            @Override
                            public void onResponse(Call<LeaveApprovalDeletedResponse> call, Response<LeaveApprovalDeletedResponse> response) {
                                try {
                                    if (response.isSuccessful()){
                                        progressDialog.dismiss();

                                        if (response.body().getSuccess().trim().equals("1")){
                                            Toast.makeText(activity, response.body().getMessage() ,Toast.LENGTH_SHORT).show();
                                            activity.finish();
                                            activity.overridePendingTransition(0, 0);
                                            activity.startActivity(activity.getIntent());
                                            activity.overridePendingTransition(0, 0);
                                        }else {

                                            AlertDialog.Builder msg=new AlertDialog.Builder(activity,R.style.AlertDialogCustom);
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
                            public void onFailure(Call<LeaveApprovalDeletedResponse> call, Throwable t) {

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

        holder.ivAttch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Callfile(baseUrl+Attachfile);
                Log.e(TAG, "onClick: "+baseUrl+Attachfile );
            }
        });


//        if (leaveApprovelModels.get(position).getEmptype().trim().equals("3")) {
//            holder.tvCL.setVisibility(View.GONE);
//            holder.tvML.setVisibility(View.GONE);
//            holder.tvPL.setVisibility(View.GONE);
//            holder.tvClTag.setVisibility(View.GONE);
//            holder.tvMLTag.setVisibility(View.GONE);
//            holder.tvPLTag.setVisibility(View.GONE);
//            holder.view1.setVisibility(View.GONE);
//            holder.view2.setVisibility(View.GONE);
//            holder.view3.setVisibility(View.GONE);
//
//            holder.view4.setVisibility(View.VISIBLE);
//            holder.tvProb.setVisibility(View.VISIBLE);
//            holder.tvCompOff.setVisibility(View.VISIBLE);
//            holder.tvProbTag.setVisibility(View.VISIBLE);
//            holder.tvCompOffTag.setVisibility(View.VISIBLE);
//        } else {
//            holder.tvCL.setVisibility(View.VISIBLE);
//            holder.tvML.setVisibility(View.VISIBLE);
//            holder.tvPL.setVisibility(View.VISIBLE);
//            holder.tvClTag.setVisibility(View.VISIBLE);
//            holder.tvMLTag.setVisibility(View.VISIBLE);
//            holder.tvPLTag.setVisibility(View.VISIBLE);
//
//            holder.tvProb.setVisibility(View.GONE);
//            holder.tvProbTag.setVisibility(View.GONE);
//            holder.tvCompOff.setVisibility(View.VISIBLE);
//            holder.tvCompOffTag.setVisibility(View.VISIBLE);
//
//            holder.view1.setVisibility(View.VISIBLE);
//            holder.view2.setVisibility(View.VISIBLE);
//            holder.view3.setVisibility(View.VISIBLE);
//            holder.view4.setVisibility(View.GONE);
//        }

        if (leaveApprovelModels.get(position).getMedattach().trim().equals("")) {
            //holder.tvFileAttachTag.setVisibility(View.GONE);
            holder.ivAttch.setVisibility(View.GONE);
        } else {
           // holder.tvFileAttachTag.setVisibility(View.VISIBLE);
            holder.ivAttch.setVisibility(View.VISIBLE);
        }


        if (leaveApprovelModels.get(position).getExpired().trim().equals("")) {
            //holder.tvexpiredTag.setVisibility(View.GONE);
            holder.tvexpired.setVisibility(View.GONE);
        } else {
           // holder.tvexpiredTag.setVisibility(View.VISIBLE);
            holder.tvexpired.setVisibility(View.VISIBLE);
            holder.tvexpired.setText(leaveApprovelModels.get(position).getExpired());
        }

    }

    private void Callfile(String s) {
        Uri uri = Uri.parse(s);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return leaveApprovelModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmpId, tvLeaveDate, tvCL, tvML, tvPL, tvProb, tvCompOff, tvLeaveType,tvcisorsuki,
                tvReason, tvFDHD, tvAppliedDate, tvAppliedTime, tvexpired, tvApproved, tvRejected, tvFileAttachTag, tvexpiredTag, tvProbTag, tvPLTag, tvClTag, tvMLTag, tvCompOffTag, tvDeleted;

        ImageView ivAttch;

        View view1, view2, view3, view4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvexpiredTag = itemView.findViewById(R.id.tvexpiredTag);
            tvFileAttachTag = itemView.findViewById(R.id.tvFileAttachTag);
            ivAttch = itemView.findViewById(R.id.ivAttch);
            tvexpired = itemView.findViewById(R.id.tvexpired);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
//            tvCL = itemView.findViewById(R.id.tvCL);
//            tvML = itemView.findViewById(R.id.tvML);
            tvLeaveDate = itemView.findViewById(R.id.tvLeaveDate);
//            tvPL = itemView.findViewById(R.id.tvPL);
//            tvProb = itemView.findViewById(R.id.tvProb);
//            tvCompOff = itemView.findViewById(R.id.tvCompOff);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvReason = itemView.findViewById(R.id.tvReason);
            tvFDHD = itemView.findViewById(R.id.tvFDHD);
            tvAppliedDate = itemView.findViewById(R.id.tvAppliedDate);
            tvAppliedTime = itemView.findViewById(R.id.tvAppliedTime);
            tvApproved = itemView.findViewById(R.id.tvApproved);
            tvRejected = itemView.findViewById(R.id.tvRejected);
            tvDeleted = itemView.findViewById(R.id.tvDeleted);

            view1 = itemView.findViewById(R.id.view1);
            view2 = itemView.findViewById(R.id.view2);
            tvcisorsuki = itemView.findViewById(R.id.tvcisorsuki);
//            view4 = itemView.findViewById(R.id.view4);

//            tvProbTag = itemView.findViewById(R.id.tvProbTag);
//            tvPLTag = itemView.findViewById(R.id.tvPLTag);
//            tvClTag = itemView.findViewById(R.id.tvClTag);
//            tvMLTag = itemView.findViewById(R.id.tvMLTag);
//            tvCompOffTag = itemView.findViewById(R.id.tvCompOffTag);


        }
    }
}
