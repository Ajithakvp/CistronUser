package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareInwardRecordModel {

    //"reqId": "2",
    //          "reqNo": "2023012514TEMPYUGAN02",
    //          "reqDate": "2023-01-25",
    //          "reqPurpose": "23010502",
    //opt
    //          "allocateDt": "0000-00-00",
    //          "reqInfo":

    @SerializedName("reqId")
    private String  reqId;

    @SerializedName("reqNo")
    private String  reqNo;

    @SerializedName("reqDate")
    private String  reqDate;

    @SerializedName("reqPurpose")
    private String  reqPurpose;

    @SerializedName("opt")
    private String opt;

    @SerializedName("allocateDt")
    private String  allocateDt;

    @SerializedName("reqInfo")
    private ArrayList<SpareInwardReqInfoModel>spareInwardReqInfoModels=new ArrayList<>();

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getReqPurpose() {
        return reqPurpose;
    }

    public void setReqPurpose(String reqPurpose) {
        this.reqPurpose = reqPurpose;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getAllocateDt() {
        return allocateDt;
    }

    public void setAllocateDt(String allocateDt) {
        this.allocateDt = allocateDt;
    }

    public ArrayList<SpareInwardReqInfoModel> getSpareInwardReqInfoModels() {
        return spareInwardReqInfoModels;
    }

    public void setSpareInwardReqInfoModels(ArrayList<SpareInwardReqInfoModel> spareInwardReqInfoModels) {
        this.spareInwardReqInfoModels = spareInwardReqInfoModels;
    }
}
