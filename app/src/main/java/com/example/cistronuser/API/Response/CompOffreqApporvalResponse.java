package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CompOffreqApporvalResponse {



    @SerializedName("category")
    private String category;

    @SerializedName("message")
    private String message;

    @SerializedName("success")
    private String success;

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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
