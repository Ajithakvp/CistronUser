package com.example.cistronuser.API.Model;

import com.google.gson.annotations.SerializedName;

public class SalesQuoteCategoryModel {

//     "seriesId": "all",
//             "seriesName": "Select Category"

    @SerializedName("seriesId")
    private String seriesId;

    @SerializedName("seriesName")
    private String seriesName;

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
