package com.example.cistronuser.SalesAndservice.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cistronuser.API.APIClient;
import com.example.cistronuser.API.Interface.VisitEntryGetDistrictInterface;
import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.example.cistronuser.API.Response.VisitEntryGetDistrictResponse;
import com.example.cistronuser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitEntry extends AppCompatActivity {


    //District
    Spinner spDistrict;
    ArrayList<VisitEntryGetDistrictModel>visitEntryGetDistrictModels=new ArrayList<>();
    ArrayList<String>strDistrict=new ArrayList<>();
    ArrayAdapter districtAdapter;
    String District;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_entry);

        spDistrict=findViewById(R.id.spDistrict);

        callDistrict();
        districtAdapter=new ArrayAdapter(this,R.layout.spinner_item,strDistrict);
        districtAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spDistrict.setAdapter(districtAdapter);





    }

    private void callDistrict() {
        VisitEntryGetDistrictInterface visitEntryGetDistrictInterface= APIClient.getClient().create(VisitEntryGetDistrictInterface.class);
        visitEntryGetDistrictInterface.CallDistrict("getDistricts","Tamil Nadu").enqueue(new Callback<VisitEntryGetDistrictResponse>() {
            @Override
            public void onResponse(Call<VisitEntryGetDistrictResponse> call, Response<VisitEntryGetDistrictResponse> response) {
                try {
                    if (response.body().getVisitEntryGetDistrictModels().size()>0){
                        visitEntryGetDistrictModels=response.body().getVisitEntryGetDistrictModels();
                        for (int i=0;i<visitEntryGetDistrictModels.size();i++){
                            strDistrict.add(visitEntryGetDistrictModels.get(i).getDistrict());
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<VisitEntryGetDistrictResponse> call, Throwable t) {

            }
        });
    }
}