package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SpareReqPendingReportCoModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareReqPendingReportCoResponse {

    //"action": "spareReqPending",
    // "user": "admin"
    //    "records": [

    @SerializedName("action")
    private String action;

    @SerializedName("records")
    private ArrayList<SpareReqPendingReportCoModel>spareReqPendingReportCoModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SpareReqPendingReportCoModel> getSpareReqPendingReportCoModels() {
        return spareReqPendingReportCoModels;
    }

    public void setSpareReqPendingReportCoModels(ArrayList<SpareReqPendingReportCoModel> spareReqPendingReportCoModels) {
        this.spareReqPendingReportCoModels = spareReqPendingReportCoModels;
    }
}
