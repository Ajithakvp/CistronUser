package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareInwardModel {

    //"count": 0,
    //      "record": [

    @SerializedName("count")
    private String  count;

    @SerializedName("record")
    private ArrayList<SpareInwardRecordModel>spareInwardRecordModels=new ArrayList<>();

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<SpareInwardRecordModel> getSpareInwardRecordModels() {
        return spareInwardRecordModels;
    }

    public void setSpareInwardRecordModels(ArrayList<SpareInwardRecordModel> spareInwardRecordModels) {
        this.spareInwardRecordModels = spareInwardRecordModels;
    }
}
