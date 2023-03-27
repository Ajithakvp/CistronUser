package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryHospitalModel {



//      "id": "3643",
//              "hospital": "A.r. Hospital / Ramanathapuram / Fatima / 9842822472"
    //"lat": "11.23804050",
    //      "lng": "78.86530120"

    @SerializedName("id")
    private String id;
    @SerializedName("hospital")
    private String hospital;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
