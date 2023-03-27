package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CreateSparesendreqViewModel {


    // "reqId": "10",
    //      "opt": "cspl",
    //      "part_no": "MFABFNBU",
    //      "name": "1/4\" FEMALE TO 10MM ADAPTER ",
    //      "qty": "3.00",
    //      "price": "144",
    //coord_qty
    //      "purpose": [

    @SerializedName("reqId")
    private String reqId;

    @SerializedName("opt")
    private String opt;

    @SerializedName("part_no")
    private String part_no;

    @SerializedName("name")
    private String name;

    @SerializedName("qty")
    private String qty;

    @SerializedName("coord_qty")
    private String coord_qty;

    @SerializedName("price")
    private String price;

    @SerializedName("purpose")
    private ArrayList<CreateSparesendreqProposeViewModel>createSparesendreqProposeViewModels=new ArrayList<>();


    public String getCoord_qty() {
        return coord_qty;
    }

    public void setCoord_qty(String coord_qty) {
        this.coord_qty = coord_qty;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<CreateSparesendreqProposeViewModel> getCreateSparesendreqProposeViewModels() {
        return createSparesendreqProposeViewModels;
    }

    public void setCreateSparesendreqProposeViewModels(ArrayList<CreateSparesendreqProposeViewModel> createSparesendreqProposeViewModels) {
        this.createSparesendreqProposeViewModels = createSparesendreqProposeViewModels;
    }
}
