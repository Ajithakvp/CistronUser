package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class HiddenValuesModel {

    //    "hpidd": "13465",
    //      "pdtidd": "90",
    //      "spc": 0,
    //      "cus_po": 0,
    //      "jsonPartIds": "",
    //      "chk1": 1

    @SerializedName("hpidd")
    private String hpidd;

    @SerializedName("pdtidd")
    private String pdtidd;

    @SerializedName("spc")
    private String spc;

    @SerializedName("chk1")
    private String chk1;

    @SerializedName("jsonPartIds")
    private String jsonPartIds;

    public String getJsonPartIds() {
        return jsonPartIds;
    }

    public void setJsonPartIds(String jsonPartIds) {
        this.jsonPartIds = jsonPartIds;
    }

    public String getHpidd() {
        return hpidd;
    }

    public void setHpidd(String hpidd) {
        this.hpidd = hpidd;
    }

    public String getPdtidd() {
        return pdtidd;
    }

    public void setPdtidd(String pdtidd) {
        this.pdtidd = pdtidd;
    }

    public String getSpc() {
        return spc;
    }

    public void setSpc(String spc) {
        this.spc = spc;
    }

    public String getChk1() {
        return chk1;
    }

    public void setChk1(String chk1) {
        this.chk1 = chk1;
    }
}
