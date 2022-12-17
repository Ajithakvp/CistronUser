package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SalesQuotesUpdateFinalizeResponse {


//    "action": "finalizeQuote",
//            "error": 1,
//            "message": "PO Not Uploaded, Plz Try Again"

    @SerializedName("action")
    private String action;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
