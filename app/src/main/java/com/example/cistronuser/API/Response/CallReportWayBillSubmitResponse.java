package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CallReportWayBillSubmitResponse {

    //{"action":"uploadWayBill","wb":null,"uploadStatus":null}

    @SerializedName("action")
    private String action;

    @SerializedName("wb")
    private String wb;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }
}
