package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class Verfiy_otp_policy_Response {

    //  "Response": "0"     1--->otp matched   0--->otp not matched

    @SerializedName("Response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
