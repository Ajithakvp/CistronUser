package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class DailyReportUserAttendanceModel {

    @SerializedName("employee")
    private String employee;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
