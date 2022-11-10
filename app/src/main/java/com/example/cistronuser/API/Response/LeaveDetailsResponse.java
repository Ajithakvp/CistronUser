package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.CompOffModel;
import com.example.cistronuser.API.Model.LeavedetailsModel;
import com.example.cistronuser.API.Model.LoginuserModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LeaveDetailsResponse {


    @SerializedName("category")
    private String category;
    @SerializedName("record")
    private ArrayList<LeavedetailsModel> data;

    @SerializedName("message")
    private ArrayList<CompOffModel> compOffModels;

    public ArrayList<CompOffModel> getCompOffModels() {
        return compOffModels;
    }

    public void setCompOffModels(ArrayList<CompOffModel> compOffModels) {
        this.compOffModels = compOffModels;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<LeavedetailsModel> getData() {
        return data;
    }

    public void setData(ArrayList<LeavedetailsModel> data) {
        this.data = data;
    }
}
