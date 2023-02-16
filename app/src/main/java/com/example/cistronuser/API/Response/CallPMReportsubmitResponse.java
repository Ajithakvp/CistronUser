package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallPMReportsubmitResponse {


    //{"action":"uploadPMReport","uploadStatus":[],"pm":1}

    @SerializedName("action")
    private String action;

    @SerializedName("pm")
    private String pm;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
