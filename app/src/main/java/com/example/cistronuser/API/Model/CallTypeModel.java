package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CallTypeModel {

//     "id": "1",
//             "text": "AMC(2022-02-03 to 2023-02-02)",
//             "selected": "1"


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
