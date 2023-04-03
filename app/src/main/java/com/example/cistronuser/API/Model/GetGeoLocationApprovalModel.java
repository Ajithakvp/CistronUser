package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class GetGeoLocationApprovalModel {

    // "id": "1",
    //      "oldLat": "19.85090980",
    //      "oldLng": "75.34892680",
    //      "oldAdrs": "Plot No 18,gut No 140 Devlai Chowk,\nAurangabad (mh)\nAurangabad-431001\nMaharashtra",
    //      "oldAdrsStatus": "Approved",
    //      "newLat": "19.8509098",
    //      "newLng": "75.3489268",
    //      "newAdrs": "V82X+9H8, Mirajgave Nagari, Aurangabad, Maharashtra 431005, India",
    //      "distance": "1 m",
    //      "emploee": "E411 : GHODKE VINOD BHASKARRAO ",
    //      "source": "visit_entry"

    //hospital

    @SerializedName("id")
    private String id;

    @SerializedName("oldLat")
    private String oldLat;

    @SerializedName("oldLng")
    private String oldLng;

    @SerializedName("oldAdrs")
    private String oldAdrs;

    @SerializedName("oldAdrsStatus")
    private String oldAdrsStatus;

    @SerializedName("newLat")
    private String newLat;

    @SerializedName("newLng")
    private String newLng;

    @SerializedName("newAdrs")
    private String newAdrs;

    @SerializedName("distance")
    private String distance;

    @SerializedName("emploee")
    private String emploee;

    @SerializedName("source")
    private String source;

    @SerializedName("hospital")
    private String hospital;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldLat() {
        return oldLat;
    }

    public void setOldLat(String oldLat) {
        this.oldLat = oldLat;
    }

    public String getOldLng() {
        return oldLng;
    }

    public void setOldLng(String oldLng) {
        this.oldLng = oldLng;
    }

    public String getOldAdrs() {
        return oldAdrs;
    }

    public void setOldAdrs(String oldAdrs) {
        this.oldAdrs = oldAdrs;
    }

    public String getOldAdrsStatus() {
        return oldAdrsStatus;
    }

    public void setOldAdrsStatus(String oldAdrsStatus) {
        this.oldAdrsStatus = oldAdrsStatus;
    }

    public String getNewLat() {
        return newLat;
    }

    public void setNewLat(String newLat) {
        this.newLat = newLat;
    }

    public String getNewLng() {
        return newLng;
    }

    public void setNewLng(String newLng) {
        this.newLng = newLng;
    }

    public String getNewAdrs() {
        return newAdrs;
    }

    public void setNewAdrs(String newAdrs) {
        this.newAdrs = newAdrs;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEmploee() {
        return emploee;
    }

    public void setEmploee(String emploee) {
        this.emploee = emploee;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
