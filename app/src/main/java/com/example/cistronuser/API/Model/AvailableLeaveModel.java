package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class AvailableLeaveModel {

    @SerializedName("cl")
    private String cl;

    @SerializedName("ml")
    private String ml;

    @SerializedName("pl")
    private String pl;

    @SerializedName("probl")
    private String probl;


    @SerializedName("compoff")
    private String compoff;

    public String getCompoff() {
        return compoff;
    }

    public void setCompoff(String compoff) {
        this.compoff = compoff;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getMl() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml = ml;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getProbl() {
        return probl;
    }

    public void setProbl(String probl) {
        this.probl = probl;
    }
}
