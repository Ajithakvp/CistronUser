package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class LoginuserModel {



//      "empid": "E367",
//              "name": "Mr. VELMURUGAN P",
//              "mobile": "8110982923",
//              "email": "callmeasvelan@gmail.com",
//              "dob": "1990-05-06",
//              "doj": "2022-02-01",
//              "status": "User",
//              "designation": "Software Developer",
//              "branch": "Tamilnadu",
//              "teamleader": "Gomathi (e327)"
//              "user":admin/user
//              "company":Cistron/Sukimos/all


    @SerializedName("empid")
    private String empid;
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private String dob;
    @SerializedName("doj")
    private String doj;
    @SerializedName("status")
    private String status;
    @SerializedName("designation")
    private String designation;
    @SerializedName("branch")
    private String branch;
    @SerializedName("teamleader")
    private String teamleader;

    @SerializedName("photo")
    private String photo;

    @SerializedName("is_manager")
    private String is_manager;

    @SerializedName("user")
    private String user;

    @SerializedName("company")
    private String company;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LoginuserModel(String empid) {
        this.empid = empid;


    }

    public String getEmpid() {
        return empid;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTeamleader() {
        return teamleader;
    }

    public void setTeamleader(String teamleader) {
        this.teamleader = teamleader;
    }

    public String getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(String is_manager) {
        this.is_manager = is_manager;
    }
}
