package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ServiceSpareListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceSpareResponse {

    // "action": "getSparePartsList",
    //    "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<ServiceSpareListModel>serviceSpareListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<ServiceSpareListModel> getServiceSpareListModels() {
        return serviceSpareListModels;
    }

    public void setServiceSpareListModels(ArrayList<ServiceSpareListModel> serviceSpareListModels) {
        this.serviceSpareListModels = serviceSpareListModels;
    }
}
