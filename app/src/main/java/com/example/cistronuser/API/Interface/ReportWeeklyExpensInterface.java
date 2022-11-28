package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.ReportExpenseWMResponses;
import com.example.cistronuser.API.Response.ReportExpenseWeeklyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportWeeklyExpensInterface {


    //http://192.168.29.173/beta1/app/expenses.php?action=expensesReport&
    // reprtType=weekly&employee=All&startdate=2022-09-25&enddate=2022-10-01


    //Weekly

    @GET("expenses.php")
    Call<ReportExpenseWeeklyResponse> callWeekly(@Query("action")String action,
                                                 @Query("reprtType")String reprtType, @Query("employee")String employee,
                                                 @Query("startdate")String startdate, @Query("enddate")String enddate);
}
