package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class CallReportComplaintSubCategoryModel {

//       "id": "634",
//               "text": "Dryer Not Working"

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("selected")
    private String selected;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

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
