package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ServiceSpareRequestModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceSpareRequestResponse {


//    "action": "onRequireSparesCallStatus",
//            "reponse": [

    @SerializedName("action")
    private String action;

    @SerializedName("reponse")
    private ArrayList<ServiceSpareRequestModel>serviceSpareRequestModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ServiceSpareRequestModel> getServiceSpareRequestModels() {
        return serviceSpareRequestModels;
    }

    public void setServiceSpareRequestModels(ArrayList<ServiceSpareRequestModel> serviceSpareRequestModels) {
        this.serviceSpareRequestModels = serviceSpareRequestModels;
    }
}
