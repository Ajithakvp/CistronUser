package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PaymentProcessedExpensesInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=expPaymentProcessed&empid=e376&startdate=2022-11-06
    // &enddate=2022-11-12&date_pay=2022-11-15


    @GET("expenses.php")
    Call<AdjustmentExpensesResponse> callPay(@Query("action")String action,
                                              @Query("empid")String empid, @Query("startdate")String startdate,
                                              @Query("enddate")String enddate, @Query("date_pay")String date_pay);
}
