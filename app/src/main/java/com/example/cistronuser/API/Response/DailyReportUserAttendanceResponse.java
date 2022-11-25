package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.DailyReportAttendanceModel;
import com.example.cistronuser.API.Model.DailyReportUserAttendanceModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DailyReportUserAttendanceResponse {


    @SerializedName("category")
    private String category;
    @SerializedName("report")
    private ArrayList<DailyReportUserAttendanceModel> dailyReportUserAttendanceModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<DailyReportUserAttendanceModel> getDailyReportUserAttendanceModels() {
        return dailyReportUserAttendanceModels;
    }

    public void setDailyReportUserAttendanceModels(ArrayList<DailyReportUserAttendanceModel> dailyReportUserAttendanceModels) {
        this.dailyReportUserAttendanceModels = dailyReportUserAttendanceModels;
    }
}
