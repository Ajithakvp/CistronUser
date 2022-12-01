package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.WeeklyExpensesModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class response {


//     "category": "weekDays",
//             "startdate": "2022-11-27",
//             "enddate": "2022-12-03",
//             "attachBaseUrl": "https://cistronsystems.in/",
//             "extra": "Expenses for the week (2022-11-27 to 2022-12-03) is already submitted to Accounts department.\n\n",
//             "filename_r": "exp_report/272407565~filename_r.png",
//             "weeklyExpenses": [
//
//             "selectedDtExp": "",
//             "expCount": 1,
//             "error": 1,
//             "grandSum": 6543


    @SerializedName("category")
    private String category;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("enddate")
    private String enddate;

    @SerializedName("attachBaseUrl")
    private String attachBaseUrl;

    @SerializedName("extra")
    private String extra;

    @SerializedName("filename_r")
    private String filename_r;

    @SerializedName("grandSum")
    private String  grandSum;

    @SerializedName("weeklyExpenses")
    private ArrayList<WeeklyExpensesModel>weeklyExpensesModels=new ArrayList<>();

    @SerializedName("error")
    private String error;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getAttachBaseUrl() {
        return attachBaseUrl;
    }

    public void setAttachBaseUrl(String attachBaseUrl) {
        this.attachBaseUrl = attachBaseUrl;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getFilename_r() {
        return filename_r;
    }

    public void setFilename_r(String filename_r) {
        this.filename_r = filename_r;
    }

    public String getGrandSum() {
        return grandSum;
    }

    public void setGrandSum(String grandSum) {
        this.grandSum = grandSum;
    }

    public ArrayList<WeeklyExpensesModel> getWeeklyExpensesModels() {
        return weeklyExpensesModels;
    }

    public void setWeeklyExpensesModels(ArrayList<WeeklyExpensesModel> weeklyExpensesModels) {
        this.weeklyExpensesModels = weeklyExpensesModels;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
