package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResetExpensesReportInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=resetSubmittedExpenses&empid=e367&startdate=2022-10-30&enddate=2022-11-05


    @GET("expenses.php")
    Call<AdjustmentExpensesResponse> callReset(@Query("action")String action,
                                                  @Query("empid")String empid, @Query("startdate")String startdate,
                                                  @Query("enddate")String enddate);
}
