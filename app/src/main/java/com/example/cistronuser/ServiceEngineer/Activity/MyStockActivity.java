package com.example.cistronuser.ServiceEngineer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.MyStockSEInterface;
import com.example.cistronuser.API.Interface.MyStockSESearchInteface;
import com.example.cistronuser.API.Model.MyStockSEModel;
import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.example.cistronuser.API.Response.MyStockSEResponse;
import com.example.cistronuser.API.Response.MyStockSESearchResponse;
import com.example.cistronuser.Common.PreferenceManager;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.MyStockAdapter;
import com.example.cistronuser.ServiceEngineer.Adapter.MyStockSearchAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStockActivity extends AppCompatActivity {


    RecyclerView rvMystockList;
    ImageView ivBack;
    EditText edSearch;
    ArrayList<MyStockSEModel> myStockSEModels = new ArrayList<>();
    MyStockAdapter myStockAdapter;

    //Search List
    RecyclerView rvMystockSearchList;
    MyStockSearchAdapter myStockSearchAdapter;
    ArrayList<MyStockSESearchModel> myStockSESearchModels = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stock);

        rvMystockList = findViewById(R.id.rvMystockList);
        ivBack = findViewById(R.id.ivBack);
        edSearch = findViewById(R.id.edSearch);
        rvMystockSearchList = findViewById(R.id.rvMystockSearchList);


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CallMystockList();
        myStockAdapter = new MyStockAdapter(this, myStockSEModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvMystockList.setLayoutManager(linearLayoutManager);
        rvMystockList.setAdapter(myStockAdapter);




        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edSearch.getText().toString().length() > 0) {
                    rvMystockSearchList.setVisibility(View.VISIBLE);
                    rvMystockList.setVisibility(View.GONE);
                    CallMyStockSearch();
                    myStockSearchAdapter = new MyStockSearchAdapter(myStockSESearchModels, MyStockActivity.this);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MyStockActivity.this);
                    linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
                    rvMystockSearchList.setLayoutManager(linearLayoutManager1);
                    rvMystockSearchList.setAdapter(myStockSearchAdapter);
                }else {
                    rvMystockSearchList.setVisibility(View.GONE);
                    rvMystockList.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void CallMyStockSearch() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSESearchInteface myStockSESearchInteface = APIClient.getClient().create(MyStockSESearchInteface.class);
        myStockSESearchInteface.CallMystock("myStock", PreferenceManager.getEmpID(this), edSearch.getText().toString()).enqueue(new Callback<MyStockSESearchResponse>() {
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
        progressDialog.setMessage("My Stock List...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        MyStockSEInterface myStockSEInterface = APIClient.getClient().create(MyStockSEInterface.class);
        myStockSEInterface.CallMystock("getSeriesData", PreferenceManager.getEmpID(this)).enqueue(new Callback<MyStockSEResponse>() {
            @Override
            public void onResponse(Call<MyStockSEResponse> call, Response<MyStockSEResponse> response) {

                try {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        myStockAdapter.myStockSEModels = response.body().getMyStockSEModels();
                        myStockAdapter.notifyDataSetChanged();

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