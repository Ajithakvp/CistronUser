package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteProductsModel {


//    "prodId": "556",
//            "seriesName": "Cistron Full SS Horizontal Steam Sterilizer - 16x24 Single Door (556ST01)",
//            "price": "300000",
//            "addons": [



    @SerializedName("prodId")
    private String prodId;

    @SerializedName("seriesName")
    private String seriesName;

    @SerializedName("price")
    private String price;

    @SerializedName("addons")
    private ArrayList<SalesQuoteProductsAddonModel>salesQuoteProductsAddonModels=new ArrayList<>();


    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<SalesQuoteProductsAddonModel> getSalesQuoteProductsAddonModels() {
        return salesQuoteProductsAddonModels;
    }

    public void setSalesQuoteProductsAddonModels(ArrayList<SalesQuoteProductsAddonModel> salesQuoteProductsAddonModels) {
        this.salesQuoteProductsAddonModels = salesQuoteProductsAddonModels;
    }
}
