package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryProductModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryProductResponse {

//    "action": "getAvailProd",
//            "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<VisitEntryProductModel>visitEntryProductModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryProductModel> getVisitEntryProductModels() {
        return visitEntryProductModels;
    }

    public void setVisitEntryProductModels(ArrayList<VisitEntryProductModel> visitEntryProductModels) {
        this.visitEntryProductModels = visitEntryProductModels;
    }
}
