package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SubmitSpareReqTmpResponse {

    //"action": "submitSpareRequestsTmp",
    //  "error": "1",
    //  "response": "No Spare Request Found"

    @SerializedName("action")
    private String action;

    @SerializedName("error")
    private String error;

    @SerializedName("response")
    private String response;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
