package com.example.cistronuser.API.Response;

import android.widget.ArrayAdapter;

import com.example.cistronuser.API.Model.VisitEntryGetDistrictModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryGetDistrictResponse {

//     "action": "getDistricts",
//             "response": [

    @SerializedName("action")
    private String action;
    @SerializedName("response")
    private ArrayList<VisitEntryGetDistrictModel>visitEntryGetDistrictModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryGetDistrictModel> getVisitEntryGetDistrictModels() {
        return visitEntryGetDistrictModels;
    }

    public void setVisitEntryGetDistrictModels(ArrayList<VisitEntryGetDistrictModel> visitEntryGetDistrictModels) {
        this.visitEntryGetDistrictModels = visitEntryGetDistrictModels;
    }
}
