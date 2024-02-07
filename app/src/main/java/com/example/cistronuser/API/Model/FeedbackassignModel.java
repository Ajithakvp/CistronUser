package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class FeedbackassignModel {

    //"sno": 1,
    //      "product_id": "325",
    //      "hospital_address": {
    //        "hospital": "Abc Hospital",
    //        "chief_prefix": "Mrs. ",
    //        "chief_dr": "Gandhimathi",
    //        "mobile": "",
    //        "email": "",
    //        "geodata": "105272",
    //        "address": "No:1,annamalai Nagar Main Road, Near Karur Bypass Road\nTiruchirappalli\nTiruchirappalli-620001\nTamil Nadu",
    //        "lat": "10.83095020",
    //        "lng": "78.68458350",
    //        "geoloc_status": "0"
    //      },
    //      "Pserial_no": "146",
    //      "assign_emp": "test ( test ) ",
    //      "assign_date": "2024-02-01",
    //      "sid": null,
    //      "cid": "7",
    //      "hid": "10725",
    //      "pro_name": "ETO STERILIZER (Cartridge Model - Auto) - 12x12x48 - 108 Lts"

    @SerializedName("sno")
    private String sno;

    @SerializedName("product_id")
    private String product_id;


    @SerializedName("hospital_address")
    private HospitalDataModel hospitalDataModel;


    @SerializedName("Pserial_no")
    private String Pserial_no;

    @SerializedName("assign_emp")
    private String assign_emp;

    @SerializedName("assign_date")
    private String assign_date;

    @SerializedName("sid")
    private String sid;


    @SerializedName("cid")
    private String cid;

    @SerializedName("hid")
    private String hid;

    @SerializedName("pro_name")
    private String pro_name;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public HospitalDataModel getHospitalDataModel() {
        return hospitalDataModel;
    }

    public void setHospitalDataModel(HospitalDataModel hospitalDataModel) {
        this.hospitalDataModel = hospitalDataModel;
    }

    public String getPserial_no() {
        return Pserial_no;
    }

    public void setPserial_no(String pserial_no) {
        Pserial_no = pserial_no;
    }

    public String getAssign_emp() {
        return assign_emp;
    }

    public void setAssign_emp(String assign_emp) {
        this.assign_emp = assign_emp;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }
}
