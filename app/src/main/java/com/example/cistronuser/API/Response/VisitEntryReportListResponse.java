package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryReportListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryReportListResponse {

//     "action": "visitReportForAdmin",
//             "quote": 0,
//             "oa": 0,
//             "response": [

//    "quote": 0,
//            "oa": 0,
//            "visit": 278,



    @SerializedName("action")
    private String action;

    @SerializedName("quote")
    private String quote;

    @SerializedName("oa")
    private String oa;

    @SerializedName("visit")
    private String visit;

    @SerializedName("response")
    private ArrayList<VisitEntryReportListModel>visitEntryReportListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getOa() {
        return oa;
    }

    public void setOa(String oa) {
        this.oa = oa;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public ArrayList<VisitEntryReportListModel> getVisitEntryReportListModels() {
        return visitEntryReportListModels;
    }

    public void setVisitEntryReportListModels(ArrayList<VisitEntryReportListModel> visitEntryReportListModels) {
        this.visitEntryReportListModels = visitEntryReportListModels;
    }
}
