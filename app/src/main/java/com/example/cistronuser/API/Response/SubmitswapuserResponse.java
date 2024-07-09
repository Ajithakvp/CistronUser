package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SubmitswapuserResponse {

    //  "Response": "1"

    @SerializedName("Response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
