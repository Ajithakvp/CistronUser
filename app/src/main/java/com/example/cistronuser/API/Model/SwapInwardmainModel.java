package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SwapInwardmainModel {
    // "id": "176",
    //      "req_no": "2024041811RE261176",
    //      "return_date": "2024-04-18",
    //      "part_ids_count": 1,
    //      "opt": "1",
    //      "req_by": "e261",
    //      "req_by_name": "Karthick Srinivasan"

    @SerializedName("id")
    private String id;

    @SerializedName("req_no")
    private String req_no;

    @SerializedName("return_date")
    private String return_date;

    @SerializedName("part_ids_count")
    private String part_ids_count;

    @SerializedName("opt")
    private String opt;

    @SerializedName("req_by")
    private String req_by;

    @SerializedName("req_by_name")
    private String req_by_name;

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

    public String getPart_ids_count() {
        return part_ids_count;
    }

    public void setPart_ids_count(String part_ids_count) {
        this.part_ids_count = part_ids_count;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getReq_by() {
        return req_by;
    }

    public void setReq_by(String req_by) {
        this.req_by = req_by;
    }

    public String getReq_by_name() {
        return req_by_name;
    }

    public void setReq_by_name(String req_by_name) {
        this.req_by_name = req_by_name;
    }
}
