package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SpareInwardCoModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareInwardCoResponse {

    // "action": "spareInwardReport",
    //    "records": [

    @SerializedName("action")
    private String action;

    @SerializedName("records")
    private ArrayList<SpareInwardCoModel>spareInwardCoModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SpareInwardCoModel> getSpareInwardCoModels() {
        return spareInwardCoModels;
    }

    public void setSpareInwardCoModels(ArrayList<SpareInwardCoModel> spareInwardCoModels) {
        this.spareInwardCoModels = spareInwardCoModels;
    }
}
