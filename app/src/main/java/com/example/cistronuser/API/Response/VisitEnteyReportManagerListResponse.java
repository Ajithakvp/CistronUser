package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEnteyReportManagerListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEnteyReportManagerListResponse {

//     "action": "getManagersFilter",
//             "response": [


    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<VisitEnteyReportManagerListModel>visitEnteyReportManagerListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEnteyReportManagerListModel> getVisitEnteyReportManagerListModels() {
        return visitEnteyReportManagerListModels;
    }

    public void setVisitEnteyReportManagerListModels(ArrayList<VisitEnteyReportManagerListModel> visitEnteyReportManagerListModels) {
        this.visitEnteyReportManagerListModels = visitEnteyReportManagerListModels;
    }
}
