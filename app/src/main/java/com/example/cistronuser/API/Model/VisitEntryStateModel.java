package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryStateModel {

//     "state": "Andaman And Nicobar Islands"



    @SerializedName("state")
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
