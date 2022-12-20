package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class SalesQuotegetHospitalAddressResponse {


//     "action": "getHospitalAddress",
//             "rsponse": "24 Hours Isaac Bone And Joint Super Speciality Hospital\n" +
//      "geodata": "109017",
//              "hospitalId": "5830"

    @SerializedName("action")
    private String action;

    @SerializedName("rsponse")
    private String rsponse;

    @SerializedName("geodata")
    private String geodata;

    @SerializedName("hospitalId")
    private String hospitalId;


    public String getGeodata() {
        return geodata;
    }

    public void setGeodata(String geodata) {
        this.geodata = geodata;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRsponse() {
        return rsponse;
    }

    public void setRsponse(String rsponse) {
        this.rsponse = rsponse;
    }
}
