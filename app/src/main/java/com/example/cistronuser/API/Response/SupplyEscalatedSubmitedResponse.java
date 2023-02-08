package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SupplyEscalatedSubmitedResponse {

    //  "action": "onSupplyCallEscalate",
    //    "response": "Supply call payment escalated and sent for approval"

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
