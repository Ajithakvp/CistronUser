package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.LocationTrackerCallReportModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationTrackerCallReportResponse {


    //response": "getTodaysWork",
    //  "locations": [

    @SerializedName("response")
    private String response;

    @SerializedName("locations")
    private ArrayList<LocationTrackerCallReportModel>locationTrackerCallReportModels=new ArrayList<>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<LocationTrackerCallReportModel> getLocationTrackerCallReportModels() {
        return locationTrackerCallReportModels;
    }

    public void setLocationTrackerCallReportModels(ArrayList<LocationTrackerCallReportModel> locationTrackerCallReportModels) {
        this.locationTrackerCallReportModels = locationTrackerCallReportModels;
    }
}
