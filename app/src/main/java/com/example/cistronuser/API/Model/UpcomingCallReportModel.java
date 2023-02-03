package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpcomingCallReportModel {


//"label":
    // "callInfo": {
    //  "callType": {
    //"callStatus": [
    //"compliantRequired": 1,
    //"complaintCategory": [
    //sparesConsumed
    //"customerPo": {
    //hiddenValues

    //id
    //"compliantRequired": 1,
    //    "seriesid1": "1",
    //    "seriesid2": "",


    @SerializedName("compliantRequired")
    private String compliantRequired;

    @SerializedName("seriesid1")
    private String seriesid1;

    @SerializedName("seriesid2")
    private String seriesid2;

    @SerializedName("callInfo")
    private CallInfoModel callInfoModel;

    @SerializedName("callType")
    private ArrayList<CallTypeModel>callTypeModels=new ArrayList<>();

    @SerializedName("callStatus")
    private ArrayList<CallStatusModel>callStatusModels=new ArrayList<>();

    @SerializedName("complaintCategory")
    private ArrayList<ComplaintCategoryModel>complaintCategoryModels=new ArrayList<>();

    @SerializedName("sparesConsumed")
    private SparesConsumedModel sparesConsumedModel;

    @SerializedName("spareRequests")
    private SpareRequestsModel spareRequestsModel;

    @SerializedName("customerPo")
    private CustomerPoModel customerPoModel;

    @SerializedName("label")
    private LabelModel labelModel;

    @SerializedName("hiddenValues")
    private HiddenValuesModel hiddenValuesModel;

    @SerializedName("id")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompliantRequired() {
        return compliantRequired;
    }

    public void setCompliantRequired(String compliantRequired) {
        this.compliantRequired = compliantRequired;
    }

    public String getSeriesid1() {
        return seriesid1;
    }

    public void setSeriesid1(String seriesid1) {
        this.seriesid1 = seriesid1;
    }

    public String getSeriesid2() {
        return seriesid2;
    }

    public void setSeriesid2(String seriesid2) {
        this.seriesid2 = seriesid2;
    }

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

    public ArrayList<ComplaintCategoryModel> getComplaintCategoryModels() {
        return complaintCategoryModels;
    }

    public void setComplaintCategoryModels(ArrayList<ComplaintCategoryModel> complaintCategoryModels) {
        this.complaintCategoryModels = complaintCategoryModels;
    }

    public SparesConsumedModel getSparesConsumedModel() {
        return sparesConsumedModel;
    }

    public void setSparesConsumedModel(SparesConsumedModel sparesConsumedModel) {
        this.sparesConsumedModel = sparesConsumedModel;
    }

    public SpareRequestsModel getSpareRequestsModel() {
        return spareRequestsModel;
    }

    public void setSpareRequestsModel(SpareRequestsModel spareRequestsModel) {
        this.spareRequestsModel = spareRequestsModel;
    }

    public CustomerPoModel getCustomerPoModel() {
        return customerPoModel;
    }

    public void setCustomerPoModel(CustomerPoModel customerPoModel) {
        this.customerPoModel = customerPoModel;
    }

    public LabelModel getLabelModel() {
        return labelModel;
    }

    public void setLabelModel(LabelModel labelModel) {
        this.labelModel = labelModel;
    }

    public HiddenValuesModel getHiddenValuesModel() {
        return hiddenValuesModel;
    }

    public void setHiddenValuesModel(HiddenValuesModel hiddenValuesModel) {
        this.hiddenValuesModel = hiddenValuesModel;
    }
}
