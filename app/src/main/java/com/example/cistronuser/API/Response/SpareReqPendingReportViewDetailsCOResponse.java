package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SpareReqPendingReportViewDetailsCOModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareReqPendingReportViewDetailsCOResponse {

    //"action": "viewSpareReqDetail",
    //  "reqId": "2508",
    //  "linkReqId": "2513",
    //  "opt": "1",
    //  "records": [
    //    {
    //      "partNo": "MMVSDXX",
    //      "partName": "SOLENOID SEAL KIT ",
    //      "reqQty": "1"
    //    }
    //  ],
    //  "callType": "WARRANTY",
    //  "hospital": "VIZAG SPECIALITY HOSPITAL,Arilova,Visakhapatnam",
    //  "product": "OG-250 Medical Oxygen Generating System (OG-250)"


    @SerializedName("action")
    private String action;

    @SerializedName("reqId")
    private String reqId;

    @SerializedName("linkReqId")
    private String linkReqId;

    @SerializedName("opt")
    private String opt;


    @SerializedName("records")
    private ArrayList<SpareReqPendingReportViewDetailsCOModel>spareReqPendingReportViewDetailsCOModels=new ArrayList<>();

    @SerializedName("callType")
    private String callType;

    @SerializedName("hospital")
    private String hospital;

    @SerializedName("product")
    private String product;

    @SerializedName("purpose")
    private String purpose;


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getLinkReqId() {
        return linkReqId;
    }

    public void setLinkReqId(String linkReqId) {
        this.linkReqId = linkReqId;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public ArrayList<SpareReqPendingReportViewDetailsCOModel> getSpareReqPendingReportViewDetailsCOModels() {
        return spareReqPendingReportViewDetailsCOModels;
    }

    public void setSpareReqPendingReportViewDetailsCOModels(ArrayList<SpareReqPendingReportViewDetailsCOModel> spareReqPendingReportViewDetailsCOModels) {
        this.spareReqPendingReportViewDetailsCOModels = spareReqPendingReportViewDetailsCOModels;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
