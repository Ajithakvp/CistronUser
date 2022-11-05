package com.example.cistronuser.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.LoginuserModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

Activity activity;
    public ArrayList<LoginuserModel>loginuserModels=new ArrayList<>();

    public ProfileAdapter(Activity activity, ArrayList<LoginuserModel> loginuserModels) {
        this.activity = activity;
        this.loginuserModels = loginuserModels;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.tvname.setText(loginuserModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return loginuserModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // tvname=itemView.findViewById(R.id.tvname);
        }
    }
}
