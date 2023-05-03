package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class HolidaylistModel {


//      "id": "131",
//              "branch": "4",
//              "text": "Telugu New Year / Ugadi",
//              "active": "1",
//              "timestamp": "2023-03-21 22:54:55",
//              "year": "2023",
//              "datein": "2023-03-22"

    @SerializedName("id")
    public String id;

    @SerializedName("branch")
    public String branch;

    @SerializedName("text")
    public String text;

    @SerializedName("active")
    public String active;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("year")
    public String year;

    @SerializedName("datein")
    public String datein;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDatein() {
        return datein;
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }
}
