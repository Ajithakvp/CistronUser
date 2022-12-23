package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteApprovalViewInstallmentModel {


//    {
//        "instalment": "888"
//    },


    @SerializedName("instalment")
    private String instalment;

    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }
}
