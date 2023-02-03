package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ConsumeCustSparesModel {


    //"title": "CONSUME CUSTOMER SPARES",
    //    "count": 2,
    //    "record": [

    @SerializedName("title")
    private String title;

    @SerializedName("count")
    private String count;

    @SerializedName("record")
    private ArrayList<ConsumerCustSpareRecordModel>consumerCustSpareRecordModels=new ArrayList<>();


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

    public ArrayList<ConsumerCustSpareRecordModel> getConsumerCustSpareRecordModels() {
        return consumerCustSpareRecordModels;
    }

    public void setConsumerCustSpareRecordModels(ArrayList<ConsumerCustSpareRecordModel> consumerCustSpareRecordModels) {
        this.consumerCustSpareRecordModels = consumerCustSpareRecordModels;
    }
}
