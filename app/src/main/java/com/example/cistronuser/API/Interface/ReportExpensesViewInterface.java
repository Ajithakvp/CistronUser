package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttachRemoveResponse;
import com.example.cistronuser.API.Response.ReportExpensesViewResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportExpensesViewInterface {

    //http://192.168.29.173/beta1/app/expenses.php?action=viewsubmittedExpenses&empid=e357&startdate=2022-11-06&enddate=2022-11-12

    @GET("expenses.php")
    Call<ReportExpensesViewResponses>callreportviewexpenses(@Query("action")String action,
                                                       @Query("empid")String empid, @Query("startdate")String startdate,
                                                       @Query("enddate")String enddate);

}
