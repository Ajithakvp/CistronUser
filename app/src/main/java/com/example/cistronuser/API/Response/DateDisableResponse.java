package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.DateDisableModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DateDisableResponse {


//     "category": "getDisabledDates",
//             "dates": [

    @SerializedName("category")
    private String category;

    @SerializedName("dates")
    private ArrayList<DateDisableModel>dateDisableModels=new ArrayList<>();


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<DateDisableModel> getDateDisableModels() {
        return dateDisableModels;
    }

    public void setDateDisableModels(ArrayList<DateDisableModel> dateDisableModels) {
        this.dateDisableModels = dateDisableModels;
    }
}
