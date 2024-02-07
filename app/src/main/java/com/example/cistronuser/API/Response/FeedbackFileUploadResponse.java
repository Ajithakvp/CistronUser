package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class FeedbackFileUploadResponse {

    //"status":"Data Upload Success Fully"

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
