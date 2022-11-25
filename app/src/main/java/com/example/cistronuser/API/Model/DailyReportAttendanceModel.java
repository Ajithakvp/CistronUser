package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class DailyReportAttendanceModel {

//     "sno": 1,
//             "emp": "3233232: 32323232",
//             "status": null,
//             "city": null


    @SerializedName("sno")
    private String sno;

    @SerializedName("emp")
    private String emp;

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

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
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
