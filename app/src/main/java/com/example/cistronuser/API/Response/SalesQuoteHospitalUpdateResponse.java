package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteHospitalUpdateModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteHospitalUpdateResponse {


//     "action": "viewAvailableSalesQuote",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<SalesQuoteHospitalUpdateModel>salesQuoteHospitalUpdateModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SalesQuoteHospitalUpdateModel> getSalesQuoteHospitalUpdateModels() {
        return salesQuoteHospitalUpdateModels;
    }

    public void setSalesQuoteHospitalUpdateModels(ArrayList<SalesQuoteHospitalUpdateModel> salesQuoteHospitalUpdateModels) {
        this.salesQuoteHospitalUpdateModels = salesQuoteHospitalUpdateModels;
    }
}
