package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerPoModel {
    //"count": 0,
    //      "response": [

    @SerializedName("count")
    private String count;

    @SerializedName("response")
    private ArrayList<CustomerPoResponseModel>customerPoResponseModels=new ArrayList<>();

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<CustomerPoResponseModel> getCustomerPoResponseModels() {
        return customerPoResponseModels;
    }

    public void setCustomerPoResponseModels(ArrayList<CustomerPoResponseModel> customerPoResponseModels) {
        this.customerPoResponseModels = customerPoResponseModels;
    }
}
