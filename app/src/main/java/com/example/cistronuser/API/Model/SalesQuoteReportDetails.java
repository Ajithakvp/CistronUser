package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteReportDetails {

//    "date": "2022-12-24",
//            "status": "Finalized",
//            "remarks": "",
//            "person": "S P Jain (e032)"

    @SerializedName("date")
    private String date;

    @SerializedName("status")
    private String status;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("person")
    private String person;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}

