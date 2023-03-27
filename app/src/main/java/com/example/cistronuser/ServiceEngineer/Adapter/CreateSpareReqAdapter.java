package com.example.cistronuser.ServiceEngineer.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.MyStockListSEInterface;
import com.example.cistronuser.API.Model.MyStockListSEModel;
import com.example.cistronuser.API.Model.MyStockSEModel;
import com.example.cistronuser.API.Response.MyStockListSEResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSpareReqAdapter extends RecyclerView.Adapter<CreateSpareReqAdapter.ViewHolder> {

    public ArrayList<MyStockSEModel> myStockSEModels = new ArrayList<>();
    Activity activity;

    public CreateSpareReqAdapter(ArrayList<MyStockSEModel> myStockSEModels, Activity activity) {
        this.myStockSEModels = myStockSEModels;
        this.activity = activity;
    }

    // Mystock List
    ImageView ivClose;
    RecyclerView rvSpareList;
    EditText edSearch;
    CreateSpareReqListAdapter mystockListAdapter;
    ArrayList<MyStockListSEModel> myStockListSEModels = new ArrayList<>();
    TextView tvNodata;
    RelativeLayout rlSearch;
    @NonNull
    @Override
    public CreateSpareReqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_spare_req_adapter, parent, false);
        return new CreateSpareReqAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CreateSpareReqAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvReq.setText(myStockSEModels.get(position).getSeriesName());

        holder.rbStock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.mystock_se_recycleview);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();

                    rvSpareList = dialog.findViewById(R.id.rvSpareList);
                    ivClose = dialog.findViewById(R.id.ivClose);
                    edSearch = dialog.findViewById(R.id.edSearch);
                    rlSearch=dialog.findViewById(R.id.rlSearch);
                    tvNodata=dialog.findViewById(R.id.tvNodata);


                    CallMystockList(myStockSEModels.get(position).getSeriesId());
                    mystockListAdapter = new CreateSpareReqListAdapter(activity, myStockListSEModels);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    rvSpareList.setAdapter(mystockListAdapter);
                    rvSpareList.setLayoutManager(linearLayoutManager);

                    edSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            mystockListAdapter.filter(s.toString());
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            activity.overridePendingTransition(0, 0);
                            activity.startActivity(activity.getIntent());
                            activity.overridePendingTransition(0, 0);
                            activity.finish();
                            dialog.dismiss();
                        }
                    });


                }
            }
        });


    }

    private void CallMystockList(String seriesId) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockListSEInterface myStockListSEInterface = APIClient.getClient().create(MyStockListSEInterface.class);
        myStockListSEInterface.CallMystock("viewAllSpareParts", PreferenceManager.getEmpID(activity), seriesId).enqueue(new Callback<MyStockListSEResponse>() {
            @Override
            public void onResponse(Call<MyStockListSEResponse> call, Response<MyStockListSEResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        if (response.body().getMyStockListSEModels().size()>0) {
                            mystockListAdapter.myStockListSEModels = response.body().getMyStockListSEModels();
                            mystockListAdapter.searchAdapter(response.body().getMyStockListSEModels());
                            mystockListAdapter.notifyDataSetChanged();
                            tvNodata.setVisibility(View.GONE);
                        }else {
                            tvNodata.setVisibility(View.VISIBLE);
                            rlSearch.setVisibility(View.GONE);
                            mystockListAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<MyStockListSEResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myStockSEModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbStock;
        TextView tvReq;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReq = itemView.findViewById(R.id.tvReq);
            rbStock = itemView.findViewById(R.id.rbStock);
        }
    }
}
