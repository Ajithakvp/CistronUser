package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.DailyReportAttendanceModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyReportAttendanceResponse {

    @SerializedName("category")
    private String category;
    @SerializedName("report")
    private ArrayList<DailyReportAttendanceModel>dailyReportAttendanceModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<DailyReportAttendanceModel> getDailyReportAttendanceModels() {
        return dailyReportAttendanceModels;
    }

    public void setDailyReportAttendanceModels(ArrayList<DailyReportAttendanceModel> dailyReportAttendanceModels) {
        this.dailyReportAttendanceModels = dailyReportAttendanceModels;
    }
}
