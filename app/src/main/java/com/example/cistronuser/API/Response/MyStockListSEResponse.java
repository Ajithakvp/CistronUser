package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.MyStockListSEModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyStockListSEResponse {

    //"action": "mystock",
    //  "count": 5,
    //  "records": [

    @SerializedName("action")
    private String action;
    @SerializedName("count")
    private String count;
    @SerializedName("records")
    private ArrayList<MyStockListSEModel>myStockListSEModels=new ArrayList<>();

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

    public ArrayList<MyStockListSEModel> getMyStockListSEModels() {
        return myStockListSEModels;
    }

    public void setMyStockListSEModels(ArrayList<MyStockListSEModel> myStockListSEModels) {
        this.myStockListSEModels = myStockListSEModels;
    }
}
