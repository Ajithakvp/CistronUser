package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryDoctorModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryDoctorResponse {

//     "action": "getChiefDr",
//             "response": [

    @SerializedName("action")
    private String action;
    @SerializedName("response")
    private ArrayList<VisitEntryDoctorModel>visitEntryDoctorModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryDoctorModel> getVisitEntryDoctorModels() {
        return visitEntryDoctorModels;
    }

    public void setVisitEntryDoctorModels(ArrayList<VisitEntryDoctorModel> visitEntryDoctorModels) {
        this.visitEntryDoctorModels = visitEntryDoctorModels;
    }
}
