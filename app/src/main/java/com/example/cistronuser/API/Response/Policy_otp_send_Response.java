package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class Policy_otp_send_Response {

    //  "Response": "1"    1-->success ,2--->not insert value ,3--->  not send mail

    @SerializedName("Response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
