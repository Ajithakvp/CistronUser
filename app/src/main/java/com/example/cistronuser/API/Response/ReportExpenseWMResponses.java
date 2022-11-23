package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.UserDailyExpensesWMModel;
import com.example.cistronuser.API.Model.WeekRangeWmModel;
import com.example.cistronuser.API.Model.WeeklyUserWMModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReportExpenseWMResponses {


//     "action": "expensesReport",
//             "reprtType": "monthly",
//             "employee": "all",
//             "month": "10",
//             "year": "2022",
//             "expenses_sum": 30211,
//
//             "week_range": {
//        "users_weekly_exp": {
//    }
//
//        users_daily_expenses




    @SerializedName("action")
    private String action;

    @SerializedName("reprtType")
    private String reprtType;

    @SerializedName("employee")
    private String employee;

    @SerializedName("month")
    private String month;

    @SerializedName("year")
    private String year;

    @SerializedName("expenses_sum")
    private String expenses_sum;

    @SerializedName("week_range")
    private WeekRangeWmModel weekRangeWmModel;

    @SerializedName("users_weekly_exp")
    private WeeklyUserWMModel weeklyUserWMModel;

    @SerializedName("users_daily_expenses")
    private ArrayList<UserDailyExpensesWMModel>userDailyExpensesWMModels;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getExpenses_sum() {
        return expenses_sum;
    }

    public void setExpenses_sum(String expenses_sum) {
        this.expenses_sum = expenses_sum;
    }

    public WeekRangeWmModel getWeekRangeWmModel() {
        return weekRangeWmModel;
    }

    public void setWeekRangeWmModel(WeekRangeWmModel weekRangeWmModel) {
        this.weekRangeWmModel = weekRangeWmModel;
    }

    public WeeklyUserWMModel getWeeklyUserWMModel() {
        return weeklyUserWMModel;
    }

    public void setWeeklyUserWMModel(WeeklyUserWMModel weeklyUserWMModel) {
        this.weeklyUserWMModel = weeklyUserWMModel;
    }

    public ArrayList<UserDailyExpensesWMModel> getUserDailyExpensesWMModels() {
        return userDailyExpensesWMModels;
    }

    public void setUserDailyExpensesWMModels(ArrayList<UserDailyExpensesWMModel> userDailyExpensesWMModels) {
        this.userDailyExpensesWMModels = userDailyExpensesWMModels;
    }
}
