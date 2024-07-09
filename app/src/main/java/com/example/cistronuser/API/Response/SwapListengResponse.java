package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SwapListengModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SwapListengResponse {

    //"response": "1",
    //  "Data": [
//  "Insert_response": "1"
    @SerializedName("response")
    private String response;

    @SerializedName("Data")
    private ArrayList<SwapListengModel>swapListengModels=new ArrayList<>();

    @SerializedName("Insert_response")
    private String Insert_response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<SwapListengModel> getSwapListengModels() {
        return swapListengModels;
    }

    public void setSwapListengModels(ArrayList<SwapListengModel> swapListengModels) {
        this.swapListengModels = swapListengModels;
    }

    public String getInsert_response() {
        return Insert_response;
    }

    public void setInsert_response(String insert_response) {
        Insert_response = insert_response;
    }
}
