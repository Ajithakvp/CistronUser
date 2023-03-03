package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class UserLocationLatLngResponse {

    // "action": "getHomeLocation",
    //  "lat": "10.77774670",
    //  "lng": "78.67631950"

    @SerializedName("action")
    private String action;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
