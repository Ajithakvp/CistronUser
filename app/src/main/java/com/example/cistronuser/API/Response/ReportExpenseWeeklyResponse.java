package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.UserDailyExpensesWMModel;
import com.example.cistronuser.API.Model.WeekRangeWmModel;
import com.example.cistronuser.API.Model.WeeklyUserWMModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReportExpenseWeeklyResponse {


    @SerializedName("action")
    private String action;

    @SerializedName("reprtType")
    private String reprtType;

    @SerializedName("employee")
    private String employee;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("enddate")
    private String enddate;

    @SerializedName("expenses_sum")
    private String expenses_sum;



    @SerializedName("users_daily_expenses")
    private ArrayList<UserDailyExpensesWMModel> userDailyExpensesWMModels;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getReprtType() {
        return reprtType;
    }

    public void setReprtType(String reprtType) {
        this.reprtType = reprtType;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getExpenses_sum() {
        return expenses_sum;
    }

    public void setExpenses_sum(String expenses_sum) {
        this.expenses_sum = expenses_sum;
    }



    public ArrayList<UserDailyExpensesWMModel> getUserDailyExpensesWMModels() {
        return userDailyExpensesWMModels;
    }

    public void setUserDailyExpensesWMModels(ArrayList<UserDailyExpensesWMModel> userDailyExpensesWMModels) {
        this.userDailyExpensesWMModels = userDailyExpensesWMModels;
    }
}
