package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.VisitEntryStateModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VisityEntryStateResponse {

//    "action": "getUserStates",
//            "response":

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<VisitEntryStateModel>visitEntryStateModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<VisitEntryStateModel> getVisitEntryStateModels() {
        return visitEntryStateModels;
    }

    public void setVisitEntryStateModels(ArrayList<VisitEntryStateModel> visitEntryStateModels) {
        this.visitEntryStateModels = visitEntryStateModels;
    }
}
