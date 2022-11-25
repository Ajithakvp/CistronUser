package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.LeaveApprovelModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class LeaveApprovalAdapter extends RecyclerView.Adapter<LeaveApprovalAdapter.ViewHolder> {

    Activity activity;

    public ArrayList<LeaveApprovelModel>leaveApprovelModels;

    public LeaveApprovalAdapter(Activity activity, ArrayList<LeaveApprovelModel> leaveApprovelModels) {
        this.activity = activity;
        this.leaveApprovelModels = leaveApprovelModels;
    }

    @NonNull
    @Override
    public LeaveApprovalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_reqesut_approvel, parent, false);
        return new LeaveApprovalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveApprovalAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(leaveApprovelModels.get(position).getEmpname());
        holder.tvEmpId.setText("EmpID-"+leaveApprovelModels.get(position).getEmpid());
        holder.tvLeaveDate.setText(leaveApprovelModels.get(position).getLeavedate());
        holder.tvCL.setText(leaveApprovelModels.get(position).getCl());
        holder.tvPL.setText(leaveApprovelModels.get(position).getPl());
        holder.tvML.setText(leaveApprovelModels.get(position).getMl());
        holder.tvProb.setText(leaveApprovelModels.get(position).getProbl());
        holder.tvCompOff.setText(leaveApprovelModels.get(position).getCompoff());
        holder.tvReason.setText(leaveApprovelModels.get(position).getReason());
        holder.tvLeaveType.setText(leaveApprovelModels.get(position).getLeavetype());
        holder.tvAppliedDate.setText(leaveApprovelModels.get(position).getApplied_date());
        holder.tvAppliedTime.setText(leaveApprovelModels.get(position).getApplied_time());
        holder.tvApproved.setText(leaveApprovelModels.get(position).getBtn_txt());

        if (leaveApprovelModels.get(position).getEmptype().trim().equals("3")){
            holder.tvCL.setVisibility(View.GONE);
            holder.tvML.setVisibility(View.GONE);
            holder.tvPL.setVisibility(View.GONE);
            holder.tvClTag.setVisibility(View.GONE);
            holder.tvMLTag.setVisibility(View.GONE);
            holder.tvPLTag.setVisibility(View.GONE);
            holder.view1.setVisibility(View.GONE);
            holder.view2.setVisibility(View.GONE);
            holder.view3.setVisibility(View.GONE);

            holder.view4.setVisibility(View.VISIBLE);
            holder.tvProb.setVisibility(View.VISIBLE);
            holder.tvCompOff.setVisibility(View.VISIBLE);
            holder.tvProbTag.setVisibility(View.VISIBLE);
            holder.tvCompOffTag.setVisibility(View.VISIBLE);
        }else {
            holder.tvCL.setVisibility(View.VISIBLE);
            holder.tvML.setVisibility(View.VISIBLE);
            holder.tvPL.setVisibility(View.VISIBLE);
            holder.tvClTag.setVisibility(View.VISIBLE);
            holder.tvMLTag.setVisibility(View.VISIBLE);
            holder.tvPLTag.setVisibility(View.VISIBLE);

            holder.tvProb.setVisibility(View.GONE);
            holder.tvProbTag.setVisibility(View.GONE);
            holder.tvCompOff.setVisibility(View.VISIBLE);
            holder.tvCompOffTag.setVisibility(View.VISIBLE);

            holder.view1.setVisibility(View.VISIBLE);
            holder.view2.setVisibility(View.VISIBLE);
            holder.view3.setVisibility(View.VISIBLE);
            holder.view4.setVisibility(View.GONE);
        }

        if (leaveApprovelModels.get(position).getMedattach().trim().equals("")){
            holder.tvFileAttachTag.setVisibility(View.GONE);
            holder.ivAttch.setVisibility(View.GONE);
        }else {
            holder.tvFileAttachTag.setVisibility(View.VISIBLE);
            holder.ivAttch.setVisibility(View.VISIBLE);
        }


        if (leaveApprovelModels.get(position).getExpired().trim().equals("")){
            holder.tvexpiredTag.setVisibility(View.GONE);
            holder.tvexpired.setVisibility(View.GONE);
        }else {
            holder.tvexpiredTag.setVisibility(View.VISIBLE);
            holder.tvexpired.setVisibility(View.VISIBLE);
            holder.tvexpired.setText(leaveApprovelModels.get(position).getExpired());
        }

    }

    @Override
    public int getItemCount() {
        return leaveApprovelModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvEmpId,tvLeaveDate,tvCL,tvML,tvPL,tvProb,tvCompOff,tvLeaveType,
                tvReason,tvFDHD,tvAppliedDate,tvAppliedTime,tvexpired,tvApproved,tvRejected,tvFileAttachTag,tvexpiredTag
                ,tvProbTag,tvPLTag,tvClTag,tvMLTag,tvCompOffTag;

        ImageView ivAttch;

        View view1,view2,view3,view4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvexpiredTag=itemView.findViewById(R.id.tvexpiredTag);
            tvFileAttachTag=itemView.findViewById(R.id.tvFileAttachTag);
            ivAttch=itemView.findViewById(R.id.ivAttch);
            tvexpired=itemView.findViewById(R.id.tvexpired);
            tvName=itemView.findViewById(R.id.tvName);
            tvEmpId=itemView.findViewById(R.id.tvEmpId);
            tvCL=itemView.findViewById(R.id.tvCL);
            tvML=itemView.findViewById(R.id.tvML);
            tvLeaveDate=itemView.findViewById(R.id.tvLeaveDate);
            tvPL=itemView.findViewById(R.id.tvPL);
            tvProb=itemView.findViewById(R.id.tvProb);
            tvCompOff=itemView.findViewById(R.id.tvCompOff);
            tvLeaveType=itemView.findViewById(R.id.tvLeaveType);
            tvReason=itemView.findViewById(R.id.tvReason);
            tvFDHD=itemView.findViewById(R.id.tvFDHD);
            tvAppliedDate=itemView.findViewById(R.id.tvAppliedDate);
            tvAppliedTime=itemView.findViewById(R.id.tvAppliedTime);
            tvApproved=itemView.findViewById(R.id.tvApproved);
            tvRejected=itemView.findViewById(R.id.tvRejected);

            view1=itemView.findViewById(R.id.view1);
            view2=itemView.findViewById(R.id.view2);
            view3=itemView.findViewById(R.id.view3);
            view4=itemView.findViewById(R.id.view4);

            tvProbTag=itemView.findViewById(R.id.tvProbTag);
            tvPLTag=itemView.findViewById(R.id.tvPLTag);
            tvClTag=itemView.findViewById(R.id.tvClTag);
            tvMLTag=itemView.findViewById(R.id.tvMLTag);
            tvCompOffTag=itemView.findViewById(R.id.tvCompOffTag);


        }
    }
}
