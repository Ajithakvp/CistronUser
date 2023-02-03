package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerPospareModel {

    //"title": "CUSTOMER PO SPARES",
    //    "count": 1,
    //    "record": [

    @SerializedName("title")
    private String title;

    @SerializedName("count")
    private String count;

    @SerializedName("record")
    private ArrayList<CustomerPoSpareRecordModel>customerPoSpareRecordModels=new ArrayList<>();

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

    public ArrayList<CustomerPoSpareRecordModel> getCustomerPoSpareRecordModels() {
        return customerPoSpareRecordModels;
    }

    public void setCustomerPoSpareRecordModels(ArrayList<CustomerPoSpareRecordModel> customerPoSpareRecordModels) {
        this.customerPoSpareRecordModels = customerPoSpareRecordModels;
    }
}
