package com.example.cistronuser.ServiceEngineer.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.ServiceSpareListInterface;
import com.example.cistronuser.API.Interface.SpareSendReqListInterface;
import com.example.cistronuser.API.Interface.SubmitSpareReqTmpInterface;
import com.example.cistronuser.API.Model.ServiceSpareListModel;
import com.example.cistronuser.API.Model.ServiceSpareRequestModel;
import com.example.cistronuser.API.Model.SpareSendReqListModel;
import com.example.cistronuser.API.Response.ServiceSpareResponse;
import com.example.cistronuser.API.Response.SpareSendReqListResponse;
import com.example.cistronuser.API.Response.SubmitSpareReqTmpResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpareReqAdapter extends RecyclerView.Adapter<SpareReqAdapter.ViewHolder> {

    public ArrayList<ServiceSpareRequestModel> serviceSpareRequestModels;
    Activity activity;
    // **********  Spare List **********//
    RecyclerView rvSpareList;

    EditText edSearch;

    ArrayList<String>strMyQty=new ArrayList<String>();
    String MyQty;

    SpareSendReqListAdapter spareSendReqListAdapter;
    ArrayList<ServiceSpareListModel> serviceSpareListModels = new ArrayList<>();
    // **********  Spare List End **********//


    // ********** Send Spare List **********//
    RecyclerView rvSendReqSpareList;
    SpareReqListAdapter spareReqListAdapter;
    ArrayList<SpareSendReqListModel> sendReqListModels = new ArrayList<>();
    // ********** Send Spare List End **********//

//    @NonNull
//    private ItemClickListener onItemClick;


    public SpareReqAdapter(Activity activity, ArrayList<ServiceSpareRequestModel> serviceSpareRequestModels) {
        this.activity = activity;
        this.serviceSpareRequestModels = serviceSpareRequestModels;
    }

    @NonNull
    @Override
    public SpareReqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spare_request_dialog, parent, false);
        return new SpareReqAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpareReqAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvReq.setText(serviceSpareRequestModels.get(position).getText());


        holder.rbReq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    Dialog dialog = new Dialog(activity);
                    dialog.setContentView(R.layout.spare_send_request_dialog_recycleview);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    ImageView ivClose = dialog.findViewById(R.id.ivClose);
                    edSearch = dialog.findViewById(R.id.edSearch);
                    rvSpareList = dialog.findViewById(R.id.rvSpareList);
                    ImageView ivListView = dialog.findViewById(R.id.ivListView);

                    CallSendSpareList(serviceSpareRequestModels.get(position).getId());
                    spareSendReqListAdapter = new SpareSendReqListAdapter(activity, serviceSpareListModels);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    rvSpareList.setAdapter(spareSendReqListAdapter);
                    rvSpareList.setLayoutManager(linearLayoutManager);

                    ivClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity.finish();
                            activity.overridePendingTransition(0, 0);
                            activity.startActivity(activity.getIntent());
                            activity.overridePendingTransition(0, 0);
                        }
                    });

                    ivListView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CallSendReqListView();
                        }
                    });

                    edSearch.setBackgroundResource(android.R.color.transparent);

                    edSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            spareSendReqListAdapter.filter(s.toString());

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                }
            }
        });

    }

    private void CallSendReqListView() {

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.spare_send_request_list_dialog_recycleview);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        rvSendReqSpareList = dialog.findViewById(R.id.rvSendReqSpareList);
        TextView tvSubmit=dialog.findViewById(R.id.tvSubmit);



        CallSendReqList();
        spareReqListAdapter = new SpareReqListAdapter(sendReqListModels, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSendReqSpareList.setLayoutManager(linearLayoutManager);
        rvSendReqSpareList.setAdapter(spareReqListAdapter);




        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMyQty.clear();
                for (int i=0;i<rvSendReqSpareList.getAdapter().getItemCount();i++){
                    v=rvSendReqSpareList.getChildAt(i);
                    EditText edQty=(EditText) v.findViewById(R.id.edMyQty);
                    strMyQty.add(i,edQty.getText().toString());

                }
                MyQty= android.text.TextUtils.join(",", strMyQty);

                final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                SubmitSpareReqTmpInterface submitSpareReqTmpInterface=APIClient.getClient().create(SubmitSpareReqTmpInterface.class);
                submitSpareReqTmpInterface.submitSpare("submitSpareRequestsTmp",PreferenceManager.getEmpID(activity),MyQty,PreferenceManager.getCallNo(activity)).enqueue(new Callback<SubmitSpareReqTmpResponse>() {
                    @Override
                    public void onResponse(Call<SubmitSpareReqTmpResponse> call, Response<SubmitSpareReqTmpResponse> response) {

                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(activity, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                            activity.finish();
                            activity.overridePendingTransition(0, 0);
                            activity.startActivity(activity.getIntent());
                            activity.overridePendingTransition(0, 0);
                        }

                    }

                    @Override
                    public void onFailure(Call<SubmitSpareReqTmpResponse> call, Throwable t) {

                    }
                });




            }
        });





    }



    private void CallSendReqList() {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Request Spare List...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SpareSendReqListInterface spareSendReqListInterface = APIClient.getClient().create(SpareSendReqListInterface.class);
        spareSendReqListInterface.CallSpareList("spareRequestTmp", PreferenceManager.getEmpID(activity)).enqueue(new Callback<SpareSendReqListResponse>() {
            @Override
            public void onResponse(Call<SpareSendReqListResponse> call, Response<SpareSendReqListResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        spareReqListAdapter.spareSendReqListModels = response.body().getSpareSendReqListModels();
                        spareReqListAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<SpareSendReqListResponse> call, Throwable t) {

            }
        });

    }

    private void CallSendSpareList(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(activity, R.style.ProgressBarDialog);
        progressDialog.setMessage("Spare List...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ServiceSpareListInterface serviceSpareListInterface = APIClient.getClient().create(ServiceSpareListInterface.class);
        serviceSpareListInterface.CallSpareList("getSparePartsList", id, PreferenceManager.getEmpID(activity)).enqueue(new Callback<ServiceSpareResponse>() {
            @Override
            public void onResponse(Call<ServiceSpareResponse> call, Response<ServiceSpareResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        spareSendReqListAdapter.serviceSpareListModels = response.body().getServiceSpareListModels();
                        spareSendReqListAdapter.searchAdapter(response.body().getServiceSpareListModels());
                        spareSendReqListAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void onFailure(Call<ServiceSpareResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceSpareRequestModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton rbReq;
        TextView tvReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvReq = itemView.findViewById(R.id.tvReq);
            rbReq = itemView.findViewById(R.id.rbReq);
        }
    }
}
