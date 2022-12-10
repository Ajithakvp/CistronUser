package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryDoctorModel {

//    "chiefDr": "Dr.  Natchi Muthu",
//            "id": "0"
    //"mobile": "9444326965",
    //      "mail": "kannanramana1971@gmail.com"


    @SerializedName("chiefDr")
    private String chiefDr;
    @SerializedName("id")
    private String id;

    @SerializedName("mobile")
    private String mobile;
    @SerializedName("mail")
    private String mail;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

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
