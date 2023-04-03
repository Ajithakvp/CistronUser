package com.example.cistronuser.WaitingforApprovel.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.GeoLocationApprovalInterface;
import com.example.cistronuser.API.Interface.GeoLocationRejectedInferface;
import com.example.cistronuser.API.Model.GetGeoLocationApprovalModel;
import com.example.cistronuser.API.Response.GeoLocationApprovalResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoLocationAdapter extends RecyclerView.Adapter<GeoLocationAdapter.ViewHolder> {

    public ArrayList<GetGeoLocationApprovalModel> getGeoLocationApprovalModels;
    Activity activity;

    public GeoLocationAdapter(Activity activity, ArrayList<GetGeoLocationApprovalModel> getGeoLocationApprovalModels) {
        this.activity = activity;
        this.getGeoLocationApprovalModels = getGeoLocationApprovalModels;
    }

    @NonNull
    @Override
    public GeoLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.geo_location_adapter, parent, false);
        return new GeoLocationAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoLocationAdapter.ViewHolder holder, int position) {

        holder.tvHospitalName.setText(getGeoLocationApprovalModels.get(position).getHospital());
        holder.tvCurrentAddress.setText(getGeoLocationApprovalModels.get(position).getOldAdrs());
        holder.tvLatitude.setText(getGeoLocationApprovalModels.get(position).getOldLat());
        holder.tvLongtitude.setText(getGeoLocationApprovalModels.get(position).getOldLng());
        holder.tvChangeAddress.setText(getGeoLocationApprovalModels.get(position).getNewAdrs());
        holder.tvChangeLatitude.setText(getGeoLocationApprovalModels.get(position).getNewLat());
        holder.tvChangeLongtitude.setText(getGeoLocationApprovalModels.get(position).getNewLng());
        holder.tvDistance.setText(getGeoLocationApprovalModels.get(position).getDistance());
        holder.tvReqbyemp.setText(getGeoLocationApprovalModels.get(position).getEmploee());
        holder.tvStatus.setText(getGeoLocationApprovalModels.get(position).getOldAdrsStatus());
        holder.tvReport.setText(getGeoLocationApprovalModels.get(position).getSource());

        String reqID = getGeoLocationApprovalModels.get(position).getId();

        holder.tvbtnApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                GeoLocationApprovalInterface geoLocationApprovalInterface = APIClient.getClient().create(GeoLocationApprovalInterface.class);
                geoLocationApprovalInterface.CallApprovalGeo("approveGeolocationReq", PreferenceManager.getEmpID(activity), reqID).enqueue(new Callback<GeoLocationApprovalResponse>() {
                    @Override
                    public void onResponse(Call<GeoLocationApprovalResponse> call, Response<GeoLocationApprovalResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(activity, response.body().getResponse(), Toast.LENGTH_SHORT).show();
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
                    public void onFailure(Call<GeoLocationApprovalResponse> call, Throwable t) {
                        progressDialog.dismiss();

                    }
                });
            }
        });

        holder.tvbtnRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                GeoLocationRejectedInferface geoLocationRejectedInferface = APIClient.getClient().create(GeoLocationRejectedInferface.class);
                geoLocationRejectedInferface.CallRejectedGeo("rejectGeolocation", PreferenceManager.getEmpID(activity), reqID).enqueue(new Callback<GeoLocationApprovalResponse>() {
                    @Override
                    public void onResponse(Call<GeoLocationApprovalResponse> call, Response<GeoLocationApprovalResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(activity, response.body().getResponse(), Toast.LENGTH_SHORT).show();
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
                    public void onFailure(Call<GeoLocationApprovalResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return getGeoLocationApprovalModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHospitalName, tvReport, tvReqbyemp, tvCurrentAddress, tvLatitude, tvLongtitude, tvDistance, tvChangeAddress, tvChangeLatitude, tvChangeLongtitude, tvbtnApproval, tvbtnRejected, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName = itemView.findViewById(R.id.tvHospitalName);
            tvReport = itemView.findViewById(R.id.tvReport);
            tvCurrentAddress = itemView.findViewById(R.id.tvCurrentAddress);
            tvLatitude = itemView.findViewById(R.id.tvLatitude);
            tvLongtitude = itemView.findViewById(R.id.tvLongtitude);
            tvChangeAddress = itemView.findViewById(R.id.tvChangeAddress);
            tvChangeLatitude = itemView.findViewById(R.id.tvChangeLatitude);
            tvChangeLongtitude = itemView.findViewById(R.id.tvChangeLongtitude);
            tvbtnApproval = itemView.findViewById(R.id.tvbtnApproval);
            tvbtnRejected = itemView.findViewById(R.id.tvbtnRejected);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvReqbyemp = itemView.findViewById(R.id.tvReqbyemp);
            tvDistance = itemView.findViewById(R.id.tvDistance);


        }
    }
}
