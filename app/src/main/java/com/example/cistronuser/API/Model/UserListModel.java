package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class UserListModel {


    //"empid": "e338",
    //      "name": "Santhosh",
    //      "photo":

    @SerializedName("empid")
    private String empid;

    @SerializedName("name")
    private String name;

    @SerializedName("Designation")
    private String Designation;

    @SerializedName("photo")
    private String photo;

    public String getEmpid() {
        return empid;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
