package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SwapOutwardMainModel {

    // "id": "177",
    //      "req_no": "2024042015RTEST177",
    //      "return_date": "2024-04-24",
    //      "req_by": "test",
    //      "part_ids": 1,
    //      "secondeng": "Karthick Srinivasan (E261)",
    //      "opt": "1"

    @SerializedName("id")
    private String id;

    @SerializedName("req_no")
    private String req_no;

    @SerializedName("return_date")
    private String return_date;

    @SerializedName("req_by")
    private String req_by;

    @SerializedName("part_ids")
    private String part_ids;

    @SerializedName("secondeng")
    private String secondeng;

    @SerializedName("opt")
    private String opt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReq_no() {
        return req_no;
    }

    public void setReq_no(String req_no) {
        this.req_no = req_no;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getReq_by() {
        return req_by;
    }

    public void setReq_by(String req_by) {
        this.req_by = req_by;
    }

    public String getPart_ids() {
        return part_ids;
    }

    public void setPart_ids(String part_ids) {
        this.part_ids = part_ids;
    }

    public String getSecondeng() {
        return secondeng;
    }

    public void setSecondeng(String secondeng) {
        this.secondeng = secondeng;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
