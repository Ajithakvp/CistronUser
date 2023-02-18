package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ReturnReqPendingReportCoModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReturnReqPendingReportCoResponse {

    //  "action": "returnReqPendingReport",
    //  "records": [

    @SerializedName("action")
    private String action;

    @SerializedName("records")
    private ArrayList<ReturnReqPendingReportCoModel>returnReqPendingReportCoModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ReturnReqPendingReportCoModel> getReturnReqPendingReportCoModels() {
        return returnReqPendingReportCoModels;
    }

    public void setReturnReqPendingReportCoModels(ArrayList<ReturnReqPendingReportCoModel> returnReqPendingReportCoModels) {
        this.returnReqPendingReportCoModels = returnReqPendingReportCoModels;
    }
}
