package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SaleQuoteExistingUpdateModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SaleQuoteExistingUpdateResponse {

//     "action": "getQuoteUpdates",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<SaleQuoteExistingUpdateModel>saleQuoteExistingUpdateModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SaleQuoteExistingUpdateModel> getSaleQuoteExistingUpdateModels() {
        return saleQuoteExistingUpdateModels;
    }

    public void setSaleQuoteExistingUpdateModels(ArrayList<SaleQuoteExistingUpdateModel> saleQuoteExistingUpdateModels) {
        this.saleQuoteExistingUpdateModels = saleQuoteExistingUpdateModels;
    }
}
