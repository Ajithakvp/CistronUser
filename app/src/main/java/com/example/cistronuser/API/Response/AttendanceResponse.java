package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class AttendanceResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
