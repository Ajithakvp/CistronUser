package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.TransportModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewSparesInwardDetailsResponse {

    // "action": "viewSparesInwardDetail",
    //    "chkDc": "1",
    //    "dcAttach": "http://192.168.29.173/beta1/spare_dc/3897_15.pdf",
    //    "dcNo": "dc101",
    //    "chkInv": "1",
    //    "invAttch": "http://192.168.29.173/beta1/spare_invoice/474_18.pdf",
    //    "invNo": "inv101",
    //    "chkPo": "1",
    //    "poAttch": "http://192.168.29.173/beta1/servicepobycust/17516_po.pdf",
    //    "poNo": "po101",
    //    "chkTransport": "0",
    //transport
    // "refNo": "",
    //    "allocateDt": "2023-01-15",
    //    "allocateTime": "08:10:12"

    @SerializedName("action")
    private String action;

    @SerializedName("chkDc")
    private String chkDc;

    @SerializedName("dcAttach")
    private String dcAttach;

    @SerializedName("dcNo")
    private String dcNo;

    @SerializedName("chkInv")
    private String chkInv;

    @SerializedName("invAttch")
    private String invAttch;

    @SerializedName("invNo")
    private String invNo;

    @SerializedName("poNo")
    private String poNo;

    @SerializedName("chkPo")
    private String chkPo;

    @SerializedName("poAttch")
    private String poAttch;

    @SerializedName("chkTransport")
    private String chkTransport;

    @SerializedName("transport")
    private ArrayList<TransportModel>transportModels=new ArrayList<>();

    @SerializedName("refNo")
    private String refNo;

    @SerializedName("allocateDt")
    private String allocateDt;

    @SerializedName("allocateTime")
    private String allocateTime;

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChkDc() {
        return chkDc;
    }

    public void setChkDc(String chkDc) {
        this.chkDc = chkDc;
    }

    public String getDcAttach() {
        return dcAttach;
    }

    public void setDcAttach(String dcAttach) {
        this.dcAttach = dcAttach;
    }

    public String getDcNo() {
        return dcNo;
    }

    public void setDcNo(String dcNo) {
        this.dcNo = dcNo;
    }

    public String getChkInv() {
        return chkInv;
    }

    public void setChkInv(String chkInv) {
        this.chkInv = chkInv;
    }

    public String getInvAttch() {
        return invAttch;
    }

    public void setInvAttch(String invAttch) {
        this.invAttch = invAttch;
    }

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public String getChkPo() {
        return chkPo;
    }

    public void setChkPo(String chkPo) {
        this.chkPo = chkPo;
    }

    public String getPoAttch() {
        return poAttch;
    }

    public void setPoAttch(String poAttch) {
        this.poAttch = poAttch;
    }

    public String getChkTransport() {
        return chkTransport;
    }

    public void setChkTransport(String chkTransport) {
        this.chkTransport = chkTransport;
    }

    public ArrayList<TransportModel> getTransportModels() {
        return transportModels;
    }

    public void setTransportModels(ArrayList<TransportModel> transportModels) {
        this.transportModels = transportModels;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAllocateDt() {
        return allocateDt;
    }

    public void setAllocateDt(String allocateDt) {
        this.allocateDt = allocateDt;
    }

    public String getAllocateTime() {
        return allocateTime;
    }

    public void setAllocateTime(String allocateTime) {
        this.allocateTime = allocateTime;
    }
}
