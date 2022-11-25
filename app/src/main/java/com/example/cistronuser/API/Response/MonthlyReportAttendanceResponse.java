package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.MonthlyReportAttendanceModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MonthlyReportAttendanceResponse {

//    "category": "attendanceReport",
//    "empid": "e367",
//            "name": "VELMURUGAN P",
//            "report": [


    @SerializedName("category")
    private String category;

    @SerializedName("empid")
    private String empid;
    @SerializedName("name")
    private String name;

    @SerializedName("report")
    private ArrayList<MonthlyReportAttendanceModel>monthlyReportAttendanceModels;


    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<MonthlyReportAttendanceModel> getMonthlyReportAttendanceModels() {
        return monthlyReportAttendanceModels;
    }

    public void setMonthlyReportAttendanceModels(ArrayList<MonthlyReportAttendanceModel> monthlyReportAttendanceModels) {
        this.monthlyReportAttendanceModels = monthlyReportAttendanceModels;
    }
}
