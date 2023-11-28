package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CloseFromLocChkResponse {

    //  "response": "21171"

    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
