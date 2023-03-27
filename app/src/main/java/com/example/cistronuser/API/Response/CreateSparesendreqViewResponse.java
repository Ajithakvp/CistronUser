package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.CreateSparesendreqViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CreateSparesendreqViewResponse {

    // "action": "viewSpareRequestQueue",
    //  "response": [

    @SerializedName("action")
    private String action;

    @SerializedName("response")
    private ArrayList<CreateSparesendreqViewModel>createSparesendreqViewModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<CreateSparesendreqViewModel> getCreateSparesendreqViewModels() {
        return createSparesendreqViewModels;
    }

    public void setCreateSparesendreqViewModels(ArrayList<CreateSparesendreqViewModel> createSparesendreqViewModels) {
        this.createSparesendreqViewModels = createSparesendreqViewModels;
    }
}
