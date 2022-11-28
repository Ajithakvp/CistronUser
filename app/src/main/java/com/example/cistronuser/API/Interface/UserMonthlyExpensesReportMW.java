package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.UserWeeklyReportExpenseMWResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserMonthlyExpensesReportMW {


    //http://192.168.29.173/beta1/app/expenses.php?action=userListForExpenses&reportType=monthly&monthYear=January 2022


    @GET("expenses.php")
    Call<UserWeeklyReportExpenseMWResponses> callMonthly(@Query("action")String action, @Query("reportType")String reportType,
                                                        @Query("monthYear")String monthYear, @Query("role")String role, @Query("empid")String empid);
}
