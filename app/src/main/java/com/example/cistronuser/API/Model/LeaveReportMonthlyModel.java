package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LeaveReportMonthlyModel {



    //     "employee": "E367 : VELMURUGAN P",
//             "date": "2022-10-22\n2022-10-28\n",
//             "leavetype": "CL\nLOP\n",
//             "reason": "Family Occasions\nFamily Occasions\n",
//             "fdhd": "Full Day\nFull Day\n"


    @SerializedName("employee")
    private String employee;

    @SerializedName("date")
    private String date;

    @SerializedName("leavetype")
    private String leavetype;

    @SerializedName("reason")
    private String reason;

    @SerializedName("fdhd")
    private String fdhd;

    @SerializedName("leave_info")
    private String leave_info;

    @SerializedName("total")
    private String total;

    public String getLeave_info() {
        return leave_info;
    }

    public void setLeave_info(String leave_info) {
        this.leave_info = leave_info;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFdhd() {
        return fdhd;
    }

    public void setFdhd(String fdhd) {
        this.fdhd = fdhd;
    }
}
