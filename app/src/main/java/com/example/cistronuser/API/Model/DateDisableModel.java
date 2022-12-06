package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class DateDisableModel {


    @SerializedName("date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
