package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class VisitEntryAddResponse {

//    "action": "addVisitEntry",
//            "response": "success",
//            "message": "Visit Entry Added Successfully"

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private String response;

    @SerializedName("message")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
