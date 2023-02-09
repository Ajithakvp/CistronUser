package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LogisticsInstallmentsModel {

    //"installment": [
    //
    //        "bp_installmentTot": 6000,
    //        "bp_installmentrTot": 0,
    //        "balinspayamt": 6000,
    //        "tm": 1


    @SerializedName("installment")
    private ArrayList<InstallamentModel>installamentModels=new ArrayList<>();

    @SerializedName("bp_installmentTot")
    private String  bp_installmentTot;

    @SerializedName("bp_installmentrTot")
    private String  bp_installmentrTot;

    @SerializedName("balinspay")
    private String  balinspayamt;

    @SerializedName("tm")
    private String  tm;

    public ArrayList<InstallamentModel> getInstallamentModels() {
        return installamentModels;
    }

    public void setInstallamentModels(ArrayList<InstallamentModel> installamentModels) {
        this.installamentModels = installamentModels;
    }

    public String getBp_installmentTot() {
        return bp_installmentTot;
    }

    public void setBp_installmentTot(String bp_installmentTot) {
        this.bp_installmentTot = bp_installmentTot;
    }

    public String getBp_installmentrTot() {
        return bp_installmentrTot;
    }

    public void setBp_installmentrTot(String bp_installmentrTot) {
        this.bp_installmentrTot = bp_installmentrTot;
    }

    public String getBalinspayamt() {
        return balinspayamt;
    }

    public void setBalinspayamt(String balinspayamt) {
        this.balinspayamt = balinspayamt;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }
}
