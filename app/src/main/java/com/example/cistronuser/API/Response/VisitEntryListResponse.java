package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisitEntryListResponse {
//     "action": "viewVisitEntries",
//             "response": [

    @SerializedName("action")
    private String action;
    @SerializedName("response")
    private ArrayList<VisitEntryListModel>visitEntryListModels;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryListModel> getVisitEntryListModels() {
        return visitEntryListModels;
    }

    public void setVisitEntryListModels(ArrayList<VisitEntryListModel> visitEntryListModels) {
        this.visitEntryListModels = visitEntryListModels;
    }
}
