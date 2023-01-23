package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LabelModel {

    //  "label1": "Received pending sales payment",
    //      "label2": "Sales Amount"


    @SerializedName("label1")
    private String label1;

    @SerializedName("label2")
    private String label2;

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }
}
