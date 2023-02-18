package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ReturnRequestViewCoModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReturnRequestViewCoResponse {



    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<ReturnRequestViewCoModel>returnRequestViewCoModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ReturnRequestViewCoModel> getReturnRequestViewCoModels() {
        return returnRequestViewCoModels;
    }

    public void setReturnRequestViewCoModels(ArrayList<ReturnRequestViewCoModel> returnRequestViewCoModels) {
        this.returnRequestViewCoModels = returnRequestViewCoModels;
    }
}
