package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class AdjustmentExpensesResponse {


    @SerializedName("category")
    private String category;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
