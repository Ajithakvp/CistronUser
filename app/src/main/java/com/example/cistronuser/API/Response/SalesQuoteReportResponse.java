package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteReportModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteReportResponse {

    // "action": "getQuoteReport",
    //    "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<SalesQuoteReportModel>salesQuoteReportModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SalesQuoteReportModel> getSalesQuoteReportModels() {
        return salesQuoteReportModels;
    }

    public void setSalesQuoteReportModels(ArrayList<SalesQuoteReportModel> salesQuoteReportModels) {
        this.salesQuoteReportModels = salesQuoteReportModels;
    }
}
