package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CreateSendSpareReqSubmitResponse {


    // "action": "submitSpareRequestsQueue",
    //  "result": "1",
    //  "message": "Spare Request Submitted"

    @SerializedName("action")
    private String action;

    @SerializedName("result")
    private String result;

    @SerializedName("message")
    private String message;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
