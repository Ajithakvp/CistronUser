package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class DailyReportUserAttendanceModel {

    @SerializedName("All")
    private String All;

    public String getAll() {
        return All;
    }

    public void setAll(String all) {
        All = all;
    }
}
