package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class AppVersionResponse {

//     "category": "getAppVersion",
//             "message": "cistron 1.0",
//             "error": 0

    @SerializedName("category")
    private String category;

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
