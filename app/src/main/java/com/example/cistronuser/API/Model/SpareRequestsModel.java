package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareRequestsModel {

    //"count": 1,
    //      "record": {


    @SerializedName("count")
    private String count;

    @SerializedName("record")
    private ArrayList<SpareRequestsRecordModel>spareRequestsRecordModels=new ArrayList<>();

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<SpareRequestsRecordModel> getSpareRequestsRecordModels() {
        return spareRequestsRecordModels;
    }

    public void setSpareRequestsRecordModels(ArrayList<SpareRequestsRecordModel> spareRequestsRecordModels) {
        this.spareRequestsRecordModels = spareRequestsRecordModels;
    }
}
