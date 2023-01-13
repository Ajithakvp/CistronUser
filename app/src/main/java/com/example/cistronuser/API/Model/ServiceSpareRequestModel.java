package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ServiceSpareRequestModel {


//     "id": "2",
//             "text": "Anaesthesia machine"


    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
