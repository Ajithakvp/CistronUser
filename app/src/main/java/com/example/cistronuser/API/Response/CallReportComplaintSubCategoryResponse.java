package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.CallReportComplaintSubCategoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CallReportComplaintSubCategoryResponse {



//     "action": "getCompliantSubcategory",
//             "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<CallReportComplaintSubCategoryModel>callReportComplaintSubCategoryModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<CallReportComplaintSubCategoryModel> getCallReportComplaintSubCategoryModels() {
        return callReportComplaintSubCategoryModels;
    }

    public void setCallReportComplaintSubCategoryModels(ArrayList<CallReportComplaintSubCategoryModel> callReportComplaintSubCategoryModels) {
        this.callReportComplaintSubCategoryModels = callReportComplaintSubCategoryModels;
    }
}
