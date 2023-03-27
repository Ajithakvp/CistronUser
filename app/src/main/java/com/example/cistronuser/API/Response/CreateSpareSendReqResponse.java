package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CreateSpareSendReqResponse {

    //"action": "addToSpareRequestsQueue",
    //  "status": "1",
    //  "message": ""

    @SerializedName("action")
    private String action;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
