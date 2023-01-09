package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.UpcomingCallReportModel;
import com.google.gson.annotations.SerializedName;

public class UpcomingCallReportResponse {

//    "action": "callReporting",
//            "response": {


    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private UpcomingCallReportModel upcomingCallReportModel;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public UpcomingCallReportModel getUpcomingCallReportModel() {
        return upcomingCallReportModel;
    }

    public void setUpcomingCallReportModel(UpcomingCallReportModel upcomingCallReportModel) {
        this.upcomingCallReportModel = upcomingCallReportModel;
    }
}
