package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class HospitalDataModel {

    //"hospital": "Abc Hospital",
    //        "chief_prefix": "Mrs. ",
    //        "chief_dr": "Gandhimathi",
    //        "mobile": "",
    //        "email": "",
    //        "geodata": "105272",
    //        "address": "No:1,annamalai Nagar Main Road, Near Karur Bypass Road\nTiruchirappalli\nTiruchirappalli-620001\nTamil Nadu",
    //        "lat": "10.83095020",
    //        "lng": "78.68458350",
    //        "geoloc_status": "0"

    @SerializedName("hospital")
    private String hospital;


    @SerializedName("chief_prefix")
    private String chief_prefix;

    @SerializedName("chief_dr")
    private String chief_dr;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;


    @SerializedName("geodata")
    private String geodata;

    @SerializedName("address")
    private String address;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName("geoloc_status")
    private String geoloc_status;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getChief_prefix() {
        return chief_prefix;
    }

    public void setChief_prefix(String chief_prefix) {
        this.chief_prefix = chief_prefix;
    }

    public String getChief_dr() {
        return chief_dr;
    }

    public void setChief_dr(String chief_dr) {
        this.chief_dr = chief_dr;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeodata() {
        return geodata;
    }

    public void setGeodata(String geodata) {
        this.geodata = geodata;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGeoloc_status() {
        return geoloc_status;
    }

    public void setGeoloc_status(String geoloc_status) {
        this.geoloc_status = geoloc_status;
    }
}
