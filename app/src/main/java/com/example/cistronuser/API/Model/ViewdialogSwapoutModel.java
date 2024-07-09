package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ViewdialogSwapoutModel {

    //"sno": 1,
    //      "part_no": "testing",
    //      "name": "testing",
    //      "req_qty1": "1"

    @SerializedName("sno")
    private String sno;

    @SerializedName("part_no")
    private String part_no;

    @SerializedName("name")
    private String name;

    @SerializedName("req_qty1")
    private String req_qty1;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReq_qty1() {
        return req_qty1;
    }

    public void setReq_qty1(String req_qty1) {
        this.req_qty1 = req_qty1;
    }
}
