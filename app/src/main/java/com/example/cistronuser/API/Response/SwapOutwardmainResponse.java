package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SwapOutwardMainModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SwapOutwardmainResponse {

    // "response": "Stock OutWard",
    //  "Data": [

    @SerializedName("response")
    private String response;

    @SerializedName("Data")
    private ArrayList<SwapOutwardMainModel>swapOutwardMainModels=new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<SwapOutwardMainModel> getSwapOutwardMainModels() {
        return swapOutwardMainModels;
    }

    public void setSwapOutwardMainModels(ArrayList<SwapOutwardMainModel> swapOutwardMainModels) {
        this.swapOutwardMainModels = swapOutwardMainModels;
    }
}
