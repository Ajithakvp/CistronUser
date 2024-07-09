package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class Accept_policy_otp_Response {
    //  "response": "1"      2---> sign not upload  1--->success  0--->contact admin

    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
