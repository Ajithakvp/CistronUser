package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class DeleteSwaplistResponse {

    @SerializedName("Responsechk")
    private String Responsechk;

    public String getResponsechk() {
        return Responsechk;
    }

    public void setResponsechk(String responsechk) {
        Responsechk = responsechk;
    }
}
