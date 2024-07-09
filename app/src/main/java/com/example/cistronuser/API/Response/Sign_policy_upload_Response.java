package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class Sign_policy_upload_Response {

    //  "response": "3"


    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
