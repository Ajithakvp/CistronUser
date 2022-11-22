package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AdjustmentExpensesResponse;
import com.example.cistronuser.API.Response.ReportExpensesViewResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AdjustmentExpensesInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=expSaveAdjustment&empid=e357&
    // startdate=2022-11-06&enddate=2022-11-12&adj_op=1& adj_amt=1200&adj_reason=adjustment reason


    @GET("expenses.php")
    Call<AdjustmentExpensesResponse> callAdjustment(@Query("action")String action,
                                                    @Query("empid")String empid, @Query("startdate")String startdate,
                                                    @Query("enddate")String enddate, @Query("adj_op")String adj_op,
                                                    @Query("adj_amt")String adj_amt, @Query("adj_reason")String adj_reason);

}
