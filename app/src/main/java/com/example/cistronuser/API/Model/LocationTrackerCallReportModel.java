package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LocationTrackerCallReportModel {

    //  "hospId": "H001",
    //      "hospName": "Ariyamangalam",
    //      "lat": "10.809372198188248",
    //      "lng": "78.72364822856878"
    //"id": "480~19383~26602~dashboard~260~todaysCall"

    @SerializedName("hospId")
    private String hospId;

    @SerializedName("hospName")
    private String hospName;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("id")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospId() {
        return hospId;
    }

    public void setHospId(String hospId) {
        this.hospId = hospId;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
