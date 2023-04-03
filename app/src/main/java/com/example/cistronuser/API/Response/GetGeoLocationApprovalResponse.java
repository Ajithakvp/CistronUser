package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.GetGeoLocationApprovalModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetGeoLocationApprovalResponse {

    // "action": "getGeolocationApprovalReq",
    //  "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<GetGeoLocationApprovalModel>getGeoLocationApprovalModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<GetGeoLocationApprovalModel> getGetGeoLocationApprovalModels() {
        return getGeoLocationApprovalModels;
    }

    public void setGetGeoLocationApprovalModels(ArrayList<GetGeoLocationApprovalModel> getGeoLocationApprovalModels) {
        this.getGeoLocationApprovalModels = getGeoLocationApprovalModels;
    }
}
