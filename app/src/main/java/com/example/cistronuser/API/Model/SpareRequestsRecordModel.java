package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareRequestsRecordModel {

    // "reqNo": "2023011017TEMPYUGAN66",
    //          "reqDt": "2023-01-10",
    //          "reqId": "2321",
    //          "reqPurpose": "23010502",
    //          "reqOpt": "1",
    //          "viewBtn": "2321,1"

    //"reqNo": "2023010910TEMPYUGAN65",
    //          "reqDt": "2023-01-09",
    //          "reqPurpose": "23010502",

    @SerializedName("reqNo")
    private String reqNo;

    @SerializedName("reqDt")
    private String reqDt;

    @SerializedName("reqPurpose")
    private String reqPurpose;


    @SerializedName("viewBtn")
    private ArrayList<SpareReqViewModel>spareReqViewModels=new ArrayList<>();

    public ArrayList<SpareReqViewModel> getSpareReqViewModels() {
        return spareReqViewModels;
    }

    public void setSpareReqViewModels(ArrayList<SpareReqViewModel> spareReqViewModels) {
        this.spareReqViewModels = spareReqViewModels;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getReqDt() {
        return reqDt;
    }

    public void setReqDt(String reqDt) {
        this.reqDt = reqDt;
    }

    public String getReqPurpose() {
        return reqPurpose;
    }

    public void setReqPurpose(String reqPurpose) {
        this.reqPurpose = reqPurpose;
    }
}
