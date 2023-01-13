package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SpareSendReqListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SpareSendReqListResponse {

    //"action": "spareRequestTmp",
    //    "response": [

    @SerializedName("action")
    private String  action;

    @SerializedName("action")
    private ArrayList<SpareSendReqListModel>spareSendReqListModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<SpareSendReqListModel> getSpareSendReqListModels() {
        return spareSendReqListModels;
    }

    public void setSpareSendReqListModels(ArrayList<SpareSendReqListModel> spareSendReqListModels) {
        this.spareSendReqListModels = spareSendReqListModels;
    }
}
