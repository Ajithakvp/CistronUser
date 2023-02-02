package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallReportsubmitCusInvoiceResponse {

    //"action": "uploadCustomerInvoice",
    //    "ps": 0,

    @SerializedName("action")
    private String action;

    @SerializedName("ps")
    private String ps;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
