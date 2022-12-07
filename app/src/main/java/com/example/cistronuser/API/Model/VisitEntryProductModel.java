package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class VisitEntryProductModel {

//      "id": "516",
//              "product": "ACP 01 / Fully Automatic Control Panel"


    @SerializedName("id")
    private String id;

    @SerializedName("product")
    private String product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
