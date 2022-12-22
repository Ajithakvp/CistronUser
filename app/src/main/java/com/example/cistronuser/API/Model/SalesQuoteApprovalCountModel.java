package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteApprovalCountModel {

   //  "oaApproval": "2"


    @SerializedName("oaApproval")
    private String oaApproval;

    public String getOaApproval() {
        return oaApproval;
    }

    public void setOaApproval(String oaApproval) {
        this.oaApproval = oaApproval;
    }
}
