package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PaidReportExpensesInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=expPaymentPaid&empid=e357&startdate=2022-11-06&enddate=2022-11-12&date_paid=2022-11-15

    @GET("expenses.php")
    Call<AdjustmentExpensesResponse> callPaid(@Query("action")String action,
                                                    @Query("empid")String empid, @Query("startdate")String startdate,
                                                    @Query("enddate")String enddate, @Query("date_paid")String date_paid);
}
