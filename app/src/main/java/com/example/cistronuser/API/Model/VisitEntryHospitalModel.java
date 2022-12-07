package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryHospitalModel {



//      "id": "3643",
//              "hospital": "A.r. Hospital / Ramanathapuram / Fatima / 9842822472"

    @SerializedName("id")
    private String id;
    @SerializedName("hospital")
    private String hospital;

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
