package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;
import com.example.cistronuser.API.Response.ReportExpenseWMResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportExpenseWMInterface {



    //Monthly
    //http://192.168.29.173/beta1/app/expenses.php?action=expensesReport&reprtType=monthly&employee=all&monthYear=10

    @GET("expenses.php")
    Call<ReportExpenseWMResponses> callMonthly(@Query("action")String action,
                                               @Query("reprtType")String reprtType, @Query("employee")String employee,
                                               @Query("monthYear")String monthYear);
}
