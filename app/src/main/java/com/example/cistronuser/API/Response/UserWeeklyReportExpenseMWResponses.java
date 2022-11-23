package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Interface.UserWeeklyReportExpenseMW;
import com.example.cistronuser.API.Model.UserWeeklyReportExpensesMWModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserWeeklyReportExpenseMWResponses {

    @SerializedName("employees")
    private ArrayList<UserWeeklyReportExpensesMWModel>weeklyReportExpenseMWS;

    public ArrayList<UserWeeklyReportExpensesMWModel> getWeeklyReportExpenseMWS() {
        return weeklyReportExpenseMWS;
    }

    public void setWeeklyReportExpenseMWS(ArrayList<UserWeeklyReportExpensesMWModel> weeklyReportExpenseMWS) {
        this.weeklyReportExpenseMWS = weeklyReportExpenseMWS;
    }
}
