package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteCategoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteCategoryResponse {


//     "action": "getAvailableCategory",
//             "categories": [

    @SerializedName("action")
    private String action;

    @SerializedName("categories")
    private ArrayList<SalesQuoteCategoryModel>salesQuoteCategoryModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SalesQuoteCategoryModel> getSalesQuoteCategoryModels() {
        return salesQuoteCategoryModels;
    }

    public void setSalesQuoteCategoryModels(ArrayList<SalesQuoteCategoryModel> salesQuoteCategoryModels) {
        this.salesQuoteCategoryModels = salesQuoteCategoryModels;
    }
}
