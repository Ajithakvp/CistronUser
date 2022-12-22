package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteApprovalListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SalesQuoteApprovalListResponse {

//     "action": "getOrderCallforApproval",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<SalesQuoteApprovalListModel>salesQuoteApprovalListModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SalesQuoteApprovalListModel> getSalesQuoteApprovalListModels() {
        return salesQuoteApprovalListModels;
    }

    public void setSalesQuoteApprovalListModels(ArrayList<SalesQuoteApprovalListModel> salesQuoteApprovalListModels) {
        this.salesQuoteApprovalListModels = salesQuoteApprovalListModels;
    }
}
