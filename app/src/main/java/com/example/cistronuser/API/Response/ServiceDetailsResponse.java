package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ServiceDetailsInterModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceDetailsResponse {

//     "action": "getCallHistory",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<ServiceDetailsInterModel>serviceDetailsInterModels;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ServiceDetailsInterModel> getServiceDetailsInterModels() {
        return serviceDetailsInterModels;
    }

    public void setServiceDetailsInterModels(ArrayList<ServiceDetailsInterModel> serviceDetailsInterModels) {
        this.serviceDetailsInterModels = serviceDetailsInterModels;
    }
}
