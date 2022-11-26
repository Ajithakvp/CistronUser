package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.CompOffRequestModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CompOffRequestResponse {

//      "category": "compoffForApproval",
//              "response": [


    @SerializedName("category")
    private String category;

    @SerializedName("response")
    private ArrayList<CompOffRequestModel>compOffRequestModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<CompOffRequestModel> getCompOffRequestModels() {
        return compOffRequestModels;
    }

    public void setCompOffRequestModels(ArrayList<CompOffRequestModel> compOffRequestModels) {
        this.compOffRequestModels = compOffRequestModels;
    }
}
