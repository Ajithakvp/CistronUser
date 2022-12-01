package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class leavesubmitresponse {

    @SerializedName("category")
    private String category;
    @SerializedName("message")
    private String message;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
