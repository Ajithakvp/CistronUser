package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.LeaveApprovelModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class leaveApprovelResponse {


//    "category": "leaveForApproval",
//            "record": [

    @SerializedName("category")
    private String category;

    @SerializedName("record")
    private ArrayList<LeaveApprovelModel>leaveApprovelModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<LeaveApprovelModel> getLeaveApprovelModels() {
        return leaveApprovelModels;
    }

    public void setLeaveApprovelModels(ArrayList<LeaveApprovelModel> leaveApprovelModels) {
        this.leaveApprovelModels = leaveApprovelModels;
    }
}
