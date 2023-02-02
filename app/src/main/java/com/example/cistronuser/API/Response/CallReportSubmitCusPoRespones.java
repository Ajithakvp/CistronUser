package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallReportSubmitCusPoRespones {

//     "action": "uploadInvoice",
//             "sq": 1,

    @SerializedName("action")
    private String action;

    @SerializedName("sq")
    private String sq;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }
}
