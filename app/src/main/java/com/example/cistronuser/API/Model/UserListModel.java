package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class UserListModel {


    //"empid": "e338",
    //      "name": "Santhosh",
    //      "photo":


    // "empid": "e339",
    //      "name": "Kannan N",
    //      "Designation": "Service Engineer",
    //      "Mobile no": "7540042073",
    //      "DOJ": "2020-10-19",
    //      "DOB": "1993-12-16",
    //      "Email": "gomathi@cistronsystems.com",
    //      "Branch": "Tamilnadu",
    //      "photo":
    //       Company

    @SerializedName("empid")
    private String empid;

    @SerializedName("name")
    private String name;

    @SerializedName("Designation")
    private String Designation;

    @SerializedName("Mobile no")
    private String Mobileno;

    @SerializedName("DOJ")
    private String DOJ;

    @SerializedName("DOB")
    private String DOB;

    @SerializedName("Email")
    private String Email;

    @SerializedName("Company")
    private String Company;

    @SerializedName("Branch")
    private String Branch;

    @SerializedName("photo")
    private String photo;

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getMobileno() {
        return Mobileno;
    }



    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

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
