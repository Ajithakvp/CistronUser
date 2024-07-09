package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ServiceEnguserModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceEnguserResponse {

//  "response": "Engineer list",
//          "Data": [

    @SerializedName("response")
    private String response;


    @SerializedName("Data")
    private ArrayList<ServiceEnguserModel>serviceEnguserModels=new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<ServiceEnguserModel> getServiceEnguserModels() {
        return serviceEnguserModels;
    }

    public void setServiceEnguserModels(ArrayList<ServiceEnguserModel> serviceEnguserModels) {
        this.serviceEnguserModels = serviceEnguserModels;
    }
}
