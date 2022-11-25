package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {

//     "category": "deleteLeave",
//             "message": "Leave ID(13) not found in main_leave"

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
