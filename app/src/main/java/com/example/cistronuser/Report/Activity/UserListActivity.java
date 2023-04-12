package com.example.cistronuser.Report.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.UserListInterface;
import com.example.cistronuser.API.Model.UserListModel;
import com.example.cistronuser.API.Response.UserListResponse;
import com.example.cistronuser.R;
import com.example.cistronuser.ServiceEngineer.Adapter.UserListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {

    RecyclerView rvUserList;
    ImageView ivBack;
    EditText edSearch;

    ArrayList<UserListModel>userListModels=new ArrayList<>();
    UserListAdapter userListAdapter;  // service enginner --> adapter --> UserlistAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ivBack=findViewById(R.id.ivBack);
        rvUserList=findViewById(R.id.rvUserList);
        edSearch=findViewById(R.id.edSearch);


        CallUserList();
        userListAdapter=new UserListAdapter(this,userListModels);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvUserList.setAdapter(userListAdapter);
        rvUserList.setLayoutManager(linearLayoutManager);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userListAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    private void CallUserList() {
        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.ProgressBarDialog);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        UserListInterface userListInterface= APIClient.getClient().create(UserListInterface.class);
        userListInterface.calluserlist("getAllUserRecords").enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                try {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        userListAdapter.userListModels=response.body().getUserListModels();
                        userListAdapter.searchAdapter(response.body().getUserListModels());
                        userListAdapter.notifyDataSetChanged();
                    }

                }catch (Exception e){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }
}