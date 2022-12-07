package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryListModel {

//     "id": "52094",
//             "comment": "",
//             "entry": "2022-09-23",
//             "doctor": "0",
//             "hospital": "Test Hospital / Chennai\n19TN139667",
//             "chiefDr": "Mr.  KANTHAMARI\nMBBS",
//             "product": "Cistron Harmony Plus with ISOFLURANE / HAR06"


    @SerializedName("id")
    private String id;

    @SerializedName("comment")
    private String comment;

    @SerializedName("entry")
    private String entry;

    @SerializedName("doctor")
    private String doctor;

    @SerializedName("hospital")
    private String hospital;

    @SerializedName("chiefDr")
    private String chiefDr;

    @SerializedName("product")
    private String product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getChiefDr() {
        return chiefDr;
    }

    public void setChiefDr(String chiefDr) {
        this.chiefDr = chiefDr;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
