package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.LeaveReportMonthlyModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaveReportMonthlyResponse {




    @SerializedName("response")
    private ArrayList<LeaveReportMonthlyModel>leaveReportMonthlyModels;

    public ArrayList<LeaveReportMonthlyModel> getLeaveReportMonthlyModels() {
        return leaveReportMonthlyModels;
    }

    public void setLeaveReportMonthlyModels(ArrayList<LeaveReportMonthlyModel> leaveReportMonthlyModels) {
        this.leaveReportMonthlyModels = leaveReportMonthlyModels;
    }
}
