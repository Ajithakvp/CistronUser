package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class ExpensePolicyResponse
{

//    "category": "checkExpPolicy",
//            "message": "accepted",
//            "title": "",
//            "policy": ""


    @SerializedName("category")
    private String category;

    @SerializedName("message")
    private String message;

    @SerializedName("title")
    private String title;

    @SerializedName("policy_ref")
    private String policy_ref;

    @SerializedName("policy")
    private String policy;

    public String getPolicy_ref() {
        return policy_ref;
    }

    public void setPolicy_ref(String policy_ref) {
        this.policy_ref = policy_ref;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}
