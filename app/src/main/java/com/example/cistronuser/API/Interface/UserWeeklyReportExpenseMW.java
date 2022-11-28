package com.example.cistronuser.API.Interface;

import com.example.cistronuser.API.Response.AttachRemoveResponse;
import com.example.cistronuser.API.Response.UserWeeklyReportExpenseMWResponses;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserWeeklyReportExpenseMW {

    //http://192.168.29.173/beta1/app/expenses.php?action=userListForExpenses&reportType=
    // weekly&startdate=2022-09-25&enddate=2022-10-01

    @GET("expenses.php")
    Call<UserWeeklyReportExpenseMWResponses> callWeekly(@Query("action")String action, @Query("reportType")String reportType,
                                                        @Query("startdate")String startdate, @Query("enddate")String enddate, @Query("role")String role, @Query("empid")String empid);


}
