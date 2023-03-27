package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.MyStockSEModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyStockSEResponse {


    // "action": "getSeriesData",
    //  "records": [

    @SerializedName("action")
    private String action;

    @SerializedName("records")
    private ArrayList<MyStockSEModel>myStockSEModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<MyStockSEModel> getMyStockSEModels() {
        return myStockSEModels;
    }

    public void setMyStockSEModels(ArrayList<MyStockSEModel> myStockSEModels) {
        this.myStockSEModels = myStockSEModels;
    }
}
