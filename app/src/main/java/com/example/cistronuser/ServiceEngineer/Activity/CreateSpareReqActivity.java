package com.example.cistronuser.ServiceEngineer.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.CreateSendSpareReqSubmitInterface;
import com.example.cistronuser.API.Interface.CreateSparesendreqViewInterface;
import com.example.cistronuser.API.Interface.MyStockSEInterface;
import com.example.cistronuser.API.Interface.MyStockSESearchInteface;
import com.example.cistronuser.API.Model.CreateSendSpareReqSubmitResponse;
import com.example.cistronuser.API.Model.CreateSparesendreqViewModel;
import com.example.cistronuser.API.Model.MyStockSEModel;
import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.example.cistronuser.API.Response.CreateSparesendreqViewResponse;
import com.example.cistronuser.API.Response.MyStockSEResponse;
import com.example.cistronuser.API.Response.MyStockSESearchResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.CreateSpareReqAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.CreateSpareReqSearchAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.CreateSpareSendReqListAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSpareReqActivity extends AppCompatActivity {


    // ****** My Stock Copy All , adapter change Only ****** //
    EditText edSearch;
    RecyclerView rvCreateSpareReq;
    ImageView ivBack,ivList;
    CreateSpareReqAdapter createSpareReqAdapter;
    public ArrayList<MyStockSEModel> myStockSEModels = new ArrayList<>();

    //Search List
    RecyclerView rvSearchCreateSpareReq;
    CreateSpareReqSearchAdapter myStockSearchAdapter;
    ArrayList<MyStockSESearchModel> myStockSESearchModels = new ArrayList<>();


    //BottomSheet Dialog
    RecyclerView rvSendReqSpareList;
    ImageView ivBottomBack;
    CreateSpareSendReqListAdapter createSpareSendReqListAdapter;
    ArrayList<CreateSparesendreqViewModel>createSparesendreqViewModels=new ArrayList<>();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spare_req);


        edSearch = findViewById(R.id.edSearch);
        rvCreateSpareReq=findViewById(R.id.rvCreateSpareReq);
        ivBack=findViewById(R.id.ivBack);
        rvSearchCreateSpareReq=findViewById(R.id.rvSearchCreateSpareReq);
        ivList=findViewById(R.id.ivList);



        CallMystockList();
        createSpareReqAdapter=new CreateSpareReqAdapter(myStockSEModels,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvCreateSpareReq.setAdapter(createSpareReqAdapter);
        rvCreateSpareReq.setLayoutManager(linearLayoutManager);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edSearch.getText().toString().length() > 0) {
                    rvSearchCreateSpareReq.setVisibility(View.VISIBLE);
                    rvCreateSpareReq.setVisibility(View.GONE);
                    CallMyStockSearch();
                    myStockSearchAdapter = new CreateSpareReqSearchAdapter(myStockSESearchModels, CreateSpareReqActivity.this);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(CreateSpareReqActivity.this);
                    linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
                    rvSearchCreateSpareReq.setLayoutManager(linearLayoutManager1);
                    rvSearchCreateSpareReq.setAdapter(myStockSearchAdapter);
                }else {
                    rvSearchCreateSpareReq.setVisibility(View.GONE);
                    rvCreateSpareReq.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallBottomDialog();
            }
        });


    }

    private void CallBottomDialog() {
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.create_spare_req_bottom_recycleview);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();
        rvSendReqSpareList=bottomSheetDialog.findViewById(R.id.rvSendReqSpareList);
        ivBottomBack=bottomSheetDialog.findViewById(R.id.ivBottomBack);
        TextView tvSubmitbtn=bottomSheetDialog.findViewById(R.id.tvSubmitbtn);
        TextView tvNodata=bottomSheetDialog.findViewById(R.id.tvNodata);

        ArrayList<String>strQtyToReq=new ArrayList<>();
        ArrayList<String>strPurpose=new ArrayList<>();
        ArrayList<String>strSeriesID=new ArrayList<>();
        ArrayList<String>strPartID=new ArrayList<>();


        CallSendReqList(tvNodata,tvSubmitbtn);
        createSpareSendReqListAdapter=new CreateSpareSendReqListAdapter(createSparesendreqViewModels, this, new CreateSpareSendReqListAdapter.PartId() {
            @Override
            public void partid(String reqid) {

                strPartID.add(reqid.toString());

            }
        }, new CreateSpareSendReqListAdapter.SeriesId() {
            @Override
            public void seriesid(String strseriesid) {
                strSeriesID.add(strseriesid);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvSendReqSpareList.setLayoutManager(linearLayoutManager);
        rvSendReqSpareList.setAdapter(createSpareSendReqListAdapter);


        ivBottomBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        tvSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strQtyToReq.clear();
                strPurpose.clear();
                for (int i=0;i<createSpareSendReqListAdapter.getItemCount();i++){
                    v=rvSendReqSpareList.getChildAt(i);
                    EditText edQtyReq=(EditText)v.findViewById(R.id.edQtyReq);
                    Spinner spinner=(Spinner) v.findViewById(R.id.spPurpose);
                    strPurpose.add(spinner.getSelectedItem().toString());
                    strQtyToReq.add(edQtyReq.getText().toString());
                }
                final ProgressDialog progressDialog = new ProgressDialog(CreateSpareReqActivity.this, R.style.ProgressBarDialog);
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                CreateSendSpareReqSubmitInterface createSendSpareReqSubmitInterface=APIClient.getClient().create(CreateSendSpareReqSubmitInterface.class);
                createSendSpareReqSubmitInterface.CallSubmit("submitSpareRequestsQueue",PreferenceManager.getEmpID(CreateSpareReqActivity.this),strPartID,strQtyToReq,strSeriesID,strPurpose).enqueue(new Callback<CreateSendSpareReqSubmitResponse>() {
                    @Override
                    public void onResponse(Call<CreateSendSpareReqSubmitResponse> call, Response<CreateSendSpareReqSubmitResponse> response) {
                        try {
                            if (response.isSuccessful()){
                                progressDialog.dismiss();
                                if (response.body().getResult().trim().equals("1")){
                                    AlertDialog.Builder builder=new AlertDialog.Builder(CreateSpareReqActivity.this,R.style.ProgressBarDialog);
                                    builder.setCancelable(false);
                                    builder.setMessage(response.body().getMessage());
                                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            bottomSheetDialog.dismiss();
                                        }
                                    });
                                    AlertDialog alertDialog=builder.create();
                                    alertDialog.show();
                                }else {
                                    AlertDialog.Builder builder=new AlertDialog.Builder(CreateSpareReqActivity.this,R.style.AlertDialogCustom);
                                    builder.setMessage(response.body().getMessage());
                                    AlertDialog alertDialog=builder.create();
                                    alertDialog.show();
                                }
                            }

                        }catch (Exception e){
                            progressDialog.dismiss();

                        }

                    }

                    @Override
                    public void onFailure(Call<CreateSendSpareReqSubmitResponse> call, Throwable t) {

                        progressDialog.dismiss();
                    }
                });
            }
        });



    }

    private void CallSendReqList(TextView tvNodata, TextView tvSubmitbtn) {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        CreateSparesendreqViewInterface createSparesendreqViewInterface=APIClient.getClient().create(CreateSparesendreqViewInterface.class);
        createSparesendreqViewInterface.CallSpareReq("viewSpareRequestQueue",PreferenceManager.getEmpID(this)).enqueue(new Callback<CreateSparesendreqViewResponse>() {
            @Override
            public void onResponse(Call<CreateSparesendreqViewResponse> call, Response<CreateSparesendreqViewResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        if (response.body().getCreateSparesendreqViewModels().size()>0) {
                            createSpareSendReqListAdapter.createSparesendreqViewModels = response.body().getCreateSparesendreqViewModels();
                            createSpareSendReqListAdapter.notifyDataSetChanged();
                            tvNodata.setVisibility(View.GONE);

                        }else {

                            rvSendReqSpareList.setVisibility(View.GONE);
                            tvNodata.setVisibility(View.VISIBLE);
                            tvSubmitbtn.setVisibility(View.GONE);

                        }
                    }

                }catch (Exception e){
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<CreateSparesendreqViewResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    private void CallMyStockSearch() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSESearchInteface myStockSESearchInteface = APIClient.getClient().create(MyStockSESearchInteface.class);
        myStockSESearchInteface.CallMystock("viewAllSpareParts", PreferenceManager.getEmpID(this), edSearch.getText().toString()).enqueue(new Callback<MyStockSESearchResponse>() {
            @Override
            public void onResponse(Call<MyStockSESearchResponse> call, Response<MyStockSESearchResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        myStockSearchAdapter.myStockListSEModels = response.body().getMyStockSESearchModels();
                        myStockSearchAdapter.searchAdapter(response.body().getMyStockSESearchModels());
                        myStockSearchAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<MyStockSESearchResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private void CallMystockList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSEInterface myStockSEInterface = APIClient.getClient().create(MyStockSEInterface.class);
        myStockSEInterface.CallMystock("getSeriesData", PreferenceManager.getEmpID(this)).enqueue(new Callback<MyStockSEResponse>() {
            @Override
            public void onResponse(Call<MyStockSEResponse> call, Response<MyStockSEResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        createSpareReqAdapter.myStockSEModels = response.body().getMyStockSEModels();
                        createSpareReqAdapter.notifyDataSetChanged();

                    }

                } catch (Exception e) {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<MyStockSEResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}