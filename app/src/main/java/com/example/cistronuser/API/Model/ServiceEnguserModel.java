package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class ServiceEnguserModel {

    // "uid": "102",
    //      "engname": "(e000) System Generated"
    //    },

    @SerializedName("uid")
    private String uid;


    @SerializedName("engname")
    private String engname;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }
}
