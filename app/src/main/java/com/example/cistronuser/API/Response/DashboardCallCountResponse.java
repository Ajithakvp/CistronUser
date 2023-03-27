package com.example.cistronuser.API.Response;

import com.google.gson.annotations.SerializedName;

public class DashboardCallCountResponse {

//     "action": "getDashboardCounts",
//             "upcomingCalls": 8,
//             "pendingCalls": 142

    //"spareReqPending": 25,
    //  "sparesInward": 108,
    //  "returnReqPending": 10

    //mystock


    @SerializedName("action")
    private String action;

    @SerializedName("upcomingCalls")
    private String upcomingCalls;

    @SerializedName("pendingCalls")
    private String pendingCalls;


    @SerializedName("spareReqPending")
    private String spareReqPending;

    @SerializedName("sparesInward")
    private String sparesInward;

    @SerializedName("returnReqPending")
    private String returnReqPending;

    @SerializedName("todayCalls")
    private String todayCalls;

    @SerializedName("mystock")
    private String mystock;

    public String getMystock() {
        return mystock;
    }

    public void setMystock(String mystock) {
        this.mystock = mystock;
    }

    public String getTodayCalls() {
        return todayCalls;
    }

    public void setTodayCalls(String todayCalls) {
        this.todayCalls = todayCalls;
    }

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

    public String getSpareReqPending() {
        return spareReqPending;
    }

    public void setSpareReqPending(String spareReqPending) {
        this.spareReqPending = spareReqPending;
    }

    public String getSparesInward() {
        return sparesInward;
    }

    public void setSparesInward(String sparesInward) {
        this.sparesInward = sparesInward;
    }

    public String getReturnReqPending() {
        return returnReqPending;
    }

    public void setReturnReqPending(String returnReqPending) {
        this.returnReqPending = returnReqPending;
    }
}
