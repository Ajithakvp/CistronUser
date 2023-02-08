package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class InstallamentModel {

    //"amtIns": "1000",
    //            "amtInsr": "0",
    //            "sno": 1

    @SerializedName("amtIns")
    private String amtIns;

    @SerializedName("amtInsr")
    private String amtInsr;

    @SerializedName("sno")
    private String sno;


    public String getAmtIns() {
        return amtIns;
    }

    public void setAmtIns(String amtIns) {
        this.amtIns = amtIns;
    }

    public String getAmtInsr() {
        return amtInsr;
    }

    public void setAmtInsr(String amtInsr) {
        this.amtInsr = amtInsr;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
