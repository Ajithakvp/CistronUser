package com.example.cistronuser.API.Response;

import com.example.cistronuser.API.Model.ReportExpensesModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReportExpensesResponse {


    @SerializedName("category")
    private String category;

    @SerializedName("submittedExpenses")
    private ArrayList<ReportExpensesModel>reportExpensesModels;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<ReportExpensesModel> getReportExpensesModels() {
        return reportExpensesModels;
    }

    public void setReportExpensesModels(ArrayList<ReportExpensesModel> reportExpensesModels) {
        this.reportExpensesModels = reportExpensesModels;
    }
}
