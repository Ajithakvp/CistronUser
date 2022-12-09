package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryReportUserIListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryReportUserIListResponse {


//    "action": "getEmpWithVisitEntry",
//            "response": [



    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<VisitEntryReportUserIListModel>visitEntryReportUserIListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryReportUserIListModel> getVisitEntryReportUserIListModels() {
        return visitEntryReportUserIListModels;
    }

    public void setVisitEntryReportUserIListModels(ArrayList<VisitEntryReportUserIListModel> visitEntryReportUserIListModels) {
        this.visitEntryReportUserIListModels = visitEntryReportUserIListModels;
    }
}
