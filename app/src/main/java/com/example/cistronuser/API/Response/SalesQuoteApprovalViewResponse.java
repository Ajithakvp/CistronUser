package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteApprovalViewModel;
import com.google.gson.annotations.SerializedName;

public class SalesQuoteApprovalViewResponse {

//     "action": "viewOrderCall",
//             "reponse": {

    @SerializedName("action")
    private String action;


    @SerializedName("reponse")
    private SalesQuoteApprovalViewModel salesQuoteApprovalViewModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SalesQuoteApprovalViewModel getSalesQuoteApprovalViewModel() {
        return salesQuoteApprovalViewModel;
    }

    public void setSalesQuoteApprovalViewModel(SalesQuoteApprovalViewModel salesQuoteApprovalViewModel) {
        this.salesQuoteApprovalViewModel = salesQuoteApprovalViewModel;
    }
}
