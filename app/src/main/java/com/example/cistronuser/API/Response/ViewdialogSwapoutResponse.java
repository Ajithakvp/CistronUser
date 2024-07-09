package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ViewdialogSwapoutModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewdialogSwapoutResponse {

    // "response": "view stock outward",
    //  "Data": [

    @SerializedName("response")
    private String response;

    @SerializedName("Data")
    private ArrayList<ViewdialogSwapoutModel>viewdialogSwapoutModels=new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<ViewdialogSwapoutModel> getViewdialogSwapoutModels() {
        return viewdialogSwapoutModels;
    }

    public void setViewdialogSwapoutModels(ArrayList<ViewdialogSwapoutModel> viewdialogSwapoutModels) {
        this.viewdialogSwapoutModels = viewdialogSwapoutModels;
    }
}
