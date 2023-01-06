package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class DashboardCallCountResponse {

//     "action": "getDashboardCounts",
//             "upcomingCalls": 8,
//             "pendingCalls": 142


    @SerializedName("action")
    private String action;

    @SerializedName("upcomingCalls")
    private String upcomingCalls;

    @SerializedName("pendingCalls")
    private String pendingCalls;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUpcomingCalls() {
        return upcomingCalls;
    }

    public void setUpcomingCalls(String upcomingCalls) {
        this.upcomingCalls = upcomingCalls;
    }

    public String getPendingCalls() {
        return pendingCalls;
    }

    public void setPendingCalls(String pendingCalls) {
        this.pendingCalls = pendingCalls;
    }
}
