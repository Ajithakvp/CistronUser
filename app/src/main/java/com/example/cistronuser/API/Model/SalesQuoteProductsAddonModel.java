package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteProductsAddonModel {




//    "addonId": "564",
//            "addonName": "Vaccum Pump Upgradeable Kit"




    @SerializedName("addonId")
    private String addonId;

    @SerializedName("addonName")
    private String addonName;

    @SerializedName("price")
    private String price;

    private boolean isSelected=false;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getAddonId() {
        return addonId;
    }

    public void setAddonId(String addonId) {
        this.addonId = addonId;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
