package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ReportExpensesModel {

//     "empid": "tempdandu",
//             "name": " Dandu Narashimha Ganesh",
//             "startdate": "2022-11-06",
//             "enddate": "2022-11-12",
//             "timestamp": "2022-11-18 18:09:20"

    @SerializedName("empid")
    private String empid;
    @SerializedName("name")
    private String name;
    @SerializedName("startdate")
    private String startdate;
    @SerializedName("enddate")
    private String enddate;
    @SerializedName("timestamp")
    private String timestamp;

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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
