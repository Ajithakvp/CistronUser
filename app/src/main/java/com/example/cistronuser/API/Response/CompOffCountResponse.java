package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class CompOffCountResponse {

    @SerializedName("category")
    private String category;

    @SerializedName("count")
    private String count;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
