package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class MonthlyReportAttendanceModel {


//     "sno": 1,
//             "date": "2022-11-01",
//             "status": "Office work - Trichy",
//             "city": "Trichy"



    @SerializedName("sno")
    private String sno;

    @SerializedName("date")
    private String date;

    @SerializedName("status")
    private String status;

    @SerializedName("city")
    private String city;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
