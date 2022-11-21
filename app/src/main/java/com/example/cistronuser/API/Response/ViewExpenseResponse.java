package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.SelecteddtExpenses;
import com.example.cistronuser.API.Model.WeeklyExpensesModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewExpenseResponse {

    @SerializedName("category")
    private String category;

    @SerializedName("error")
    private String error;

    @SerializedName("startdate")
    private String startdate;

    @SerializedName("enddate")
    private String enddate;

    @SerializedName("attachBaseUrl")
    private String attachBaseUrl;

    @SerializedName("extra")
    private String extra;

    @SerializedName("consolidatedAttach")
    private String consolidatedAttach;

    @SerializedName("weeklyExpenses")
   private ArrayList<WeeklyExpensesModel>weeklyExpensesModels;

    @SerializedName("selectedDtExp")
    private SelecteddtExpenses selecteddtExpenses;

    @SerializedName("grandSum")
    private String  grandSum;

    public String getGrandSum() {
        return grandSum;
    }

    public void setGrandSum(String grandSum) {
        this.grandSum = grandSum;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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

    public String getConsolidatedAttach() {
        return consolidatedAttach;
    }

    public void setConsolidatedAttach(String consolidatedAttach) {
        this.consolidatedAttach = consolidatedAttach;
    }

    public ArrayList<WeeklyExpensesModel> getWeeklyExpensesModels() {
        return weeklyExpensesModels;
    }

    public void setWeeklyExpensesModels(ArrayList<WeeklyExpensesModel> weeklyExpensesModels) {
        this.weeklyExpensesModels = weeklyExpensesModels;
    }

    public SelecteddtExpenses getSelecteddtExpenses() {
        return selecteddtExpenses;
    }

    public void setSelecteddtExpenses(SelecteddtExpenses selecteddtExpenses) {
        this.selecteddtExpenses = selecteddtExpenses;
    }
}
