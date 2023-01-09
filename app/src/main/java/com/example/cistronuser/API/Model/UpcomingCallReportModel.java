package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpcomingCallReportModel {



    // "callInfo": {
    //  "callType": {
    //"callStatus": [
    //"compliantRequired": 1,
    //"complaintCategory": [

    @SerializedName("callInfo")
    private CallInfoModel callInfoModel;

    @SerializedName("callType")
    private ArrayList<CallTypeModel>callTypeModels=new ArrayList<>();

    @SerializedName("callStatus")
    private ArrayList<CallStatusModel>callStatusModels=new ArrayList<>();

    @SerializedName("compliantRequired")
    private String compliantRequired;

    @SerializedName("complaintCategory")
    private ArrayList<ComplaintCategoryModel>complaintCategoryModels=new ArrayList<>();


    public CallInfoModel getCallInfoModel() {
        return callInfoModel;
    }

    public void setCallInfoModel(CallInfoModel callInfoModel) {
        this.callInfoModel = callInfoModel;
    }

    public ArrayList<CallTypeModel> getCallTypeModels() {
        return callTypeModels;
    }

    public void setCallTypeModels(ArrayList<CallTypeModel> callTypeModels) {
        this.callTypeModels = callTypeModels;
    }

    public ArrayList<CallStatusModel> getCallStatusModels() {
        return callStatusModels;
    }

    public void setCallStatusModels(ArrayList<CallStatusModel> callStatusModels) {
        this.callStatusModels = callStatusModels;
    }

    public String getCompliantRequired() {
        return compliantRequired;
    }

    public void setCompliantRequired(String compliantRequired) {
        this.compliantRequired = compliantRequired;
    }

    public ArrayList<ComplaintCategoryModel> getComplaintCategoryModels() {
        return complaintCategoryModels;
    }

    public void setComplaintCategoryModels(ArrayList<ComplaintCategoryModel> complaintCategoryModels) {
        this.complaintCategoryModels = complaintCategoryModels;
    }
}
