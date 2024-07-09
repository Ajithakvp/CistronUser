package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ViewSwapindialogModel {
    //"sno": 1,
    //      "part_no": "testconsumablesonly",
    //      "name": "testconsumablesonly",
    //      "return_qty": "1"

    @SerializedName("sno")
    private String sno;

    @SerializedName("part_no")
    private String part_no;

    @SerializedName("name")
    private String name;

    @SerializedName("return_qty")
    private String return_qty;

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

    public String getReturn_qty() {
        return return_qty;
    }

    public void setReturn_qty(String return_qty) {
        this.return_qty = return_qty;
    }
}
