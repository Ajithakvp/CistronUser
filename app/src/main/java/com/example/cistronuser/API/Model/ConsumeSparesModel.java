package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConsumeSparesModel {


    //title": "CONSUME SPARES",
    //    "count": 6,
    //    "sqid": "988",
    //    "payopt": "4",
    //    "id": "7522~12806~19141~dashboard~260~up",
    //    "button": "Consume",
    //    "record": [

    @SerializedName("title")
    private String title;

    @SerializedName("count")
    private String count;

    @SerializedName("sqid")
    private String sqid;

    @SerializedName("payopt")
    private String payopt;

    @SerializedName("id")
    private String id;

    @SerializedName("button")
    private String button;

    @SerializedName("record")
    private ArrayList<ConsumeSpareRecordModel>consumeSpareRecordModels=new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getPayopt() {
        return payopt;
    }

    public void setPayopt(String payopt) {
        this.payopt = payopt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public ArrayList<ConsumeSpareRecordModel> getConsumeSpareRecordModels() {
        return consumeSpareRecordModels;
    }

    public void setConsumeSpareRecordModels(ArrayList<ConsumeSpareRecordModel> consumeSpareRecordModels) {
        this.consumeSpareRecordModels = consumeSpareRecordModels;
    }
}
