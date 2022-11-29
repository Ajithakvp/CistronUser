package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.LeaveReportDailyModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaveReportDailyResponse {


//     "date": "28-11-2022",
//             "day": "Monday",
//             "count": 23,
//             "records": [

    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private String day;
    @SerializedName("count")
    private String count;

    @SerializedName("attachBaseUrl")
    private String attachBaseUrl;

    @SerializedName("records")
    private ArrayList<LeaveReportDailyModel>leaveReportDailyModels;

    public String getAttachBaseUrl() {
        return attachBaseUrl;
    }

    public void setAttachBaseUrl(String attachBaseUrl) {
        this.attachBaseUrl = attachBaseUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<LeaveReportDailyModel> getLeaveReportDailyModels() {
        return leaveReportDailyModels;
    }

    public void setLeaveReportDailyModels(ArrayList<LeaveReportDailyModel> leaveReportDailyModels) {
        this.leaveReportDailyModels = leaveReportDailyModels;
    }
}
