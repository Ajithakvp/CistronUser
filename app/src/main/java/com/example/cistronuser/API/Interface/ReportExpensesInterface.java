package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttendanceResponse;
import com.example.cistronuser.API.Response.ReportExpensesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportExpensesInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=submittedExpensesReport
    @GET("expenses.php")
    Call<ReportExpensesResponse> callSubmittedOn(@Query("action")String action);
}
