package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.UpcomingCallListModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UpcomingCallListResponse {


//     "action": "getUpcomingCallsRecord",
//             "response": [


    @SerializedName("action")
    private String action;
    @SerializedName("response")
    private ArrayList<UpcomingCallListModel> upcomingCallListModels=new ArrayList<>();


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<UpcomingCallListModel> getUpcomingCallListModels() {
        return upcomingCallListModels;
    }

    public void setUpcomingCallListModels(ArrayList<UpcomingCallListModel> upcomingCallListModels) {
        this.upcomingCallListModels = upcomingCallListModels;
    }
}
