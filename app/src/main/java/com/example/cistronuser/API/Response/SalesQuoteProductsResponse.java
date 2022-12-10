package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteProductsModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteProductsResponse {

//      "action": "getProductOfTheSeries",
//              "product": [



    @SerializedName("action")
    private String action;

    @SerializedName("product")
    private ArrayList<SalesQuoteProductsModel>salesQuoteProductsModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SalesQuoteProductsModel> getSalesQuoteProductsModels() {
        return salesQuoteProductsModels;
    }

    public void setSalesQuoteProductsModels(ArrayList<SalesQuoteProductsModel> salesQuoteProductsModels) {
        this.salesQuoteProductsModels = salesQuoteProductsModels;
    }
}
