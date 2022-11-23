package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class UserWeeklyReportExpensesMWModel {


    //     "employee": "All",
//             "startdate": ""

    @SerializedName("employee")
    private String employee;

    @SerializedName("startdate")
    private String startdate;


    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
}

