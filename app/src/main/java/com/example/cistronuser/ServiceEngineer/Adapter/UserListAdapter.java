package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlist_adapter, parent, false);
        return new UserListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {


        holder.tvEmpId.setText( userListModels.get(position).getEmpid());
        holder.tvEmpName.setText(userListModels.get(position).getName());
        holder.tvDesignation.setText(userListModels.get(position).getDesignation());

        String photo = userListModels.get(position).getPhoto();

        try {
            byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.ivProfile.setImageBitmap(decodedByte);
        } catch (Exception e) {
            e.printStackTrace();
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

        TextView tvEmpName, tvEmpId, tvDesignation;
        CircleImageView ivProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEmpName = itemView.findViewById(R.id.tvEmpName);
            tvEmpId = itemView.findViewById(R.id.tvEmpId);
            tvDesignation = itemView.findViewById(R.id.tvDesignation);
            ivProfile = itemView.findViewById(R.id.ivProfile);

        }
    }
}
