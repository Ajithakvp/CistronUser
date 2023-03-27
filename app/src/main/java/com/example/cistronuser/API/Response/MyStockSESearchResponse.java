package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.MyStockSESearchModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyStockSESearchResponse {

    // "action": "mystock",
    //  "count": 1,
    //  "records": [

    @SerializedName("action")
    private String action;

    @SerializedName("count")
    private String count;

    @SerializedName("records")
    private ArrayList<MyStockSESearchModel>myStockSESearchModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<MyStockSESearchModel> getMyStockSESearchModels() {
        return myStockSESearchModels;
    }

    public void setMyStockSESearchModels(ArrayList<MyStockSESearchModel> myStockSESearchModels) {
        this.myStockSESearchModels = myStockSESearchModels;
    }
}
