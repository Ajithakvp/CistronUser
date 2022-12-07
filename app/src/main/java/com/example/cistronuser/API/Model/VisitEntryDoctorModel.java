package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryDoctorModel {

//    "chiefDr": "Dr.  Natchi Muthu",
//            "id": "0"


    @SerializedName("chiefDr")
    private String chiefDr;
    @SerializedName("id")
    private String id;

    public String getChiefDr() {
        return chiefDr;
    }

    public void setChiefDr(String chiefDr) {
        this.chiefDr = chiefDr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
