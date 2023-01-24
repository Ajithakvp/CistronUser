package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SparesConsumedModel {


    //"count": 3,
    //            "record": [

    @SerializedName("count")
    private String count;

    @SerializedName("record")
    private ArrayList<SparesConsumedRecordModel>sparesConsumedRecordModels=new ArrayList<>();

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<SparesConsumedRecordModel> getSparesConsumedRecordModels() {
        return sparesConsumedRecordModels;
    }

    public void setSparesConsumedRecordModels(ArrayList<SparesConsumedRecordModel> sparesConsumedRecordModels) {
        this.sparesConsumedRecordModels = sparesConsumedRecordModels;
    }
}
