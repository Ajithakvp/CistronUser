package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SwapListengModel {

    // "Data": [
    //    {
    //      "id": "960",
    //      "name": "TESTHAR",
    //      "part_no": "TESTHAR",
    //      "price": "400",
    //      "qty": "10",
    //      "part_id": "3037",
    //      "opt": "1"
    //    },


    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("part_no")
    private String part_no;

    @SerializedName("price")
    private String price;

    @SerializedName("qty")
    private String qty;

    @SerializedName("part_id")
    private String part_id;

    @SerializedName("opt")
    private String opt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPart_no() {
        return part_no;
    }

    public void setPart_no(String part_no) {
        this.part_no = part_no;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPart_id() {
        return part_id;
    }

    public void setPart_id(String part_id) {
        this.part_id = part_id;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
