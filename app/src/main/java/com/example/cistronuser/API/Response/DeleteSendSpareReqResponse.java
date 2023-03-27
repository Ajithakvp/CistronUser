package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class DeleteSendSpareReqResponse {

    // "action": "deleteFromSpareRequestsQueue",
    //  "count": 0

    @SerializedName("action")
    private String action;

    @SerializedName("count")
    private String count;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
