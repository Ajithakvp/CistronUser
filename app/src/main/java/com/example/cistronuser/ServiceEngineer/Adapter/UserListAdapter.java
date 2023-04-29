package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.MyStockListSEModel;
import com.example.cistronuser.API.Model.UserListModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    Activity activity;
    public ArrayList<UserListModel> userListModels;

    ArrayList<UserListModel> tempUserlistModel = new ArrayList<>();
    ArrayList<String> strUserlist = new ArrayList<>();

    public UserListAdapter(Activity activity, ArrayList<UserListModel> userListModels) {
        this.activity = activity;
        this.userListModels = userListModels;
    }

    boolean isClick=true;

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist_adapter, parent, false);
        return new UserListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {


        holder.tvEmpId.setText(userListModels.get(position).getEmpid());
        holder.tvEmpName.setText(userListModels.get(position).getName());
        holder.tvDesignation.setText(userListModels.get(position).getDesignation());
        String photo = userListModels.get(position).getPhoto();
        holder.tvEmail.setText(userListModels.get(position).getEmail());
        holder.tvBranch.setText(userListModels.get(position).getBranch());
        holder.tvDob.setText(userListModels.get(position).getDOB());
        holder.tvDoj.setText(userListModels.get(position).getDOJ());
        holder.tvMobile.setText(userListModels.get(position).getMobileno());
        String MobileNo=userListModels.get(position).getMobileno();
        String Email=userListModels.get(position).getEmail();

        if (userListModels.get(position).getCompany().trim().equals("1")){
            holder.tvCistronCompany.setVisibility(View.VISIBLE);
            holder.tvSukCompany.setVisibility(View.GONE);
        }else {
            holder.tvCistronCompany.setVisibility(View.GONE);
            holder.tvSukCompany.setVisibility(View.VISIBLE);
        }

        try {
            if (MobileNo.trim().equals("") || MobileNo.trim().equals(null)) {
                holder.tvMobile.setText("Not Available");
                holder.ivCall.setVisibility(View.INVISIBLE);
            } else {
                holder.ivCall.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }

        if (Email.trim().equals("") || Email.trim().equals(null)){
            holder.tvEmail.setText("Not Available");
            holder.ivMail.setVisibility(View.INVISIBLE);
        }else {
            holder.ivMail.setVisibility(View.VISIBLE);
        }

        holder.ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" +Email ));
                activity.startActivity(intent);

            }
        });


        holder.cvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick==true){
                    holder.rlDetails.setVisibility(View.VISIBLE);
                    isClick=false;
                }else {
                    isClick=true;
                    holder.rlDetails.setVisibility(View.GONE);
                }

            }
        });

        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + MobileNo));
                activity.startActivity(intent);
            }
        });


        if (photo.trim().equals("") || photo.trim().equals(null)) {
            holder.ivProfile.setImageResource(R.drawable.user);
        } else {

            try {
                byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.ivProfile.setImageBitmap(decodedByte);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void filter(String charText) {
        charText = charText.toLowerCase();
        userListModels.clear();
        if (charText.length() == 0) {
            userListModels.addAll(tempUserlistModel);
        } else {
            for (String searchedValue : strUserlist) {

                if (searchedValue.toLowerCase().contains(charText)) {
                    for (int i = 0; i < tempUserlistModel.size(); i++) {

                        if (tempUserlistModel.get(i).getEmpid().equalsIgnoreCase(searchedValue)) {
                            userListModels.add(tempUserlistModel.get(i));
                            break;
                        }
                    }

                }
            }


        }
        notifyDataSetChanged();
    }

    public void searchAdapter(ArrayList<UserListModel> data) {
        tempUserlistModel.addAll(data);
        strUserlist.clear();
        for (int i = 0; i < tempUserlistModel.size(); i++) {
            strUserlist.add(tempUserlistModel.get(i).getEmpid());
        }
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return userListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEmpName, tvEmpId, tvDesignation,tvEmail,tvMobile,tvDob,tvDoj,tvBranch,tvCistronCompany,tvSukCompany;
        CircleImageView ivProfile;
        ImageView ivCall,ivMail;
        RelativeLayout rlDetails;
        CardView cvView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvView = itemView.findViewById(R.id.cvView);
            rlDetails = itemView.findViewById(R.id.rlDetails);
            tvEmpName = itemView.findViewById(R.id.tvEmpName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvDob = itemView.findViewById(R.id.tvDob);
            tvDoj = itemView.findViewById(R.id.tvDoj);
            tvBranch = itemView.findViewById(R.id.tvBranch);
            ivCall = itemView.findViewById(R.id.ivCall);
            ivMail=itemView.findViewById(R.id.ivMail);
            tvCistronCompany = itemView.findViewById(R.id.tvCistronCompany);
            tvSukCompany=itemView.findViewById(R.id.tvSukCompany);


        }
    }
}
