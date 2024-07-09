package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class LeavePolicyResponse {
//    category": "leave policy",
//            "message": "Leave for Probationary employees is 1 day per month."

    @SerializedName("category")
    private String category;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("policy")
    private String policy;


    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
