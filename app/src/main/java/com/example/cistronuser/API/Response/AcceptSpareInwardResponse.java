package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class AcceptSpareInwardResponse {

    //"action": "acceptSparesInward",
    //    "response": "1"

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private String response;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
