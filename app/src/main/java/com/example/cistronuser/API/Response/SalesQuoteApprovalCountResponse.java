package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SalesQuoteApprovalCountModel;
import com.google.gson.annotations.SerializedName;

public class SalesQuoteApprovalCountResponse {

//     "action": "getApprovalCounts",
//             "response": {
//        "oaApproval": "2"
    //}


    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private SalesQuoteApprovalCountModel salesQuoteApprovalCountModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SalesQuoteApprovalCountModel getSalesQuoteApprovalCountModel() {
        return salesQuoteApprovalCountModel;
    }

    public void setSalesQuoteApprovalCountModel(SalesQuoteApprovalCountModel salesQuoteApprovalCountModel) {
        this.salesQuoteApprovalCountModel = salesQuoteApprovalCountModel;
    }
}
