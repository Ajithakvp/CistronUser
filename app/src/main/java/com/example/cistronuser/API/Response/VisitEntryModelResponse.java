package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryHospitalModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryModelResponse {

//     "action": "getStateHospitals",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<VisitEntryHospitalModel>visitEntryHospitalModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryHospitalModel> getVisitEntryHospitalModels() {
        return visitEntryHospitalModels;
    }

    public void setVisitEntryHospitalModels(ArrayList<VisitEntryHospitalModel> visitEntryHospitalModels) {
        this.visitEntryHospitalModels = visitEntryHospitalModels;
    }
}
