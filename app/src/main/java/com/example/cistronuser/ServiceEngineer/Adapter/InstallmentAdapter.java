package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.InstallamentModel;
import com.example.cistronuser.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentAdapter.ViewHolder> {

    Activity activity;
    public ArrayList<InstallamentModel>installamentModels;

    GetInstallament getInstallament;

    public InstallmentAdapter(Activity activity, ArrayList<InstallamentModel> installamentModels, GetInstallament getInstallament) {
        this.activity = activity;
        this.installamentModels = installamentModels;
        this.getInstallament = getInstallament;
    }

    @NonNull
    @Override
    public InstallmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.installment_adapter, parent, false);
        return new InstallmentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstallmentAdapter.ViewHolder holder, int position) {

        holder.tvSno.setText(installamentModels.get(position).getSno());
        holder.tvamtIns.setText(installamentModels.get(position).getAmtIns());
        holder.tvamtInsr.setText(installamentModels.get(position).getAmtInsr());

        InstallamentModel id=installamentModels.get(position);

        getInstallament.getData(id);

    }

    @Override
    public int getItemCount() {
        return installamentModels.size();
    }

    public interface GetInstallament{
        void getData(InstallamentModel installamentModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSno,tvamtIns,tvamtInsr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSno=itemView.findViewById(R.id.tvSno);
            tvamtIns=itemView.findViewById(R.id.tvamtIns);
            tvamtInsr=itemView.findViewById(R.id.tvamtInsr);

        }
    }
}
