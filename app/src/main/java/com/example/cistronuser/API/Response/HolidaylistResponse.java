package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.HolidaylistModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HolidaylistResponse {

    //  "action": "holiday",
    //  "list": [

    @SerializedName("action")
    public String action;

    @SerializedName("list")
    public ArrayList<HolidaylistModel>holidaylistModels=new ArrayList<>();

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<HolidaylistModel> getHolidaylistModels() {
        return holidaylistModels;
    }

    public void setHolidaylistModels(ArrayList<HolidaylistModel> holidaylistModels) {
        this.holidaylistModels = holidaylistModels;
    }
}
