package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class TransportModel {

    // "id": "1",
    //            "text": "By hand",
    //            "selected": 0

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("selected")
    private String selected;

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

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
