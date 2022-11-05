package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class AttendanceMessageModel {
// "message": "no attendance",
//         "category": "no attendance"
    @SerializedName("message")
    private String message;
    @SerializedName("category")
    private String category;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
