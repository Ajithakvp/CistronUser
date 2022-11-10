package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LeavedetailsModel {


//    "id": "7",
//            "date": "2022-11-11",
//            "day": "Friday",
//            "leave_type": "Privilege Leave(LOP)",
//            "reason": "Personal Emergency",
//            "fd_hd": "Full Day",
//            "attachment": null

    @SerializedName("id")
    private String id;

    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private String day;

    @SerializedName("leave_type")
    private String leave_type;

    @SerializedName("reason")
    private String reason;

    @SerializedName("fd_hd")
    private String fd_hd;

    @SerializedName("attachment")
    private String attachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFd_hd() {
        return fd_hd;
    }

    public void setFd_hd(String fd_hd) {
        this.fd_hd = fd_hd;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
