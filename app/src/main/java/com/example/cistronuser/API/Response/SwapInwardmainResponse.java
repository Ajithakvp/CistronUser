package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SwapInwardmainModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SwapInwardmainResponse {

    //{
    //  "response": "Swap_Inward",
    //  "Data": [

    @SerializedName("response")
    private String response;

    @SerializedName("Data")
    private ArrayList<SwapInwardmainModel>swapInwardmainModels=new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<SwapInwardmainModel> getSwapInwardmainModels() {
        return swapInwardmainModels;
    }

    public void setSwapInwardmainModels(ArrayList<SwapInwardmainModel> swapInwardmainModels) {
        this.swapInwardmainModels = swapInwardmainModels;
    }
}
