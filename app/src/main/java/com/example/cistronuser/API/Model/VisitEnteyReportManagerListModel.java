package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEnteyReportManagerListModel {


// "empid": "e032",
//         "name": "E032 : S P Jain"


    @SerializedName("empid")
    private String empid;

    @SerializedName("name")
    private String name;

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
}
